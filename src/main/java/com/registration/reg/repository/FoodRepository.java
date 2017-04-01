package com.registration.reg.repository;


import com.registration.reg.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stasia on 09.03.17.
 */
public interface FoodRepository extends JpaRepository<Food, Long> {
    Food findByFoodName(String foodName);
}
