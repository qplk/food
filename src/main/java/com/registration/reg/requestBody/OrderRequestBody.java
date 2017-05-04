package com.registration.reg.requestBody;

import com.registration.reg.model.Address;
import com.registration.reg.model.OrderElement;
import com.registration.reg.model.Restaurant;
import com.registration.reg.model.User;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Stasia on 16.03.17.
 */
public class OrderRequestBody {
    private Long orderId;
    private Long userId;

    public List<OrderElement> getOrderElements() {
        return orderElements;
    }

    public void setOrderElements(List<OrderElement> orderElements) {
        this.orderElements = orderElements;
    }

    private List<OrderElement> orderElements = new ArrayList<>();

    public OrderRequestBody() {
    }


    public OrderRequestBody(Long orderId, String status, String statusInfo) {
        this.orderId = orderId;
        this.status = status;
        this.statusInfo = statusInfo;
    }

    public OrderRequestBody(Long orderId, List<OrderElement> orderElements, Integer fullPrice) {
        this.orderId = orderId;
        this.orderElements = orderElements;
        this.fullPrice = fullPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Time getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Time deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(Integer fullPrice) {
        this.fullPrice = fullPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    private Long restaurantId;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    private Long addressId;
    private Time deliveryTime;
    private Integer fullPrice;
    private String status;
    private String statusInfo;
    private String paymentInfo;
}
