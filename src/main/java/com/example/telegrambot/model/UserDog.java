package com.example.telegrambot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity(name = "user_dog")
public class UserDog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String name;
    private String mail;
    private String phone;
    @OneToOne
    private Pet pet;

    @OneToMany(mappedBy = "userDog")
    private List<ReportPet> reportPets;
}
