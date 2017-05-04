package com.registration.reg.validator;

import com.registration.reg.model.Assortment;
import com.registration.reg.model.Order;
import com.registration.reg.model.OrderElement;
import com.registration.reg.model.Restaurant;
import com.registration.reg.requestBody.OrderRequestBody;
import com.registration.reg.service.AddressService;
import com.registration.reg.service.AssortmentService;
import com.registration.reg.service.OrderService;
import com.registration.reg.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Stasia on 03.05.17.
 */
@Component
public class OrderValidator implements Validator {
    @Autowired
    private AssortmentService assortmentService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private RestaurantService restauranService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Order.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        OrderRequestBody order = (OrderRequestBody) o;

        Restaurant restaurant = restauranService.get(order.getRestaurantId());

        if (order.getFullPrice() < restaurant.getCityByRestaurantId().getMinPrice()) {
            errors.rejectValue("fullPrice", "TooSmall");
        }


        List <OrderElement> orderElements = order.getOrderElements();
        for (OrderElement orderElement: orderElements) {
            Assortment assortment = assortmentService.findByRestaurantIdAndFoodId(restaurant.getRestaurantId(), orderElement.getFood().getFoodId());
            if (!assortment.getEnable()) {
                errors.rejectValue("orderElements[" + orderElements.indexOf(orderElement) + "].orderElementId", "Disabled");
            }
            if ((assortment.getQuantity() != null) && (assortment.getQuantity() < orderElement.getQuantity())) {
                errors.rejectValue("orderElements[" + orderElements.indexOf(orderElement) + "].quantity", "TooBig");
            }
        }

    }


}