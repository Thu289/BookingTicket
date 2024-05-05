package com.example.bookingticket.repository;

import com.example.bookingticket.model.entity.Account;
import com.example.bookingticket.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Token findByToken(String token);

    List<Token> findByAccount(Account account);
}
