package com.example.healtindicatorscalculator.repositories;

import com.example.healtindicatorscalculator.model.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<Calculation, Long> {
}