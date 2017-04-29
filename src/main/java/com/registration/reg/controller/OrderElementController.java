package com.registration.reg.controller;

import com.registration.reg.model.OrderElement;
import com.registration.reg.requestBody.OrderElementRequestBody;
import com.registration.reg.service.OrderElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Stasia on 16.03.17.
 */
@Controller("OrderElementController")
public class OrderElementController {
    @Autowired
    OrderElementService orderElementService;

    /*@RequestMapping(value = "/orderElements", method = RequestMethod.GET)
    public ResponseEntity findAllOrderElements() {
        Iterable<OrderElement> orderElementList = orderElementService.findAll();
        ResponseEntity responseEntity = new ResponseEntity<>(orderElementList, HttpStatus.OK);
        return responseEntity;
    }*/

    @RequestMapping(value = "/orderElements", method = RequestMethod.GET)
    public ResponseEntity findAllOrderElementsForAuthenticatedUser(@RequestParam("userId") Long userId){
        Iterable<OrderElement> orderElementList = orderElementService.findOrderElementsForUserId(userId);
        ResponseEntity responseEntity = new ResponseEntity<>(orderElementList, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/orderElements", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity updateOrderElement(@RequestBody OrderElementRequestBody orderElementRequestBody, @RequestParam("orderElementId") Long orderElementId){
        OrderElement oldOrderElement = orderElementService.get(orderElementId);

        oldOrderElement.setQuantity(orderElementRequestBody.getQuantity());

        orderElementService.save(oldOrderElement, oldOrderElement.getFood().getFoodId(), oldOrderElement.getOrder().getOrderId());

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/orderElements", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addOrderElement(@RequestBody OrderElementRequestBody orderElementRequestBody) {
        OrderElement orderElement = new OrderElement(orderElementRequestBody.getQuantity());

        orderElementService.save(orderElement, orderElementRequestBody.getFoodId(), orderElementRequestBody.getOrderId());
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/orderElements/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrderElement(@PathVariable("id") Long id) {
        orderElementService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
