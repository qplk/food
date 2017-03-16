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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

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

    @Transactional
    @Override
    public void save(OrderElement orderElement, Long foodId, Long orderId) {
        Food food = foodRepository.getOne(foodId);
        Order order = orderRepository.getOne(orderId);

        orderElement.setFood(food);
        orderElement.setOrder(order);

        HashSet<OrderElement> orderElementsInOrder = new HashSet<>(order.getOrderElements());
        orderElementsInOrder.add(orderElement);
        order.setOrderElements(orderElementsInOrder);

        HashSet<OrderElement> orderElementsWithFood = new HashSet<>(food.getOrderElements());
        orderElementsWithFood.add(orderElement);
        food.setOrderElements(orderElementsWithFood);

        orderElementRepository.save(orderElement);
        foodRepository.save(food);
        orderRepository.save(order);
    }

    @Override
    public OrderElement get(Long orderElementId) {
        return orderElementRepository.getOne(orderElementId);
    }


    @Override
    public List<OrderElement> findAll() {
        return orderElementRepository.findAll();
    }


    @Override
    public void delete(Long orderElementId) {
        orderElementRepository.delete(orderElementId);
    }
}
