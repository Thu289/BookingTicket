package com.example.bookingticket.repository;

import com.example.bookingticket.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
