package com.example.productservicenov24.inheritance.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="msp_instructor")
public class Instructor  extends User{
    String specialization;
}
