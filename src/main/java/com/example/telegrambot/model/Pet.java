package com.example.telegrambot.model;


import com.example.telegrambot.enums.WhichPet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Enumerated(value = EnumType.STRING)
    private WhichPet whichPet;
    private Boolean availabilityAnimal;

    public Pet(String name, int age, WhichPet whichPet, String breed) {
    }

    public Pet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public WhichPet getWhichPet() {
        return whichPet;
    }

    public void setWhichPet(WhichPet whichPet) {
        this.whichPet = whichPet;
    }

    public Boolean getAvailabilityAnimal() {
        return availabilityAnimal;
    }

    public void setAvailabilityAnimal(Boolean availabilityAnimal) {
        this.availabilityAnimal = availabilityAnimal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) && Objects.equals(nickname, pet.nickname) && whichPet == pet.whichPet && Objects.equals(availabilityAnimal, pet.availabilityAnimal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, whichPet, availabilityAnimal);
    }
}
