package com.registration.reg.controller;

import com.registration.reg.model.Restaurant;
import com.registration.reg.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.registration.reg.requestBody.RestaurantRequestBody;

/**
 * Created by Stasia on 16.03.17.
 */
@Controller("RestaurantController")
public class RestaurantContoller {
    @Autowired
    RestaurantService restaurantService;

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public ResponseEntity findAllFood() {
        Iterable<Restaurant> restaurantList = restaurantService.findAll();
        ResponseEntity responseEntity = new ResponseEntity<>(restaurantList, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequestBody restaurantRequestBody) {
        Restaurant restaurant = new Restaurant(restaurantRequestBody.getStreet(), restaurantRequestBody.getBuildingNumber(), restaurantRequestBody.getRestaurantPhone());
        restaurantService.save(restaurant, restaurantRequestBody.getCityId());
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/restaurants/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteRestaurant(@PathVariable("id") Long id) {
        restaurantService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
