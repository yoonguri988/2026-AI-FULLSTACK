package com.thejoa703.controller;

import com.thejoa703.service.StatisticsSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics/")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsSyncService statisticsSyncService;

    @PostMapping("/sync")
    public ResponseEntity<String> syncStatistics() {
        try {
            // Service 내의 sendRealStatsToDjango() 호출
            statisticsSyncService.sendRealStatsToDjango();
            return ResponseEntity.ok("✅ Spring Boot -> Django 통계 동기화 성공!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("❌ 동기화 실패: " + e.getMessage());
        }
    }   
}