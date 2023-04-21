package com.example.telegrambot.service;

import com.example.telegrambot.exception.DogNotFoundException;
import com.example.telegrambot.model.Dog;
import com.example.telegrambot.repository.DogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Класс DogService
 */
@Service
public class DogService {

    private final DogRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(DogService.class);

    public DogService(DogRepository repository) {
        this.repository = repository;
    }

    /**
     * Method to get a dog by id
     * @param id
     * @return {@link DogRepository#findById(Object)}
     * @see DogService
     * @exception DogNotFoundException
     */
    public Dog getById(Long id){
        logger.info("Was invoked method to get a dog by id={}", id);
        return this.repository.findById(id)
                .orElseThrow(DogNotFoundException::new);
    }

    /**
     * Method to add a dog
     * @param dog
     * @return {@link DogRepository#save(Object)}
     * @see DogService
     */
    public Dog add (Dog dog){
        logger.info("Was invoked method to add a dog");

        return this.repository.save(dog);
    }

    /**
     * Method to update a dog
     * @param dog
     * @return {@link DogRepository#save(Object)}
     * @see DogService
     * @exception DogNotFoundException
     */
    public Dog update (Dog dog){
        logger.info("Was invoked method to update a dog");
        if(dog.getId() != null && getById(dog.getId()) != null){
            return this.repository.save(dog);
        }
        throw new DogNotFoundException();
    }


    /**
     * Method to get all dogs
     * @return {@link DogRepository#findAll()}
     * @see DogService
     */
    public Collection <Dog> getAll(){
        logger.info("Was invoked method to get all dogs");

        return this.repository.findAll();
    }

    /**
     * Method to remove a cat by id
     * @param id
     */
    public void removeById(Long id){
        logger.info("Was invoked method to remove a dog by id={}", id);

        this.repository.deleteById(id);
    }
}
