package com.registration.reg.controller;

import com.registration.reg.model.OrderElement;
import com.registration.reg.requestBody.OrderElementRequestBody;
import com.registration.reg.service.OrderElementService;
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
@Controller("OrderElementController")
public class OrderElementController {
    @Autowired
    OrderElementService orderElementService;

    @RequestMapping(value = "/orderElements", method = RequestMethod.GET)
    public ResponseEntity findAllOrderElements() {
        Iterable<OrderElement> orderElementList = orderElementService.findAll();
        ResponseEntity responseEntity = new ResponseEntity<>(orderElementList, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/orderElements", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addOrderElement(@RequestBody OrderElementRequestBody orderElementRequestBody) {
        OrderElement orderElement = new OrderElement(orderElementRequestBody.getQuantity());

        orderElementService.save(orderElementRequestBody.getUserId(), orderElementRequestBody.getFoodId(), orderElementRequestBody);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/orderElements/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrderElement(@PathVariable("id") Long id) {
        orderElementService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
