package com.registration.reg.web;

import com.registration.reg.model.Assortment;
import com.registration.reg.model.Restaurant;
import com.registration.reg.requestBody.AssortmentRequestBody;
import com.registration.reg.requestBody.RestaurantRequestBody;
import com.registration.reg.service.AssortmentService;
import com.registration.reg.service.FoodService;
import com.registration.reg.service.RestaurantService;
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
public class AssortmentController {
    @Autowired
    AssortmentService assortmentService;
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    FoodService foodService;


    @RequestMapping(value = "/admin/assortment/assortment", method = RequestMethod.GET)
    public ModelAndView findAllAssortment() {
        List<Restaurant> restaurantList = restaurantService.findAll();
        ModelAndView model = new ModelAndView("/admin/assortment/assortment");
        model.addObject("restaurantList", restaurantList);


        return model;
    }


    @RequestMapping(value = "/admin/assortment/assortmentAdd/{restaurantId}", method = RequestMethod.GET)
    public ModelAndView assortmentAddForm(@PathVariable Long restaurantId) {
        ModelAndView model = new ModelAndView("/admin/assortment/assortmentAdd");
        model.addObject("restaurant", restaurantService.get(restaurantId));
        model.addObject("foodList", foodService.findAll());

        model.addObject("assortmentForm", new AssortmentRequestBody());

        return model;
    }



    @RequestMapping(value = "/admin/assortment/assortmentAdd/{restaurantId}", method = RequestMethod.POST)
    public String assortmentAdd(@PathVariable Long restaurantId, @ModelAttribute("assortmentForm") AssortmentRequestBody assortmentForm, BindingResult bindingResult, ModelMap model) {

        if (bindingResult.hasErrors()) {
            return "/admin/assortment/assortmentAdd/" + assortmentForm.getRestaurantId();
        }

        assortmentService.save(assortmentForm);

        return "redirect:/admin/assortment/assortment";
    }
}
