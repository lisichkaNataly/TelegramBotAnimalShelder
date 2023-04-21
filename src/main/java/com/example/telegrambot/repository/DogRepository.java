package com.example.telegrambot.repository;

import com.example.telegrambot.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * интерфейс Dog Repository
 */
@Repository
public interface DogRepository extends JpaRepository <Dog, Long> {

}
