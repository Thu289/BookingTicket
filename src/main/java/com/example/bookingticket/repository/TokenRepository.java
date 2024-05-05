package com.example.bookingticket.repository;

import com.example.bookingticket.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer> {
}
