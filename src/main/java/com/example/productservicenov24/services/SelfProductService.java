package com.example.productservicenov24.services;

import com.example.productservicenov24.exceptions.ProductNotFoundException;
import com.example.productservicenov24.models.Category;
import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.projections.ProductTitleAndDescription;
import com.example.productservicenov24.repos.CategoryRepo;
import com.example.productservicenov24.repos.ProductRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Product> optProduct = productRepo.findById(id);
        if(optProduct.isEmpty()) {
            //throw new ProductNotFoundException(id, "Product with Id " +id + " not found");
            return null;

        }
        Product product1 = optProduct.get();
        product1.setTitle(product.getTitle());
        product1.setPrice(product.getPrice());
        product1.setDescription(product.getDescription());
        return productRepo.save(product1);
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Category> categoryOPt = categoryRepo.findByTitle(product.getCategory().getTitle());
        if(categoryOPt.isEmpty()) {
            Category category = categoryRepo.save(product.getCategory());
            product.setCategory(category);
        }else{
            product.setCategory(categoryOPt.get());
        }

        return productRepo.save(product);
    }

    @Override
    public Product patchProdcut(Long id, Product product) throws ProductNotFoundException {
        Product storedProduct = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(100L, "Product with Id " +id + " not found"));

        if(product.getTitle() == null) {
            product.setTitle(storedProduct.getTitle());
        }
        if(product.getPrice() == null) {
            product.setPrice(storedProduct.getPrice());
        }
        if(product.getDescription() == null) {
            product.setDescription(storedProduct.getDescription());
        }

        return productRepo.save(product);
    }

    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException {
        Product storedProduct = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException(100L, "Product with Id " +id + " not found"));
        productRepo.deleteById(id);

    }

    @Override
    public List<Product> addMultipleProducts(List<Product> products) {
        List<Product>   productList = new ArrayList<>();
        for(Product product : products) {
            Product prod = createProduct(product);
            productList.add(prod);
        }
        return productList;
    }


}
