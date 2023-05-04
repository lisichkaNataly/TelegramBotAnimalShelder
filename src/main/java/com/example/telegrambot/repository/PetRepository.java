package com.example.telegrambot.repository;

import com.example.telegrambot.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet,Long> {
}
