package com.example.telegrambot.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class of dogs
 */
@Entity
@Table(name="dog")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String breed;
    private String name;
    private int yearOfBirth;
    private String description;

    public Dog(String breed, String name, int yearOfBirth, String description) {
        this.breed = breed;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.description = description;
    }

    public Dog(Long id, String breed, String name, int yearOfBirth, String description) {
        this.id = id;
        this.breed = breed;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.description = description;
    }

    public Dog() {
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
        Dog dog = (Dog) o;
        return yearOfBirth == dog.yearOfBirth && Objects.equals(id, dog.id) && Objects.equals(breed, dog.breed) && Objects.equals(name, dog.name) && Objects.equals(description, dog.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, breed, name, yearOfBirth, description);
    }
}
