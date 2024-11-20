package com.example.productservicenov24.inheritance.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tc_mentor")
public class Mentor extends User {
    String company;
    int avgRating;
}
