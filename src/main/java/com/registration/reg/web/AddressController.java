package com.registration.reg.web;

import com.registration.reg.model.Address;
import com.registration.reg.repository.AddressRepository;
import com.registration.reg.requestBody.AddressRequestBody;
import com.registration.reg.service.AddressService;
import com.registration.reg.service.CityService;
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
 * Created by Stasia on 12.04.17.
 */
@Controller
public class AddressController {
    @Autowired
    AddressService addressService;
    @Autowired
    CityService cityService;


    @RequestMapping(value = "/admin/addresses/addressAdd/{userId}", method = RequestMethod.GET)
    public ModelAndView assortmentAddForm(@PathVariable Long userId) {
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



}
