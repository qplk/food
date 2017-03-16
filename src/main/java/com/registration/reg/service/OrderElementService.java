package com.registration.reg.service;

import com.registration.reg.model.OrderElement;
import com.registration.reg.model.Order;
import com.registration.reg.model.Food;

import java.util.List;

/**
 * Created by Stasia on 09.03.17.
 */
public interface OrderElementService {
    void save(OrderElement orderElement, Long foodId, Long orderId);

    OrderElement get(Long orderElementId);

    List<OrderElement> findAll();

    void delete(Long orderElementId);
}
