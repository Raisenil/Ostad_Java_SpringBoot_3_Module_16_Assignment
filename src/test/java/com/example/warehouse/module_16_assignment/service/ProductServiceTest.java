package com.example.warehouse.module_16_assignment.service;

import com.example.warehouse.module_16_assignment.entity.Product;
import com.example.warehouse.module_16_assignment.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testSaveProduct() {
        // Arrange
        Product product = new Product("Wireless Mouse", 50, 25.99);
        Product savedProduct = new Product("Wireless Mouse", 50, 25.99);
        savedProduct.setId(1L);

        when(productRepository.save(product)).thenReturn(savedProduct);

        // Act
        Product result = productService.saveProduct(product);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Wireless Mouse", result.getName());
        assertEquals(50, result.getQuantity());
        assertEquals(25.99, result.getPrice());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testGetProductById_Exists() {
        // Arrange
        Product product = new Product("Wireless Mouse", 50, 25.99);
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));

        // Act
        Product foundProduct = productService.getProductById(1L);

        // Assert
        assertNotNull(foundProduct);
        assertEquals("Wireless Mouse", foundProduct.getName());
        assertEquals(1L, foundProduct.getId());
    }

    @Test
    void testGetProductById_NotExists() {
        // Arrange
        Product existingProduct = new Product("Wireless Mouse", 50, 25.99);
        existingProduct.setId(1L);

        when(productRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> productService.getProductById(1L));

        verify(productRepository, times(1)).findById(anyLong());
    }
}
