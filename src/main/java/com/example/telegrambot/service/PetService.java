package com.example.telegrambot.service;

import com.example.telegrambot.model.Pet;
import com.example.telegrambot.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PetService {
    private PetRepository petRepository;
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet addPet(Pet pet){
        return petRepository.save(pet);
    }


    public Pet findPet(Long id){
        return petRepository.findById(id).orElse(null);
    }

    public Collection<Pet> getAllPet(){
        return petRepository.findAll();
    }
}
