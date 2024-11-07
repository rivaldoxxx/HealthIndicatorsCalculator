package com.example.healtindicatorscalculator.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity
@Table(name = "_user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private double weight;
    private double height;
    private int age;
    private char gender;
    private LocalDateTime createdAt = LocalDateTime.now();

}
