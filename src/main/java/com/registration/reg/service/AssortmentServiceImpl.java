package com.registration.reg.service;

import com.registration.reg.model.Assortment;
import com.registration.reg.model.Restaurant;
import com.registration.reg.model.Food;
import com.registration.reg.repository.AssortmentRepository;
import com.registration.reg.repository.RestaurantRepository;
import com.registration.reg.repository.FoodRepository;
import com.registration.reg.requestBody.AssortmentRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Set;
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
    public void save(AssortmentRequestBody assortmentRequestBody) {
        Assortment assortment = new Assortment(assortmentRequestBody.getQuantity(), assortmentRequestBody.getEnable());
        Restaurant restaurant = restaurantRepository.findOne(assortmentRequestBody.getRestaurantId());
        Food food = foodRepository.findOne(assortmentRequestBody.getFoodId());

        assortment.setRestaurant(restaurant);
        assortment.setFood(food);
        assortmentRepository.save(assortment);

        restaurant.getAssortment().add(assortment);
        restaurantRepository.save(restaurant);


        food.getAssortment().add(assortment);
        foodRepository.save(food);
    }

    @Override
    public Assortment get(Long assortmentId) {
        return assortmentRepository.getOne(assortmentId);
    }

    @Override
    public Assortment findByRestaurantIdAndFoodId(Long restaurantId, Long foodId) {
        return assortmentRepository.findByRestaurantAndFood(restaurantRepository.getOne(restaurantId), foodRepository.getOne(foodId));
    }

    @Override
    public void delete(Long assortmentId) {
        assortmentRepository.delete(assortmentId);
    }

    @Override
    public List<Assortment> findAll() {
        return assortmentRepository.findAll();
    }

    @Override
    public void update(Long assortmentId, AssortmentRequestBody assortmentRequestBody) {
        Assortment assortment = assortmentRepository.getOne(assortmentId);

        assortment.setQuantity(assortmentRequestBody.getQuantity());
        assortment.setEnable(assortmentRequestBody.getEnable());

        assortmentRepository.save(assortment);
    }

    @Override
    public void update(Long restauranId, Long foodId, AssortmentRequestBody assortmentRequestBody) {
        Assortment assortment = this.findByRestaurantIdAndFoodId(restauranId, foodId);

        assortment.setQuantity(assortmentRequestBody.getQuantity());
        assortment.setEnable(assortmentRequestBody.getEnable());

        assortmentRepository.save(assortment);
    }

    @Override
    public List<Food> findAllFoodInRestaurant(Long restaurantId, String category){
        List<Assortment> allAssortmentList = findAll();
        List<Food> foodAssortmentList = new ArrayList<Food>();
        Set<Food> foodAssortmentSet = new HashSet<Food>();
        for(Assortment item : allAssortmentList){
            if (item.getRestaurant().getRestaurantId() == restaurantId){
                if(item.getEnable() && (item.getFood().getCategory().equals(category))){
                    foodAssortmentSet.add(item.getFood());
                }
            }
        }
        for(Food item: foodAssortmentSet){
            item.setAssortment(null);
            foodAssortmentList.add(item);
        }
        return foodAssortmentList;
    }
}
