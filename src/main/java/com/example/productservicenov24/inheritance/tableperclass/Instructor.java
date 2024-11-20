package com.example.productservicenov24.inheritance.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tc_instructor")
public class Instructor  extends User {
    String specialization;
}
