package com.registration.reg.service;

import com.registration.reg.model.OrderElement;
import com.registration.reg.model.Food;
import com.registration.reg.model.Order;
import com.registration.reg.model.Restaurant;
import com.registration.reg.repository.OrderElementRepository;
import com.registration.reg.repository.FoodRepository;
import com.registration.reg.repository.OrderRepository;
import com.registration.reg.requestBody.OrderElementRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Set;
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
        System.out.println("id is " + orderId);

        orderElement.setFood(food);
        orderElement.setOrder(order);
        orderElement.setElementPrice(food.getPrice() * orderElement.getQuantity());
        orderElementRepository.save(orderElement);


        order.getOrderElements().add(orderElement);
        order.setFullPrice(order.getFullPrice() + orderElement.getElementPrice());
        orderRepository.save(order);


        food.getOrderElements().add(orderElement);
        foodRepository.save(food);
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
        OrderElement orderElement = orderElementRepository.getOne(orderElementId);
        Order order = orderRepository.getOne(orderElement.getOrder().getOrderId());
        order.setFullPrice(order.getFullPrice() - orderElement.getElementPrice());
        orderRepository.save(order);

        orderElementRepository.delete(orderElementId);
    }

    @Override
    public void update(Long orderElementId, OrderElementRequestBody orderElementRequestBody) {
        if (orderElementRequestBody.getQuantity() == 0) {
            delete(orderElementId);
            return;
        }

        OrderElement orderElement = orderElementRepository.getOne(orderElementId);
        Order order = orderRepository.getOne(orderElement.getOrder().getOrderId());
        Food food = foodRepository.getOne(orderElementRequestBody.getFoodId());

        order.setFullPrice(order.getFullPrice() - orderElement.getElementPrice());
        orderElement.setQuantity(orderElementRequestBody.getQuantity());
        orderElement.setElementPrice(food.getPrice() * orderElement.getQuantity());
        orderElementRepository.save(orderElement);
        order.setFullPrice(order.getFullPrice() + orderElement.getElementPrice());
        orderRepository.save(order);
    }


    @Override
    public List<OrderElement> findOrderElementsForUserId(Long userId) {
        Order userIdOrder = null;
        List<OrderElement> userIdOrderElements = new ArrayList<OrderElement>();
        List<Order> allOrders = orderRepository.findAll();
        for(Order item: allOrders){
            if((item.getUserByOrderId().getUserId() == userId)&&("Forming".equals(item.getStatus()))){
                userIdOrder = item;
            }
        }
        List<OrderElement> allOrderElements = orderElementRepository.findAll();
        for(OrderElement item: allOrderElements){
            if(item.getOrder().getOrderId() == userIdOrder.getOrderId()){
                item.getFood().setAssortment(null);
                item.getFood().setOrderElements(null);
                item.setOrder(null);
                userIdOrderElements.add(item);
            }
        }
        return userIdOrderElements;
    }
}
