package com.example.productservicenov24.services;

import com.example.productservicenov24.exceptions.ProductNotFoundException;
import com.example.productservicenov24.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product replaceProduct(Long id, Product product) throws ProductNotFoundException;

    Product createProduct(Product product);

    Product patchProdcut(Long id, Product product) throws ProductNotFoundException;

    void deleteProduct(Long id) throws ProductNotFoundException;
}
