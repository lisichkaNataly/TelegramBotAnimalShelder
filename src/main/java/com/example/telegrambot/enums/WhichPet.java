package com.example.telegrambot.enums;

public enum WhichPet {
    CAT("Кошка"),
    DOG("Собака");
    private final String description;

    WhichPet(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}