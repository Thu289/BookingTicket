package com.example.bookingticket.repository;

import com.example.bookingticket.model.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
}
