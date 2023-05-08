package com.example.telegrambot.service;

import com.example.telegrambot.model.ReportPet;
import com.example.telegrambot.repository.ReportPetRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class ReportPetService {

    private final ReportPetRepository reportPetRepository;

    public ReportPetService(ReportPetRepository reportPetRepository) {
        this.reportPetRepository = reportPetRepository;
    }

    @Transactional
    public void add(@NotNull ReportPet report) {
       reportPetRepository.save(report);
    }

    public Optional<ReportPet> getById(Long id) {
        return reportPetRepository.findById(id);
    }

    public List<ReportPet> getAll() {
        return reportPetRepository.findAll();
    }

    @Transactional
    public void remove(@NotNull ReportPet report) {
        reportPetRepository.delete(report);
    }

    @Transactional
    public void remove(@NotNull Long id) {
        reportPetRepository.deleteById(id);
    }
}
