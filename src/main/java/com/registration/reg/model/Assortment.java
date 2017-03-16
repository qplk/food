package com.registration.reg.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.sql.*;

/**
 * Created by Stasia on 08.03.17.
 */
@Entity //@IdClass(AssortmentId.class)
@Table(name = "assortment")
public class Assortment {
    //@Id private Long restaurantId;
    //@Id private Long foodId;
    private Long assortmentId;

    public Assortment(Integer quantity, Boolean enable) {
        this.quantity = quantity;
        this.enable = enable;
    }

    public Assortment() {
    }

    private Integer quantity;
    private Boolean enable;
    private Restaurant restaurant;
    private Food food;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "assortment_id")
    public Long getAssortmentId() {
        return assortmentId;
    }

    public void setAssortmentId(Long assortmentId) {
        this.assortmentId = assortmentId;
    }

   /* @Column(name = "restaurant_id")
    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Column(name = "food_id")
    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }*/

    @Column(name = "quantity", nullable = true)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "enable", nullable = false)
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }


    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    @ManyToOne
    @JoinColumn(name = "food_id")
    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

}
/*
class AssortmentId implements Serializable {
    private Long restaurantId;
    private Long foodId;
}*/


