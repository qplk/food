package com.registration.reg.service;

import com.registration.reg.model.City;
import com.registration.reg.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.logging.Level;
import java.util.List;

/**
 * Created by Stasia on 09.03.17.
 */
@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public void save(City city) {
        cityRepository.save(city);
    }

    @Override
    public City get(Long cityId) {
        return cityRepository.getOne(cityId);
    }

    @Override
    public void delete(Long cityId) {
        cityRepository.delete(cityId);
    }

    @Override
    public List <City> findAll() {
        return cityRepository.findAll();
    }
}
