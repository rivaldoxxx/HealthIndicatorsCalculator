package com.example.healtindicatorscalculator.services;

import com.example.healtindicatorscalculator.model.Calculation;
import com.example.healtindicatorscalculator.model.User;
import com.example.healtindicatorscalculator.repositories.CalculationRepository;
import com.example.healtindicatorscalculator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class CalculationService {

    @Autowired
    private CalculationRepository calculationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HealthCalculatorService healthCalculatorService;

    @Transactional
    public Calculation createBMIResult(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        double bmi = healthCalculatorService.calculateBMI(user.getWeight(), user.getHeight());
        String advice = healthCalculatorService.getBMIAdvice(bmi);

        double[] healthyWeightRange = healthCalculatorService.getHealthyWeightRange(user.getHeight());
        double bmiPrime = healthCalculatorService.calculateBMIPRIME(bmi);
        double ponderalIndex = healthCalculatorService.calculatePonderalIndex(user.getWeight(), user.getHeight());

        Calculation calculation = new Calculation(user, "BMI", bmi, advice,
                healthyWeightRange[0], healthyWeightRange[1], bmiPrime, ponderalIndex);
        return calculationRepository.save(calculation);
    }


    @Transactional
    public Calculation createBMRResult(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        double bmr = healthCalculatorService.calculateBMR(user.getWeight(), user.getHeight(), user.getAge(), user.getGender());
        String advice = healthCalculatorService.getBMRAdvice(bmr);

        // Obliczanie dziennego zapotrzebowania kalorycznego w zależności od poziomu aktywności
        Map<String, Double> caloricNeeds = healthCalculatorService.calculateCaloricNeeds(bmr);

        // Tworzenie obiektu Calculation i zapisanie w bazie (przykładowo)
        Calculation calculation = new Calculation(user, "BMR", bmr, advice);
        calculation.setCaloricNeeds(caloricNeeds); // Upewnij się, że Calculation ma pole caloricNeeds
        return calculationRepository.save(calculation);
    }

}
