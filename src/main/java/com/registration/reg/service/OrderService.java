package com.registration.reg.service;

import com.registration.reg.model.Address;
import com.registration.reg.model.Order;
import com.registration.reg.model.Restaurant;
import com.registration.reg.model.User;

/**
 * Created by Stasia on 09.03.17.
 */
public interface OrderService {
    void save(Order order, User user, Restaurant restaurant, Address address);
}