package com.registration.reg.repository;

import com.registration.reg.model.OrderElement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Stasia on 09.03.17.
 */
public interface OrderElementRepository extends JpaRepository<OrderElement, Long> {
}
