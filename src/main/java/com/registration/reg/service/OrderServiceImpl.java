package com.registration.reg.service;

import com.registration.reg.model.*;
import com.registration.reg.repository.OrderRepository;
import com.registration.reg.repository.UserRepository;
import com.registration.reg.repository.AddressRepository;
import com.registration.reg.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Created by Stasia on 09.03.17.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void save(Order order, User user, Restaurant restaurant, Address address) {
        order.setUserByOrderId(userRepository.getOne(user.getId()));
        order.setRestaurantByOrderId(restaurantRepository.getOne(restaurant.getRestaurantId()));
        order.setAddressByOrderId(addressRepository.getOne(address.getId()));

        HashSet<Order> ordersFromUser = new HashSet<>(userRepository.getOne(user.getId()).getOrders());
        ordersFromUser.add(order);
        userRepository.getOne(user.getId()).setOrders(ordersFromUser);

        HashSet<Order> ordersFromAddress = new HashSet<>(addressRepository.getOne(address.getId()).getOrders());
        ordersFromAddress.add(order);
        addressRepository.getOne(address.getId()).setOrders(ordersFromAddress);

        HashSet<Order> ordersInRestaurant = new HashSet<>(restaurantRepository.getOne(restaurant.getRestaurantId()).getOrders());
        ordersInRestaurant.add(order);
        restaurantRepository.getOne(restaurant.getRestaurantId()).setOrders(ordersInRestaurant);

        orderRepository.save(order);
    }
}
