package com.example.telegrambot.controller;

//import com.example.telegrambot.model.Dog;
//import com.example.telegrambot.service.DogService;
//import io.swagger.v3.oas.annotations.Operation;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Collection;
//@RestController
//@RequestMapping("dog")
//public class DogController {
//    private final DogService service;
//
//    public DogController(DogService service) {
//        this.service = service;
//    }
//
//    @Operation(summary = "Получение собаки по id")
//    @GetMapping("{id}")
//    public Dog getById(@PathVariable Long id){
//        return service.getById(id);
//    }
//
//    @Operation(summary = "Добавление собаки")
//    @PostMapping
//    public Dog save(@RequestBody Dog dog){
//      return service.add(dog);
//    }
//
//    @Operation(summary = "Изменение данных у собаки")
//    @PutMapping
//    public Dog update(@RequestBody Dog dog){
//        return service.update(dog);
//    }
//
//    @Operation(summary = "Удаление собаки по id")
//    @DeleteMapping("{id}")
//    public void remove(@PathVariable Long id){
//        service.removeById(id);
//    }
//
//    @Operation(summary = "Просмотр всех собак")
//    @GetMapping("all")
//    public Collection <Dog> getAll(){
//        return service.getAll();
//    }
//}
