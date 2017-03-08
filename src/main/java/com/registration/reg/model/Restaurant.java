package com.registration.reg.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Stasia on 08.03.17.
 */
@Entity
@Table(name = "restaurant")
public class Restaurant {
    private Long restaurantId;
    //private Long cityId;
    private String street;
    private String buildingNumber;
    private String restaurantPhone;
    private City cityByRestaurantId;
    private Set<Order> orders = new HashSet<>();
    private Set<Assortment> assortment = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "restaurant_id")
    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

   /* @Column(name = "city_id", nullable = false)
    public Long getCityId() {
        return cityId;
    }


    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }*/

    @Column(name = "street", nullable = false)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "building_number", nullable = false)
    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }


    @Column(name = "restaurant_phone", nullable = false)
    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }


    @ManyToOne
    @JoinColumn(name = "city_id")
    public City getCityByRestaurantId() {
        return cityByRestaurantId;
    }

    public void setCityByRestaurantId(City cityByRestaurantId) {
        this.cityByRestaurantId = cityByRestaurantId;
    }


    @OneToMany(mappedBy = "restaurantByOrderId", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }


    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Assortment> getAssortment() {
        return assortment;
    }

    public void setAssortment(Set<Assortment> assortment) {
        this.assortment = assortment;
    }
}
