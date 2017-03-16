package com.registration.reg.controller;

import com.registration.reg.model.Food;
import com.registration.reg.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Stasia on 16.03.17.
 */
@Controller("FoodController")
public class FoodController {
    @Autowired
    FoodService foodService;

    @RequestMapping(value = "/food", method = RequestMethod.GET)
    public ResponseEntity findAllFood() {
        Iterable<Food> foodList = foodService.findAll();
        ResponseEntity responseEntity = new ResponseEntity<>(foodList, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/food", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addFood(@RequestBody Food food) {
        foodService.save(food);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/food/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteFood(@PathVariable("id") Long id) {
        foodService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
