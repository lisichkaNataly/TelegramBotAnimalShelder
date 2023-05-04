package com.example.telegrambot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class ReportPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String infoPet;
    private LocalDateTime dateTime;
    private boolean quality;

    @OneToOne
    private PhotoReport photoReport;

    @ManyToOne
    @JoinColumn(name = "user_dog_id")
    private UserDog userDog;

    @ManyToOne
    @JoinColumn(name = "user_cat_id")
    private UserCat userCat;

}
