package com.registration.reg.service;

import com.registration.reg.model.OrderElement;
import com.registration.reg.model.Food;
import com.registration.reg.model.Order;
import com.registration.reg.model.Restaurant;
import com.registration.reg.repository.OrderElementRepository;
import com.registration.reg.repository.FoodRepository;
import com.registration.reg.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Created by Stasia on 09.03.17.
 */
@Service
public class OrderElementServiceImpl implements OrderElementService {
    @Autowired
    private OrderElementRepository orderElementRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void save(OrderElement orderElement, Food food, Order order) {
        orderElement.setFood(foodRepository.getOne(food.getFoodId()));
        orderElement.setOrder(orderRepository.getOne(order.getOrderId()));

        HashSet<OrderElement> orderElementsInOrder = new HashSet<>(orderRepository.getOne(order.getOrderId()).getOrderElements());
        orderElementsInOrder.add(orderElement);
        orderRepository.getOne(order.getOrderId()).setOrderElements(orderElementsInOrder);

        HashSet<OrderElement> orderElementsWithFood = new HashSet<>(foodRepository.getOne(food.getFoodId()).getOrderElements());
        orderElementsWithFood.add(orderElement);
        foodRepository.getOne(food.getFoodId()).setOrderElements(orderElementsWithFood);

        orderElement.setOrder(orderRepository.getOne(order.getOrderId()));
        orderElement.setFood(foodRepository.getOne(food.getFoodId()));
        orderElementRepository.save(orderElement);
    }
}
