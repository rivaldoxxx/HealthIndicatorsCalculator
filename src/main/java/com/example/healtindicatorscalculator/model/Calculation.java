package com.example.healtindicatorscalculator.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

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

    // Pola dla dodatkowych informacji BMI
    private double healthyWeightMin;
    private double healthyWeightMax;
    private double bmiPrime;
    private double ponderalIndex;


    // Nowe pole dla zapotrzebowania kalorycznego
    @ElementCollection
    @CollectionTable(name = "caloric_needs", joinColumns = @JoinColumn(name = "calculation_id"))
    @MapKeyColumn(name = "activity_level")
    @Column(name = "calories")
    private Map<String, Double> caloricNeeds;


    // Konstruktor z pełną listą parametrów (do obliczeń BMI)
    public Calculation(User user, String calcType, double result, String advice,
                       double healthyWeightMin, double healthyWeightMax, double bmiPrime, double ponderalIndex) {
        this.user = user;
        this.calcType = calcType;
        this.result = result;
        this.advice = advice;
        this.healthyWeightMin = healthyWeightMin;
        this.healthyWeightMax = healthyWeightMax;
        this.bmiPrime = bmiPrime;
        this.ponderalIndex = ponderalIndex;
        this.calculatedAt = LocalDateTime.now();
    }

    // Konstruktor dla obliczeń BMR (bez dodatkowych informacji)
    public Calculation(User user, String calcType, double result, String advice) {
        this.user = user;
        this.calcType = calcType;
        this.result = result;
        this.advice = advice;
        this.calculatedAt = LocalDateTime.now();
    }

    // Domyślny konstruktor bez argumentów (wymagany przez Hibernate)
    public Calculation() {
    }
}
