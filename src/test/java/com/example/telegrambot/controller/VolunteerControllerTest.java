package com.example.telegrambot.controller;

import com.example.telegrambot.model.Volunteer;
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
class VolunteerControllerTest {

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
    private VolunteerController volunteerController;

    @Test
    void getVolunteerAllTest() throws Exception {

        Long firstId = 1L;
        Long secondId = 2L;
        Long thirdId = 3L;

        Volunteer firstVolunteer = new Volunteer();
        firstVolunteer.setId(firstId);

        Volunteer secondVolunteer = new Volunteer();
        secondVolunteer.setId(secondId);

        Volunteer thirdVolunteer = new Volunteer();
        thirdVolunteer.setId(thirdId);

        when(volunteerRepositiory.findAll())
                .thenReturn(List.of(firstVolunteer, secondVolunteer, thirdVolunteer));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/volunteers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value(firstId))
                .andExpect(jsonPath("$[1].id").value(secondId))
                .andExpect(jsonPath("$[2].id").value(thirdId));
    }

    @Test
    void getVolunteerAllFreeTest() throws Exception {

        Long firstId = 1L;
        Long secondId = 2L;
        Long thirdId = 3L;

        Volunteer firstVolunteer = new Volunteer();
        firstVolunteer.setId(firstId);
        firstVolunteer.setAvailable(false);

        Volunteer secondVolunteer = new Volunteer();
        secondVolunteer.setId(secondId);
        secondVolunteer.setAvailable(true);

        Volunteer thirdVolunteer = new Volunteer();
        thirdVolunteer.setId(thirdId);
        thirdVolunteer.setAvailable(true);

        when(volunteerRepositiory.findVolunteersByAvailableTrue())
                .thenReturn(List.of(secondVolunteer, thirdVolunteer));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/volunteers/free"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(secondId))
                .andExpect(jsonPath("$[1].id").value(thirdId));
    }

    @Test
    void createVolunteerTest() throws Exception {

        Long id = 123L;
        String volunteerName = "Ace Ventura";
        Long chatId = 1234L;
        boolean available = true;

        Volunteer volunteer = new Volunteer();
        volunteer.setId(id);
        volunteer.setVolunteerName(volunteerName);
        volunteer.setChatId(chatId);
        volunteer.setAvailable(available);

        JSONObject requestObject = new JSONObject();
        requestObject.put("id", id);
        requestObject.put("volunteerName", volunteerName);
        requestObject.put("chatId", chatId);
        requestObject.put("available", available);

        when(volunteerRepositiory.save(eq(volunteer)))
                .thenReturn(volunteer);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/volunteers")
                        .content(requestObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.volunteerName").value(volunteerName))
                .andExpect(jsonPath("$.chatId").value(chatId))
                .andExpect(jsonPath("$.available").value(available));
    }

    @Test
    void deleteVolunteerIdTest() throws Exception {

        Long id = 123L;
        Long wrongId = 321L;
        Volunteer volunteer = new Volunteer();
        volunteer.setId(id);

        when(volunteerRepositiory.findById(eq(id))).thenReturn(Optional.of(volunteer));
        when(volunteerRepositiory.findById(eq(wrongId))).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/volunteers/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/volunteers/{id}", wrongId))
                .andExpect(status().isNotFound());

    }

    @Test
    void updateVolunteerTest() throws Exception {

        Long id = 1L;
        Long wrongId = 2L;
        String oldVolunteerName = "Ace Ventura";
        String newVolunteerName = "Doctor Aibolit";

        Volunteer oldVolunteer = new Volunteer();
        oldVolunteer.setId(id);
        oldVolunteer.setVolunteerName(oldVolunteerName);

        Volunteer newVolunteer = new Volunteer();
        newVolunteer.setId(id);
        newVolunteer.setVolunteerName(newVolunteerName);

        JSONObject requestObject = new JSONObject();
        requestObject.put("id", id);
        requestObject.put("volunteerName", newVolunteerName);

        when(volunteerRepositiory.findById(eq(id))).thenReturn(Optional.of(oldVolunteer));
        when(volunteerRepositiory.save(eq(newVolunteer))).thenReturn(newVolunteer);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/volunteers")
                        .content(requestObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.volunteerName").value(newVolunteerName));

        when(volunteerRepositiory.findById(eq(wrongId))).thenReturn(Optional.empty());
        requestObject.put("id", wrongId);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/volunteers")
                        .content(requestObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}