# 📖 Spring Boot + Django + Pandas 연동 대시보드 프로젝트  
 
---

## 1. 연동 시스템 아키텍처 및 핵심 개념 점검

### 1-1. 전체 시스템 구조

```text
[ React 프론트엔드 ] ──(JWT 인증)──> [ Spring Boot 백엔드 ] ──(데이터 저장)──> [ Oracle DB ]
                                          │
                                    (통계 데이터 전송)
                                          ▼
                             [ Django API + Pandas 대시보드 ] ──(시각화)──> [ 관리자 화면 ]

# Spring Boot : 메인 비즈니스 로직
# Django: 통계 수집, Rest api, pandas 데이터 분석/시각화
# Rest Api 이용해서 post json 데이터 송수신
```

```text
# ✏️ [필기 개념 점검 Section 1]
# Q1. Spring Boot와 Django 간의 통계 데이터 송수신 시 사용하는 대표적인 데이터 포맷은 무엇인가요?
# 답: ( ____________________ )

# Q2. 메인 DB(Oracle)의 데이터를 직접 분석하지 않고 Django로 이관해 분석하는 아키텍처적 이유는 무엇인가요?
# 답: 메인 서비스의 ( ____________________ )를 분산시키고, 파이썬의 강력한 ( ____________________ ) 생태계를 활용하기 위함

```

---

## 2. 필수 라이브러리 추가 및 환경 점검

```bash
# 💡 [필기] Django에서 외부 REST API(Spring Boot)로 HTTP 요청을 보내기 위해 설치하는 패키지명은?
pip install requests

# 패키지 정상 설치 확인 명령어
python -c "import requests; print(requests.__version__)"

```

```text
# ✏️ [필기 개념 점검 Section 2]
# Q1. 파이썬에서 외부 HTTP 서버로 요청(GET, POST 등)을 보낼 때 사용하는 대표적인 라이브러리는 무엇인가요?
# 답: ( ____________________ )

```

---

## 3. 실전 MSA 연동 구현 (빈칸 채우기)

