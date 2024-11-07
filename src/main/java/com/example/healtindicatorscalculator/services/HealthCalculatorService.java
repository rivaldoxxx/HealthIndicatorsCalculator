package com.example.healtindicatorscalculator.services;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class HealthCalculatorService {

    public double calculateBMI(double weight, double height) {
        return weight / Math.pow(height / 100, 2);
    }

    public double[] getHealthyWeightRange(double height) {
        double minHeightM = height / 100.0;
        double minWeight = 18.5 * minHeightM * minHeightM;
        double maxWeight = 25 * minHeightM * minHeightM;
        return new double[] {minWeight, maxWeight};
    }

    public double calculateBMIPRIME(double bmi) {
        return bmi / 25.0;
    }

    public double calculatePonderalIndex(double weight, double height) {
        return weight / Math.pow(height / 100.0, 3);
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


    // Nowa metoda, która oblicza zapotrzebowanie kaloryczne na podstawie poziomu aktywności
    public Map<String, Double> calculateCaloricNeeds(double bmr) {
        Map<String, Double> caloricNeeds = new LinkedHashMap<>();
        caloricNeeds.put("Sedentary: little or no exercise", bmr * 1.2);
        caloricNeeds.put("Exercise 1-3 times/week", bmr * 1.375);
        caloricNeeds.put("Exercise 4-5 times/week", bmr * 1.55);
        caloricNeeds.put("Daily exercise or intense exercise 3-4 times/week", bmr * 1.725);
        caloricNeeds.put("Intense exercise 6-7 times/week", bmr * 1.9);
        caloricNeeds.put("Very intense exercise daily, or physical job", bmr * 2.0);
        return caloricNeeds;
    }
}
