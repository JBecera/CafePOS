package com.cafepos.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafepos.backend.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
