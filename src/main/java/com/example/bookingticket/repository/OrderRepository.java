package com.example.bookingticket.repository;

import com.example.bookingticket.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
