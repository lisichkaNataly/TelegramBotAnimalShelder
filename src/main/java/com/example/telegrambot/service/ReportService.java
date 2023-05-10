package com.example.telegrambot.service;

import com.example.telegrambot.listener.TelegramBotUpdatesListener;
import com.example.telegrambot.model.Photo;
import com.example.telegrambot.model.Report;
import com.example.telegrambot.repository.ReportRepository;
import com.example.telegrambot.repository.TrialPeriodRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetFileResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.telegrambot.model.TrialPeriod;

import java.io.IOException;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final TrialPeriodRepository trialPeriodRepository;
    private final UserPetService custodianService;
    private final TelegramBot telegramBot;
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Value("${telegram.bot.token}")
    private String token;

    public ReportService(ReportRepository reportRepository, TrialPeriodRepository trialPeriodRepository, UserPetService custodianService, TelegramBot telegramBot) {
        this.reportRepository = reportRepository;
        this.trialPeriodRepository = trialPeriodRepository;
        this.custodianService = custodianService;
        this.telegramBot = telegramBot;
    }

    public List<Report> getReports(Long volunteerId, String stringDateFrom, String stringDateTo) {

        LocalDate dateFrom = LocalDate.parse(stringDateFrom);
        LocalDate dateTo = LocalDate.parse(stringDateTo);

        Collection<Report> reportList = new ArrayList<>();
        trialPeriodRepository.findAllByVolunteerId(volunteerId)
                .stream()
                .map(TrialPeriod::getUserId)
                .distinct()
                .forEach(id -> reportList.addAll(reportRepository.findReportsByUserId(id)));

        return reportList
                .stream()
                .filter(e -> e.getReportDate().isAfter(dateFrom.minusDays(1))
                        && e.getReportDate().isBefore(dateTo.plusDays(1)))
                .collect(Collectors.toList());
    }

    public boolean sendMessage(Long id, String text) {
        Optional<Long> chatId = custodianService.findChatIdByUserId(id);
        if (chatId.isPresent()) {
            SendMessage message = new SendMessage(chatId.get(), text);
            message.parseMode(ParseMode.HTML);
            SendResponse response = telegramBot.execute(message);
            logger.info("Sending message to " + chatId);
            return true;
        }
        return false;
    }

    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    public Optional<Report> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }

    public Photo getPhotoById(String fileId) {
        Photo photo = new Photo();
        GetFile getFile = new GetFile(fileId);
        GetFileResponse getFileResponse = telegramBot.execute(getFile);
        if (getFileResponse.isOk()) {
            File file = getFileResponse.file();
            try {
                photo.setData(telegramBot.getFileContent(file));
                photo.setFileSize(file.fileSize());
                photo.setMediaType(URLConnection.guessContentTypeFromName(file.filePath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return photo;
    }
}
