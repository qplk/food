package com.registration.reg.validator;

import com.registration.reg.model.Address;
import com.registration.reg.model.Assortment;
import com.registration.reg.model.Order;
import com.registration.reg.model.OrderElement;
import com.registration.reg.requestBody.AssortmentRequestBody;
import com.registration.reg.requestBody.OrderElementRequestBody;
import com.registration.reg.requestBody.OrderRequestBody;
import com.registration.reg.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Stasia on 19.04.17.
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

        if (order.getAddressByOrderId() != null) {
            if (!order.getAddressByOrderId().getCityByAddressId().equals(order.getRestaurantByOrderId().getCityByRestaurantId())) {
                errors.rejectValue("addressId", "City.addressForm.addressId");
                System.out.println("Address error");
            }

        }


        if (order.getFullPrice() < order.getRestaurantByOrderId().getCityByRestaurantId().getMinPrice()) {
            errors.rejectValue("fullPrice", "TooSmall.orderForm.fullPrice");
            System.out.println("Price error");
        }
        else {
            System.out.println("Price ok");
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
    }


    public void validateRequestBody(Long orderId, OrderRequestBody orderRequestBody, Errors errors) {
        Order order = orderService.get(orderId);
        Address address = addressService.get(orderRequestBody.getAddressId());

        if (!order.getRestaurantByOrderId().getCityByRestaurantId().getCityId().equals(address.getCityByAddressId().getCityId())) {
            errors.rejectValue("addressId", "City.addressForm.addressId");
            System.out.println("Address error");
        }

    }



}
