package com.example.telegrambot.repository;

import com.example.telegrambot.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * интерфейс Cat Repository
 */
@Repository
public interface CatRepository extends JpaRepository <Cat, Long> {

}
