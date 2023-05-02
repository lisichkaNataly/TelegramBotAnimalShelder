package com.example.telegrambot.repository;

import com.example.telegrambot.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * интерфейс Volunteer Repository
 */
@Repository
public interface VolunteerRepository extends JpaRepository <Volunteer, Long> {

}
