package com.example.healtindicatorscalculator.services;

import org.springframework.stereotype.Service;

@Service
public class HealthCalculatorService {

    public double calculateBMI(double weight, double height) {
        return weight / Math.pow(height / 100, 2);
    }

    public double calculateBMR(double weight, double height, int age, char gender) {
        if (gender == 'M') {
            return 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            return 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }
    }

    public String getBMIAdvice(double bmi) {
        if (bmi < 18.5) return "Underweight - consider gaining weight for optimal health.";
        if (bmi < 24.9) return "Normal weight - maintain your current lifestyle.";
        if (bmi < 29.9) return "Overweight - consider a balanced diet and regular exercise.";
        return "Obese - consult a healthcare provider for advice.";
    }

    public String getBMRAdvice(double bmr) {
        return "Based on your BMR, maintain a balanced diet and regular exercise.";
    }
}
