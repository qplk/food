package com.registration.reg.web;

import com.registration.reg.model.Order;
import com.registration.reg.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Stasia on 29.03.17.
 */
@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/admin/orders/orders", method = RequestMethod.GET)
    public ModelAndView findAllOrders() {
        List<Order> ordersList = orderService.findAll();
        ModelAndView model = new ModelAndView("/admin/orders/orders");
        model.addObject("ordersList", ordersList);

        return model;
    }
}
