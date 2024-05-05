package com.example.bookingticket.repository;

import com.example.bookingticket.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}
