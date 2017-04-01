package com.registration.reg.service;

import com.registration.reg.model.Food;
import com.registration.reg.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Stasia on 09.03.17.
 */
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;


    @Override
    public void save(Food food) {
       foodRepository.save(food);
    }

    @Override
    public Food get(Long foodId) {
        return foodRepository.getOne(foodId);
    }

    @Override
    public Food findByFoodName(String foodName) {
        return foodRepository.findByFoodName(foodName);
    }

    @Override
    public List <Food> findAll() {
        return foodRepository.findAll();
    }

    @Override
    public void delete(Long foodId) {
        foodRepository.delete(foodId);
    }
}
