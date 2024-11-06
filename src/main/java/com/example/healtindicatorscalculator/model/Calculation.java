package com.example.healtindicatorscalculator.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Entity
@Table(name = "Calculations")
@Getter
@Setter
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calcId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String calcType; // BMI or BMR
    private double result;
    private String advice;
    private LocalDateTime calculatedAt = LocalDateTime.now();

    public Calculation(User user, String calcType, double result, String advice) {
        this.user = user;
        this.calcType = calcType;
        this.result = result;
        this.advice = advice;
        this.calculatedAt = LocalDateTime.now();
    }

    public Calculation() {

    }


}
