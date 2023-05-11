package com.example.telegrambot.service;

import com.example.telegrambot.enums.Buttons;
import com.example.telegrambot.enums.ReplyButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import org.springframework.stereotype.Service;

@Service
public class KeyBoardService {

    public ReplyKeyboardMarkup sendContactKeyboard() {
        KeyboardButton keyboardButton = new KeyboardButton(ReplyButton.SEND_CONTACT_BUTTON.getText());
        keyboardButton.requestContact(true);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(keyboardButton);
        return replyKeyboardMarkup
                .resizeKeyboard(true)
                .oneTimeKeyboard(true);
    }
    public InlineKeyboardMarkup startButtonKeyboard() {

        InlineKeyboardButton[] buttons = new InlineKeyboardButton[1];
        buttons[0] = new InlineKeyboardButton(Buttons.START_BUTTON.getText())
                .callbackData(Buttons.START_BUTTON.getCallback());
        return new InlineKeyboardMarkup(buttons);
    }

    public InlineKeyboardMarkup backButtonKeyboard() {

        InlineKeyboardButton[] buttons = new InlineKeyboardButton[1];
        buttons[0] = new InlineKeyboardButton(Buttons.BACK_BUTTON.getText())
                .callbackData(Buttons.BACK_BUTTON.getCallback());
        return new InlineKeyboardMarkup(buttons);
    }

    public InlineKeyboardMarkup backButtonKeyboardDog() {

        InlineKeyboardButton[] buttons = new InlineKeyboardButton[1];
        buttons[0] = new InlineKeyboardButton(Buttons.BACK_BUTTON_DOG.getText())
                .callbackData(Buttons.BACK_BUTTON_DOG.getCallback());
        return new InlineKeyboardMarkup(buttons);
    }

    public InlineKeyboardMarkup backButtonKeyboardCat() {

        InlineKeyboardButton[] buttons = new InlineKeyboardButton[1];
        buttons[0] = new InlineKeyboardButton(Buttons.BACK_BUTTON_CAT.getText())
                .callbackData(Buttons.BACK_BUTTON_CAT.getCallback());
        return new InlineKeyboardMarkup(buttons);
    }

