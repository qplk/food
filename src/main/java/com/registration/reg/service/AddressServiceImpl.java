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
    public Long save(AddressRequestBody addressRequestBody) {
        Address currAddress = addressRepository.findByStreetAndBuildingNumberAndRoomNumber(addressRequestBody.getStreet(), addressRequestBody.getBuildingNumber(), addressRequestBody.getRoomNumber());
        User user = userRepository.findOne(addressRequestBody.getUserId());

        if ((currAddress != null) && (currAddress.getCityByAddressId().getCityId().equals(addressRequestBody.getCityId()))) {
            currAddress.getUsers().add(user);
            addressRepository.save(currAddress);

            user.getAddresses().add(currAddress);
            userRepository.save(user);

        } else {
            currAddress = new Address(addressRequestBody.getStreet(), addressRequestBody.getBuildingNumber(), addressRequestBody.getRoomNumber(), addressRequestBody.getComment());
            City city = cityRepository.findOne(addressRequestBody.getCityId());

            currAddress.getUsers().add(user);
            currAddress.setCityByAddressId(city);
            addressRepository.save(currAddress);
            userRepository.save(user);

            city.getAddresses().add(currAddress);
            cityRepository.save(city);
        }

        return currAddress.getAddressId();
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

    @Override
    public void update(AddressRequestBody addressRequestBody) {
        Address address = addressRepository.getOne(addressRequestBody.getAddressId());

        address.setStreet(addressRequestBody.getStreet());
        address.setBuildingNumber(addressRequestBody.getBuildingNumber());
        address.setRoomNumber(addressRequestBody.getRoomNumber());
        address.setComment(addressRequestBody.getComment());

        if (!address.getCityByAddressId().getCityId().equals(addressRequestBody.getCityId())) {
            City city = cityRepository.getOne(addressRequestBody.getCityId());
            City oldCity = address.getCityByAddressId();

            oldCity.getAddresses().remove(address);
            address.setCityByAddressId(city);
            city.getAddresses().add(address);

            cityRepository.save(city);
            cityRepository.save(oldCity);
        }

        addressRepository.save(address);
    }

    @Override
    public Address findAddress(AddressRequestBody addressRequestBody) {
        City city = cityRepository.getOne(addressRequestBody.getCityId());

        return addressRepository.findByCityByAddressIdAndStreetAndBuildingNumberAndRoomNumber(city, addressRequestBody.getStreet(), addressRequestBody.getBuildingNumber(), addressRequestBody.getRoomNumber());
    }

}
