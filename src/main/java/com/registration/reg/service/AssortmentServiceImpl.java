package com.registration.reg.service;

import com.registration.reg.model.Assortment;
import com.registration.reg.model.Restaurant;
import com.registration.reg.repository.AssortmentRepository;
import com.registration.reg.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Created by Stasia on 09.03.17.
 */
@Service
public class AssortmentServiceImpl implements AssortmentService {
    @Autowired
    private AssortmentRepository assortmentRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public void save(Assortment assortment, Restaurant restaurant) {
        assortment.setRestaurant(restaurantRepository.findOne(restaurant.getRestaurantId()));

        HashSet<Assortment> assortmentInRestaurant = new HashSet<>(restaurantRepository.getOne(restaurant.getRestaurantId()).getAssortment());
        assortmentInRestaurant.add(assortment);
        restaurantRepository.getOne(restaurant.getRestaurantId()).setAssortment(assortmentInRestaurant);

        assortmentRepository.save(assortment);
    }
}
