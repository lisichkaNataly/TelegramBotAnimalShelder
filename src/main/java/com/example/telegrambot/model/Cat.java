package com.example.telegrambot.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class of cats
 */
@Entity
@Table(name="cat")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String breed;
    private String name;
    private int yearOfBirth;
    private String description;

    public Cat() {
    }

    public Cat(String breed, String name, int yearOfBirth, String description) {
        this.breed = breed;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.description = description;
    }

    public Cat(Long id, String breed, String name, int yearOfBirth, String description) {
        this.id = id;
        this.breed = breed;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return yearOfBirth == cat.yearOfBirth && Objects.equals(id, cat.id) && Objects.equals(breed, cat.breed) && Objects.equals(name, cat.name) && Objects.equals(description, cat.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, breed, name, yearOfBirth, description);
    }
}
