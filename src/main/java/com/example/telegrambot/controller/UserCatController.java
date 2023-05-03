package com.example.telegrambot.controller;

import com.example.telegrambot.model.UserCat;
import com.example.telegrambot.service.UserCatService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("userCat")
public class UserCatController {
    private final UserCatService service;

    public UserCatController(UserCatService service) {
        this.service = service;
    }

    @Operation(summary = "Получение пользователя по id")
    @GetMapping("{id}")
    public UserCat getById(@PathVariable Long id){
        return service.getById(id);
    }

    @Operation(summary = "Добавление пользователя")
    @PostMapping
    public UserCat save (@RequestBody UserCat userCat){
        return service.add(userCat);
    }

    @Operation(summary = "Изменение данных пользователя")
    @PutMapping
    public UserCat update(@RequestBody UserCat userCat){
        return service.update(userCat);
    }

    @Operation(summary = "Удаление пользователя по id")
    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id){
        service.removeById(id);
    }


    @Operation(summary = "Просмотр всех пользователей",
            description = "Просмотр всех пользователей, либо определенного пользователя по chat_id")
    @GetMapping("all")
    public Collection <UserCat> getAll(@RequestParam(required = false) Long chatId){
        if(chatId != null) {
            return service.getByChatId(chatId);
        }
        return service.getAll();
    }


}
