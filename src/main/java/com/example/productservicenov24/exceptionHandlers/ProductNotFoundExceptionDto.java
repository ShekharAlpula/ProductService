package com.example.productservicenov24.exceptionHandlers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundExceptionDto {
    private Long Id;
    private String message;

    public ProductNotFoundExceptionDto(Long id, String message) {
        this.Id = id;
        this.message = message;
    }
}
