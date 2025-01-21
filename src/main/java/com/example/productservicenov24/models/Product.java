package com.example.productservicenov24.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Product extends BaseModel implements Serializable {
    Double price;
    String description;
    @ManyToOne(cascade = CascadeType.PERSIST)
    Category category;
}
