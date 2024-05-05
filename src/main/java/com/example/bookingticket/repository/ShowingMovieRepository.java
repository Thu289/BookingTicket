package com.example.bookingticket.repository;

import com.example.bookingticket.model.entity.ShowingMovie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowingMovieRepository extends JpaRepository<ShowingMovie, Integer> {
}
