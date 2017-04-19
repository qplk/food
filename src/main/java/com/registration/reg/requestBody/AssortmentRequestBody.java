package com.registration.reg.requestBody;

import com.registration.reg.model.Food;
import com.registration.reg.model.Restaurant;

/**
 * Created by Stasia on 16.03.17.
 */
public class AssortmentRequestBody {
    private Long assortmentId;
    private Integer quantity;
    private Boolean enable;


    public AssortmentRequestBody() {
    }

    public AssortmentRequestBody(Integer quantity, Boolean enable, Long restaurantId, Long foodId) {
        this.quantity = quantity;
        this.enable = enable;
        this.restaurantId = restaurantId;
        this.foodId = foodId;
    }

    public Long getAssortmentId() {
        return assortmentId;
    }

    public void setAssortmentId(Long assortmentId) {
        this.assortmentId = assortmentId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    private Long restaurantId;
    private Long foodId;
}
