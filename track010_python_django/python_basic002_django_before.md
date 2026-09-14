# 📖 Python Django + Pandas 대시보드 프로젝트

---

## 1. 개발 환경 설정 및 명령 도구 점검

### 1-1. 파이썬 가상환경 생성 및 활성화

```bash
# 작업 폴더 이동 후 가상환경 생성 

# 가상환경 활성화 (Windows CMD) 
# (Windows PowerShell인 경우: .\venv\Scripts\Activate.ps1)
# (Mac/Linux인 경우: source venv/bin/activate)


```

### 1-2. pip 패키지 관리자 업그레이드

* `pip`: 파이썬 패키지 인덱스(PyPI)의 패키지 설치/관리 도구

```bash
# pip: Node.js 의 npm, Java Gradle/Maven 같은 패키지 설치 관리자
python -m pip install --upgrade pip

# pip 정상 설치 및 현재 버전 확인
pip --version
```

### 1-3. 필수 패키지(Django, Pandas) 한번에 설치

```bash
# 웹 프레임워크 및 데이터 분석 패키지 통합 설치 
# Django : 웹 프레임 워크
# Pandas : 데이터 분석/집계 라이브러리
pip install Django pandas
```

```bash
# ✏️ 연습문제 & 개념 점검 [Section 1]
# Q1. 독립된 개발 환경을 구축하기 위해 생성하고 활성화한 파이썬 가상환경 폴더의 이름은 무엇인가요?
# 답: (                                             )

# Q2. 파이썬 패키지 설치/관리 도구인 pip를 최신 버전으로 업그레이드할 때 사용하는 옵션 키워드는 무엇인가요?
# 답: -- (                                             )

```

---

## 2. Django 핵심 개념: MVT 패턴
```bash
# 타언어의 MVC 패턴과 거의 동일 (mvc == mtv)
# JAVA
# Model    - 데이터 관련: Entity/Dao/Dto
# Template - 화면 구성: html, jsp, Thymesleaf etc.
# View     - 비즈니스 로직: Controller etc.
```

| MVC 패턴 (일반 웹) | MTV 패턴 (Django) | 주요 역할 및 담당 기능 |
| --- | --- | --- |
| **Model** | **Model** | DB 데이터 구조 관리 및 ORM 매핑 |
| **View** | **Template** | 사용자 화면 구성 (HTML/CSS/JS) |
| **Controller** | **View** | 비즈니스 로직 처리, DB와 Template 연결 |

```text
# ✏️ 연습문제 & 개념 점검 [Section 2]
# Q1. Django에서 사용자 화면(HTML)을 구성하는 템플릿 영역은 MVT 중 어디에 해당하나요?
# 답: (                                             )

# Q2. Django의 View가 담당하는 주요 역할 2가지를 간단히 적어보세요.
# 답: 1) (                                  ) / 2) (                                  )

```

---

## 3. 프로젝트 및 앱(App) 기초 세팅

### 3-1. 작업 디렉토리 생성 및 이동

```bash
# 프로젝트 폴더 만들기 / 프로젝트 이동
mkdir project
cd project
```

### 3-2. Django 프로젝트 생성

```bash
# analysis_pjt 생성 및 이동
# django-admin startproject 프로젝트명
django-admin startproject analysis_pjt
cd analysis_pjt
```

### 3-3. 프로젝트 이름 변경 방법 (참고용)

```bash
# 예시: tempPjt를 tempProject로 변경 시
move teamPjt tempProject 
```

### 3-4. 분석용 애플리케이션(App) 생성

```bash
# 장고 프로젝트는 여러개의 App (기능단위모듈)로 구성
# analytics 앱 생성
# python manage.py startapp 앱이름 # 게시판, 장바구니
python manage.py startapp analytics
```

### 3-5. 서버 실행 및 접속 확인

```bash
python manage.py runserver

http://127.0.0.1:8080
http://127.0.0.1:8080/admin
```

```bash
# ✏️ 연습문제 & 개념 점검 [Section 3]
# Q1. 전체 웹사이트 설정 집합은 ( 프로젝트 / 앱 )이고, 독립적인 기능 단위는 ( 프로젝트 / 앱 )입니다.
# 답: (                                             )

# Q2. 로컬 개발 서버를 구동하는 명령어는 무엇인가요?
# 답: python manage.py (                                             )
```

---

## 4. 최종 프로젝트 디렉토리 구조

