package com.registration.reg.web;

import com.registration.reg.model.Order;
import com.registration.reg.model.OrderElement;
import com.registration.reg.model.Restaurant;
import com.registration.reg.model.User;
import com.registration.reg.requestBody.OrderRequestBody;
import com.registration.reg.service.OrderService;
import com.registration.reg.service.RestaurantService;
import com.registration.reg.service.UserService;
import com.registration.reg.validator.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Stasia on 29.03.17.
 */
@Controller
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    OrderValidator orderValidator;
    @Autowired
    RestaurantService restaurantService;


    User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal().toString().equals("anonymousUser")) {
            return null;
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authenticatedUsername = userDetails.getUsername();
        return userService.findByUsername(authenticatedUsername);
    }

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

    @RequestMapping(value = "/completeForming", method = RequestMethod.GET)
    public ModelAndView formingOrder() {
        User currentUser = getCurrentUser();

        if (currentUser == null) {
            return new ModelAndView("/welcome");
        }

        ModelAndView model = new ModelAndView("/completeForming");

        Order order = orderService.findByUserAndStatus(currentUser.getUserId(), "Forming").get(0);
        model.addObject("order", order);
        model.addObject("orderForm", new OrderRequestBody(order.getOrderId(), order.getOrderElements(), order.getFullPrice()));

        return model;
    }


    @RequestMapping(value = "/completeForming", method = RequestMethod.POST)
    public String formingOrder(@ModelAttribute("orderForm") OrderRequestBody orderForm, BindingResult bindingResult, ModelMap model) {
        User currentUser = getCurrentUser();

        if (currentUser == null) {
            return "redirect:/welcome";
        }


        Order order = orderService.findByUserAndStatus(currentUser.getUserId(), "Forming").get(0);

        if (order.getAddressByOrderId() == null) {
            model.addAttribute("addressError", "You should select delivery address");
            return "/completeForming";
        }
        Restaurant restaurant = restaurantService.selectRestaurant(order.getAddressByOrderId());

        orderForm.setOrderId(order.getOrderId());
        orderForm.setOrderElements(order.getOrderElements());
        orderForm.setAddressId(order.getAddressByOrderId().getAddressId());
        orderForm.setRestaurantId(restaurant.getRestaurantId());
        orderForm.setFullPrice(order.getFullPrice());
        orderForm.setStatus(order.getStatus());
        orderForm.setStatusInfo(order.getStatusInfo());
        orderValidator.validate(orderForm, bindingResult);

        if (bindingResult.hasErrors()) {
            System.out.println("ERRORS");
            for (ObjectError e: bindingResult.getAllErrors()) {
                System.out.println(e.toString());
            }
            model.addAttribute("order", orderService.findByUserAndStatus(currentUser.getUserId(), "Forming").get(0));
           // model.addAttribute("orderForm", new OrderRequestBody(order.getOrderId(), order.getOrderElements(), order.getFullPrice()));

            for (OrderElement e: orderForm.getOrderElements()) {
                System.out.println(e.toString());
            }


            return "/completeForming";
        }

        orderForm.setStatus("Formed");
        orderForm.setStatusInfo("Autoformed");
        orderService.update(orderForm);

        return "redirect:/profile/profile";
    }


}
