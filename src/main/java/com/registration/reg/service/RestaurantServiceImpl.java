package com.registration.reg.service;

import com.registration.reg.model.Restaurant;
import com.registration.reg.model.City;
import com.registration.reg.repository.RestaurantRepository;
import com.registration.reg.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Created by Stasia on 09.03.17.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private CityRepository cityRepository;

    @Override
    public void save(Restaurant restaurant, City city) {
        restaurant.setCityByRestaurantId(cityRepository.getOne(city.getCityId()));

        HashSet<Restaurant> restaurantsInCity = new HashSet<>(cityRepository.getOne(city.getCityId()).getRestaurants());
        restaurantsInCity.add(restaurant);
        cityRepository.getOne(city.getCityId()).setRestaurants(restaurantsInCity);

        restaurantRepository.save(restaurant);
    }
}