```text
analysis_pjt/          <-- 👈 [터미널 명령어 실행 기준점] (manage.py 위치)
│
├── analysis_pjt/      <-- 프로젝트 메인 설정 폴더
│   ├── __init__.py
│   ├── settings.py    <-- INSTALLED_APPS 및 타임존 설정
│   ├── urls.py        <-- 메인 URL 라우팅 및 리다이렉트 설정
│   └── wsgi.py
│
├── analytics/         <-- 데이터 분석 전담 앱 폴더
│   ├── migrations/
│   │   └── 0001_initial.py
│   ├── __init__.py
│   ├── admin.py       <-- [관리자] 관리자 페이지 모델 등록
│   ├── apps.py
│   ├── models.py      <-- [DB] ServiceLog 데이터 모델 정의
│   ├── views.py       <-- [로직] Pandas 데이터 집계 및 뷰 로직
│   ├── urls.py        <-- [라우팅] 앱 내부 URL 라우팅 설정
│   └── templates/     <-- [화면] 템플릿 폴더
│       └── analytics/
│           └── dashboard.html <-- [화면] 대시보드 시각화 HTML
│
├── db.sqlite3         <-- SQLite 데이터베이스 파일
└── manage.py          <-- Django 실행 제어 파일

```

```bash
# ✏️ 연습문제 & 개념 점검 [Section 4]
# Q1. 모든 터미널 명령어 실행 기준이 되는 핵심 파일은 무엇인가요?
# 답: (                                             )

```

---

## 5. 실전 대시보드 프로젝트 구체적 구현 단계

### Step 1. 프로젝트 설정 (`analysis_pjt/settings.py`)

```python
INSTALLED_APPS = [
    # ... 기존 기본 앱들 ...
    # 새로 생성한 앱 등록
    'analytics'
]

# 한국어 및 한국 표준시 설정
LANGUAGE_CODE = 'ko-kr'
TIME_ZONE = 'Asia/Seoul'

```

### Step 2. 데이터 모델 정의 (`analytics/models.py`)

```python
# DB에 생성할 데이터구조를 Class로 작성(JPA Entity 선언 동일)
from django.db import models

class ServiceLog(models.Model):
    # date, category, visitor_count, sales_amount 필드 정의
    # DateField : YYYY-MM-DD 날짜필드
    date=models.DateField(verbose_name="날짜")
    category=models.CharField(max_length=50, verbose_name="카테고리")
    visitor_count=models.IntegerFiled(default=0, verbose_name="방문자 수")
    sales_amount=models.IntegerFiled(default=0, verbose_name="매출액")
    # Java toString 해당기능
    def __str__(self):
        return f"[{self.date}] {self.category} 로그"


```

### Step 3. 관리자 페이지 등록 (`analytics/admin.py`)

```python
from django.contrib import admin
from .models import ServiceLog

# ServiceLogAdmin 클래스 정의 및 등록
@admin.register(ServiceLog)
class ServiceLogAdmin(admin.ModelAdmin): 
    list_display = ('date', 'category', 'visitor_count', 'sales_amount')

```

### Step 4. DB 마이그레이션 적용

```bash
# 1. 마이그레이션 생성 - models.py 변경사항을 db 설계도를 생성
python manage.py makemigrations

# 2. DB 적용 - db.sqlite3 데이터 베이스에 반영해서 테이블을 생성
python manage.py migrate

```

```python
# ✏️ 연습문제 & 개념 점검 [Step 1~4]
# Q1. DB 변경사항 청사진을 만들고(1) 적용(2)하는 연속 명령어를 적으세요.
# 답: 1) python manage.py (                     ) / 2) python manage.py (                     )

```

### Step 5. Superuser 생성 및 샘플 데이터 입력

```bash
# 관리자 계정 생성
python manage.py createsuperuser

# 서버 실행 후 admin 페이지 접속해서 데이터 생성
python manage.py runserver

```

```text
사용자 이름 (leave blank to use 'user'): admin
이메일 주소: admin@example.com
Password: 
Password (again): 
Bypass password validation and create user anyway? [y/N]: y
Superuser created successfully.
```

```
* 예시 1) 날짜: 오늘 / 카테고리: `커뮤니티` / 방문자 수: `150` / 매출액: `10000`
* 예시 2) 날짜: 오늘 / 카테고리: `커머스` / 방문자 수: `300` / 매출액: `50000`
* 예시 3) 날짜: 오늘 / 카테고리: `콘텐츠` / 방문자 수: `500` / 매출액: `60000`
```

# M(model) T(html,css:화면) V(Controller:비즈니스로직, db와view 연결)


### Step 6. Pandas를 활용한 대시보드 뷰 구현 (`analytics/views.py`)

