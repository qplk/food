package com.registration.reg.repository;


import com.registration.reg.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stasia on 09.03.17.
 */
public interface CityRepository extends JpaRepository<City, Long>  {
    City findByCityName(String cityName);
}
