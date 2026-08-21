package com.javatechie.repository;

import com.javatechie.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findByCategory(@Param("category") String category);

    @Query("SELECT p FROM Product p WHERE (p.createdAt IS NOT NULL AND p.createdAt >= :cutoff) OR (p.updatedAt IS NOT NULL AND p.updatedAt >= :cutoff)")
    List<Product> findRecentlyChanged(@Param("cutoff") LocalDateTime cutoff);

    //List<Product> findByCategory(String category);


    // Complex derived query with LIKE and case-insensitive matching
    List<Product> findByNameContainingIgnoreCase(String name);

    // Multi-property query with AND conditions
    List<Product> findByPriceGreaterThan(BigDecimal price);

}
