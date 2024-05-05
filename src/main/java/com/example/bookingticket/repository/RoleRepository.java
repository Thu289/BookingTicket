package com.example.bookingticket.repository;

import com.example.bookingticket.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
