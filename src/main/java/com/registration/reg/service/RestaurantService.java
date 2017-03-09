package com.registration.reg.service;

import com.registration.reg.model.Restaurant;
import com.registration.reg.model.City;

/**
 * Created by Stasia on 09.03.17.
 */
public interface RestaurantService {
    void save(Restaurant restaurant, City city);
}
