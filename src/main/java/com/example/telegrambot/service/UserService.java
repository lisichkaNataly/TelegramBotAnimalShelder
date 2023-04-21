package com.example.telegrambot.service;

import com.example.telegrambot.exception.CatNotFoundException;
import com.example.telegrambot.exception.DogNotFoundException;
import com.example.telegrambot.exception.UserNotFoundException;
import com.example.telegrambot.model.Cat;
import com.example.telegrambot.model.Dog;
import com.example.telegrambot.model.User;
import com.example.telegrambot.repository.DogRepository;
import com.example.telegrambot.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Класс UserService
 */
@Service
public class UserService {

    private final UserRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Method to get at user by id
     * @param id
     * @return {@link UserRepository#findById(Object)}
     * @see UserService
     * @exception UserNotFoundException
     */
    public User getById(Long id){
        logger.info("Was invoked method to get at user by id={}", id);
        return this.repository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    /**
     * Method to add at user
     * @param user
     * @return {@link UserRepository#findById(Object)}
     * @see UserService
     */
    public User add (User user){
        logger.info("Was invoked method to add at user");

        return this.repository.save(user);
    }


    /**
     * Method to update at user
     * @param user
     * @return {@link UserRepository#findById(Object)}
     * @see UserService
     * @exception UserNotFoundException
     */
    public User update (User user){
        logger.info("Was invoked method to update at user");
        if(user.getId() != null && getById(user.getId()) != null){
            return this.repository.save(user);
        }
        throw new UserNotFoundException();
    }

    /**
     * Method to get all users
     * @return {@link UserRepository#findById(Object)}
     * @see UserService
     */
    public Collection<User> getAll(){
        logger.info("Was invoked method to get all users");

        return this.repository.findAll();
    }

    /**
     * Method to remove at user by id
     * @param id
     */
    public void removeById(Long id){
        logger.info("Was invoked method to remove at user by id={}", id);

        this.repository.deleteById(id);
    }
}
