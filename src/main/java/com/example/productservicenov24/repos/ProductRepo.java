package com.example.productservicenov24.repos;

import com.example.productservicenov24.models.Product;
import com.example.productservicenov24.projections.ProductTitleAndDescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("select p.title as title , p.description as description from Product p where p.id = :id")
    ProductTitleAndDescription getProductTitleAndDescription(Long id);

    @Query(value = "select title, description from product where id = :id", nativeQuery=true)
    ProductTitleAndDescription getProductTitleAndDescriptionSQL(Long id);

    Page<Product> findByCategoryTitleContaining(String query, Pageable pageable);
}
