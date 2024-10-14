package com.example.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth_service.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
