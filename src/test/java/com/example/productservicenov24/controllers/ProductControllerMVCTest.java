package com.example.productservicenov24.controllers;

import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

//@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    ProductController productController;

    @MockBean(name ="SelfProductService")
    private ProductService productService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }
    @Test
    void testGetProductById() throws Exception {
        // Arrange
        Long productId = 1L;
        Product mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setTitle("Test Product");
        mockProduct.setPrice(100.0);
        when(productService.getProductById(productId)).thenReturn(mockProduct);

        // Act and Assert
        mockMvc.perform(get("/products/{id}", productId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(productId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Product"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(100.0));

        verify(productService, times(1)).getProductById(productId);
    }

//    @Test
//    public void testGetProductById_NotFound() throws Exception {
//        // Define behavior for the mock service when the product is not found
//        when(productService.getProductById(1L)).thenThrow(new ProductNotFoundException("Product not found"));
//
//        // Perform GET request and verify the result for not found case
//        mockMvc.perform(get("/products/{id}", 1L))
//                .andExpect(status().isNotFound())  // HTTP Status 404
//                .andExpect(content().string("Product not found"));
//    }
}
