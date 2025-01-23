package com.example.productservicenov24.services;

import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.repos.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private ProductRepo productRepo;
    public SearchService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    public List<Product> getProducts(String query, int pageNumber, int pageSize, List<String> sortParams) {

        Sort sort = Sort.by(sortParams.get(0));
        for(int i=1;i<sortParams.size();i++)
            sort = sort.and(Sort.by(sortParams.get(i)));

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> page = productRepo.findByCategoryTitleContaining(query, pageable);

        return page.getContent();
    }
}
