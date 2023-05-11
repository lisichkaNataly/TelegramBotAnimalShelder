package com.example.telegrambot.repository;


import com.example.telegrambot.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolunteerRepositiory extends JpaRepository<Volunteer, Long> {

  List<Volunteer> findVolunteersByAvailableTrue();
}
