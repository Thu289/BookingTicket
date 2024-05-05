package com.example.bookingticket.repository;

import com.example.bookingticket.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
