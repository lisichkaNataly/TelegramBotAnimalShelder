package com.example.telegrambot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserDogNotFoundException extends RuntimeException{
    public UserDogNotFoundException() {
        super("UserDog is not found!");
    }
}
