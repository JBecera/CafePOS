package com.cafepos.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cafepos.backend.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p From Product p WHERE p.stockQuantity <= p.lowStockThreshold")
    List<Product> findLowStockProducts();
}
