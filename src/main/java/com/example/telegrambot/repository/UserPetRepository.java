package com.example.telegrambot.repository;


import com.example.telegrambot.model.UserPet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPetRepository extends JpaRepository<UserPet, Long> {
    UserPet findUserPetByUserChatId(Long chatId);
}
