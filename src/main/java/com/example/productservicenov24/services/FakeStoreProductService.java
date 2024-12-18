package com.example.productservicenov24.services;

import com.example.productservicenov24.dtos.FakeStoreProductDto;
import com.example.productservicenov24.models.Category;
import com.example.productservicenov24.models.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    RestTemplate restTemplate;
    RedisTemplate redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }
    @Override
    public Product getProductById(Long id) {
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCTS_" +  id.toString());
        if(product != null) {
            return product;
        }
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);

//        if(fakeStoreProductDto == null) {
//            throw new ProductNotFoundException("Product with Id "+ id +" not found");
//        }
       product =  convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
        redisTemplate.opsForHash().put("PRODUCTS",  "PRODUCTS_" + id, product);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        if (fakeStoreProductDto == null) {
            return null;
        }

        // Create the Product object and populate its fields

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription()); // Assuming description in Product maps to 'description' in FakeStoreProductDto
        product.setPrice(fakeStoreProductDto.getPrice());
        // Create the Category object using the category string from FakeStoreProductDto
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }

}
