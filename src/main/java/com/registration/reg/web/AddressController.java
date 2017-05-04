package com.registration.reg.web;

import com.registration.reg.model.*;
import com.registration.reg.repository.AddressRepository;
import com.registration.reg.requestBody.AddressRequestBody;
import com.registration.reg.requestBody.OrderRequestBody;
import com.registration.reg.service.AddressService;
import com.registration.reg.service.CityService;
import com.registration.reg.service.OrderService;
import com.registration.reg.service.UserService;
import com.registration.reg.validator.AddressValidator;
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
    AddressValidator addressValidator;
    @Autowired
    OrderValidator orderValidator;


    User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal().toString().equals("anonymousUser")) {
            return null;
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authenticatedUsername = userDetails.getUsername();
        return userService.findByUsername(authenticatedUsername);
    }

    @RequestMapping(value = "/profile/addAddress", method = RequestMethod.GET)
    public ModelAndView addAddress() {
        ModelAndView model = new ModelAndView("/profile/addAddress");
        model.addObject("citiesList", cityService.findAll());
        model.addObject("addressForm", new AddressRequestBody());

        return model;
    }



    @RequestMapping(value = "/profile/addAddress", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("addressForm") AddressRequestBody addressForm, BindingResult bindingResult, ModelMap model) {
        User currentUser = getCurrentUser();

        if (currentUser != null) {
            addressForm.setUserId(currentUser.getUserId());
        }

        addressValidator.validate(addressForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("citiesList", cityService.findAll());
            return "/profile/addAddress";
        }

        addressService.save(addressForm);

        return "redirect:/profile/profile";
    }

    @RequestMapping(value = "/profile/updateAddress/{addressId}", method = RequestMethod.GET)
    public ModelAndView updateAddress(@PathVariable Long addressId) {
        ModelAndView model = new ModelAndView("/profile/updateAddress");
        model.addObject("citiesList", cityService.findAll());
        model.addObject("addressForm", new AddressRequestBody());
        model.addObject("address", addressService.get(addressId));

        return model;
    }


    @RequestMapping(value = "/profile/updateAddress/{addressId}", method = RequestMethod.PUT)
    public String updateAddress(@PathVariable Long addressId, @ModelAttribute("addressForm") AddressRequestBody addressForm, BindingResult bindingResult, ModelMap model) {
        User currentUser = getCurrentUser();

        if (currentUser != null) {
            addressForm.setUserId(currentUser.getUserId());
        }

        addressValidator.validate(addressForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("citiesList", cityService.findAll());
            model.addAttribute("address", addressService.get(addressId));

            return "/profile/updateAddress";
        }

        addressService.update(addressForm);

        return "redirect:/profile/profile";
    }


    @RequestMapping(value = "/profile/deleteAddress/{addressId}", method = RequestMethod.DELETE)
    public String deleteAddress(@PathVariable Long addressId) {
        addressService.delete(addressId);

        return "redirect:/profile/profile";
    }


    @RequestMapping(value = "/addOrderAddress", method = RequestMethod.GET)
    public String selectAddress(Model model) {
        User currentUser = getCurrentUser();

        if (currentUser == null) {
            return "redirect:/welcome/login";
        }
        model.addAttribute("addressList", currentUser.getAddresses());
        model.addAttribute("addressForm", new AddressRequestBody());
        model.addAttribute("citiesList", cityService.findAll());

        return "/addOrderAddress";
    }

    @RequestMapping(value = "/addOrderAddress", method = RequestMethod.POST)
    public String completeForming(@ModelAttribute("addressForm") AddressRequestBody addressForm, BindingResult bindingResult, ModelMap model) {
        User currentUser = getCurrentUser();

        if (currentUser == null) {
            return "redirect:/welcome";
        }

        addressForm.setUserId(currentUser.getUserId());
        OrderRequestBody orderRequestBody = new OrderRequestBody();
        Order order = orderService.findByUserAndStatus(currentUser.getUserId(), "Forming").get(0);
        orderRequestBody.setOrderId(order.getOrderId());
        orderRequestBody.setAddressId(addressForm.getAddressId());

        if (!addressForm.getStreet().isEmpty()) {
            System.out.println("["+addressForm.getStreet() + "]");
            addressValidator.validate(addressForm, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("citiesList", cityService.findAll());
                model.addAttribute("addressList", currentUser.getAddresses());

                Address address = addressService.findAddress(addressForm);
                if (address != null) {
                    model.addAttribute("addressId", address.getAddressId());
                    model.addAttribute("addressForm", new AddressRequestBody());
                }

                return "/addOrderAddress";
            }

            orderRequestBody.setAddressId(addressService.save(addressForm));
        }

        orderService.update(orderRequestBody);

        return "redirect:/completeForming";
    }

}