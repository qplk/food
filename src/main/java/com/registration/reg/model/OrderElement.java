package com.registration.reg.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.sql.*;

/**
 * Created by Stasia on 08.03.17.
 */
@Entity //@IdClass(OrderElementId.class)
@Table(name = "order_element")
public class OrderElement {
    //@Id private Long orderId;
    //@Id private Long foodId;
    private Long orderElementId;
    private Integer quantity;
    private Food food;
    private Integer elementPrice;
    private Order order;

    public OrderElement(Integer quantity, Food food, Order order) {
        this.quantity = quantity;
        this.food = food;
        this.order = order;
    }

    public OrderElement() {
    }

    public OrderElement(Integer quantity) {
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_element_id")
    public Long getOrderElementId() {
        return orderElementId;
    }

    public void setOrderElementId(Long orderElementId) {
        this.orderElementId = orderElementId;
    }


  /*  @Column(name = "order_id")
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Column(name = "food_id")
    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }*/

    @Column(name = "quantity", nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    @ManyToOne
    @JoinColumn(name = "food_id")
    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }


    @ManyToOne
    @JoinColumn(name = "order_id")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    @Column(name = "element_price", nullable = false)
    public Integer getElementPrice() {
        return elementPrice;
    }

    public void setElementPrice(Integer elementPrice) {
        this.elementPrice = elementPrice;
    }
}

/*
class OrderElementId implements Serializable {
    private Long orderId;
    private Long foodId;
}*/
