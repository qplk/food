package com.registration.reg.service;

import com.registration.reg.model.Assortment;
import com.registration.reg.requestBody.AssortmentRequestBody;

import java.util.List;

/**
 * Created by Stasia on 09.03.17.
 */
public interface AssortmentService {
    void save(AssortmentRequestBody assortmentRequestBody);

    Assortment get(Long assortmentId);

    Assortment findByRestaurantIdAndFoodId(Long restaurantId, Long foodId);

    List<Assortment> findAll();

    void delete(Long assortmentId);

    void update(Long assortmentId, AssortmentRequestBody assortmentRequestBody);

    void update(Long restauranId, Long foodId, AssortmentRequestBody assortmentRequestBody);
}
