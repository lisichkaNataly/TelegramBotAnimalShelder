package com.example.telegrambot.repository;


import com.example.telegrambot.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ReportRepository extends JpaRepository<Report,Long> {

    Collection<Report> findReportsByUserId (Long id);
}
