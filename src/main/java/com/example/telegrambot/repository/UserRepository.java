package com.example.telegrambot.repository;

import com.example.telegrambot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * интерфейс User Repository
 */
@Repository
public interface UserRepository extends JpaRepository <User, Long> {

}
