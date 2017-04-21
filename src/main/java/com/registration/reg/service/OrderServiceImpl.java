package com.registration.reg.service;

import com.registration.reg.model.*;
import com.registration.reg.repository.*;
import com.registration.reg.requestBody.AssortmentRequestBody;
import com.registration.reg.requestBody.OrderRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
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
    @Autowired
    private AssortmentRepository assortmentRepository;

    @Transactional
    @Override
    public void save(Order order, Long userId, Long restaurantId, Long addressId) {
        User user = userRepository.getOne(userId);
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        Address address = addressRepository.getOne(addressId);

        order.setUser(user);
        order.setRestaurantByOrderId(restaurant);
        order.setAddressByOrderId(address);
        orderRepository.save(order);

        user.getOrders().add(order);
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
    public List<Order> findByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public Order findCurrentOrder(Long userId) {
        return orderRepository.findByUserAndStatus(userRepository.findOne(userId), "Forming");
    }


    @Override
    public void delete(Long orderId) {
        orderRepository.delete(orderId);
    }

    @Override
    public void update(Long orderId, OrderRequestBody orderRequestBody) {
        Order order = orderRepository.getOne(orderId);

        if (order.getStatus().equals("Forming") && (orderRequestBody.getStatus().equals("Formed"))) {
            User user = userRepository.getOne(order.getUser().getUserId());

            Order newOrder = new Order("New order", user);

            orderRepository.save(newOrder);
            user.getOrders().add(newOrder);
            userRepository.save(user);

            for (OrderElement orderElement : order.getOrderElements()) {
                Assortment assortment = assortmentRepository.findByRestaurantAndFood(order.getRestaurantByOrderId(), orderElement.getFood());
                if (assortment.getQuantity() != null) {

                    assortment.setQuantity(assortment.getQuantity() - orderElement.getQuantity());
                    if (assortment.getQuantity() == 0) {
                        assortment.setEnable(false);
                    }

                    assortmentRepository.save(assortment);
                }
            }

            order.setDeliveryTime(order.getRestaurantByOrderId().getCityByRestaurantId().getDeliveryTime());
        }


        order.setStatus(orderRequestBody.getStatus());
        order.setStatusInfo(orderRequestBody.getStatusInfo());

        orderRepository.save(order);

    }

}
