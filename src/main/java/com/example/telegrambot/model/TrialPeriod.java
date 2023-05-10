package com.example.telegrambot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class TrialPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long volunteerId;
    private Long userId;
    private Long petId;


    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Long volunteerId) {
        this.volunteerId = volunteerId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TrialPeriod{" +
                "id=" + id +
                ", StartDate=" + startDate +
                ", EndDate=" + endDate +
                ", volunteerId=" + volunteerId +
                ", userId=" + userId +
                ", petId=" + petId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrialPeriod that = (TrialPeriod) o;
        return id.equals(that.id) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && volunteerId.equals(that.volunteerId) && userId.equals(that.userId) && petId.equals(that.petId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, volunteerId, userId, petId);
    }
}
