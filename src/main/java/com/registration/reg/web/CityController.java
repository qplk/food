package com.registration.reg.web;

import com.registration.reg.model.City;
import com.registration.reg.service.CityService;
import com.registration.reg.validator.FoodValidator;
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
public class CityController {
    @Autowired
    CityService cityService;


    @RequestMapping(value = "/admin/cities/cityAdd", method = RequestMethod.GET)
    public String cityAdd(Model model) {
        model.addAttribute("cityForm", new City());

        return "/admin/cities/cityAdd";
    }


    @RequestMapping(value = "/admin/cities/cityAdd", method = RequestMethod.POST)
    public String cityAdd(@ModelAttribute("cityForm") City cityForm, BindingResult bindingResult, ModelMap model) {

        if (bindingResult.hasErrors()) {
            return "/admin/cities/cityAdd";
        }

        cityService.save(cityForm);

        return "redirect:/admin/cities/cities";
    }

    @RequestMapping(value = "/admin/cities/cities", method = RequestMethod.GET)
    public ModelAndView findAllCities() {
        List<City> citiesList = cityService.findAll();
        ModelAndView model = new ModelAndView("/admin/cities/cities");
        model.addObject("citiesList", citiesList);

        return model;
    }

}
