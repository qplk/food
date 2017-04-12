package com.registration.reg.validator;

import com.registration.reg.model.City;
import com.registration.reg.model.Restaurant;
import com.registration.reg.requestBody.RestaurantRequestBody;
import com.registration.reg.service.CityService;
import com.registration.reg.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

/**
 * Created by Stasia on 05.04.17.
 */
@Component
public class RestaurantValidator implements Validator {
    private static String patternPhoneString = "^\\+?[0-9. ()-]{10,25}$";
    Pattern patternPhone = Pattern.compile(patternPhoneString);


    @Autowired
    private RestaurantService restaurantService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Restaurant.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RestaurantRequestBody restaurant = (RestaurantRequestBody) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "buildingNumber", "NotEmpty");

        if(!patternPhone.matcher(restaurant.getRestaurantPhone()).matches()){
            errors.rejectValue("restaurantPhone", "Match.restaurantForm.restaurantPhone");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cityId", "NotEmpty");
    }

}
