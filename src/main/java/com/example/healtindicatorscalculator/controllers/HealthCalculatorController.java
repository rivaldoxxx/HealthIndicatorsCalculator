package com.example.healtindicatorscalculator.controllers;

import com.example.healtindicatorscalculator.model.Calculation;
import com.example.healtindicatorscalculator.services.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/health")
public class HealthCalculatorController {

    @Autowired
    private CalculationService calculationService;

    @PostMapping("/calculateBMI/{userId}")
    public String calculateBMI(@PathVariable Long userId, Model model) {
        Calculation calculation = calculationService.createBMIResult(userId);
        model.addAttribute("calculation", calculation);
        return "bmiResult";
    }

    @PostMapping("/calculateBMR/{userId}")
    public String calculateBMR(@PathVariable Long userId, Model model) {
        Calculation calculation = calculationService.createBMRResult(userId);
        model.addAttribute("calculation", calculation);
        return "bmrResult";
    }
}