```python
import pandas as pd
from django.shortcuts import render
from .models import ServiceLog

def dashboard_view(request):
    # 1. DB 전체 데이터를 QuerySet으로 추출 후 Pandas DataFrame으로 변환
    qs = ServiceLog.objects.all().values( 'date' , 'category' , 'visitor_count' , 'sales_amount')

    if qs.exists():
        df = pd.DataFrame(list(qs))
        # 2. Pandas 연산: 카테고리별 방문자 수 및 매출액 합계 집계
        #   1) 그룹핑 합계 다시정렬
        summary_df = df.groupby('category')[['visitor_count' , 'sales_amount']].sum().reset_index()
        # 3. 템플릿 전달용 순수 파이썬 리스트 추출
        categories = summary_df['category'].tolist()
        visitors   = summary_df['visitor_count'].tolist()
        sales      = summary_df['sales_amount'].tolist()
    else:
        categories, visitors, sales = [],[],[]

    #4. 템플릿(html) 전달할 때 바인딩 객체 (Spring - Model, ModelAndView)
    context = {
        'categories': categories,
        'visitors': visitors,
        'sales': sales,
    }
    return render(request, 'analytics/dashboard.html', context)
```

### Step 7. URL 라우팅 설정

```python
# 1) analytics/urls.py 생성 및 작성 [앱]
from django.urls import path
from . import views

urlpatterns = [
    # http://127.0.0.1:8000/dashboard/ 요청시 views.py 의 dashboard_view 연결
    # 기본경로, 해결사:처리.dashboard_view, 이름
    path('', views.dashboard_view, name='dashboard'),
]

# 2) analysis_pjt/urls.py 수정 [프로젝트]
from django.contrib import admin
from django.urls import path , include
from django.views.generic import RedirectView

urlpatterns = [
    # Admin 관리자 라우팅
    path('admin/', admin.site.urls),
    # /dashboard/ 주소로 들어오는 애들
    path('dashboard/', include('analytics.urls'))

    # http://127.0.0.1:8000 접속시, /dashboard/로 자동이동
    path('', RedirectView.as_view(url='/dashboard/', permanent=False)),
]

```

### Step 8. 대시보드 HTML 템플릿 작성 (`analytics/templates/analytics/dashboard.html`)

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>파이썬 데이터 분석 대시보드</title>
    <!-- Chart.js 라이브러리 -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <div style="width: 750px; margin: 50px auto;">
        <h2>Python + Pandas 카테고리별 분석 대시보드</h2>
        <canvas id="analysisChart"></canvas>
    </div>

    <!-- 안전한 데이터 전달을 위한 Django json_script 필터 사용 -->
    <!-- 장고에서 내장필터 json_script: 파이썬의 list로 
        <script id="categories-data" type="application/json">[카테고리1, 카테고리2]</script> 변환 
    -->
    {{ categories | json_script: "categories-data"}}
    {{ visitors | json_script: "visitors-data"}}
    {{ sales | json_script: "sales-data"}}
    <script>
        const ctx = document.getElementById('analysisChart').getContext('2d');
        // JSON 파싱을 통해 안전하게 데이터 수신
        const chartCategories = JSON.parse(document.getElementById('categories-data').textContent);
        const chartVisitors = JSON.parse(document.getElementById('visitors-data').textContent);
        const chartSales = JSON.parse(document.getElementById('sales-data').textContent);

        // Chart.js 인스턴스 생성
        const analysisChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: chartCategories,  
                datasets: [
                    {
                        label: '카테고리별 방문자 수 합계 (명)',
                        data: chartVisitors, 
                        backgroundColor: 'rgba(54, 162, 235, 0.5)',
                        borderColor: 'rgba(54, 162, 235, 1)',
                        borderWidth: 1
                    },
                    {
                        label: '카테고리별 총 매출액 (원)',
                        data: chartSales, // Y축 2번 데이터 (매출액)
                        backgroundColor: 'rgba(255, 99, 132, 0.5)',
                        borderColor: 'rgba(255, 99, 132, 1)',
                        borderWidth: 1
                    }
                ]
            },
            options: {
                responsive: true,
                scales: {
                    y: { beginAtZero: true } // y 축 0부터 시작
                }
            }
        });

    </script>
</body>
</html>

```

```javascript
// ✏️ 연습문제 & 개념 점검 [최종 종합]
// Q1. Django 템플릿 변수를 JS 객체로 안전하게 전달하기 위해 사용한 필터는?
// 답: (                                             )
// Q2. Pandas에서 그룹별 합계를 구하기 위해 사용한 대표 함수 2개는?
// 답: (                                             ), (                                             )

```
