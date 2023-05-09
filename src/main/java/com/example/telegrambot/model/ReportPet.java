package com.example.telegrambot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class ReportPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate reportDate;

    private String photo;

    private String diet;

    private String wellbeing;

    private String behaviour;

    private Long userId;

    public ReportPet() {
    }

    public ReportPet(Long id, LocalDate reportDate, String photo, String diet, String wellbeing, String behaviour, Long userId) {
        this.id = id;
        this.reportDate = reportDate;
        this.photo = photo;
        this.diet = diet;
        this.wellbeing = wellbeing;
        this.behaviour = behaviour;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getWellbeing() {
        return wellbeing;
    }

    public void setWellbeing(String wellbeing) {
        this.wellbeing = wellbeing;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ReportPet{" +
                "id=" + id +
                ", reportDate=" + reportDate +
                ", photo='" + photo + '\'' +
                ", diet='" + diet + '\'' +
                ", wellbeing='" + wellbeing + '\'' +
                ", behaviour='" + behaviour + '\'' +
                ", userId=" + userId +
                '}';
    }
}
