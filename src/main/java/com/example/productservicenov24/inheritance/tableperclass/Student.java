package com.example.productservicenov24.inheritance.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tc_student")
public class Student extends User {
    String cource;
    String batch;
}
