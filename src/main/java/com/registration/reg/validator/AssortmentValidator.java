package com.registration.reg.validator;

import com.registration.reg.model.Assortment;
import com.registration.reg.requestBody.AssortmentRequestBody;
import com.registration.reg.service.AssortmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.registration.reg.model.City;
import com.registration.reg.service.CityService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Stasia on 05.04.17.
 */
@Component
public class AssortmentValidator implements Validator {
    @Autowired
    private AssortmentService assortmentService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Assortment.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AssortmentRequestBody assortment = (AssortmentRequestBody) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "foodId", "NotEmpty");

        if (assortmentService.findByRestaurantIdAndFoodId(assortment.getRestaurantId(), assortment.getFoodId()) != null) {
            errors.rejectValue("foodId", "Duplicate.assortmentForm.foodId");
        }

        if (assortment.getQuantity() < 0) {
            errors.rejectValue("quantity", "Value.assortmentForm.quantity");
        }
    }
}
