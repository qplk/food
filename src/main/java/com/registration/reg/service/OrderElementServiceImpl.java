package com.registration.reg.service;

import com.registration.reg.model.*;
import com.registration.reg.repository.*;
import com.registration.reg.requestBody.OrderElementRequestBody;
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
public class OrderElementServiceImpl implements OrderElementService {
    @Autowired
    private OrderElementRepository orderElementRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssortmentRepository assortmentRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private CityRepository cityRepository;

    @Transactional
    @Override
    public void save(Long userId, Long foodId, OrderElementRequestBody orderElementRequestBody) {
        Food food = foodRepository.getOne(foodId);
        Order order = orderRepository.findByUserAndStatus(userRepository.getOne(userId), "Forming");
        OrderElement orderElement = new OrderElement(orderElementRequestBody.getQuantity(), food, order);
        orderElementRepository.save(orderElement);


        order.getOrderElements().add(orderElement);
        order.setFullPrice(order.getFullPrice() + orderElement.getFood().getPrice()*orderElement.getQuantity());
        order.setRestaurantByOrderId(restaurantRepository.findByCityByRestaurantId(cityRepository.findOne(orderElementRequestBody.getCityId())));
        orderRepository.save(order);

        food.getOrderElements().add(orderElement);
        foodRepository.save(food);

      /*  Assortment assortment = assortmentRepository.findByRestaurantAndFood(order.getRestaurantByOrderId(), food);
        if (assortment.getQuantity() != null) {
            assortment.setQuantity(assortment.getQuantity() - orderElement.getQuantity());
            if (assortment.getQuantity() == 0) {
                assortment.setEnable(false);
            }
        }

        assortmentRepository.save(assortment);*/
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
        order.setFullPrice(order.getFullPrice() - orderElement.getQuantity()*orderElement.getFood().getPrice());

       /* Assortment assortment = assortmentRepository.findByRestaurantAndFood(order.getRestaurantByOrderId(), orderElement.getFood());
        if (assortment.getQuantity() != null) {
            assortment.setQuantity(assortment.getQuantity() + orderElement.getQuantity());
            if (assortment.getQuantity() > 0) {
                assortment.setEnable(true);
            }
        }

        assortmentRepository.save(assortment);*/

        orderElementRepository.delete(orderElementId);
    }
}
