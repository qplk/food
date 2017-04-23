package com.registration.reg.repository;

import com.registration.reg.model.Order;
import com.registration.reg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Stasia on 09.03.17.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(String status);
    List<Order> findByUserByOrderIdAndStatus(User userByOrderId, String status);
}
