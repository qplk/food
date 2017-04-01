package com.registration.reg.model;

import javax.persistence.*;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Stasia on 08.03.17.
 */
@Entity
@Table(name = "city")
public class City {
    private Long cityId;
    private String cityName;
    private String deliveryPhone;
    private Time openTime;
    private Time closeTime;
    private Time deliveryTime;
    private Integer minPrice;
    private Set<Address> addresses = new HashSet<>();
    private Set<Restaurant> restaurants = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "city_id")
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Column(name = "city_name", nullable = false)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Column(name = "delivery_phone", nullable = false)
    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    @Column(name = "open_time", nullable = false)
    public Time getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }

    @Column(name = "close_time", nullable = false)
    public Time getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }

    @Column(name = "delivery_time", nullable = false)
    public Time getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Time deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Column(name = "min_price", nullable = false)
    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }


    @OneToMany(mappedBy = "cityByAddressId", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @OneToMany(mappedBy = "cityByRestaurantId", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {

        this.restaurants = restaurants;
    }
}
