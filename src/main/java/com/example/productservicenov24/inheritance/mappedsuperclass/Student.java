package com.example.productservicenov24.inheritance.mappedsuperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "msp_student")
public class Student extends User {
    String cource;
    String batch;
}
