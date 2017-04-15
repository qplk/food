package com.registration.reg.repository;

import com.registration.reg.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;

/**
 * Created by Stasia on 09.03.17.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query("delete from Order o where o.id = ?1")
    void delete(Long orderId);
}
