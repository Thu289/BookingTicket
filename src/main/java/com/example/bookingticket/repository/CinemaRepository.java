package com.example.bookingticket.repository;

import com.example.bookingticket.model.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
    @Query("SELECT c FROM Cinema c WHERE c.status <> 2")
    List<Cinema> findAllCinema();

    @Query("SELECT c FROM Cinema c WHERE c.status = ?1")
    List<Cinema> findCinemaWithStatus(int status);

    @Query("SELECT c FROM Cinema c WHERE c.code = ?1")
    Cinema findByCode(String code);
}
