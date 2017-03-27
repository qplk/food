package com.registration.reg.controller;

import com.registration.reg.model.Order;
import com.registration.reg.requestBody.OrderRequestBody;
import com.registration.reg.requestBody.RestaurantRequestBody;
import com.registration.reg.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Stasia on 16.03.17.
 */
@Controller("OrderController")
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity findAllOrders() {
        Iterable<Order> orderList = orderService.findAll();
        ResponseEntity responseEntity = new ResponseEntity<>(orderList, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addOrder(@RequestBody OrderRequestBody orderRequestBody) {
        Order order = new Order(orderRequestBody.getDeliveryTime(), orderRequestBody.getFullPrice(), orderRequestBody.getStatus(), orderRequestBody.getStatusInfo(), orderRequestBody.getPaymentInfo());

        orderService.save(order, orderRequestBody.getUserId(), orderRequestBody.getRestaurantId(), orderRequestBody.getAddressId());
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrder(@PathVariable("id") Long id) {
        orderService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
