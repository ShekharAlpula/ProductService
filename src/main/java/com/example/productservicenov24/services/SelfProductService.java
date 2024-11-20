package com.example.productservicenov24.services;

import com.example.productservicenov24.exceptions.ProductNotFoundException;
import com.example.productservicenov24.models.Category;
import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.projections.ProductTitleAndDescription;
import com.example.productservicenov24.repos.CategoryRepo;
import com.example.productservicenov24.repos.ProductRepo;
import jakarta.persistence.Id;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service("SelfProductService")
@Primary
public class SelfProductService implements ProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    public SelfProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        //Sample Projections simulations - where we want to restrict data to be  sent out
        ProductTitleAndDescription productTitleAndDescription = productRepo.getProductTitleAndDescription(id);
        System.out.println("Title: " + productTitleAndDescription.getTitle() +
                ", Description " +productTitleAndDescription.getDescription());

        productTitleAndDescription = productRepo.getProductTitleAndDescriptionSQL(id);
        System.out.println("With Native Query: Title: " + productTitleAndDescription.getTitle() +
                ", Description " +productTitleAndDescription.getDescription());
        Optional<Product> savedProduct = productRepo.findById(id);
        if(savedProduct.isEmpty()){
            throw new ProductNotFoundException(id, "Product with Id " +id +" is not found");
        }
        return productRepo.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {
       return productRepo.findAll();
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException{
        Optional<Product> optProduct = productRepo.findById(id);
        if(optProduct.isEmpty()) {
            throw new ProductNotFoundException(id, "Product with Id " +id + " not found");

        }
        Product product1 = optProduct.get();
        product1.setTitle(product.getTitle());
        product1.setPrice(product.getPrice());
        product1.setDescription(product.getDescription());
        return productRepo.save(product1);
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
}
