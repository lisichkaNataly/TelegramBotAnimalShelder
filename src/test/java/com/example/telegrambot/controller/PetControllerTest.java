package com.example.telegrambot.controller;

import com.example.telegrambot.enums.WhichPet;
import com.example.telegrambot.model.Pet;
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

import java.util.List;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TrialPeriodRepository trialPeriodRepository;
    @MockBean
    private ReportRepository reportRepository;
    @MockBean
    private UserPetRepository userUserPetRepository;
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
    private PetController petController;

    @Test
    void getPetAllTest() throws Exception {

        Long firstId = 1L;
        Long secondId = 2L;
        Long thirdId = 3L;

        Pet firstPet = new Pet();
        firstPet.setId(firstId);

        Pet secondPet = new Pet();
        secondPet.setId(secondId);

        Pet thirdPet = new Pet();
        thirdPet.setId(thirdId);


        when(petRepository.findAll())
                .thenReturn(List.of(firstPet, secondPet, thirdPet));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value(firstId))
                .andExpect(jsonPath("$[1].id").value(secondId))
                .andExpect(jsonPath("$[2].id").value(thirdId));
    }

    @Test
    void createPetTest() throws Exception {

        Long id = 1L;
        WhichPet whichPet = WhichPet.CAT;
        String nickname = "mr.Kitty";
        Boolean availabilityPet = true;

        Pet pet = new Pet();
        pet.setId(id);
        pet.setWhichPet(whichPet);
        pet.setNickname(nickname);
        pet.setAvailabilityPet(availabilityPet);

        JSONObject requestObject = new JSONObject();
        requestObject.put("id", id);
        requestObject.put("whichPet", whichPet.name());
        requestObject.put("nickname", nickname);
        requestObject.put("availabilityPet", availabilityPet);

        when(petRepository.save(eq(pet)))
                .thenReturn(pet);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/pets")
                        .content(requestObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.whichPet").value(whichPet.name()))
                .andExpect(jsonPath("$.nickname").value(nickname))
                .andExpect(jsonPath("$.availabilityPet").value(availabilityPet));
    }

    @Test
    void deletePetIdTest() throws Exception {

        Long correctId = 123L;
        Long wrongId = 321L;
        Pet pet = new Pet();
        pet.setId(correctId);

        when(petRepository.findById(eq(correctId))).thenReturn(Optional.of(pet));
        when(petRepository.findById(eq(wrongId))).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/pets/{id}", correctId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(correctId));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/pets/{id}", wrongId))
                .andExpect(status().isNotFound());
    }

    @Test
    void updatePetTest() throws Exception {

        Long id = 1L;
        Long wrongId = 2L;
        WhichPet whichPet = WhichPet.CAT;
        WhichPet newWhichPet = WhichPet.DOG;
        String nickname = "mr.Kitty";
        String newNickname = "mr.Doggy";
        Boolean availabilityPet = true;

        Pet pet = new Pet();
        pet.setId(id);
        pet.setWhichPet(whichPet);
        pet.setNickname(nickname);
        pet.setAvailabilityPet(availabilityPet);

        Pet newPet = new Pet();
        newPet.setId(id);
        newPet.setWhichPet(whichPet);
        newPet.setNickname(newNickname);
        newPet.setAvailabilityPet(availabilityPet);

        JSONObject requestObject = new JSONObject();
        requestObject.put("id", id);
        requestObject.put("whichPet", whichPet.name());
        requestObject.put("nickname", newNickname);
        requestObject.put("availabilityPet", availabilityPet);

        when(petRepository.findById(eq(id))).thenReturn(Optional.of(pet));
        when(petRepository.save(eq(newPet))).thenReturn(newPet);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/pets")
                        .content(requestObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.whichPet").value(whichPet.name()))
                .andExpect(jsonPath("$.nickname").value(newNickname))
                .andExpect(jsonPath("$.availabilityPet").value(availabilityPet));

        when(petRepository.findById(eq(wrongId))).thenReturn(Optional.empty());
        requestObject.put("id", wrongId);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/pets")
                        .content(requestObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}