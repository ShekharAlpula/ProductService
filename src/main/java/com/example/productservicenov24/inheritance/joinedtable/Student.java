package com.example.productservicenov24.inheritance.joinedtable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_student")
public class Student extends User {
    String cource;
    String batch;
}
