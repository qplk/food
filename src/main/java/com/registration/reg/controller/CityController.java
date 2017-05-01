package com.registration.reg.controller;

import com.registration.reg.model.City;
import com.registration.reg.requestBody.AddressRequestBody;
import com.registration.reg.service.CityService;
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
@Controller("CityController")
public class CityController {
    @Autowired
    CityService cityService;

    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public ResponseEntity findAllCyties() {
        Iterable<City> cityList = cityService.findAll();
        for(City item: cityList){
            item.setRestaurants(null);
        }
        ResponseEntity responseEntity = new ResponseEntity<>(cityList, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/cities", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity addCity(@RequestBody City city) {
        cityService.save(city);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/cities/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCity(@PathVariable("id") Long id) {
        cityService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
