package com.deltasource.eu.domainmodel.repository;


import com.deltasource.eu.domainmodel.model.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderNumber(String orderNumber);
}
