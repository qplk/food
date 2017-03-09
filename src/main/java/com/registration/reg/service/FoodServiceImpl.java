package com.registration.reg.service;

import com.registration.reg.model.Food;
import com.registration.reg.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
}
