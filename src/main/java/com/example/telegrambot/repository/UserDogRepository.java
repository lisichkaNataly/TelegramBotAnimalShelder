package com.example.telegrambot.repository;

import com.example.telegrambot.model.UserDog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDogRepository extends JpaRepository<UserDog,Long> {

    UserDog findUserDogByChatId(Long chatId);

    UserDog findUserDogById(Long id);
}
