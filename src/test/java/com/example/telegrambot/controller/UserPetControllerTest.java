package com.example.telegrambot.controller;

import com.example.telegrambot.repository.*;
import com.example.telegrambot.service.*;
import com.pengrad.telegrambot.TelegramBot;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.example.telegrambot.model.UserPet;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class UserPetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TrialPeriodRepository trialPeriodRepository;
    @MockBean
    private ReportRepository reportRepository;
    @MockBean
    private UserPetRepository userPetRepository;
    @MockBean
    private PetRepository petRepository;
    @MockBean
    private VolunteerRepositiory volunteerRepositiory;
    @MockBean
    private TelegramBot telegramBot;
    @SpyBean
    private TrialPeriodService trialPeriodService;
    @SpyBean
    private ReportService reportService;
    @SpyBean
    private PetService petService;
    @SpyBean
    private UserPetService userPetService;
    @SpyBean
    private VolunteerService volunteerService;
    @InjectMocks
    private UserPetController userPetController;

    @Test
    void createUserPetTest() throws Exception {

        Long id = 123L;
        Long userChatId = 1234L;
        String fullName = "Ivan Ivanov";
        String contacts = "+7966966966";

        UserPet userPet = new UserPet();
        userPet.setId(id);
        userPet.setUserChatId(userChatId);
        userPet.setFullName(fullName);
        userPet.setContacts(contacts);

        JSONObject requestObject = new JSONObject();
        requestObject.put("id", id);
        requestObject.put("userChatId", userChatId);
        requestObject.put("fullName", fullName);
        requestObject.put("contacts", contacts);

        when(userPetRepository.save(eq(userPet)))
                .thenReturn(userPet);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user-pet")
                        .content(requestObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.userChatId").value(userChatId))
                .andExpect(jsonPath("$.fullName").value(fullName))
                .andExpect(jsonPath("$.contacts").value(contacts));
    }

    @Test
    void updateUserPetTest() throws Exception {

        Long id = 123L;
        Long wrongId = 231L;
        String oldName = "Ivan Ivanov";
        String newName = "Petr Petrov";

        UserPet oldUserPet = new UserPet();
        oldUserPet.setId(id);
        oldUserPet.setFullName(oldName);

        UserPet newUserPet = new UserPet();
        newUserPet.setId(id);
        newUserPet.setFullName(newName);

        JSONObject requestObject = new JSONObject();
        requestObject.put("id", id);
        requestObject.put("fullName", newName);

        when(userPetRepository.findById(eq(id))).thenReturn(Optional.of(oldUserPet));
        when(userPetRepository.findById(eq(wrongId))).thenReturn(Optional.empty());
        when(userPetRepository.save(eq(newUserPet))).thenReturn(newUserPet);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/user-pet")
                        .content(requestObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.fullName").value(newName));

        requestObject.put("id", wrongId);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/user-pet")
                        .content(requestObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getUserPetTest() throws Exception {

        Long firstId = 1L;
        Long secondId = 2L;
        Long thirdId = 3L;

        UserPet firstUserPet = new UserPet();
        firstUserPet.setId(firstId);

        UserPet secondUserPet = new UserPet();
        secondUserPet.setId(secondId);

        UserPet thirdUserPet = new UserPet();
        thirdUserPet.setId(thirdId);

        when(userPetRepository.findAll())
                .thenReturn(List.of(firstUserPet, secondUserPet, thirdUserPet));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user-pet"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value(firstId))
                .andExpect(jsonPath("$[1].id").value(secondId))
                .andExpect(jsonPath("$[2].id").value(thirdId));
    }

    @Test
    void isUserPetByChatIdExistsTest() throws Exception {

        Long id = 1L;
        Long chatID = 123L;
        Long wrongChatId = 321L;
        UserPet userPet = new UserPet();
        userPet.setId(id);
        userPet.setUserChatId(chatID);

        when(userPetRepository.findUserPetByUserChatId(eq(chatID))).thenReturn(userPet);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user-pet/exists-by-chat-id/{chat-id}", chatID))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        when(userPetRepository.findUserPetByUserChatId(eq(wrongChatId))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user-pet/exists-by-chat-id/{chat-id}", wrongChatId))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}