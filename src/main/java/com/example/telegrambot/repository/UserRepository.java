package com.example.telegrambot.repository;

import com.example.telegrambot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByChatId(Long chatId);

}