package com.registration.reg.service;

import com.registration.reg.model.Assortment;
import com.registration.reg.model.Restaurant;
import com.registration.reg.model.Food;
import com.registration.reg.repository.AssortmentRepository;
import com.registration.reg.repository.RestaurantRepository;
import com.registration.reg.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Stasia on 09.03.17.
 */
@Service
public class AssortmentServiceImpl implements AssortmentService {
    @Autowired
    private AssortmentRepository assortmentRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private FoodRepository foodRepository;

    @Transactional
    @Override
    public void save(Assortment assortment, Long restaurantId, Long foodId) {
        Restaurant restaurant = restaurantRepository.findOne(restaurantId);
        Food food = foodRepository.findOne(foodId);

        assortment.setRestaurant(restaurant);

        HashSet<Assortment> assortmentInRestaurant = new HashSet<>(restaurant.getAssortment());
        assortmentInRestaurant.add(assortment);
        restaurant.setAssortment(assortmentInRestaurant);

        assortment.setFood(food);


        HashSet<Assortment> assortmentOfFood = new HashSet<>(food.getAssortment());
        assortmentOfFood.add(assortment);
        food.setAssortment(assortmentOfFood);

        foodRepository.save(food);
        assortmentRepository.save(assortment);
        restaurantRepository.save(restaurant);
    }

    @Override
    public Assortment get(Long assortmentId) {
        return assortmentRepository.getOne(assortmentId);
    }

    @Override
    public void delete(Long assortmentId) {
        assortmentRepository.delete(assortmentId);
    }

    @Override
    public List<Assortment> findAll() {
        return assortmentRepository.findAll();
    }
}
