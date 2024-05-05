package com.example.bookingticket.repository;

import com.example.bookingticket.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
