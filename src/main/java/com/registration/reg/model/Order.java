package com.registration.reg.model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Stasia on 08.03.17.
 */
@Entity
@Table(name = "orders")
public class Order {
    public Order(Date deliveryTime, Integer fullPrice, String status, String statusInfo, String paymentInfo) {
        this.deliveryTime = deliveryTime;
        this.fullPrice = fullPrice;
        this.status = status;
        this.statusInfo = statusInfo;
        this.paymentInfo = paymentInfo;
    }

    public Order(String statusInfo, User user) {
        this.deliveryTime = new Date();
        this.fullPrice = 0;
        this.status = "Forming";
        this.statusInfo = statusInfo;
        this.paymentInfo = "Cash";
        this.user = user;
    }

    private Long orderId;

    public Order() {
    }

    //private Long userId;
    //private Long restaurantId;
    private Date deliveryTime;
    private Integer fullPrice;
    private String status;
    private String statusInfo;
    private String paymentInfo;
    private User user;
    private Restaurant restaurantByOrderId;
    private Set<OrderElement> orderElements = new HashSet<>();
    private Address addressByOrderId;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

  /*  @Column(name = "user_id", nullable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }*/


   /* @Column(name = "restaurant_id", nullable = false)
    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }*/

    @Column(name = "delivery_time", nullable = false)
    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Column(name = "full_price", nullable = false)
    public Integer getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(Integer fullPrice) {
        this.fullPrice = fullPrice;
    }

    @Column(name = "status", nullable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "status_info", nullable = true)
    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    @Column(name = "payment_info", nullable = false)
    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }


    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    public Restaurant getRestaurantByOrderId() {
        return restaurantByOrderId;
    }

    public void setRestaurantByOrderId(Restaurant restaurantByOrderId) {
        this.restaurantByOrderId = restaurantByOrderId;
    }


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<OrderElement> getOrderElements() {
        return orderElements;
    }

    public void setOrderElements(Set<OrderElement> orderElements) {
        this.orderElements = orderElements;
    }


    @ManyToOne
    @JoinColumn(name = "address_id")
    public Address getAddressByOrderId() {
        return addressByOrderId;
    }

    public void setAddressByOrderId(Address addressByOrderId) {
        this.addressByOrderId = addressByOrderId;
    }
}

