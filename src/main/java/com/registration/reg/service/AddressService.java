package com.registration.reg.service;

import com.registration.reg.model.Address;
import com.registration.reg.model.User;
import com.registration.reg.model.City;
/**
 * Created by Stasia on 09.03.17.
 */
public interface AddressService {
    void save(Address address, User user, City city);
}
