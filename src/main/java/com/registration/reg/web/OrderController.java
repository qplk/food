package com.registration.reg.web;

import com.registration.reg.model.Order;
import com.registration.reg.requestBody.OrderRequestBody;
import com.registration.reg.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addObject("formed", orderService.findByStatus("Formed"));
        model.addObject("delivering", orderService.findByStatus("Delivering"));

        return model;
    }


    @RequestMapping(value = "/admin/orders/orderUpdate/{id}", method = RequestMethod.GET)
    public String updateFood(@PathVariable Long id, Model model) {
        model.addAttribute("orderForm", new OrderRequestBody());
        model.addAttribute("order", orderService.get(id));

        return "/admin/orders/orderUpdate";
    }


    @RequestMapping(value = "/admin/orders/orderUpdate/{orderId}", method = RequestMethod.PUT)
    public String updateOrder(@PathVariable Long orderId, @ModelAttribute("orderForm") OrderRequestBody orderForm, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("order", orderService.get(orderId));

            return "/admin/orders/orderUpdate";
        }

        orderService.update(orderForm);

        return "redirect:/admin/orders/orders";
    }
}