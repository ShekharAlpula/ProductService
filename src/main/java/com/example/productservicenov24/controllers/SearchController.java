package com.example.productservicenov24.controllers;

import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private ProductService productService;
    public SearchController(@Qualifier("FakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }
    @GetMapping()
    public List<Product> search(@RequestParam Integer pageNum, @RequestParam Integer pazeSize) {
        return productService.getAllProductsPagination(pageNum, pazeSize);
    }
}
