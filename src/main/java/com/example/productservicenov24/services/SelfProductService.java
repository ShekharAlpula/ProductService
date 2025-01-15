package com.example.productservicenov24.services;

import com.example.productservicenov24.models.Category;
import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.projections.ProductTitleAndDescription;
import com.example.productservicenov24.repos.CategoryRepo;
import com.example.productservicenov24.repos.ProductRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("SelfProductService")
@Primary
public class SelfProductService implements ProductService {
    private  ProductRepo productRepo;
    private CategoryRepo categoryRepo;
    public SelfProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }
    @Override
    public Product getProductById(Long id) {
        //Sample Projections simulations - where we want to restrict data to be  sent out
        ProductTitleAndDescription productTitleAndDescription = productRepo.getProductTitleAndDescription(id);
        System.out.println("Title: " + productTitleAndDescription.getTitle() +
                ", Description " +productTitleAndDescription.getDescription());

        productTitleAndDescription = productRepo.getProductTitleAndDescriptionSQL(id);
        System.out.println("With Native Query: Title: " + productTitleAndDescription.getTitle() +
                ", Description " +productTitleAndDescription.getDescription());
        return productRepo.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public List<Product> getAllProductsPagination(Integer pageNo, Integer pageSize) {
        return List.of();
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        Category category = product.getCategory();
        if(category.getId() == null) {
            Category savedCategory = categoryRepo.save(category);
            product.setCategory(savedCategory);
        }else{
            //category exists
        }
        return productRepo.save(product);
    }
}
