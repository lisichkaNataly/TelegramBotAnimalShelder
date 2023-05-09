package com.example.telegrambot.repository;


import com.example.telegrambot.model.ReportPet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ReportPetRepository extends JpaRepository<ReportPet,Long> {

    Collection<ReportPet> findReportsByUserId (Long id);
}
