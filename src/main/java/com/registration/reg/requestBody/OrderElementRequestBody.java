package com.registration.reg.requestBody;

import com.registration.reg.model.Food;
import com.registration.reg.model.Order;

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
