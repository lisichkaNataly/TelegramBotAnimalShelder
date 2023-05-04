package com.example.telegrambot.repository;

import com.example.telegrambot.model.UserCat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCatRepository extends JpaRepository<UserCat,Long> {

    UserCat findUserCatByChatId(Long chatId);

    UserCat findUserCatById(Long id);
}
