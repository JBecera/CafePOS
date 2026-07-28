package com.cafepos.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafepos.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
