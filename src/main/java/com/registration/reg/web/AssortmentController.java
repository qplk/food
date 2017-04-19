package com.registration.reg.web;

import com.registration.reg.model.Assortment;
import com.registration.reg.model.Restaurant;
import com.registration.reg.requestBody.AssortmentRequestBody;
import com.registration.reg.requestBody.RestaurantRequestBody;
import com.registration.reg.service.AssortmentService;
import com.registration.reg.service.FoodService;
import com.registration.reg.service.RestaurantService;
import com.registration.reg.validator.AssortmentValidator;
import com.registration.reg.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AssortmentValidator assortmentValidator;

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
        assortmentValidator.validate(assortmentForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("restaurant", restaurantService.get(restaurantId));
            model.addAttribute("foodList", foodService.findAll());

            return "/admin/assortment/assortmentAdd";
        }

        assortmentService.save(assortmentForm);

        return "redirect:/admin/assortment/assortment";
    }


    @RequestMapping(value = "/admin/assortment/assortmentUpdate/{restaurantId}/{foodId}", method = RequestMethod.GET)
    public String updateAssortment(@PathVariable Long restaurantId, @PathVariable Long foodId, Model model) {
        Assortment assortment = assortmentService.findByRestaurantIdAndFoodId(restaurantId, foodId);
        model.addAttribute("assortmentForm", new AssortmentRequestBody());
        model.addAttribute("assortment", assortment);
        model.addAttribute("restaurant", restaurantService.get(restaurantId));
        model.addAttribute("food", foodService.get(foodId));
        model.addAttribute("foodList", foodService.findAll());

        return "/admin/assortment/assortmentUpdate";
    }

    @RequestMapping(value = "/admin/assortment/assortmentUpdate/{restaurantId}/{foodId}", method = RequestMethod.PUT)
    public String updateAssortment(@PathVariable Long restaurantId, @PathVariable Long foodId, @ModelAttribute("assortmentForm") AssortmentRequestBody assortmentForm, BindingResult bindingResult, ModelMap model) {
        assortmentValidator.validateFields(assortmentForm, bindingResult);

        if (bindingResult.hasErrors()) {
            System.out.println("errors");
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error: errors) {
                System.out.println(error.toString());
            }

            model.addAttribute("assortment", assortmentForm);
            model.addAttribute("restaurant", restaurantService.get(restaurantId));
            model.addAttribute("food", foodService.get(foodId));
            model.addAttribute("foodList", foodService.findAll());

            return "/admin/assortment/assortmentUpdate";
        }

        System.out.println("updating");

        assortmentService.update(restaurantId, foodId, assortmentForm);

        System.out.println("updated");

        return "redirect:/admin/assortment/assortment";
    }

    @RequestMapping(value = "/admin/assortment/assortment/{assortmentId}", method = RequestMethod.DELETE)
    public String deleteFood(@PathVariable Long assortmentId) {
        assortmentService.delete(assortmentId);

        return "redirect:/admin/assortment/assortment";
    }
}
