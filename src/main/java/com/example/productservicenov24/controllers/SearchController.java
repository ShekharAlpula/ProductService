package com.example.productservicenov24.controllers;

import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.services.ProductService;
import com.example.productservicenov24.services.SearchService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    @GetMapping()
    public List<Product> search(@RequestParam String query, @RequestParam int pageNumber,
                                @RequestParam int pageSize, @RequestParam List<String> sortParams) {
        return searchService.getProducts(query, pageNumber, pageSize, sortParams);
    }
}
