package com.registration.reg.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import com.registration.reg.model.User;


/**
 * Created by Stasia on 08.03.17.
 */
@Entity
@Table(name = "address")
public class Address {
    private Long addressId;
    //private Long userId;
    //private Long cityId;
    private String street;
    private String buildingNumber;
    private String roomNumber;
    private String comment;
    //private User userByAddressId;
    private Set<User> users = new HashSet<>();
    private City cityByAddressId;
    private Set<Order> orders = new HashSet<>();


    public Address() {

    }

    public Address(String street, String buildingNumber, String roomNumber, String comment) {
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.roomNumber = roomNumber;
        this.comment = comment;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    public Long getId() {
        return addressId;
    }

    public void setId(Long addressId) {
        this.addressId = addressId;
    }

   /*
    @Column(name = "user_id", nullable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }*/

  /*  @Column(name = "city_id", nullable = false)
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

    @Column(name = "room_number", nullable = false)
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Column(name = "comment", nullable = true)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

  /*  @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUserByAddressId() {
        return userByAddressId;
    }

    public void setUserByAddressId(User userByAddressId) {
        this.userByAddressId = userByAddressId;
    }*/


    @ManyToOne
    @JoinColumn(name = "city_id")
    public City getCityByAddressId() {
        return cityByAddressId;
    }

    public void setCityByAddressId(City cityByAddressId) {
        this.cityByAddressId = cityByAddressId;
    }


    @OneToMany(mappedBy = "addressByOrderId", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }


    @ManyToMany
    @JoinTable(name = "user_address", joinColumns = @JoinColumn(name = "address_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}

