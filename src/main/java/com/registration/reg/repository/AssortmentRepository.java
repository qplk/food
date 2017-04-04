package com.registration.reg.repository;


import com.registration.reg.model.Assortment;
import com.registration.reg.model.Restaurant;
import com.registration.reg.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Stasia on 09.03.17.
 */
public interface AssortmentRepository extends JpaRepository<Assortment, Long>  {
    Assortment findByRestaurantAndFood(Restaurant restaurant, Food food);
}
