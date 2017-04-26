package com.registration.reg.service;

import com.registration.reg.model.Address;
import com.registration.reg.requestBody.AddressRequestBody;

import java.util.List;
/**
 * Created by Stasia on 09.03.17.
 */
public interface AddressService {
    void save(AddressRequestBody addressRequestBody);

    Address get(Long addressId);

    List<Address> findAll();

    void delete(Long addressId);

    void update(AddressRequestBody addressRequestBody);
}
