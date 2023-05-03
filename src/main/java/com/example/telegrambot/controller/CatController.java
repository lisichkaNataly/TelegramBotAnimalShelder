package com.example.telegrambot.controller;

import com.example.telegrambot.model.Cat;
import com.example.telegrambot.service.CatService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("cat")
public class CatController {
    private final CatService service;

    public CatController(CatService service) {
        this.service = service;
    }

    @Operation(summary = "Получение кота по id")
    @GetMapping("{id}")
    public Cat getById(@PathVariable Long id){
        return service.getById(id);
    }

    @Operation(summary = "Добавление кота")
    @PostMapping
    public Cat save(@RequestBody Cat cat){
        return service.add(cat);
    }

    @Operation(summary = "Изменение данных у кота")
    @PutMapping
    public Cat update(@RequestBody Cat cat){
        return service.update(cat);
    }

    @Operation(summary = "Удаление кота по id")
    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id){
        service.removeById(id);
    }

    @Operation(summary = "Просмотр всех котов")
    @GetMapping("all")
    public Collection <Cat> getAll(){
        return service.getAll();
    }
}
