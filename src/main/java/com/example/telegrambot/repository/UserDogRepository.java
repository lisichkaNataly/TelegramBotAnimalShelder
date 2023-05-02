package com.example.telegrambot.repository;

import com.example.telegrambot.model.UserDog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserDogRepository extends JpaRepository <UserDog, Long> {

    Set<UserDog> findByChatId(Long chatId);
}
