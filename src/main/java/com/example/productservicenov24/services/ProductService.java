package com.example.productservicenov24.services;

import com.example.productservicenov24.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Product getProductById(Long id);
    List<Product> getAllProducts();
    Product replaceProduct(Long id, Product product);

    Product createProduct(Product product);
}
