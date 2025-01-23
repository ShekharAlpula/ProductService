package com.example.productservicenov24.services;

import com.example.productservicenov24.exceptions.ProductNotFoundException;
import com.example.productservicenov24.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getAllProductsPagination(Integer pageNo, Integer pageSize);
    Product replaceProduct(Long id, Product product);

    Product createProduct(Product product);

    List<Product> addMultipleProducts(List<Product> products);

    Product patchProdcut(Long id, Product product) throws ProductNotFoundException;

    void deleteProduct(Long id) throws ProductNotFoundException;
}
