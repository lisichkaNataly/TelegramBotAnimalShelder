package com.example.telegrambot.service;

import com.example.telegrambot.exception.CatNotFoundException;
import com.example.telegrambot.model.Cat;
import com.example.telegrambot.repository.CatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Класс CatService
 */
@Service
public class CatService {
    private final CatRepository repository;
    //private static final Logger logger = LoggerFactory.getLogger(CatService.class);

    public CatService(CatRepository repository) {
        this.repository = repository;
    }

    /**
     * Method to get a cat by id.
     * @param id
     * @return {@link CatRepository#findById(Object)}
     * @see CatService
     * @exception CatNotFoundException
     */
    public Cat getById (Long id){
        //logger.info("Was invoked method to get a cat by id={}", id);
        return this.repository.findById(id)
                .orElseThrow(CatNotFoundException::new);
    }


    /**
     * Method to add a cat
     * @param cat
     * @return {@link CatRepository#save(Object)}
     * @see CatService
     */
    public Cat add (Cat cat){
        //logger.info("Was invoked method to add a cat");

        return this.repository.save(cat);
    }

    /**
     * Method to update a cat
     * @param cat
     * @return {@link CatRepository#save(Object)}
     * @see CatService
     * @exception CatNotFoundException
     */
    public Cat update (Cat cat){
        //logger.info("Was invoked method to update a cat");
        if(cat.getId() != null && getById(cat.getId()) != null){
            return this.repository.save(cat);
        }
        throw new CatNotFoundException();
    }


    /**
     * Method to get all cats
     * @return {@link CatRepository#save(Object)}
     * @see CatService
     */
    public Collection <Cat> getAll (){
        //logger.info("Was invoked method to get all cats");

        return  this.repository.findAll();
    }

    /**
     *  Method to remove a cat by id
     * @param id
     */
    public void removeById(Long id){
        //logger.info("Was invoked method to remove a cat by id={}", id);

        this.repository.deleteById(id);
    }


}