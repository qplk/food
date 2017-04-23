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



    @RequestMapping(value = "/profile/addAddress", method = RequestMethod.GET)
    public ModelAndView addAddress() {
        ModelAndView model = new ModelAndView("/profile/addAddress");
        model.addObject("citiesList", cityService.findAll());
        model.addObject("addressForm", new AddressRequestBody());

        return model;
    }



    @RequestMapping(value = "/profile/addAddress", method = RequestMethod.POST)
    public String addAddress(@ModelAttribute("addressForm") AddressRequestBody addressForm, BindingResult bindingResult, ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authenticatedUsername = userDetails.getUsername();
        User user = userService.findByUsername(authenticatedUsername);
        addressForm.setUserId(user.getUserId());

        if (bindingResult.hasErrors()) {
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
    public String updateAddress(@ModelAttribute("addressForm") AddressRequestBody addressForm, BindingResult bindingResult, ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String authenticatedUsername = userDetails.getUsername();
        User user = userService.findByUsername(authenticatedUsername);
        addressForm.setUserId(user.getUserId());

        if (bindingResult.hasErrors()) {
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



}