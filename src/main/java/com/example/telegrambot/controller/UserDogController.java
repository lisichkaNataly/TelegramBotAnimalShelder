package com.example.telegrambot.controller;

import com.example.telegrambot.model.UserDog;
import com.example.telegrambot.service.UserDogService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("userDog")
public class UserDogController {
    private final UserDogService service;

    public UserDogController(UserDogService service) {
        this.service = service;
    }

    @Operation(summary = "Получение пользователя по id")
    @GetMapping("{id}")
    public UserDog getById(@PathVariable Long id){
        return service.getById(id);
    }

    @Operation(summary = "Добавление пользователя")
    @PostMapping
    public UserDog save (@RequestBody UserDog userDog){
        return service.add(userDog);
    }

    @Operation(summary = "Изменение данных пользователя")
    @PutMapping
    public UserDog update(@RequestBody UserDog userDog){
        return service.update(userDog);
    }

    @Operation(summary = "Просмотр всех пользователей",
            description = "Просмотр всех пользователей, либо определенного пользователя по chat_id")
    @GetMapping("all")
    public Collection <UserDog> getAll(@RequestParam(required = false) Long chatId){
        if(chatId != null){
            return service.getByChatId(chatId);
        }
        return service.getAll();
    }
}
