package com.registration.reg.service;

import com.registration.reg.model.*;
import com.registration.reg.repository.OrderRepository;
import com.registration.reg.repository.UserRepository;
import com.registration.reg.repository.AddressRepository;
import com.registration.reg.repository.RestaurantRepository;
import com.registration.reg.requestBody.OrderRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
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
        orderRepository.save(order);

        //user.setOrder(order);
        userRepository.save(user);

        address.getOrders().add(order);
        addressRepository.save(address);

        restaurant.getOrders().add(order);
        restaurantRepository.save(restaurant);
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
    public void delete(Long orderId){
        orderRepository.delete(orderId);

    }

    @Override
    public void update(Long orderId, OrderRequestBody orderRequestBody) {
        Order order = orderRepository.getOne(orderId);
    System.out.println(order.getStatus());
        System.out.println(orderRequestBody.getStatus());
        System.out.println(order.getStatus().equals("Forming"));
        System.out.println(orderRequestBody.getStatus().equals("Formed"));

        if (order.getStatus().equals("Forming") && (orderRequestBody.getStatus().equals("Formed"))) {
            User user = userRepository.getOne(order.getUserByOrderId().getUserId());

            Order newOrder = new Order(Time.valueOf("00:00:00"), 0, "Forming", "Initial order", "Cash", user);

            orderRepository.save(newOrder);
            user.getOrders().add(newOrder);
            userRepository.save(user);
        }

        order.setStatus(orderRequestBody.getStatus());
        order.setStatusInfo(orderRequestBody.getStatusInfo());

        orderRepository.save(order);

    }
}
