package com.example.telegrambot.model;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class ReportData {

    private Long id;
    private Long chatId;
    private String ration;
    private String health;

    private String habits;
    private Long days;
    private String filePath;

    private Long fileSize;
    private byte[] data;
    private String caption;

    private Date lastMessage;
    private Long lastMessageMs;

    public ReportData() {

    }

    public ReportData(Long chatId, byte[] data) {
        this.chatId = chatId;
        this.data = data;
    }

    public ReportData(String ration, String health, String habits) {
        this.ration = ration;
        this.health = health;
        this.habits = habits;
    }

    public ReportData(Long chatId, String ration, String health, String habits, byte[] data) {
        this.chatId = chatId;
        this.ration = ration;
        this.health = health;
        this.habits = habits;
        this.data = data;
    }

    public Long getLastMessageMs() {
        return lastMessageMs;
    }

    public void setLastMessageMs(Long lastMessageMs) {
        this.lastMessageMs = lastMessageMs;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getRation() {
        return ration;
    }

    public void setRation(String ration) {
        this.ration = ration;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getHabits() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits = habits;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportData that = (ReportData) o;
        return id == that.id && days == that.days && Objects.equals(ration, that.ration) && Objects.equals(health, that.health) && Objects.equals(habits, that.habits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ration, health, habits, days);
    }

    @Override
    public String toString() {
        return "ReportData{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", ration='" + ration + '\'' +
                ", health='" + health + '\'' +
                ", habits='" + habits + '\'' +
                ", days=" + days +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", data=" + Arrays.toString(data) +
                ", caption='" + caption + '\'' +
                ", lastMessage=" + lastMessage +
                ", lastMessageMs=" + lastMessageMs +
                '}';
    }
}
