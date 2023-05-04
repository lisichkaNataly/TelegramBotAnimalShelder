package com.example.telegrambot.repository;

import com.example.telegrambot.model.PhotoReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoReportRepository extends JpaRepository<PhotoReport,Long> {

    Optional<PhotoReport> findPhotoReportById(Long petId);
}