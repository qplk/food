package com.registration.reg.requestBody;

import com.registration.reg.model.User;
import com.registration.reg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Stasia on 16.03.17.
 */
public class OrderElementRequestBody {
    private Long orderElementId;
    private Integer quantity;
    private Long orderId;
    private Long foodId;

    public Long getOrderElementId() {
        return orderElementId;
    }

    public void setOrderElementId(Long orderElementId) {
        this.orderElementId = orderElementId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }


}
