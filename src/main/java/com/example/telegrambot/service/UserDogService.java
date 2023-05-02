package com.example.telegrambot.service;

import com.example.telegrambot.exception.UserDogNotFoundException;
import com.example.telegrambot.model.UserDog;
import com.example.telegrambot.repository.UserDogRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.logging.Logger;

@Service
public class UserDogService {

    private final UserDogRepository repository;

    private final Logger logger = (Logger) LoggerFactory.getLogger(UserDogService.class);

    public UserDogService(UserDogRepository repository) {
        this.repository = repository;
    }

    /**
     * Method to get a userDog by id.
     * @param id
     * @return {@link UserDogRepository#findById(Object)}
     * @see UserDogService
     * @exception UserDogNotFoundException
     */
    public UserDog getById (Long id){
        logger.info("Was invoked method to get a UserDog by id={}");
        return this.repository.findById(id)
                .orElseThrow(UserDogNotFoundException::new);
    }


    /**
     * Method to add a userDog
     * @param userDog
     * @return {@link UserDogRepository#save(Object)}
     * @see UserDogService
     */
    public UserDog add (UserDog userDog){
        logger.info("Was invoked method to add a userDog");

        return this.repository.save(userDog);
    }

    /**
     * Method to update at userDog
     * @param userDog
     * @return {@link UserDogRepository#save(Object)}
     * @see UserDogService
     * @exception UserDogNotFoundException
     */
    public UserDog update (UserDog userDog){
        logger.info("Was invoked method to update a UserDog");
        if(userDog.getId() != null && getById(userDog.getId()) != null){
            return this.repository.save(userDog);
        }
        throw new UserDogNotFoundException();
    }


    /**
     * Method to get all UserDogs
     * @return {@link UserDogRepository#save(Object)}
     * @see UserDogService
     */
    public Collection<UserDog> getAll (){
        logger.info("Was invoked method to get all UserDogs");

        return  this.repository.findAll();
    }


    /**
     * Method to get UserDog at chatId
     * @param chatId
     * @return UserDogService
     */
    public Collection<UserDog> getByChatId(Long chatId) {
        logger.info("Was invoked method to remove a UserDog by chatId={}");
        return repository.findByChatId(chatId);
    }

    /**
     *  Method to remove a UserDog by id
     * @param id
     */
    public void removeById(Long id){
        logger.info("Was invoked method to remove a UserDog by id={}");

        this.repository.deleteById(id);
    }


}
