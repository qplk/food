package com.registration.reg.web;

import com.registration.reg.model.Food;
import com.registration.reg.model.User;
import com.registration.reg.service.FoodService;
import com.registration.reg.validator.FoodValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * Created by Stasia on 16.03.17.
 */
@Controller
public class FoodController {
    @Autowired
    FoodService foodService;

    @Autowired
    private FoodValidator foodValidator;


    @RequestMapping(value = "/admin/food/foodAdd", method = RequestMethod.GET)
    public String foodAdd(Model model) {
        model.addAttribute("foodForm", new Food());

        return "/admin/food/foodAdd";
    }
   /* public ModelAndView showForm() {
        return new ModelAndView("foodForm", "foodForm", new Food());
    }*/

    @RequestMapping(value = "/admin/food/foodAdd", method = RequestMethod.POST)
    public String foodAdd(@ModelAttribute("foodForm") Food foodForm, BindingResult bindingResult, ModelMap model) {
        foodValidator.validate(foodForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/admin/food/foodAdd";
        }

       // model.addAttribute("foodName", foodForm.getFoodName());
       // model.addAttribute("description", foodForm.getDescription());
       // model.addAttribute("category", foodForm.getCategory());
        foodService.save(foodForm);

        return "redirect:/admin/food/food";
    }

    @RequestMapping(value = "/admin/food/food", method = RequestMethod.GET)
    public ModelAndView findAllFood() {
        List<Food> foodList = foodService.findAll();
        ModelAndView model = new ModelAndView("/admin/food/food");
        model.addObject("foodList", foodList);

        return model;
    }

    @RequestMapping(value = "/admin/food/foodDelete/{id}", method = RequestMethod.DELETE)
    public String foodDelete(@PathVariable Long id) {

        foodService.delete(id);

        return "redirect:/admin/food/food";
    }


}
