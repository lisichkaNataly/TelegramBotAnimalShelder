package com.example.telegrambot.controller;

import com.example.telegrambot.model.TrialPeriod;
import com.example.telegrambot.service.TrialPeriodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/trial-periods")
public class TrialPeriodController {
    private final TrialPeriodService trialPeriodService;

    public TrialPeriodController(TrialPeriodService trialPeriodService) {
        this.trialPeriodService = trialPeriodService;
    }

    @Operation(summary = "Get list of all Trial periods", tags = "Trial period")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "There's the list",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrialPeriod.class))})
    })
    @GetMapping("")
    public ResponseEntity<List<TrialPeriod>> getTrialPeriodList() {
        List<TrialPeriod> foundStudent = trialPeriodService.findAll();
        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @Operation(summary = "Create new Trial period", tags = "Trial period")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trial period is created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrialPeriod.class))})
    })

    @PostMapping("")
    public ResponseEntity<TrialPeriod> createTrialPeriod(@Parameter(description = "Period to be created")
                                                         @RequestBody TrialPeriod period) {
        return ok(trialPeriodService.createPeriod(period));
    }

    @Operation(summary = "Change an existed Trial period", tags = "Trial period",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Trial period has been changed",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TrialPeriod.class))}),
                    @ApiResponse(responseCode = "404", description = "Trial period not found",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = TrialPeriod.class))})
            })
    @PutMapping("")
    public ResponseEntity<TrialPeriod> updateTrialPeriod(@Parameter(description = "Period to be updated")
                                                         @RequestBody TrialPeriod period) {
        TrialPeriod findTrialPeriod = trialPeriodService.editTrialPeriod(period);
        if (findTrialPeriod == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(findTrialPeriod);
    }

    @Operation(summary = "Closing the trial period", tags = "Trial period")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trial period is closed",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrialPeriod.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<TrialPeriod> closureTrialPeriod(@PathVariable Long id) {
        return trialPeriodService.deletePeriod(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Extend trial period for 15 days", tags = "Trial period Extension")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trial period has been extended for 15 days",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrialPeriod.class))})
    })
    @PutMapping("/extend15/{id}")
    public ResponseEntity<TrialPeriod> extendTrialPeriodFor15Days(@PathVariable Long id) {
        TrialPeriod period = trialPeriodService.extendTrialPeriodFor15Days(id);
        if (period != null) {
            return ResponseEntity.ok(period);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Extend trial period for 30 days", tags = "Trial period Extension")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trial period has been extended for 30 days",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TrialPeriod.class))})
    })
    @PutMapping("/extend30/{id}")
    public ResponseEntity<TrialPeriod> extendTrialPeriodFor30Days(@PathVariable Long id) {
        TrialPeriod period = trialPeriodService.extendTrialPeriodFor30Days(id);
        if (period != null) {
            return ResponseEntity.ok(period);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

