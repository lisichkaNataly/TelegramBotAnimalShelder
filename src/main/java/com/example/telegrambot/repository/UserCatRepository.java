package com.example.telegrambot.repository;

import com.example.telegrambot.model.UserCat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserCatRepository extends JpaRepository <UserCat, Long> {

    Set<UserCat> findByChatId(Long chatId);

}
