package com.thejoa703.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejoa703.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticsSyncService {

    private final PostRepository postRepository;

    public void sendRealStatsToDjango() {
        String djangoUrl = "http://localhost:8000/dashboard/api/statistics/";
        
        // 💡 [필기] Spring에서 동기적 HTTP 요청을 보내기 위해 사용하는 대표 클라이언트 객체는?
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        long totalPosts = postRepository.count();

        Map<String, Object> payload = new HashMap<>();
        payload.put("date", LocalDate.now().toString());
        payload.put("category", "커뮤니티 게시글");
        payload.put("count", (int) totalPosts);

        try {
            // Map 을 Json 문자열로 변환
            String jsonBody = objectMapper.writeValueAsString(payload);

            // 💡 [필기] 요청 바디의 형식이 JSON임을 명시하기 위한 Media type은?
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

            restTemplate.postForEntity(djangoUrl, entity, String.class);

            System.out.println("✅ Django 통계 전송 성공!");
        } catch (Exception e) {
            System.out.println("❌ Django 통계 전송 실패: " + e.getMessage());
        }
    }
}