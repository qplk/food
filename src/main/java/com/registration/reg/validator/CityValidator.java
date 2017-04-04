package com.registration.reg.validator;

import com.registration.reg.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.registration.reg.model.City;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Stasia on 04.04.17.
 */
@Component
public class CityValidator implements Validator {
    private static String patternPhoneString = "^\\+?[0-9. ()-]{10,25}$";
    Pattern patternPhone = Pattern.compile(patternPhoneString);

    @Autowired
    private CityService cityService;

    @Override
    public boolean supports(Class<?> aClass) {
        return City.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        City city = (City) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cityName", "NotEmpty");
        if (city.getCityName().length() < 2 || city.getCityName().length() > 32) {
            errors.rejectValue("cityName", "Size.cityForm.cityName");
        }
        if (cityService.findByCityName(city.getCityName()) != null) {
            errors.rejectValue("cityName", "Duplicate.cityForm.cityName");
        }

        if(!patternPhone.matcher(city.getDeliveryPhone()).matches()){
            errors.rejectValue("deliveryPhone", "Match.cityForm.deliveryPhone");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "minPrice", "NotEmpty");
        if (city.getMinPrice() < 0) {
            errors.rejectValue("minPrice", "Value.cityForm.minPrice");
        }
    }
}
