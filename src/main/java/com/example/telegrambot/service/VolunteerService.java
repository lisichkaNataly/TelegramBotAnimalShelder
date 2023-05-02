package com.example.telegrambot.service;

import com.example.telegrambot.exception.VolunteerNotFoundException;
import com.example.telegrambot.model.Volunteer;
import com.example.telegrambot.repository.VolunteerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Класс VolunteerService
 */
@Service
public class VolunteerService {
    private VolunteerRepository repository;
//    private static final Logger logger = LoggerFactory.getLogger(VolunteerService.class);

    public VolunteerService(VolunteerRepository repository) {
        this.repository = repository;
    }

    /**
     * Method to get a volunteer by id
     * @param id
     * @return {@link VolunteerRepository#save(Object)}
     * @see VolunteerService
     * @exception VolunteerNotFoundException
     */
    public Volunteer getById(Long id){
//        logger.info("Was invoked method to get a volunteer by id={}", id);
        return this.repository.findById(id)
                .orElseThrow(VolunteerNotFoundException::new);
    }

    /**
     * Method to add a volunteer
     * @param volunteer
     * @return {@link VolunteerRepository#save(Object)}
     * @see VolunteerService
     */
    public Volunteer add (Volunteer volunteer){
//        logger.info("Was invoked method to as a volunteer");

        return repository.save(volunteer);
    }

    /**
     * Method to update a volunteer
     * @param volunteer
     * @return {@link VolunteerRepository#save(Object)}
     * @exception VolunteerNotFoundException
     */
    public Volunteer update (Volunteer volunteer){
//        logger.info("Was invoked method to update a volunteer");
        if(volunteer.getId() != null && getById(volunteer.getId()) != null){
            return this.repository.save(volunteer);
        }
        throw new VolunteerNotFoundException();
    }

    /**
     * Method to get all Volunteer
     * @return {@link VolunteerRepository#save(Object)}
     * @see  VolunteerService
     */
    public Collection <Volunteer> getAll (){
//        logger.info("Was invoked method to get all volunteers");
        return repository.findAll();
    }

    /**
     * Method to remove a volunteer by id
     * @param id
     */
    public void removeById(Long id){
//        logger.info("Was invoked method to remove a volunteer by id={}", id);

        this.repository.deleteById(id);
    }

}
