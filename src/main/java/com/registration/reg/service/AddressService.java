package com.registration.reg.service;

import com.registration.reg.model.Address;
import java.util.List;
/**
 * Created by Stasia on 09.03.17.
 */
public interface AddressService {
    void save(Address address, Long userId, Long cityId);

    Address get(Long addressId);

    List<Address> findAll();

    void delete(Long addressId);
}
