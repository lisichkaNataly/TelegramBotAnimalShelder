package com.example.telegrambot.model;


import com.example.telegrambot.enums.WhichPet;
import javax.persistence.*;
import java.util.Objects;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private WhichPet whichPet;

    private String nickname;

    private Boolean availabilityPet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WhichPet getWhichPet() {
        return whichPet;
    }

    public void setWhichPet(WhichPet animalType) {
        this.whichPet = animalType;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getAvailabilityPet() {
        return availabilityPet;
    }

    public void setAvailabilityPet(Boolean availabilityPet) {
        this.availabilityPet = availabilityPet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet animal = (Pet) o;
        return id.equals(animal.id) && Objects.equals(whichPet, animal.whichPet) && Objects.equals(nickname, animal.nickname) && Objects.equals(availabilityPet, animal.availabilityPet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, whichPet, nickname, availabilityPet);
    }
}
