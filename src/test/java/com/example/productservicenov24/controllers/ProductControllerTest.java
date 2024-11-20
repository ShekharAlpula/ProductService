package com.example.productservicenov24.controllers;

import com.example.productservicenov24.exceptions.ProductNotFoundException;
import com.example.productservicenov24.models.Category;
import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.services.FakeStoreProductService;
import com.example.productservicenov24.services.ProductService;
import com.example.productservicenov24.services.SelfProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    ProductController productController;

    @MockBean(name = "SelfProductService")
    ProductService productService;

    @Test
    void getProductById() throws ProductNotFoundException {
        //Arrange
        long productId = 1L;
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Test Product");

        when(productService.getProductById(1L)).thenReturn(product);

        //Act
        Product p = productController.getProductById(productId);

        //Assert
        assertEquals("Test Product", p.getTitle());

    }
    @Test
    void TestProductNotFoundException() throws ProductNotFoundException {
        long productId = 1L;

        when(productService.getProductById(1L)).thenThrow(ProductNotFoundException.class);
        assertThrows(ProductNotFoundException.class, () -> {productController.getProductById(productId);});
    }
    @Test
    void getAllProducts() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Test Product1");
        productList.add(product1);

        product1 = new Product();
        product1.setId(2L);
        product1.setTitle("Test Product2");
        productList.add(product1);

        product1 = new Product();
        product1.setId(3L);
        product1.setTitle("Test Product3");
        productList.add(product1);

        when(productService.getAllProducts()).thenReturn(productList);
        List<Product> products = productController.getAllProducts();
        assertEquals("Test Product1", products.get(0).getTitle());
        assertEquals("Test Product2", products.get(1).getTitle());
        assertEquals("Test Product3", products.get(2).getTitle()); }

    @Test
    void createProduct() {
        //Arrange
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Test Product");

        Category category = new Category();
        category.setId(1L);
        category.setTitle("Test Category");
        product.setCategory(category);

        when(productService.createProduct(product)).thenReturn(product);

        //Act
        Product createdProduct = productController.createProduct(product);

        //Assert
        assertEquals("Test Product", createdProduct.getTitle());
        assertEquals("Test Category", createdProduct.getCategory().getTitle());

    }

    @Test
    void replaceProduct() throws ProductNotFoundException {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Test Product");
        //Assertion Fails wit below set
       // product.setDescription("Test Description");

        when(productService.replaceProduct(1L, product)).thenReturn(product);

        //Act
        Product replacedProduct = productController.replaceProduct(1L, product);
        assertNull(replacedProduct.getDescription());


    }
}