package com.registration.reg.validator;

/**
 * Created by Stasia on 28.03.17.
 */

import org.springframework.stereotype.Component;
import com.registration.reg.model.Food;
import com.registration.reg.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FoodValidator implements Validator {
    @Autowired
    private FoodService foodService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Food.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Food food = (Food) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "foodName", "NotEmpty");
        if (food.getFoodName().length() < 1 || food.getFoodName().length() > 32) {
            errors.rejectValue("foodName", "Size.foodForm.foodName");
        }
        if (foodService.findByFoodName(food.getFoodName()) != null) {
            errors.rejectValue("foodName", "Duplicate.foodForm.foodName");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "portionSize", "NotEmpty");
        if (food.getPortionSize().length() < 1 || food.getPortionSize().length() > 32) {
            errors.rejectValue("portionSize", "Size.foodForm.portionSize");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty");
        if (food.getPrice() < 0) {
            errors.rejectValue("price", "Value.foodForm.price");
        }
    }
}