    public InlineKeyboardMarkup stageNullMenuKeyboard() {

        InlineKeyboardButton[][] buttons = new InlineKeyboardButton[2][2];

        buttons[0] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M0_FIRST_BUTTON.getText())
                        .callbackData(Buttons.M0_FIRST_BUTTON.getCallback()),
                new InlineKeyboardButton(Buttons.M0_SECOND_BUTTON.getText())
                        .callbackData(Buttons.M0_SECOND_BUTTON.getCallback())
        };
        buttons[1] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M0_THIRD_BUTTON.getText())
                        .callbackData(Buttons.M0_THIRD_BUTTON.getCallback()),
                new InlineKeyboardButton(Buttons.M0_FOURTH_BUTTON.getText())
                        .callbackData(Buttons.M0_FOURTH_BUTTON.getCallback())
        };
        return new InlineKeyboardMarkup(buttons);
    }

    public InlineKeyboardMarkup stageOneMenuKeyboard() {

        InlineKeyboardButton[][] buttons = new InlineKeyboardButton[2][1];

        buttons[0] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M05_FIRST_BUTTON.getText())
                        .callbackData(Buttons.M05_FIRST_BUTTON.getCallback()),
                new InlineKeyboardButton(Buttons.M05_SECOND_BUTTON.getText())
                        .callbackData(Buttons.M05_SECOND_BUTTON.getCallback())
        };
        buttons[1] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.BACK_BUTTON.getText())
                        .callbackData(Buttons.BACK_BUTTON.getCallback())
        };

        return new InlineKeyboardMarkup(buttons);
    }

    /**
     * Меню кнопок: -Позвать волонтера
     * @return
     */

    public InlineKeyboardMarkup stageOneMenuVolunteerKeyboard() {

        InlineKeyboardButton[][] buttons = new InlineKeyboardButton[2][1];

        buttons[0] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M11_FIRST_BUTTON.getText())
                        .callbackData(Buttons.M11_FIRST_BUTTON.getCallback()),
        };
        buttons[1] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.BACK_BUTTON.getText())
                        .callbackData(Buttons.BACK_BUTTON.getCallback())
        };
        return new InlineKeyboardMarkup(buttons);
    }

    public InlineKeyboardMarkup stageTwoMenuVolunteerKeyboard() {

        InlineKeyboardButton[][] buttons = new InlineKeyboardButton[1][1];

        buttons[0] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.BACK_BUTTON.getText())
                        .callbackData(Buttons.BACK_BUTTON.getCallback())
        };

        return new InlineKeyboardMarkup(buttons);
    }

    public InlineKeyboardMarkup stageTwoMenuKeyboard() {

        InlineKeyboardButton[][] buttons = new InlineKeyboardButton[3][2];

        buttons[0] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M1_FIRST_BUTTON.getText())
                        .callbackData(Buttons.M1_FIRST_BUTTON.getCallback()),
                new InlineKeyboardButton(Buttons.M1_SECOND_BUTTON.getText())
                        .callbackData(Buttons.M1_SECOND_BUTTON.getCallback())
        };
        buttons[1] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M1_THIRD_BUTTON.getText())
                        .callbackData(Buttons.M1_THIRD_BUTTON.getCallback()),
                new InlineKeyboardButton(Buttons.M1_FIFTH_BUTTON.getText())
                        .callbackData(Buttons.M1_FIFTH_BUTTON.getCallback())
        };
        buttons[2] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.BACK_BUTTON.getText())
                        .callbackData(Buttons.BACK_BUTTON.getCallback())
        };

        return new InlineKeyboardMarkup(buttons);
    }

    public InlineKeyboardMarkup stageThreeMenuDogKeyboard() {

        InlineKeyboardButton[][] buttons = new InlineKeyboardButton[11][1];

        buttons[0] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_FIRST_DOG_BUTTON.getText())
                        .callbackData(Buttons.M2_FIRST_DOG_BUTTON.getCallback())
        };
        buttons[1] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_SECOND_BUTTON.getText())
                        .callbackData(Buttons.M2_SECOND_BUTTON.getCallback())
        };
        buttons[2] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_THIRD_BUTTON.getText())
                        .callbackData(Buttons.M2_THIRD_BUTTON.getCallback())
        };
        buttons[3] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_FOURTH_DOG_BUTTON.getText())
                        .callbackData(Buttons.M2_FOURTH_DOG_BUTTON.getCallback())
        };
        buttons[4] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_FIFTH_DOG_BUTTON.getText())
                        .callbackData(Buttons.M2_FIFTH_DOG_BUTTON.getCallback())
        };
        buttons[5] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_SIXTH_DOG_BUTTON.getText())
                        .callbackData(Buttons.M2_SIXTH_DOG_BUTTON.getCallback())
        };
        buttons[6] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_SEVENTH_DOG_BUTTON.getText())
                        .callbackData(Buttons.M2_SEVENTH_DOG_BUTTON.getCallback())
        };
        buttons[7] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_EIGHTH_DOG_BUTTON.getText())
                        .callbackData(Buttons.M2_EIGHTH_DOG_BUTTON.getCallback())
        };
        buttons[8] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_NINTH_BUTTON.getText())
                        .callbackData(Buttons.M2_NINTH_BUTTON.getCallback())
        };
        buttons[9] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_ELEVENTH_BUTTON.getText())
                        .callbackData(Buttons.M2_ELEVENTH_BUTTON.getCallback())
        };
        buttons[10] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.BACK_BUTTON.getText())
                        .callbackData(Buttons.BACK_BUTTON.getCallback())
        };

        return new InlineKeyboardMarkup(buttons);
    }

    public InlineKeyboardMarkup stageThreeMenuCatKeyboard() {

        InlineKeyboardButton[][] buttons = new InlineKeyboardButton[11][1];

        buttons[0] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_FIRST_CAT_BUTTON.getText())
                        .callbackData(Buttons.M2_FIRST_CAT_BUTTON.getCallback())
        };
        buttons[1] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_SECOND_BUTTON.getText())
                        .callbackData(Buttons.M2_SECOND_BUTTON.getCallback())
        };
        buttons[2] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_THIRD_BUTTON.getText())
                        .callbackData(Buttons.M2_THIRD_BUTTON.getCallback())
        };
        buttons[3] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_FOURTH_CAT_BUTTON.getText())
                        .callbackData(Buttons.M2_FOURTH_CAT_BUTTON.getCallback())
        };
        buttons[4] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_FIFTH_CAT_BUTTON.getText())
                        .callbackData(Buttons.M2_FIFTH_CAT_BUTTON.getCallback())
        };
        buttons[5] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_SIXTH_CAT_BUTTON.getText())
                        .callbackData(Buttons.M2_SIXTH_CAT_BUTTON.getCallback())
        };
        buttons[6] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_SEVENTH_CAT_BUTTON.getText())
                        .callbackData(Buttons.M2_SEVENTH_CAT_BUTTON.getCallback())
        };
        buttons[7] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_EIGHTH_CAT_BUTTON.getText())
                        .callbackData(Buttons.M2_EIGHTH_CAT_BUTTON.getCallback())
        };
        buttons[8] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_NINTH_BUTTON.getText())
                        .callbackData(Buttons.M2_NINTH_BUTTON.getCallback())
        };
        buttons[9] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.M2_ELEVENTH_BUTTON.getText())
                        .callbackData(Buttons.M2_ELEVENTH_BUTTON.getCallback())
        };
        buttons[10] = new InlineKeyboardButton[]{
                new InlineKeyboardButton(Buttons.BACK_BUTTON.getText())
                        .callbackData(Buttons.BACK_BUTTON.getCallback())
        };

        return new InlineKeyboardMarkup(buttons);
    }

    public InlineKeyboardMarkup stageNullReportKeyboard() {

        InlineKeyboardButton[] buttons = new InlineKeyboardButton[2];

        buttons[0] = new InlineKeyboardButton(Buttons.REPORT_SEND_BUTTON.getText())
                .callbackData(Buttons.REPORT_SEND_BUTTON.getCallback());
        buttons[1] = new InlineKeyboardButton(Buttons.BACK_BUTTON.getText())
                .callbackData(Buttons.BACK_BUTTON.getCallback());

        return new InlineKeyboardMarkup(buttons);
    }

    public InlineKeyboardMarkup stageAbortReportKeyboard() {

        InlineKeyboardButton[] buttons = new InlineKeyboardButton[1];

        buttons[0] = new InlineKeyboardButton(Buttons.REPORT_SEND_ABORT_BUTTON.getText())
                .callbackData(Buttons.REPORT_SEND_ABORT_BUTTON.getCallback());

        return new InlineKeyboardMarkup(buttons);
    }



}
