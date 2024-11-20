package com.example.productservicenov24.controllers;

import com.example.productservicenov24.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import com.example.productservicenov24.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;
    public ProductController(@Qualifier("SelfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {

        return productService.getProductById(id);
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
}
