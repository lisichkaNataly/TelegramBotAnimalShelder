package com.example.telegrambot.listener;

import com.example.telegrambot.repository.ReportPetRepository;
import com.example.telegrambot.repository.UserCatRepository;
import com.example.telegrambot.repository.UserDogRepository;
import com.example.telegrambot.repository.UserRepository;
import com.example.telegrambot.service.KeyBoardShelter;
import com.example.telegrambot.service.ReportPetService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class TelegramBotUpdatesListener implements UpdatesListener {

        private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
        private final TelegramBot telegramBot;
    private static final String START_CMD = "/start";

    private static final String GREETING_TEXT = ", Приветствую! Чтобы найти то, что тебе нужно - нажми на нужную кнопку";

    private static final String infoAboutBot = "Информация о возможностях бота \n- Бот может показать информацию о приюте \n" +
            "- Покажет какие документы нужны \n- Бот может принимать ежедневный отчет о питомце\n" +
            "- Может передать контактные данные волонтерам для связи";
    private static final String infoAboutShelterDog = "Наш сайт с информацией о приюте для собак \nhttps://google.com \n" +
            "Контактные данные \nhttps://yandex.ru\n" +
            "Общие рекомендации \nhttps://ru.wikipedia.org\n" +
            "";
    private static final String infoAboutShelterCat = "Наш сайт с информацией о приюте для кошек \nhttps://google.com \n" +
            "Контактные данные \nhttps://yandex.ru\n" +
            "Общие рекомендации \nhttps://ru.wikipedia.org\n" +
            "";
    private static final String infoAboutDogs = "Правила знакомства с животным \nhttps://google.com \n" +
            "Список документов \nhttps://yandex.ru\n" +
            "Список рекомендаций \nhttps://ru.wikipedia.org\n" +
            "Советы кинолога \nhttps://ru.wikipedia.org\n" +
            "Прочая информация \nhttps://google.com\n" +
            "";

    private static final String infoAboutCats = "Правила знакомства с животным \nhttps://google.com \n" +
            "Список документов \nhttps://yandex.ru\n" +
            "Список рекомендаций \nhttps://ru.wikipedia.org\n" +
            "Прочая информация \nhttps://google.com\n" +
            "";
    private static final String infoContactsVolonter = "Контактные данные волонтера  \n @ivan_ivanov \n" +
            "Телефон - +7 999 999 99 99 \n";
    private static final String infoAboutReport = "Для отчета нужна следующая информация:\n" +
            "- Фото животного.  \n" +
            "- Рацион животного\n" +
            "- Общее самочувствие и привыкание к новому месту\n" +
            "- Изменение в поведении: отказ от старых привычек, приобретение новых.\nСкопируйте следующий пример. Не забудьте прикрепить фото";

    private static final String reportExample = "Рацион: ваш текст;\n" +
            "Самочувствие: ваш текст;\n" +
            "Поведение: ваш текст;";

    private static final String REGEX_MESSAGE = "(Рацион:)(\\s)(\\W+)(;)\n" +
            "(Самочувствие:)(\\s)(\\W+)(;)\n" +
            "(Поведение:)(\\s)(\\W+)(;)";

    private static final long telegramChatVolunteers = -748879962L;
    private long daysOfReports;
    private ReportPetRepository reportPetRepository;
    private UserDogRepository userDogRepository;
    private UserCatRepository userCatRepository;
    private KeyBoardShelter keyBoardShelter;
    private ReportPetService reportPetService;

        public TelegramBotUpdatesListener(TelegramBot telegramBot) {
            this.telegramBot = telegramBot;
        }

        @PostConstruct
        public void init() {
            telegramBot.setUpdatesListener(this);
        }

        @Override
        public int process(List<Update> updates) {
            try {
                updates.forEach(update -> {
                    logger.info("Processing update: {}", update);
                    Message message = update.message();
                    Long chatId = message.chat().id();
                    String text = message.text();

                    if ("/start".equals(text)) {
                        SendMessage sendMessage = new SendMessage(chatId, "Привет!Это приют кошек и собак.Выбери меню");
                        SendResponse sendResponse = telegramBot.execute(sendMessage);
                        if (!sendResponse.isOk()) {
                            logger.error("Error during sending message: {}", sendResponse.description());
                        }
                    }
                });
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }
    }
