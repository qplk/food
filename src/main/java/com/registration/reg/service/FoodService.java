package com.registration.reg.service;

import com.registration.reg.model.Food;

import java.util.List;

/**
 * Created by Stasia on 09.03.17.
 */
public interface FoodService {
    void save(Food food);

    Food findByFoodName(String foodName);

    Food get(Long foodId);

    List<Food> findAll();

    void delete(Long foodId);
}