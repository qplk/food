package com.registration.reg.service;

import com.registration.reg.model.Address;
import com.registration.reg.model.City;
import com.registration.reg.model.User;
import com.registration.reg.repository.AddressRepository;
import com.registration.reg.repository.CityRepository;
import com.registration.reg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Created by Stasia on 09.03.17.
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CityRepository cityRepository;

    @Override
    public void save(Address address, User user, City city) {
        if ((addressRepository.findByStreetAndBuildingNumberAndRoomNumber(address.getStreet(), address.getBuildingNumber(), address.getRoomNumber()) != null) && (addressRepository.findByStreetAndBuildingNumberAndRoomNumber(address.getStreet(), address.getBuildingNumber(), address.getRoomNumber()).getCityByAddressId().getCityId() == city.getCityId())) {
            Long addressId = addressRepository.findByStreetAndBuildingNumberAndRoomNumber(address.getStreet(), address.getBuildingNumber(), address.getRoomNumber()).getId();

            HashSet<User> usersInAddress = new HashSet<>(addressRepository.findOne(addressId).getUsers());
            usersInAddress.add(user);

            addressRepository.findOne(addressId).setUsers(usersInAddress);

            HashSet<Address> addressesOfUser = new HashSet<>(userRepository.findOne(user.getId()).getAddresses());
            addressesOfUser.add(addressRepository.findOne(addressId));
            userRepository.findOne(user.getId()).setAddresses(addressesOfUser);
        } else {
            HashSet<Address> addressesOfUser = new HashSet<>(userRepository.findOne(user.getId()).getAddresses());
            addressesOfUser.add(address);
            userRepository.findOne(user.getId()).setAddresses(addressesOfUser);

            HashSet<Address> addressesInCity = new HashSet<>(cityRepository.findOne(city.getCityId()).getAddresses());
            addressesInCity.add(address);
            cityRepository.findOne(city.getCityId()).setAddresses(addressesInCity);

            HashSet<User> users = new HashSet<>();
            users.add(userRepository.getOne(user.getId()));
            address.setUsers(users);

            address.setCityByAddressId(cityRepository.getOne(city.getCityId()));

            addressRepository.save(address);
        }

    }
}
