package com.registration.reg.service;

import com.registration.reg.model.City;

import java.util.List;

/**
 * Created by Stasia on 09.03.17.
 */
public interface CityService {
    void save(City city);

    City get(Long cityId);

    City findByCityName(String cityName);

    List<City> findAll();

    void delete(Long cityId);

    void update(Long cityId, City city);
}
