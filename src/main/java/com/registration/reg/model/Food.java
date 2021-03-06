package com.registration.reg.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.sql.*;

/**
 * Created by Stasia on 08.03.17.
 */


@Entity
@Table(name = "food")
public class Food {
    private Long foodId;
    private String foodName;
    private String description;
    private String portionSize;
    private Integer price;
    private String category;
    private String imgPath;
    private Set<Assortment> assortment = new HashSet<>();
    private Set<OrderElement> orderElements = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "food_id")
    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    @Column(name = "food_name", nullable = false)
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @Column(name = "description", nullable = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "portion_size", nullable = false)
    public String getPortionSize() {
        return portionSize;
    }

    public void setPortionSize(String portionSize) {
        this.portionSize = portionSize;
    }

    @Column(name = "price", nullable = false)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Column(name = "category", nullable = false)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Assortment> getAssortment() {
        return assortment;
    }

    public void setAssortment(Set<Assortment> assortment) {
        this.assortment = assortment;
    }


    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<OrderElement> getOrderElements() {
        return orderElements;
    }

    public void setOrderElements(Set<OrderElement> orderElements) {
        this.orderElements = orderElements;
    }

    @Column(name = "img_path", nullable = true)
    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }


}
