package com.registration.reg.service;

import com.registration.reg.model.Restaurant;
import com.registration.reg.model.City;
import com.registration.reg.repository.RestaurantRepository;
import com.registration.reg.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Stasia on 09.03.17.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private CityRepository cityRepository;

    @Transactional
    @Override
    public void save(Restaurant restaurant, Long cityId) {
        City city = cityRepository.getOne(cityId);

        restaurant.setCityByRestaurantId(city);

        Set<Restaurant> restaurantsInCity = new HashSet<>(city.getRestaurants());
        restaurantsInCity.add(restaurant);
        city.setRestaurants(restaurantsInCity);

        restaurantRepository.save(restaurant);
        cityRepository.save(city);
    }

    @Override
    public Restaurant get(Long restaurantId) {
        return restaurantRepository.getOne(restaurantId);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public void delete(Long restaurantId) {
        restaurantRepository.delete(restaurantId);
    }
}
