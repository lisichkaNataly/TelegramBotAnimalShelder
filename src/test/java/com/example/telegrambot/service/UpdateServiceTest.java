package com.example.telegrambot.service;

import com.example.telegrambot.enums.BotStatus;
import com.example.telegrambot.enums.WhichPet;
import com.example.telegrambot.model.Report;
import com.example.telegrambot.model.UserPet;
import com.example.telegrambot.model.Volunteer;
import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateServiceTest {

    @Mock
    private UserPetService userPetService;
    @Mock
    private ReportService reportService;
    @Mock
    private VolunteerService volunteerService;
    @Mock
    private TrialPeriodService trialPeriodService;
    @Mock
    private KeyBoardService keyboardService;
    @Mock
    private TelegramBot telegramBot;
    @InjectMocks
    private UpdateService updateService;
    @Captor
    ArgumentCaptor<UserPet> userCaptor;
    @Captor
    ArgumentCaptor<SendMessage> messageCaptor;

    @Test
    void shouldSaveNewUserInDatabase() throws URISyntaxException, IOException {

        String json = Files.readString(
                Paths.get(UpdateServiceTest.class.getResource("contact_update.json").toURI()));
        Update update = getUpdate(json, "/start");

        when(userPetService.findUserByChatId(any(Long.class))).thenReturn(false);

        SendMessage message = updateService.updateHandler(update);
        verify(userPetService).createUserPet(userCaptor.capture());
        assertThat(userCaptor.getValue().getUserChatId()).isEqualTo(123L);
        assertThat(userCaptor.getValue().getFullName()).isEqualTo("Ivan Ivanov");
        assertThat(userCaptor.getValue().getContacts()).isEqualTo("+79222222222");

    }

    @Test
    void shouldReturnUnhandledUpdateWhenUndefinedStatus() throws URISyntaxException, IOException {

        testCallbackHandler("Any text",
                BotStatus.UNHANDLED_UPDATE,
                BotStatus.UNHANDLED_UPDATE);
    }

    @Test
    void handleUnregisteredUserTest() throws URISyntaxException, IOException {

        String json = Files.readString(
                Paths.get(UpdateServiceTest.class.getResource("text_update.json").toURI()));
        Update update = getUpdate(json, "/start");

        when(userPetService.findUserByChatId(any(Long.class))).thenReturn(false);

        SendMessage message = updateService.updateHandler(update);

        assertThat(message.getParameters().get("chat_id")).isEqualTo(123L);
        assertThat(message.getParameters().get("text")).isEqualTo(
                BotStatus.UNREGISTERED_USER_MESSAGE.getMessageText());
        assertThat(message.getParameters().get("parse_mode"))
                .isEqualTo(ParseMode.HTML.name());

    }

    @Test
    void handleGreetingsMessageTest() throws URISyntaxException, IOException {

        testCallbackHandler("S1Callback", BotStatus.GREETINGS_MESSAGE, BotStatus.GREETINGS_MESSAGE);
    }

    @Test
    void handleGetStartButtonCallbackTest() throws URISyntaxException, IOException {

        testCallbackHandler("S1Callback", BotStatus.START_BUTTON, BotStatus.STAGE_NULL_MENU);
        testCallbackHandler("WrongCallback", BotStatus.START_BUTTON, BotStatus.UNHANDLED_UPDATE);
    }

    @Test
    void handleGetStageNullMenuCallbackTest() throws URISyntaxException, IOException {

        Long id = 123L;
        UserPet custodian = new UserPet();
        custodian.setId(id);

        when(userPetService.findUserCustodianByChatId(any(Long.class))).thenReturn(custodian);
        when(trialPeriodService.isPeriodByUserChatIdExists(any(Long.class))).thenReturn(true);

        testCallbackHandler("M0B1Callback", BotStatus.STAGE_NULL_MENU, BotStatus.STAGE_TWO_MENU);
        testCallbackHandler("M0B2Callback", BotStatus.STAGE_NULL_MENU, BotStatus.STAGE_ONE_MENU);
        testCallbackHandler("M0B3Callback", BotStatus.STAGE_NULL_MENU, BotStatus.STAGE_SEND_REPORT_MENU_NULL);
        testCallbackHandler("M0B4Callback", BotStatus.STAGE_NULL_MENU, BotStatus.STAGE_NULL_MENU_VOLUNTEER);
        testCallbackHandler("WrongCallback", BotStatus.STAGE_NULL_MENU, BotStatus.UNHANDLED_UPDATE);

        when(trialPeriodService.isPeriodByUserChatIdExists(any(Long.class))).thenReturn(false);

        testCallbackHandler("M0B3Callback", BotStatus.STAGE_NULL_MENU, BotStatus.STAGE_SEND_REPORT_MENU_NO_TRIAL_PERIOD);

    }

    @Test
    void handleGetStageOneMenuCallbackTest() throws URISyntaxException, IOException {

        testCallbackHandler("M05B1Callback", BotStatus.STAGE_ONE_MENU, BotStatus.STAGE_THREE_MENU);
        testCallbackHandler("M05B2Callback", BotStatus.STAGE_ONE_MENU, BotStatus.STAGE_THREE_MENU);
        testCallbackHandler("B1Callback", BotStatus.STAGE_ONE_MENU, BotStatus.STAGE_NULL_MENU);
        testCallbackHandler("WrongCallback", BotStatus.STAGE_ONE_MENU, BotStatus.UNHANDLED_UPDATE);
    }

    @Test
    void handleGetStageTwoMenuCallbackTest() throws URISyntaxException, IOException {

        testCallbackHandler("B1Callback", BotStatus.STAGE_TWO_MENU, BotStatus.STAGE_NULL_MENU);
        testCallbackHandler("M1B1Callback", BotStatus.STAGE_TWO_MENU, BotStatus.INFORMATION_ABOUT_THE_SHELTER);
        testCallbackHandler("M1B2Callback", BotStatus.STAGE_TWO_MENU, BotStatus.ADDRESS_OF_THE_SHELTER);
        testCallbackHandler("M1B3Callback", BotStatus.STAGE_TWO_MENU, BotStatus.SAFETY_PRECAUTIONS);
        testCallbackHandler("M1B5ButtonCallback", BotStatus.STAGE_TWO_MENU, BotStatus.STAGE_TWO_MENU_VOLUNTEER);
        testCallbackHandler("WrongCallback", BotStatus.STAGE_TWO_MENU, BotStatus.UNHANDLED_UPDATE);
    }

    @Test
    void handleGetStageThreeMenuCallbacktest() throws URISyntaxException, IOException {

        testCallbackHandler("B1Callback", BotStatus.STAGE_THREE_MENU, BotStatus.STAGE_ONE_MENU);
        testCallbackHandler("M2B1CallbackDog", BotStatus.STAGE_THREE_MENU, BotStatus.GETTING_TO_KNOW_A_DOG);
        testCallbackHandler("M2B1CallbackCat", BotStatus.STAGE_THREE_MENU, BotStatus.GETTING_TO_KNOW_A_CAT);
        testCallbackHandler("M2B1Callback", BotStatus.STAGE_THREE_MENU, BotStatus.REQUIRED_DOCUMENTS, WhichPet.DOG);
        testCallbackHandler("M2B1Callback", BotStatus.STAGE_THREE_MENU, BotStatus.REQUIRED_DOCUMENTS, WhichPet.CAT);
        testCallbackHandler("M2B13Callback", BotStatus.STAGE_THREE_MENU, BotStatus.ANIMAL_TRANSPORTATION, WhichPet.DOG);
        testCallbackHandler("M2B13Callback", BotStatus.STAGE_THREE_MENU, BotStatus.ANIMAL_TRANSPORTATION, WhichPet.CAT);
        testCallbackHandler("M2B14Callback", BotStatus.STAGE_THREE_MENU, BotStatus.HOME_IMPROVEMENT_FOR_A_PUPPY);
        testCallbackHandler("M2B15Callback", BotStatus.STAGE_THREE_MENU, BotStatus.HOME_IMPROVEMENT_FOR_A_KITTEN);
        testCallbackHandler("M2B16Callback", BotStatus.STAGE_THREE_MENU, BotStatus.HOME_IMPROVEMENT_FOR_AN_ADULT_DOG);
        testCallbackHandler("M2B17Callback", BotStatus.STAGE_THREE_MENU, BotStatus.HOME_IMPROVEMENT_FOR_AN_ADULT_CAT);
        testCallbackHandler("M2B18Callback", BotStatus.STAGE_THREE_MENU, BotStatus.CARING_FOR_A_DOG_WITH_DISABILITIES);
        testCallbackHandler("M2B19Callback", BotStatus.STAGE_THREE_MENU, BotStatus.CARING_FOR_A_CAT_WITH_DISABILITIES);
        testCallbackHandler("M2B20Callback", BotStatus.STAGE_THREE_MENU, BotStatus.TIPS_FROM_A_DOG_HANDLER);
        testCallbackHandler("M2B21Callback", BotStatus.STAGE_THREE_MENU, BotStatus.FELINOLOGIST_ADVICE);
        testCallbackHandler("M2B22Callback", BotStatus.STAGE_THREE_MENU, BotStatus.PROVEN_DOG_HANDLERS);
        testCallbackHandler("M2B23Callback", BotStatus.STAGE_THREE_MENU, BotStatus.PROVEN_FELINOLOGISTS);
        testCallbackHandler("M2B24Callback", BotStatus.STAGE_THREE_MENU, BotStatus.REASONS_FOR_REFUSAL, WhichPet.DOG);
        testCallbackHandler("M2B24Callback", BotStatus.STAGE_THREE_MENU, BotStatus.REASONS_FOR_REFUSAL, WhichPet.CAT);
        testCallbackHandler("WrongCallback", BotStatus.STAGE_THREE_MENU, BotStatus.UNHANDLED_UPDATE);
    }

    @Test
    void handleBackInfoShelterTest() throws URISyntaxException, IOException {
        testCallbackHandler("B1Callback", BotStatus.INFO_BUTTON, BotStatus.STAGE_TWO_MENU);
        testCallbackHandler("WrongCallback", BotStatus.INFO_BUTTON, BotStatus.UNHANDLED_UPDATE);
    }

    @Test
    void handleBackTheCatInfoTest() throws URISyntaxException, IOException {

        testCallbackHandler("B1CallbackCat", BotStatus.CAT_BUTTON, BotStatus.STAGE_THREE_MENU);
        testCallbackHandler("B1CallbackCat", BotStatus.COMMON_BUTTON, BotStatus.STAGE_THREE_MENU, WhichPet.CAT);
        testCallbackHandler("WrongCallback", BotStatus.CAT_BUTTON, BotStatus.UNHANDLED_UPDATE);
        testCallbackHandler("WrongCallback", BotStatus.COMMON_BUTTON, BotStatus.UNHANDLED_UPDATE, WhichPet.CAT);
    }

    @Test
    void handleBackTheDogInfoTest() throws URISyntaxException, IOException {

        testCallbackHandler("B1CallbackDog", BotStatus.DOG_BUTTON, BotStatus.STAGE_THREE_MENU);
        testCallbackHandler("B1CallbackDog", BotStatus.COMMON_BUTTON, BotStatus.STAGE_THREE_MENU, WhichPet.DOG);
        testCallbackHandler("WrongCallback", BotStatus.DOG_BUTTON, BotStatus.UNHANDLED_UPDATE);
        testCallbackHandler("WrongCallback", BotStatus.COMMON_BUTTON, BotStatus.UNHANDLED_UPDATE, WhichPet.DOG);
    }

    @Test
    void handleBackToMainReportMessageTest() throws URISyntaxException, IOException {

        testCallbackHandler("B1Callback", BotStatus.STAGE_SEND_REPORT_MENU_FINISH, BotStatus.STAGE_NULL_MENU);
        testCallbackHandler("WrongCallback", BotStatus.STAGE_SEND_REPORT_MENU_FINISH, BotStatus.UNHANDLED_UPDATE);
    }

    @Test
    void handleChangesReportMessageTest() throws URISyntaxException, IOException {

        String text = "some text";

        Report report = new Report();
        updateService.editReportMap(123L, report);

        UserPet userCustodian = new UserPet();
        userCustodian.setId(1234L);

        when(userPetService.findUserCustodianByChatId(any(Long.class))).thenReturn(userCustodian);

        testMessageHandler(text,
                BotStatus.STAGE_SEND_REPORT_MENU_CHANGES_IN_PET_LIFE,
                BotStatus.STAGE_SEND_REPORT_MENU_FINISH.getMessageText());
        assertThat(updateService.getReportMap().get(123L).getHealthStatus()).isEqualTo(text);

        testMessageHandler(BotStatus.STAGE_SEND_REPORT_MENU_CHANGES_IN_PET_LIFE,
                "Отправить нужно только текстовое описание об изменениях в поведении питомца!");
    }

    @Test
    void handleChangesReportMessageButtonTest() throws URISyntaxException, IOException {

        testCallbackHandler("RSABCallback",
                BotStatus.STAGE_SEND_REPORT_MENU_CHANGES_IN_PET_LIFE,
                BotStatus.STAGE_NULL_MENU);
        testCallbackHandler("WrongCallback",
                BotStatus.STAGE_SEND_REPORT_MENU_CHANGES_IN_PET_LIFE,
                BotStatus.UNHANDLED_UPDATE);
    }

    @Test
    void handleOverallReportMessageTest() throws URISyntaxException, IOException {

        String text = "some text";

        Report report = new Report();
        updateService.editReportMap(123L, report);

        testMessageHandler(text,
                BotStatus.STAGE_SEND_REPORT_MENU_OVERALL_FEELINGS,
                BotStatus.STAGE_SEND_REPORT_MENU_CHANGES_IN_PET_LIFE.getMessageText());
        assertThat(updateService.getReportMap().get(123L).getBehaviour()).isEqualTo(text);

        testMessageHandler(BotStatus.STAGE_SEND_REPORT_MENU_OVERALL_FEELINGS,
                "Отправить нужно только текстовое описание самочувствия питомца!");
    }

    @Test
    void handleOverallReportMessageButtonTest() throws URISyntaxException, IOException {

        testCallbackHandler("RSABCallback",
                BotStatus.STAGE_SEND_REPORT_MENU_OVERALL_FEELINGS,
                BotStatus.STAGE_NULL_MENU);

        testCallbackHandler("RSСBCallback",
                BotStatus.STAGE_SEND_REPORT_MENU_OVERALL_FEELINGS,
                BotStatus.STAGE_SEND_REPORT_MENU_CHANGES_IN_PET_LIFE);

        testCallbackHandler("WrongCallback",
                BotStatus.STAGE_SEND_REPORT_MENU_OVERALL_FEELINGS,
                BotStatus.UNHANDLED_UPDATE);
    }

    @Test
    void handleDietReportMessageTest() throws URISyntaxException, IOException {

        String text = "some text";

        Report report = new Report();
        updateService.editReportMap(123L, report);

        testMessageHandler(text,
                BotStatus.STAGE_SEND_REPORT_MENU_DIET,
                BotStatus.STAGE_SEND_REPORT_MENU_OVERALL_FEELINGS.getMessageText());
        assertThat(updateService.getReportMap().get(123L).getDiet()).isEqualTo(text);

        testMessageHandler(BotStatus.STAGE_SEND_REPORT_MENU_DIET,
                "Отправить нужно только текстовое описание меню питомца!");
    }

    @Test
    void handleDietReportMessageButtonTest() throws URISyntaxException, IOException {

        testCallbackHandler("RSABCallback",
                BotStatus.STAGE_SEND_REPORT_MENU_DIET,
                BotStatus.STAGE_NULL_MENU);

        testCallbackHandler("WrongCallback",
                BotStatus.STAGE_SEND_REPORT_MENU_DIET,
                BotStatus.UNHANDLED_UPDATE);
    }

    @Test
    void handlePhotoReportMessageTest() throws URISyntaxException, IOException {

        testPhotoHandler(BotStatus.STAGE_SEND_REPORT_MENU_PHOTO, BotStatus.STAGE_SEND_REPORT_MENU_DIET);
        assertThat(updateService.getReportMap().get(123L).getPhoto()).isEqualTo("123456789");

        testMessageHandler("some text",
                BotStatus.STAGE_SEND_REPORT_MENU_PHOTO,
                "Отправить нужно только фотографию!");
    }

    @Test
    void handlePhotoButtonReportMessageTest() throws URISyntaxException, IOException {

        testCallbackHandler("RSABCallback",
                BotStatus.STAGE_SEND_REPORT_MENU_PHOTO,
                BotStatus.STAGE_NULL_MENU);

        testCallbackHandler("RSСBCallback",
                BotStatus.STAGE_SEND_REPORT_MENU_PHOTO,
                BotStatus.STAGE_SEND_REPORT_MENU_DIET);

        testCallbackHandler("WrongCallback",
                BotStatus.STAGE_SEND_REPORT_MENU_PHOTO,
                BotStatus.UNHANDLED_UPDATE);
    }

    @Test
    void handleSendReportButtonMessageTest() throws URISyntaxException, IOException {

        testCallbackHandler("RSBCallback",
                BotStatus.STAGE_SEND_REPORT_MENU_NULL,
                BotStatus.STAGE_SEND_REPORT_MENU_PHOTO);

        testCallbackHandler("B1Callback",
                BotStatus.STAGE_SEND_REPORT_MENU_NULL,
                BotStatus.STAGE_NULL_MENU);

        testCallbackHandler("WrongCallback",
                BotStatus.STAGE_SEND_REPORT_MENU_NULL,
                BotStatus.UNHANDLED_UPDATE);
    }

    @Test
    void handleGetStageOneMenuVolunteerCallbackPositiveTest() throws URISyntaxException, IOException {

        Long volunteerChatId = 123L;
        Long custodianChatId = 123L;
        String custodianName = "Ivan Ivanov";


        Volunteer volunteer = new Volunteer();
        volunteer.setChatId(volunteerChatId);

        UserPet custodian = new UserPet();
        custodian.setUserChatId(custodianChatId);
        custodian.setFullName(custodianName);

        when(volunteerService.callVolunteer(any(Long.class))).thenReturn(volunteer);
        when(userPetService.findUserCustodianByChatId(any(Long.class))).thenReturn(custodian);

        String text = "<a href=" + " \"tg://user?id=" + custodianChatId + "\"" + ">" + custodianName + "</a>" + " просил позвонить ему";

        testCallbackHandler("M11B1Callback",
                BotStatus.STAGE_NULL_MENU_VOLUNTEER,
                BotStatus.VOLUNTEER_CALLED_SUCCESSFULLY);

        verify(telegramBot).execute(messageCaptor.capture());
        SendMessage message = messageCaptor.getValue();

        assertThat(message.getParameters().get("chat_id")).isEqualTo(volunteerChatId);
        assertThat(message.getParameters().get("text")).isEqualTo(text);
        assertThat(message.getParameters().get("parse_mode"))
                .isEqualTo(ParseMode.HTML.name());

        testCallbackHandler("M11B1Callback",
                BotStatus.STAGE_TWO_MENU_VOLUNTEER,
                BotStatus.VOLUNTEER_CALLED_SUCCESSFULLY);

        testCallbackHandler("M11B1Callback",
                BotStatus.STAGE_THREE_MENU_VOLUNTEER,
                BotStatus.VOLUNTEER_CALLED_SUCCESSFULLY);

        testCallbackHandler("B1Callback",
                BotStatus.STAGE_NULL_MENU_VOLUNTEER,
                BotStatus.STAGE_NULL_MENU);

        testCallbackHandler("B1Callback",
                BotStatus.STAGE_TWO_MENU_VOLUNTEER,
                BotStatus.STAGE_TWO_MENU);

        testCallbackHandler("B1Callback",
                BotStatus.STAGE_THREE_MENU_VOLUNTEER,
                BotStatus.STAGE_THREE_MENU, WhichPet.DOG);

        testCallbackHandler("B1Callback",
                BotStatus.STAGE_THREE_MENU_VOLUNTEER,
                BotStatus.STAGE_THREE_MENU, WhichPet.CAT);

        testCallbackHandler("WrongCallback",
                BotStatus.STAGE_NULL_MENU_VOLUNTEER,
                BotStatus.UNHANDLED_UPDATE);

        testCallbackHandler("WrongCallback",
                BotStatus.STAGE_TWO_MENU_VOLUNTEER,
                BotStatus.UNHANDLED_UPDATE);

        testCallbackHandler("WrongCallback",
                BotStatus.STAGE_THREE_MENU_VOLUNTEER,
                BotStatus.UNHANDLED_UPDATE, WhichPet.DOG);

        testCallbackHandler("WrongCallback",
                BotStatus.STAGE_THREE_MENU_VOLUNTEER,
                BotStatus.UNHANDLED_UPDATE, WhichPet.CAT);

    }

    @Test
    void handleGetStageOneMenuVolunteerCallbackNegativeTest() throws URISyntaxException, IOException {

        when(volunteerService.callVolunteer(any(Long.class))).thenReturn(null);

        testCallbackHandler("M11B1Callback",
                BotStatus.STAGE_NULL_MENU_VOLUNTEER,
                BotStatus.FREE_VOLUNTEER_NOT_FOUND);

        testCallbackHandler("M11B1Callback",
                BotStatus.STAGE_TWO_MENU_VOLUNTEER,
                BotStatus.FREE_VOLUNTEER_NOT_FOUND);

        testCallbackHandler("M11B1Callback",
                BotStatus.STAGE_THREE_MENU_VOLUNTEER,
                BotStatus.FREE_VOLUNTEER_NOT_FOUND);
    }

    @Test
    void handleGetStageTwoMenuVolunteerCallbackTest() throws URISyntaxException, IOException {

        testCallbackHandler("B1Callback",
                BotStatus.VOLUNTEER_CALLED_SUCCESSFULLY,
                BotStatus.STAGE_NULL_MENU);

        testCallbackHandler("B1Callback",
                BotStatus.FREE_VOLUNTEER_NOT_FOUND,
                BotStatus.STAGE_NULL_MENU);

        testCallbackHandler("WrongCallback",
                BotStatus.VOLUNTEER_CALLED_SUCCESSFULLY,
                BotStatus.UNHANDLED_UPDATE);

        testCallbackHandler("WrongCallback",
                BotStatus.FREE_VOLUNTEER_NOT_FOUND,
                BotStatus.UNHANDLED_UPDATE);
    }

    private Update getUpdate(String json, String replaced) {
        return BotUtils.fromJson(json.replace("%command%", replaced), Update.class);
    }

    private Update getUpdate(String json) {
        return BotUtils.fromJson(json, Update.class);
    }

    private void testCallbackHandler(String callbackText,
                                     BotStatus botStatusIn,
                                     BotStatus botStatusOut) throws URISyntaxException, IOException {

        String json = Files.readString(
                Paths.get(UpdateServiceTest.class.getResource("callback_update.json").toURI()));
        Update update = getUpdate(json, callbackText);

        when(userPetService.findUserByChatId(any(Long.class))).thenReturn(true);
        updateService.editStatusMap(123L, botStatusIn);

        SendMessage message = updateService.updateHandler(update);
        assertThat(message.getParameters().get("chat_id")).isEqualTo(123L);
        assertThat(message.getParameters().get("text")).isEqualTo(
                botStatusOut.getMessageText());
        assertThat(message.getParameters().get("parse_mode"))
                .isEqualTo(ParseMode.HTML.name());
    }

    private void testCallbackHandler(String callbackText,
                                     BotStatus botStatusIn,
                                     BotStatus botStatusOut,
                                     WhichPet whichPet) throws URISyntaxException, IOException {

        String json = Files.readString(
                Paths.get(UpdateServiceTest.class.getResource("callback_update.json").toURI()));
        Update update = getUpdate(json, callbackText);

        when(userPetService.findUserByChatId(any(Long.class))).thenReturn(true);
        updateService.editStatusMap(123L, botStatusIn);
        updateService.editStatusMenu(123L, whichPet);

        SendMessage message = updateService.updateHandler(update);
        assertThat(message.getParameters().get("chat_id")).isEqualTo(123L);
        assertThat(message.getParameters().get("text")).isEqualTo(
                botStatusOut.getMessageText());
        assertThat(message.getParameters().get("parse_mode"))
                .isEqualTo(ParseMode.HTML.name());
    }

    private void testMessageHandler(String text,
                                    BotStatus botStatusIn,
                                    String textOut) throws URISyntaxException, IOException {

        String json = Files.readString(
                Paths.get(UpdateServiceTest.class.getResource("text_update.json").toURI()));
        Update update = getUpdate(json, text);

        when(userPetService.findUserByChatId(any(Long.class))).thenReturn(true);
        updateService.editStatusMap(123L, botStatusIn);

        SendMessage message = updateService.updateHandler(update);
        assertThat(message.getParameters().get("chat_id")).isEqualTo(123L);
        assertThat(message.getParameters().get("text")).isEqualTo(textOut);
        assertThat(message.getParameters().get("parse_mode")).isEqualTo(ParseMode.HTML.name());
    }

    private void testMessageHandler(BotStatus botStatusIn,
                                    String textOut) throws URISyntaxException, IOException {

        String json = Files.readString(
                Paths.get(UpdateServiceTest.class.getResource("null_text_update.json").toURI()));
        Update update = getUpdate(json);

        when(userPetService.findUserByChatId(any(Long.class))).thenReturn(true);
        updateService.editStatusMap(123L, botStatusIn);

        SendMessage message = updateService.updateHandler(update);
        assertThat(message.getParameters().get("chat_id")).isEqualTo(123L);
        assertThat(message.getParameters().get("text")).isEqualTo(textOut);
        assertThat(message.getParameters().get("parse_mode")).isEqualTo(ParseMode.HTML.name());
    }

    private void testPhotoHandler(BotStatus botStatusIn, BotStatus botStatusOut) throws URISyntaxException, IOException {

        String json = Files.readString(
                Paths.get(UpdateServiceTest.class.getResource("photo_update.json").toURI()));
        Update update = getUpdate(json);

        when(userPetService.findUserByChatId(any(Long.class))).thenReturn(true);
        updateService.editStatusMap(123L, botStatusIn);

        SendMessage message = updateService.updateHandler(update);
        assertThat(message.getParameters().get("chat_id")).isEqualTo(123L);
        assertThat(message.getParameters().get("text")).isEqualTo(
                botStatusOut.getMessageText());
        assertThat(message.getParameters().get("parse_mode"))
                .isEqualTo(ParseMode.HTML.name());
    }
}