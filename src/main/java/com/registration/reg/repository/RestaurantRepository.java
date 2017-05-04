package com.registration.reg.repository;

import com.registration.reg.model.City;
import com.registration.reg.model.Restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Stasia on 09.03.17.
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByCityByRestaurantId(City cityByRestaurantId);
}
