package com.example.telegrambot.controller;

//import com.example.telegrambot.listener.TelegramBotUpdatesListener;
//import com.example.telegrambot.model.ReportData;
//import com.example.telegrambot.service.ReportDataService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Collection;
//
//@RestController
//@RequestMapping("photoReports")
//public class ReportDataController {
//    private final ReportDataService reportDataService;
//    private TelegramBotUpdatesListener telegramBotUpdatesListener;
//    private final String fileType = "image/jpeg";
//
//    public ReportDataController(ReportDataService reportDataService) {
//        this.reportDataService = reportDataService;
//    }
//
//    @Operation(summary = "Просмотр отчетов по id")
//    @GetMapping("/{id}/report")
//    public ReportData downloadReport(@PathVariable Long id){
//        return reportDataService.findById(id);
//    }
//
//    @Operation(summary = "Удаление отчетов по id")
//    @DeleteMapping("/{id}")
//    public void remove(@PathVariable Long id){
//        reportDataService.remove(id);
//    }
//
//    @Operation(summary = "Просмотр всех отчетов")
//    @GetMapping("getAll")
//    public ResponseEntity <Collection<ReportData>> getAll(){
//        return ResponseEntity.ok(reportDataService.getAll());
//    }
//
//    @Operation(summary = "Просмотр фото по айди отчета")
//    @GetMapping("/{id}/photo-from-db")
//    public ResponseEntity<byte[]> downloadPhotoFromDB(@PathVariable Long id){
//        ReportData reportData = reportDataService.findById(id);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType(fileType));
//        headers.setContentLength(reportData.getData().length);
//
//        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(reportData.getData());
//    }
//
//}
