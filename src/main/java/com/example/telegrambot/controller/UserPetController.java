package com.example.telegrambot.controller;

import com.example.telegrambot.model.UserPet;
import com.example.telegrambot.service.UserPetService;
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
@RequestMapping("/user-pet")
public class UserPetController {
    private final UserPetService userPetService;

    public UserPetController(UserPetService userPetService) {
        this.userPetService = userPetService;
    }


    @Operation(summary = "Create new User Pet", tags = "User Pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New User Pet is created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserPet.class))})
    })
    @PostMapping("")
    public ResponseEntity<UserPet> createUserPet(@Parameter(description = "Period to be created")
                                                 @RequestBody UserPet userPet) {
        return ok(userPetService.createUserPet(userPet));
    }
    @Operation(summary = "Change an existed User Pet", tags = "User Pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Pet has been changed",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserPet.class))})
    })
    @PutMapping("")
    public ResponseEntity<UserPet> updateUserPet(@Parameter(description = "User Pet to be updated")
                                                 @RequestBody UserPet userPet) {
        UserPet findUserPet = userPetService.editUserPet(userPet);
        if (findUserPet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(findUserPet);
    }
    @Operation(summary = "Get list of all User Pet", tags = "User Pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "There's the list",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserPet.class))})
    })
    @GetMapping("")
    public ResponseEntity<List<UserPet>> getUserPet() {
        List<UserPet> foundUserPet = userPetService.findAll();
        if (foundUserPet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundUserPet);
    }

    @Operation(summary = "Get true/false about User Pet existing by it's chatId", tags = "User Pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Answer",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserPet.class))})
    })
    @GetMapping("/exists-by-chat-id/{chat-id}")
    public Boolean isUserPetByChatIdExists(@PathVariable("chat-id") Long chatId) {
        return userPetService.findUserByChatId(chatId);
    }
}

