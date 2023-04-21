package com.example.telegrambot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;
@Entity
public class UserCat {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int yearOfBirth;
    private String phone;
    private String mail;
    private String address;

    private Long chatId;

    private Status status;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id")
    private Cat cat;

    public UserCat() {
    }

    public UserCat(String name, String phone, Long chatId) {
        this.name = name;
        this.phone = phone;
        this.chatId = chatId;
    }

    public UserCat(Long id, String name, int yearOfBirth, String phone, String mail, String address, Long chatId) {
        this.id = id;
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
        this.chatId = chatId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getChatId() {
        return chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCat userCat = (UserCat) o;
        return yearOfBirth == userCat.yearOfBirth && Objects.equals(id, userCat.id) && Objects.equals(name, userCat.name) && Objects.equals(phone, userCat.phone) && Objects.equals(mail, userCat.mail) && Objects.equals(address, userCat.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, yearOfBirth, phone, mail, address);
    }
}
