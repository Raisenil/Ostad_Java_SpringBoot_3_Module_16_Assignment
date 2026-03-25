package com.example.warehouse.module_16_assignment.repository;

import com.example.warehouse.module_16_assignment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
