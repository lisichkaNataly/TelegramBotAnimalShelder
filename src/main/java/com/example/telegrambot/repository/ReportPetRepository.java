package com.example.telegrambot.repository;


import com.example.telegrambot.model.ReportPet;
import com.example.telegrambot.model.UserCat;
import com.example.telegrambot.model.UserDog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ReportPetRepository extends JpaRepository<ReportPet,Long> {

    ReportPet findReportPetById(Long id);

    Collection<ReportPet> findReportPetByUserCat(UserCat userCat);

    Collection<ReportPet> findReportPetByUserDog(UserDog userDog);
}
