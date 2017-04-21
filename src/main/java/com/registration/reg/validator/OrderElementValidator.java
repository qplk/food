package com.registration.reg.validator;

import com.registration.reg.model.Assortment;
import com.registration.reg.model.OrderElement;
import com.registration.reg.requestBody.OrderElementRequestBody;
import com.registration.reg.service.AssortmentService;
import com.registration.reg.service.CityService;
import com.registration.reg.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Stasia on 19.04.17.
 */
@Component
public class OrderElementValidator implements Validator {
    @Autowired
    private AssortmentService assortmentService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CityService cityService;

    @Override
    public boolean supports(Class<?> aClass) {
        return OrderElementRequestBody.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        OrderElementRequestBody orderElementRequestBody = (OrderElementRequestBody) o;

        validateFields(orderElementRequestBody, errors);
    }

    public void validateFields(OrderElementRequestBody orderElementRequestBody, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "NotEmpty");

        if ((orderElementRequestBody.getQuantity() != null) && (orderElementRequestBody.getQuantity() < 0)) {

            errors.rejectValue("quantity", "Value.orderElementForm.quantity");
        }

        Assortment assortment = assortmentService.findByRestaurantIdAndFoodId(restaurantService.findByCity(cityService.get(orderElementRequestBody.getCityId())).getRestaurantId(), orderElementRequestBody.getFoodId());
        if ((assortment.getQuantity() != null) && (assortment.getQuantity() < orderElementRequestBody.getQuantity())) {
            errors.rejectValue("quantity", "ValueTooBig.orderElementForm.quantity");
        }

        if (!assortment.getEnable()) {
            errors.rejectValue("quantity", "ValueTooBig.orderElementForm.quantity");
        }
    }


}