### Step 0. 대시보드 화면 HTML 템플릿 (`templates/analytics/dashboard.html`)

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>파이썬 데이터 분석 대시보드</title>
    <!-- 💡 [필기] 시각화 그래프 작성을 위해 로드한 CDN 라이브러리 이름은? -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .dashboard-container { width: 800px; margin: 40px auto; font-family: Arial, sans-serif; }
        .summary-cards { display: flex; justify-content: space-between; gap: 15px; margin-bottom: 30px; }
        .card { flex: 1; background: #f8f9fa; border: 1px solid #e9ecef; border-radius: 8px; padding: 15px; text-align: center; }
        .card h4 { margin: 0 0 8px 0; color: #6c757d; font-size: 14px; }
        .card p { margin: 0; font-size: 20px; font-weight: bold; color: #333; }
        .chart-box { background: #ffffff; border: 1px solid #e9ecef; border-radius: 8px; padding: 20px; }
    </style>
</head>
<body>
    <div class="dashboard-container">
        <h2>Python + Pandas 카테고리별 분석 대시보드</h2>

        <!-- 💡 [필기] Django Context에서 넘겨받은 통계 데이터 변수명을 채워넣으세요. -->
        <div class="summary-cards">
            <div class="card">
                <h4>최다 발생 카테고리</h4>
                <p style="color: #2b6cb0;">{{ top_category }}</p>
            </div>
            <div class="card">
                <h4>평균 건수</h4>
                <p>{{ stats_summary.avg_visitors }}개</p>
            </div>
            <div class="card">
                <h4>최대 건수</h4>
                <p>{{ stats_summary.max_visitors }}개</p>
            </div>
            <div class="card">
                <h4>총 누적 건수</h4>
                <p>{{ stats_summary.total_count }}개</p>
            </div>
        </div>

        <div class="chart-box">
            <canvas id="analysisChart"></canvas>
        </div>
    </div>

    <!-- 💡 [필기] Python 객체를 안전하게 JS 배열로 변환하는 Django 내장 필터는? -->
    {{ categories|json_script:"categories-data" }}
    {{ visitors|json_script:"visitors-data" }}
    {{ sales|json_script:"sales-data" }}
    {{ shares|json_script:"shares-data" }}

    <script>
        const ctx = document.getElementById('analysisChart').getContext('2d');
        
        const chartCategories = JSON.parse(document.getElementById('categories-data').textContent);
        const chartVisitors = JSON.parse(document.getElementById('visitors-data').textContent);
        const chartSales = JSON.parse(document.getElementById('sales-data').textContent);
        const chartShares = JSON.parse(document.getElementById('shares-data').textContent);

        const analysisChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: chartCategories,
                datasets: [
                    {
                        label: '카테고리별 방문자 수 (건)',
                        data: chartVisitors,
                        backgroundColor: 'rgba(54, 162, 235, 0.5)',
                        borderColor: 'rgba(54, 162, 235, 1)',
                        borderWidth: 1
                    },
                    {
                        label: '카테고리별 총 매출액 (원)',
                        data: chartSales,
                        backgroundColor: 'rgba(255, 99, 132, 0.5)',
                        borderColor: 'rgba(255, 99, 132, 1)',
                        borderWidth: 1
                    }
                ]
            },
            options: { responsive: true, scales: { y: { beginAtZero: true } } }
        });
    </script>
</body>
</html>

```

---

### Step 1. Django 통계 수신 API 및 뷰 작성 (`analytics/views.py`)

```python
import json
import requests
import pandas as pd
from django.shortcuts import render
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
from .models import ServiceLog

def dashboard_view(request):
    spring_api_url = "http://localhost:8080/api/statistics/sync"

    try:
        # 💡 [필기] Spring Boot 동기화 API로 HTTP POST 요청을 전송하는 함수는?
        response = requests.____________________(spring_api_url, timeout=3)
        if response.status_code == 200:
            print("✅ 스프링 부트로부터 통계 데이터 동기화 성공!")
        else:
            print(f"⚠️ 스프링 부트 응답 코드: {response.status_code}")
    except Exception as e:
        print(f"❌ 스프링 부트 서버 연결 실패: {e}")

    qs = ServiceLog.objects.all().values('date', 'category', 'visitor_count', 'sales_amount')

    if qs.exists():
        df = pd.DataFrame(list(qs))

        # 📊 [Pandas 데이터 분석]
        # 💡 [필기] 카테고리별로 그룹화하여 방문자수와 매출액의 합계를 구하는 연산자
        summary_df = df.groupby('____________________')[['visitor_count', 'sales_amount']].____________________().reset_index()

        total_visitors = summary_df['visitor_count'].sum()
        if total_visitors > 0:
            summary_df['visitor_share'] = (summary_df['visitor_count'] / total_visitors * 100).round(1)
        else:
            summary_df['visitor_share'] = 0

        # 주요 통계 지표 산출
        stats_summary = {
            'avg_visitors': round(df['visitor_count'].mean(), 1),
            'max_visitors': int(df['visitor_count'].max()),
            'min_visitors': int(df['visitor_count'].min()),
            'total_count': int(df['visitor_count'].sum()),
        }

        # 💡 [필기] 최다 실적을 기록한 카테고리의 인덱스 번호를 구하는 함수는?
        top_category_idx = summary_df['visitor_count'].____________________()
        top_category = summary_df.loc[top_category_idx, 'category']

        categories = summary_df['category'].tolist()
        visitors = summary_df['visitor_count'].tolist()
        sales = summary_df['sales_amount'].tolist()
        shares = summary_df['visitor_share'].tolist()

    else:
        categories, visitors, sales, shares = [], [], [], []
        stats_summary = {'avg_visitors': 0, 'max_visitors': 0, 'min_visitors': 0, 'total_count': 0}
        top_category = "데이터 없음"

    context = {
        'categories': categories,
        'visitors': visitors,
        'sales': sales,
        'shares': shares,
        'stats_summary': stats_summary,
        'top_category': top_category,
    }
    return render(request, 'analytics/dashboard.html', context)


# 💡 [필기] 외부 시스템의 POST 요청 수신을 위해 CSRF 검증을 해제하는 데코레이터는?
@____________________
def api_receive_statistics(request):
    if request.method == 'POST':
        try:
            data = json.loads(request.body)
            
            log_date = data.get('date')
            category = data.get('category', '커뮤니티')
            count_val = data.get('count', 0)
            
            # 💡 [필기] 새로고침 시 데이터 중복 누적을 방지하고, 덮어쓰기(UPDATE OR CREATE)하는 ORM 메서드는?
            ServiceLog.objects.________________________________________(
                date=log_date,
                category=category,
                defaults={
                    'visitor_count': count_val,
                    'sales_amount': 0
                }
            )
            
            return JsonResponse({'status': 'success', 'message': '통계 데이터 갱신 완료!'})
        except Exception as e:
            return JsonResponse({'status': 'error', 'message': str(e)}, status=400)
            
    return JsonResponse({'status': 'fail', 'message': 'POST 요청만 지원합니다.'}, status=405)

```

---

### Step 2. Django 라우팅 경로 추가 (`analytics/urls.py`)

```python
from django.urls import path
from . import views

urlpatterns = [
    # 💡 [필기] 대시보드 뷰와 연결되는 기본 경로 지정
    path('', views.____________________, name='dashboard'),
    path('api/statistics/', views.____________________, name='api_receive_statistics'),
]

```

---

### Step 3. Spring Boot 데이터 전송 서비스 작성 (`StatisticsSyncService.java`)

```java
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

```


```java
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
```




---

### Step 4. Spring Security 예외 및 경로 허용 설정

1. **`JwtAuthenticationFilter.java`:**

```java
// 💡 [필기] 토큰 검증 필터를 거치지 않도록 예외 경로를 처리하는 메서드는?
@Override
protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    String path = request.getRequestURI();
    return path.startsWith("/uploads/") || path.startsWith("/api/statistics/");
}

```

2. **`SecurityConfig.java`:**

```java
// 💡 [필기] /api/statistics 경로에 대해 인증 없이 접근을 허용하는 Security 메서드는?
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/statistics/**").permitAll()
            .anyRequest().authenticated()
        );
    return http.build();
}

```

---

## 4. 최종 시동 및 동기화 동작 확인

```bash
# 💡 [필기] Django 서버 실행 명령어
python ____________________ runserver

```