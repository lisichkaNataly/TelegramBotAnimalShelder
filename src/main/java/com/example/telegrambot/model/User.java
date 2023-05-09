package com.example.telegrambot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long userChatId;

    private String fullName;

    private String contacts;

    public User() {
    }

    public User(Long id, long userChatId, String fullName, String contacts) {
        this.id = id;
        this.userChatId = userChatId;
        this.fullName = fullName;
        this.contacts = contacts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserChatId() {
        return userChatId;
    }

    public void setUserChatId(long userChatId) {
        this.userChatId = userChatId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userChatId == user.userChatId && Objects.equals(id, user.id) && Objects.equals(fullName, user.fullName) && Objects.equals(contacts, user.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userChatId, fullName, contacts);
    }
}
