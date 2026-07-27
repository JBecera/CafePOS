package com.cafepos.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafepos.backend.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
