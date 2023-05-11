package com.example.telegrambot.controller;

import com.example.telegrambot.model.Pet;
import com.example.telegrambot.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @Operation(summary = "Get the whole list of Pet", tags = "Pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of Pets",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pet.class))})
    })
    @GetMapping("")
    public ResponseEntity<List<Pet>> getPetAll() {
        return ResponseEntity.ok(petService.getPetAll());
    }

    @Operation(summary = "Create new Pet", tags = "Pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New Pet is created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pet.class))})
    })
    @PostMapping("")
    public ResponseEntity<Pet> createPet(@Parameter(description = "The pet that will be created")
                                         @RequestBody Pet pet) {
        return ok(petService.createPet(pet));
    }

    @Operation(summary = "Deleting an Pet by id", tags = "Pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet is deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pet.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Pet> deletePetId(@PathVariable Long id) {
        return petService.deletePetId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Updating Pet data", tags = "Pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet is updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pet.class))})
    })
    @PutMapping("")
    public Pet updatePet(@Parameter(description = "Information about the pet has been updated")
                         @RequestBody Pet pet) {
        return petService.updatePet(pet);
    }
}

