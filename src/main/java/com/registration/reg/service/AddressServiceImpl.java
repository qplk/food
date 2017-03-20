package com.registration.reg.service;

import com.registration.reg.model.Address;
import com.registration.reg.model.City;
import com.registration.reg.model.User;
import com.registration.reg.repository.AddressRepository;
import com.registration.reg.repository.CityRepository;
import com.registration.reg.repository.UserRepository;
import com.registration.reg.requestBody.AddressRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

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

    @Transactional
    @Override
    public void save(AddressRequestBody addressRequestBody) {
        Address currAddress = addressRepository.findByStreetAndBuildingNumberAndRoomNumber(addressRequestBody.getStreet(), addressRequestBody.getBuildingNumber(), addressRequestBody.getRoomNumber());
        User user = userRepository.findOne(addressRequestBody.getUserId());

        if ((currAddress != null) && (currAddress.getCityByAddressId().getCityId() == addressRequestBody.getCityId())) {
            Set<User> usersInAddress = new HashSet<>(currAddress.getUsers());
            usersInAddress.add(user);

            currAddress.setUsers(usersInAddress);

            Set<Address> addressesOfUser = new HashSet<>(user.getAddresses());
            addressesOfUser.add(currAddress);
            user.setAddresses(addressesOfUser);

            userRepository.save(user);
            addressRepository.save(currAddress);
        } else {
            Address address = new Address(addressRequestBody.getStreet(), addressRequestBody.getBuildingNumber(), addressRequestBody.getRoomNumber(), addressRequestBody.getComment());
            City city = cityRepository.findOne(addressRequestBody.getCityId());

            Set<Address> addressesOfUser = new HashSet<>(user.getAddresses());
            addressesOfUser.add(address);
            user.setAddresses(addressesOfUser);

            Set<Address> addressesInCity = new HashSet<>(city.getAddresses());
            addressesInCity.add(address);
            city.setAddresses(addressesInCity);

            Set<User> users = new HashSet<>();
            users.add(user);
            address.setUsers(users);

            address.setCityByAddressId(city);

            addressRepository.save(address);
            userRepository.save(user);
            cityRepository.save(city);
        }

    }

    @Override
    public Address get(Long addressId) {
        return addressRepository.getOne(addressId);
    }

    @Override
    public void delete(Long addressId) {
        addressRepository.delete(addressId);
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }
}
