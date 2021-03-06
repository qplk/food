package com.registration.reg.service;

import com.registration.reg.model.Address;
import com.registration.reg.model.Restaurant;
import com.registration.reg.model.City;
import com.registration.reg.requestBody.RestaurantRequestBody;

import java.util.List;

/**
 * Created by Stasia on 09.03.17.
 */
public interface RestaurantService {
    void save(RestaurantRequestBody restaurantRequestBody);

    Restaurant get(Long restaurantId);

    List<Restaurant> findAll();

    void delete(Long restaurantId);

    void update(Long restaurantId, RestaurantRequestBody restaurantRequestBody);

    Restaurant selectRestaurant(Address address);
}
