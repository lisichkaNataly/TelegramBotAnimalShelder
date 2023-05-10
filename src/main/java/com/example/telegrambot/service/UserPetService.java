package com.example.telegrambot.service;

import com.example.telegrambot.model.UserPet;
import com.example.telegrambot.repository.UserPetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPetService {
    UserPetRepository userCustodianRepository;

    public UserPetService(UserPetRepository userCustodianRepository) {
        this.userCustodianRepository = userCustodianRepository;
    }

    public UserPet createUserPet(UserPet custodian) {
        return userCustodianRepository.save(custodian);
    }

    public UserPet editUserPet(UserPet custodian) {
        return userCustodianRepository.findById(custodian.getId())
                .map(c -> userCustodianRepository.save(custodian))
                .orElse(null);
    }

    public List<UserPet> findAll() {
        return userCustodianRepository.findAll();
    }

    /**
     * Проверка на существование пользователя в БД
     */
    public Boolean findUserByChatId(Long chatId) {
        UserPet userCustodian = userCustodianRepository.findUserPetByUserChatId(chatId);
        if (userCustodian != null) {
            return userCustodian.getUserChatId() == chatId;
        } return false;
    }

    /**
     * Получение ID пользователя в базе по Telegram ID
     * @param chatId - Telegram ID пользоваеля
     * @return Long
     */
    public UserPet findUserCustodianByChatId(Long chatId) {
        return userCustodianRepository.findUserPetByUserChatId(chatId);
    }

    /**
     * Ищет chatId по userId
     * @param userId - Идентификатор пользователя в БД
     * @return Возвращает Optional со значением Телеграм-идентификатора пользователя
     */
    public Optional<Long> findChatIdByUserId(Long userId) {
        return userCustodianRepository.findById(userId)
                .map(UserPet::getUserChatId);
    }

    public Optional<UserPet> findExistingUser() {
        return Optional.ofNullable(userCustodianRepository.findAll().get(0));
    }

    public void deleteUserCustodian(Long id) {
        userCustodianRepository.deleteById(id);
    }
}
