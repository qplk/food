package com.registration.reg.validator;

import com.registration.reg.model.Assortment;
import com.registration.reg.model.Order;
import com.registration.reg.model.OrderElement;
import com.registration.reg.service.AddressService;
import com.registration.reg.service.AssortmentService;
import com.registration.reg.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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

    @Override
    public boolean supports(Class<?> aClass) {
        return Order.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Order order = (Order) o;

        // When restaurant will be set
        /*
        if (order.getAddressByOrderId() != null) {
            if (!order.getAddressByOrderId().getCityByAddressId().equals(order.getRestaurantByOrderId().getCityByRestaurantId())) {
                errors.rejectValue("addressId", "City.addressForm.addressId");
            }
        }

         for (OrderElement orderElement: order.getOrderElements()) {
            Assortment assortment = assortmentService.findByRestaurantIdAndFoodId(order.getRestaurantByOrderId().getRestaurantId(), orderElement.getFood().getFoodId());

            if (!assortment.getEnable()) {
                errors.rejectValue("orderElement", "Enable.orderForm.orderElement");
            }

            if ((assortment.getQuantity() != null) && (assortment.getQuantity() < orderElement.getQuantity())) {
                errors.rejectValue("orderElement", "Quantity.orderElementForm.orderElement");
            }

        }

        */

        // What full price will be counted
       /* if (order.getFullPrice() < order.getRestaurantByOrderId().getCityByRestaurantId().getMinPrice()) {
            errors.rejectValue("fullPrice", "TooSmall.orderForm.fullPrice");
        }
       */


    }


}