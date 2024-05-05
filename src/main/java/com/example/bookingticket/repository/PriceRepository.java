package com.example.bookingticket.repository;

import com.example.bookingticket.model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Integer> {
}
