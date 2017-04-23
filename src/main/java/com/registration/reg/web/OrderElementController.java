package com.registration.reg.web;

import com.registration.reg.model.Food;
import com.registration.reg.model.Order;
import com.registration.reg.model.OrderElement;
import com.registration.reg.model.User;
import com.registration.reg.requestBody.OrderElementRequestBody;
import com.registration.reg.requestBody.OrderRequestBody;
import com.registration.reg.service.FoodService;
import com.registration.reg.service.OrderElementService;
import com.registration.reg.service.OrderService;
import com.registration.reg.service.UserService;
import com.registration.reg.validator.OrderElementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Stasia on 19.04.17.
 */
@Controller
public class OrderElementController {
    @Autowired
    OrderService orderService;
    @Autowired
    FoodService foodService;
    @Autowired
    UserService userService;
    @Autowired
    OrderElementService orderElementService;
    @Autowired
    private OrderElementValidator orderElementValidator;

    @RequestMapping(value = "/admin/orders/order", method = RequestMethod.GET)
    public ModelAndView getOrderElements() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authenticatedUsername = userDetails.getUsername();
        User user = userService.findByUsername(authenticatedUsername);
        Order order = orderService.findCurrentOrder(user.getUserId());
        ModelAndView model = new ModelAndView("/admin/orders/order");
        model.addObject("order", order);
        model.addObject("orderForm", new OrderRequestBody());

        return model;
    }

    @RequestMapping(value = "/admin/orders/order/{orderId}/{orderElementId}", method = RequestMethod.DELETE)
    public String deleteOrderElement(@PathVariable Long orderId, @PathVariable Long orderElementId) {
        System.out.println("Delete");
        orderElementService.delete(orderElementId);

        return "redirect:/admin/orders/order/" + orderId;
    }


    @RequestMapping(value = "/admin/food/food/{cityId}/{foodId}", method = RequestMethod.POST)
    public String addOrderElement(@PathVariable Long cityId, @PathVariable Long foodId, @ModelAttribute("orderElementForm") OrderElementRequestBody orderElementForm, BindingResult bindingResult, ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authenticatedUsername = userDetails.getUsername();
        User user = userService.findByUsername(authenticatedUsername);


        orderElementValidator.validate(orderElementForm, bindingResult);

        if (bindingResult.hasErrors()) {
            List<Food> foodList = foodService.findAll();
            model.addAttribute("foodList", foodList);

            return "/admin/food/food";
        }

        orderElementService.save(user.getUserId(), foodId, orderElementForm);

        return "redirect:/admin/orders/order";
    }


    @RequestMapping(value = "/admin/food/food/{orderElementId}", method = RequestMethod.PUT)
    public String putOrderElement(@PathVariable Long orderElementId, @ModelAttribute("orderElementForm") OrderElementRequestBody orderElementForm, BindingResult bindingResult, ModelMap model) {

        orderElementValidator.validate(orderElementForm, bindingResult);

        if (bindingResult.hasErrors()) {
            List<Food> foodList = foodService.findAll();
            model.addAttribute("foodList", foodList);

            return "/admin/food/food";
        }

        orderElementService.update(orderElementId, orderElementForm);

        return "redirect:/admin/orders/order";
    }
}
