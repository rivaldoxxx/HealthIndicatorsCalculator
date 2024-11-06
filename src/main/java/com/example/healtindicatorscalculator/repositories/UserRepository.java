package com.example.healtindicatorscalculator.repositories;

import com.example.healtindicatorscalculator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

