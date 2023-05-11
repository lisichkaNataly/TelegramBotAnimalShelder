package com.example.telegrambot.service;

import com.example.telegrambot.exception.PetNotFoundException;
import com.example.telegrambot.model.Pet;
import com.example.telegrambot.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository animalRepository;

    public PetService(PetRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Pet> getPetAll() {
        return animalRepository.findAll();
    }

    public Pet getAnimalById(long id) {
        return animalRepository.findById(id).orElse(null);
    }

    public Pet createPet(Pet animal) {
        return animalRepository.save(animal);
    }

    public Optional<Pet> deletePetId(Long id) {
        Optional<Pet> deletedAnimal = animalRepository.findById(id);
        deletedAnimal.ifPresent(animal -> animalRepository.deleteById(id));
        return deletedAnimal;
    }

    public Pet updatePet(Pet animal) {
        Pet findAnimal = getAnimalById(animal.getId());
        if (findAnimal == null) {
            throw new PetNotFoundException();
        }
        return animalRepository.save(animal);
    }
}
