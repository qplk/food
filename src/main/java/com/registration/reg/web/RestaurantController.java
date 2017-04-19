package com.registration.reg.web;

import com.registration.reg.model.Restaurant;
import com.registration.reg.requestBody.RestaurantRequestBody;
import com.registration.reg.service.RestaurantService;
import com.registration.reg.service.CityService;
import com.registration.reg.validator.FoodValidator;
import com.registration.reg.validator.RestaurantValidator;
import com.registration.reg.validator.UserValidator;
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
import com.registration.reg.requestBody.RestaurantRequestBody;

import java.util.List;

/**
 * Created by Stasia on 29.03.17.
 */
@Controller
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @Autowired
    CityService cityService;

    @Autowired
    private RestaurantValidator restaurantValidator;


    @RequestMapping(value = "/admin/restaurants/restaurantAdd", method = RequestMethod.GET)
    public String restaurantAdd(Model model) {
        model.addAttribute("restaurantForm", new RestaurantRequestBody());

        model.addAttribute("citiesList", cityService.findAll());
        return "/admin/restaurants/restaurantAdd";
    }


    @RequestMapping(value = "/admin/restaurants/restaurantAdd", method = RequestMethod.POST)
    public String restaurantAdd(@ModelAttribute("restaurantForm") RestaurantRequestBody restaurantForm, BindingResult bindingResult, ModelMap model) {
        restaurantValidator.validate(restaurantForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("citiesList", cityService.findAll());
            return "/admin/restaurants/restaurantAdd";
        }

        restaurantService.save(restaurantForm);

        return "redirect:/admin/restaurants/restaurants";
    }

    @RequestMapping(value = "/admin/restaurants/restaurants", method = RequestMethod.GET)
    public ModelAndView findAllRestaurants() {
        List<Restaurant> restaurantsList = restaurantService.findAll();
        ModelAndView model = new ModelAndView("/admin/restaurants/restaurants");
        model.addObject("restaurantsList", restaurantsList);

        return model;
    }


    @RequestMapping(value = "/admin/restaurants/restaurantUpdate/{id}", method = RequestMethod.GET)
    public String updateRestaurant(@PathVariable Long id, Model model) {
        model.addAttribute("restaurantForm", new RestaurantRequestBody());
        model.addAttribute("restaurant", restaurantService.get(id));
        model.addAttribute("citiesList", cityService.findAll());

        return "/admin/restaurants/restaurantUpdate";
    }


    @RequestMapping(value = "/admin/restaurants/restaurantUpdate/{id}", method = RequestMethod.PUT)
    public String updateRestaurant(@PathVariable Long id, @ModelAttribute("restaurantForm") RestaurantRequestBody restaurantForm, BindingResult bindingResult, ModelMap model) {
        restaurantValidator.validate(restaurantForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("restaurant", restaurantService.get(id));
            model.addAttribute("citiesList", cityService.findAll());

            return "/admin/restaurants/restaurantUpdate";
        }

        restaurantService.update(id, restaurantForm);

        return "redirect:/admin/restaurants/restaurants";
    }

    @RequestMapping(value = "/admin/restaurants/restaurants/{id}", method = RequestMethod.DELETE)
    public String deleteRestaurant(@PathVariable Long id) {
        System.out.println("Deleted");

        restaurantService.delete(id);



        return "redirect:/admin/restaurants/restaurants";
    }

}
