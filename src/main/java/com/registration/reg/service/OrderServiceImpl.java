package com.registration.reg.service;

import com.registration.reg.model.*;
import com.registration.reg.repository.OrderRepository;
import com.registration.reg.repository.UserRepository;
import com.registration.reg.repository.AddressRepository;
import com.registration.reg.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

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

    @Transactional
    @Override
    public void save(Order order, Long userId, Long restaurantId, Long addressId) {
        User user = userRepository.getOne(userId);
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        Address address = addressRepository.getOne(addressId);

        order.setUserByOrderId(user);
        order.setRestaurantByOrderId(restaurant);
        order.setAddressByOrderId(address);

        Set<Order> ordersFromUser = new HashSet<>(user.getOrders());
        ordersFromUser.add(order);
        user.setOrders(ordersFromUser);

        Set<Order> ordersFromAddress = new HashSet<>(address.getOrders());
        ordersFromAddress.add(order);
        address.setOrders(ordersFromAddress);

        Set<Order> ordersInRestaurant = new HashSet<>(restaurant.getOrders());
        ordersInRestaurant.add(order);
        restaurant.setOrders(ordersInRestaurant);

        orderRepository.save(order);
        userRepository.save(user);
        restaurantRepository.save(restaurant);
        addressRepository.save(address);
    }

    @Override
    public Order get(Long orderId) {
        return orderRepository.getOne(orderId);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }


    @Override
    public void delete(Long orderId) {
        orderRepository.delete(orderId);
    }
}
