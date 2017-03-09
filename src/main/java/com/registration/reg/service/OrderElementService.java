package com.registration.reg.service;

import com.registration.reg.model.OrderElement;
import com.registration.reg.model.Order;
import com.registration.reg.model.Food;
/**
 * Created by Stasia on 09.03.17.
 */
public interface OrderElementService {
    void save(OrderElement orderElement, Food food, Order order);
}
