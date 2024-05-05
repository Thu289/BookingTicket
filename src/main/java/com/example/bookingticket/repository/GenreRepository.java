package com.example.bookingticket.repository;

import com.example.bookingticket.common.constant.Status;
import com.example.bookingticket.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findGenreWithStatus(int status);

    Genre findByCode(String code);

    @Query("SELECT g FROM Genre g WHERE g.status <> 2")
    List<Genre> findAllGenre();
}
