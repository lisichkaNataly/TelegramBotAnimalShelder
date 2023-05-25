package com.example.telegrambot.enums;

public enum ReplyButton {

    SEND_CONTACT_BUTTON("Отправить контакт");

    private final String text;

    ReplyButton(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
