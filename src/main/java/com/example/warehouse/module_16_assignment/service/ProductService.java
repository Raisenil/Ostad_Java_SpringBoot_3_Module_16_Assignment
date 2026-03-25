package com.example.warehouse.module_16_assignment.service;

import com.example.warehouse.module_16_assignment.entity.Product;
import com.example.warehouse.module_16_assignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public void updateStock(Long id, Integer quantity) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        product.get().setQuantity(quantity);
        productRepository.save(product.get());
    }
}
