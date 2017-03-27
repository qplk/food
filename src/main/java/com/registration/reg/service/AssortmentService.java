package com.registration.reg.service;

import com.registration.reg.model.Assortment;

import java.util.List;

/**
 * Created by Stasia on 09.03.17.
 */
public interface AssortmentService {
    void save(Assortment assortment, Long restaurantId, Long foodId);

    Assortment get(Long assortmentId);

    List<Assortment> findAll();

    void delete(Long assortmentId);
}
