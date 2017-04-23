package com.registration.reg.web;

import com.registration.reg.model.Address;
import com.registration.reg.model.Order;
import com.registration.reg.model.User;
import com.registration.reg.repository.AddressRepository;
import com.registration.reg.requestBody.AddressRequestBody;
import com.registration.reg.requestBody.OrderRequestBody;
import com.registration.reg.service.AddressService;
import com.registration.reg.service.CityService;
import com.registration.reg.service.OrderService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Stasia on 12.04.17.
 */
@Controller
public class AddressController {
    @Autowired
    AddressService addressService;
    @Autowired
    CityService cityService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    @Autowired
    private OrderValidator orderValidator;


    @RequestMapping(value = "/admin/addresses/addressAdd/{userId}", method = RequestMethod.GET)
    public ModelAndView addressAddForm(@PathVariable Long userId) {
        ModelAndView model = new ModelAndView("/admin/addresses/addressAdd");
        model.addObject("citiesList", cityService.findAll());

        model.addObject("addressForm", new AddressRequestBody());

        return model;
    }



    @RequestMapping(value = "/admin/addresses/addressAdd/{userId}", method = RequestMethod.POST)
    public String addressAdd(@PathVariable Long userId, @ModelAttribute("addressForm") AddressRequestBody addressForm, BindingResult bindingResult, ModelMap model) {

        if (bindingResult.hasErrors()) {
            return "/admin/addresses/addressAdd";
        }

        addressService.save(addressForm);

        return "redirect:/admin/users/users";
    }

    @RequestMapping(value = "/admin/addresses/orderAddress", method = RequestMethod.GET)
    public ModelAndView orderAddress() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authenticatedUsername = userDetails.getUsername();
        User user = userService.findByUsername(authenticatedUsername);


        ModelAndView model = new ModelAndView("/admin/addresses/orderAddress");
        model.addObject("addressList", user.getAddresses());

        model.addObject("addressForm", new AddressRequestBody());

        return model;
    }



    @RequestMapping(value = "/admin/addresses/orderAddress", method = RequestMethod.POST)
    public String orderAddress(@ModelAttribute("addressForm") AddressRequestBody addressForm, BindingResult bindingResult, ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authenticatedUsername = userDetails.getUsername();
        User user = userService.findByUsername(authenticatedUsername);


        OrderRequestBody order = new OrderRequestBody();

        order.setStatus("Formed");
        order.setStatusInfo("Autoformed");
        order.setAddressId(addressForm.getAddressId());

        orderValidator.validateRequestBody(orderService.findCurrentOrder(user.getUserId()).getOrderId(), order, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/admin/addresses/orderAddress";
        }

        orderService.update(orderService.findCurrentOrder(user.getUserId()).getOrderId(), order);

        return "redirect:/admin/users/users";
    }



}