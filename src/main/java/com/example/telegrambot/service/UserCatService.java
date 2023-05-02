package com.example.telegrambot.service;

import com.example.telegrambot.exception.CatNotFoundException;
import com.example.telegrambot.exception.UserCatNotFoundException;
import com.example.telegrambot.model.UserCat;
import com.example.telegrambot.repository.UserCatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class UserCatService {
    private final UserCatRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(UserCatService.class);

    public UserCatService(UserCatRepository repository) {
        this.repository = repository;
    }


    /**
     * Method to get a userCat by id.
     * @param id
     * @return {@link UserCatRepository#findById(Object)}
     * @see UserCatService
     * @exception UserCatNotFoundException
     */
    public UserCat getById (Long id){
        logger.info("Was invoked method to get a UserCat by id={}", id);
        return this.repository.findById(id)
                .orElseThrow(UserCatNotFoundException::new);
    }


    /**
     * Method to add a cat
     * @param userCat
     * @return {@link UserCatRepository#save(Object)}
     * @see UserCatService
     */
    public UserCat add (UserCat userCat){
        logger.info("Was invoked method to add a UserCat");

        return this.repository.save(userCat);
    }

    /**
     * Method to update a
     * @param userCat
     * @return {@link UserCatRepository#save(Object)}
     * @see UserCatService
     * @exception CatNotFoundException
     */
    public UserCat update (UserCat userCat){
        logger.info("Was invoked method to update a UserCat");
        if(userCat.getId() != null && getById(userCat.getId()) != null){
            return this.repository.save(userCat);
        }
        throw new CatNotFoundException();
    }


    /**
     * Method to get all UserCats
     * @return {@link UserCatRepository#save(Object)}
     * @see UserCatService
     */
    public Collection<UserCat> getAll (){
        logger.info("Was invoked method to get all UserCats");

        return  this.repository.findAll();
    }


    /**
     * Method to get UserCat at chatId
     * @param chatId
     * @return UserCatService
     */
    public Collection<UserCat> getByChatId(Long chatId) {
        logger.info("Was invoked method to remove a UserCat by chatId={}", chatId);
        return repository.findByChatId(chatId);
    }

    /**
     *  Method to remove a UserCat by id
     * @param id
     */
    public void removeById(Long id){
        logger.info("Was invoked method to remove a UserCat by id={}", id);

        this.repository.deleteById(id);
    }

}
