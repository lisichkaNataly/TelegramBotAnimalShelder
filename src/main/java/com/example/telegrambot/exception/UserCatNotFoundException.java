package com.example.telegrambot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserCatNotFoundException extends RuntimeException{

    public UserCatNotFoundException() {
        super("UserCat is not found!");
    }

}
