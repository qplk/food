package com.registration.reg.service;

import com.registration.reg.model.Assortment;
import com.registration.reg.model.Restaurant;
/**
 * Created by Stasia on 09.03.17.
 */
public interface AssortmentService {
    void save(Assortment assortment, Restaurant restaurant);
}
