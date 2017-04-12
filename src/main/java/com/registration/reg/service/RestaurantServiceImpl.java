package com.registration.reg.service;

import com.registration.reg.model.Restaurant;
import com.registration.reg.model.City;
import com.registration.reg.repository.RestaurantRepository;
import com.registration.reg.repository.CityRepository;
import com.registration.reg.requestBody.RestaurantRequestBody;
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
    public void save(RestaurantRequestBody restaurantRequestBody) {
        Restaurant restaurant = new Restaurant(restaurantRequestBody.getStreet(), restaurantRequestBody.getBuildingNumber(), restaurantRequestBody.getRestaurantPhone());
        City city = cityRepository.getOne(restaurantRequestBody.getCityId());

        restaurant.setCityByRestaurantId(city);
        restaurantRepository.save(restaurant);

        city.getRestaurants().add(restaurant);

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

    @Override
    public void update(Long restaurantId, RestaurantRequestBody restaurantRequestBody) {
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        if (restaurantRequestBody.getCityId() != restaurant.getCityByRestaurantId().getCityId()) {
            City city = cityRepository.getOne(restaurantRequestBody.getCityId());

            city.getRestaurants().add(restaurant);
            cityRepository.save(city);

            restaurant.setCityByRestaurantId(city);
        }


        restaurant.setStreet(restaurantRequestBody.getStreet());
        restaurant.setRestaurantPhone(restaurantRequestBody.getRestaurantPhone());
        restaurant.setBuildingNumber(restaurantRequestBody.getBuildingNumber());


        restaurantRepository.save(restaurant);
    }

}
