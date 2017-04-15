package com.registration.reg.service;

import com.registration.reg.model.Order;
import com.registration.reg.requestBody.OrderRequestBody;


import java.util.List;

/**
 * Created by Stasia on 09.03.17.
 */
public interface OrderService {
    void save(Order order, Long userId, Long restaurantId, Long addressId);

    Order get(Long orderId);

    List<Order> findAll();

    void delete(Long orderId);

    void update(Long orderId, OrderRequestBody orderRequestBody);
}