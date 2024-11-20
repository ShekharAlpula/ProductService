package com.example.productservicenov24.dtos;

import com.example.productservicenov24.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    Long id;
    String title;
    Double price;
    String category;
    String description;
}
