package com.example.telegrambot.service;

import com.example.telegrambot.exception.UserNotFoundException;
import com.example.telegrambot.model.User;
import com.example.telegrambot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findUser(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
    }

    public Collection<User> getAllOrders() {
        return userRepository.findAll();
    }


    public Collection<User> findUserByShelter(String shelter) {
        return userRepository.findUserByShelter(shelter);
    }


    public User findUserByChatId(Long chatId) {
        return userRepository.findUserByChatId(chatId);
    }
}
