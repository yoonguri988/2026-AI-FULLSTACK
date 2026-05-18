# 🥗 CaloriesTracker

> Java 콘솔 기반 칼로리 추적 및 영양 분석 시스템

---

## 📌 프로젝트 개요

사용자의 신체 정보를 기반으로 **하루 목표 칼로리를 자동 산출**하고, 섭취한 음식의 영양 성분을 기록·조회·분석할 수 있는 콘솔 애플리케이션입니다.

관리자(Admin)와 일반 사용자를 구분한 **역할 기반 접근 제어(RBAC)**를 구현하였으며, 객체지향 설계 원칙을 적용하여 유지보수성을 높였습니다.

---

## 🎯 개발 목표

- Java의 핵심 개념(OOP, Interface, Collection, I/O)을 실제 서비스에 적용
- MVC 패턴과 계층형 아키텍처(Controller → Service → Repository)를 직접 설계·구현
- 파일 기반 데이터 영속성 처리를 통해 DB 없이 CRUD를 구현
- 사용자 역할에 따른 기능 분리로 실무적인 권한 관리 경험

---

## 🛠 개발 스택

| 항목 | 내용 |
|------|------|
| Language | Java 11 |
| IDE | Eclipse |
| 빌드 방식 | Eclipse 내장 컴파일러 |
| 데이터 저장 | 텍스트 파일 (`.txt`), BufferedReader / BufferedWriter |
| 패키지 구조 | 도메인 기반 패키지 분리 (`auth`, `user`, `food`, `basic`) |

---

## 🏗 서비스 구조

### 계층형 아키텍처

```
[콘솔 입력]
     ↓
[Controller] - AuthController / UserController
     ↓
[Service] - AuthService / UserService / FoodService
     ↓
[Repository] - UserRepository / FoodRepository
     ↓
[File I/O] - users.txt / {email}_food.txt
```

### 패키지 구성

```
cyj.tracker
├── basic          # 앱 공통 기반 (Main, AppStatus, InputHandler, TrackerService 등)
├── auth           # 인증 도메인 (로그인, 로그아웃)
├── user           # 사용자 도메인 (회원 CRUD, 칼로리 분석)
└── food           # 음식 도메인 (음식 CRUD)
```

### 주요 설계 포인트

- **TrackerControllerManager**: 로그인 상태에 따라 `AuthController` 또는 `UserController`를 동적으로 반환하는 상태 기반 라우팅 구현
- **TrackerFunction 인터페이스**: `input()` / `execute()` 메서드 강제로 모든 기능을 일관된 흐름으로 처리
- **역할 분리**: `admin` 계정은 전체 회원 관리 기능 접근 가능, 일반 사용자는 본인 데이터만 접근
- **파일 영속성**: 사용자별로 독립된 파일(`{email}_food.txt`)에 음식 기록을 저장·로드

---

## ⚙️ 주요 기능

### 🔐 인증
| 기능 | 설명 |
|------|------|
| 회원가입 | 이메일, 비밀번호, 신체 정보 입력 및 저장 |
| 로그인 | 이메일/비밀번호 검증 후 세션 유지 |
| 로그아웃 | 현재 사용자 세션 초기화 |

### 👤 회원 관리 (Admin 전용)
| 기능 | 설명 |
|------|------|
| 회원 조회 | 이메일로 회원 정보 검색 |
| 회원 정보 수정 | 신체 정보 업데이트 |
| 회원 삭제 | 회원 및 관련 데이터 삭제 |

### 🍽 음식 기록
| 기능 | 설명 |
|------|------|
| 음식 등록 | 음식명, 칼로리, 탄수화물, 단백질, 지방 입력 |
| 음식 조회 | 로그인 사용자의 오늘 섭취 기록 전체 조회 |
| 음식 삭제 | 음식명으로 특정 항목 삭제 |

### 📊 영양 분석
- **BMR(기초대사량)** 자동 계산: `10 × 체중 + 6.25 × 키 - 5 × 나이 + 5` (Mifflin-St Jeor 공식)
- **TDEE(일일 총 에너지 소비량)** 산출: BMR × 활동량 계수(1.2 ~ 1.725)
- 목표 칼로리 대비 섭취 칼로리 비교 및 피드백 제공

---

## 💡 구현 시 고민한 점

**1. 상태 기반 컨트롤러 라우팅**
로그인 여부를 매 루프마다 `AppStatus`와 `AuthService`로 확인하여, 분기 처리 없이 올바른 컨트롤러가 자동 선택되도록 설계했습니다.

**2. 파일 I/O 예외 처리**
파일/폴더 미존재 상황을 모두 방어적으로 처리하여, 초기 실행 시에도 데이터 파일이 자동 생성됩니다.

**3. 도메인 간 의존성 최소화**
`TrackerService`를 통해 각 도메인 서비스(`AuthService`, `UserService`, `FoodService`)를 일원화하여, Controller가 직접 Repository에 의존하지 않도록 했습니다.

---

## 🗂 데이터 저장 형식

**users.txt** (파이프 구분자)
```
이메일|비밀번호|이름|나이|키|몸무게|활동량계수|목표칼로리
test@test.com|1234|테스트|20|160.2|55.0|1|2002.3
```

**{email}_food.txt**
```
음식명|칼로리|탄수화물|단백질|지방
샐러드|180.0|18.2|101.8|60.0
```

---

## 🖥 실행 방법

```bash
# Eclipse에서 실행
1. 프로젝트를 Eclipse로 import
2. src/cyj/tracker/basic/Main.java 실행
```

---

## 📁 프로젝트 구조

```
caloriestracker/
├── src/
│   └── cyj/tracker/
│       ├── basic/        # 앱 진입점 및 공통 인터페이스
│       ├── auth/         # 인증 처리
│       ├── user/         # 회원 도메인
│       ├── food/         # 음식 도메인
│       └── data/         # 파일 데이터 저장소
└── bin/                  # 컴파일된 클래스 파일
```

---

## ✅ Result

- 계층형 아키텍처(Controller → Service → Repository)를 순수 Java로 직접 설계하여 구조적 사고 능력을 실전에서 검증
- 핵심 도메인(인증 / 회원 / 음식 / 영양 분석) 흐름을 1차 버전에서 완성하고, 각 도메인 간 의존성을 인터페이스로 분리
- 파일 기반 데이터 영속성 처리로 DB 없이 CRUD 전 사이클을 구현하며 I/O 흐름 및 예외 처리 경험 확보
- 이후 Spring Boot + DB 연동 확장을 고려한 서비스 계층 구조 및 도메인 모델 설계를 기반 코드로 확보