package com.utad.products.repository;

import com.utad.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStock(Integer stock);
    List<Product> findByNameContainingIgnoreCase(String name);
}
