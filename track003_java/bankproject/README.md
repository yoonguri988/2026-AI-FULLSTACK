# 🏦 BankProject

> Java 콘솔 기반 은행 입출금 관리 시스템

---

## 📌 프로젝트 개요

사용자의 계정 정보를 등록하고, **입금·출금·조회·탈퇴** 기능을 제공하는 콘솔 애플리케이션입니다.

**Command Pattern**과 **계층형 아키텍처(Controller → Service → Repository)**를 직접 설계·적용하였으며, 반복적인 리팩토링을 통해 관심사 분리와 단일 책임 원칙을 코드 수준에서 체득한 프로젝트입니다.

---

## 🎯 개발 목표

- Java 객체지향 설계 원칙(OOP, Interface, 단일 책임 원칙)을 실제 서비스에 적용
- MVC 패턴과 계층형 아키텍처(Controller → Service → Repository)를 직접 설계·구현
- Command Pattern을 통해 기능 확장에 유연한 구조 설계 경험
- 단일 클래스 구현에서 출발해 점진적 리팩토링으로 클린 아키텍처에 도달하는 과정 경험

---

## 🛠 개발 스택

| 항목 | 내용 |
|------|------|
| Language | Java 11 |
| IDE | Eclipse |
| 빌드 방식 | Eclipse 내장 컴파일러 |
| 데이터 저장 | In-Memory (HashMap) |
| 패키지 구조 | 기능 기반 단일 패키지 (`com.the703.v1`) |

---

## 🏗 서비스 구조

### 계층형 아키텍처

```
[콘솔 입력]
     ↓
[Controller] - UserController
     ↓
[Command] - RegisterCommand / SearchCommand / DepositCommand / WithdrawalCommand / RemoveCommand / ExitCommand
     ↓
[Service] - UserService (Interface) / UserServiceImpl
     ↓
[Repository] - UserRepository
     ↓
[In-Memory Store] - HashMap<String, User>
```

### 패키지 구성

```
com.the703.v1
├── basic          # 앱 공통 기반 (Main, AppStatus, InputHandler)
├── domain         # 도메인 모델 (User)
├── controller     # 메뉴 라우팅 및 커맨드 실행 (UserController)
├── view           # 콘솔 입출력 전담 (UserInputView)
├── service        # 비즈니스 로직 (UserService, UserServiceImpl)
├── repository     # 데이터 접근 계층 (UserRepository)
└── command        # 커맨드 구현체 (MenuCommand 및 각 Command)
```

### 주요 설계 포인트

- **Command Pattern**: `UserController`가 `Map<Integer, MenuCommand>`로 커맨드를 관리하여, 새 기능 추가 시 `if-else` 분기 수정 없이 커맨드 등록만으로 확장 가능한 구조 구현
- **MenuCommand 인터페이스**: `input()` / `execute()` 메서드를 강제하여 모든 기능을 일관된 흐름으로 처리
- **관심사 분리**: View는 입출력만, Service는 비즈니스 검증만, Repository는 CRUD만 담당하도록 레이어 역할을 엄격히 분리
- **인터페이스 기반 설계**: `UserService`를 인터페이스로 추상화하여 구현체 교체 시 상위 레이어에 영향 없는 구조 확보

---

## ⚙️ 주요 기능

### 👤 회원 관리
| 기능 | 설명 |
|------|------|
| 회원 등록 | 아이디, 비밀번호, 나이, 초기 잔액 입력 후 계정 생성 |
| 회원 조회 | 아이디 + 비밀번호 인증 후 계정 정보 출력 |
| 회원 탈퇴 | 인증 후 재확인 절차를 거쳐 계정 삭제 |

### 💰 입출금 처리
| 기능 | 설명 |
|------|------|
| 입금 | 아이디 + 비밀번호 인증 후 입력 금액만큼 잔액 증가 |
| 출금 | 인증 후 잔액 부족 여부 검증 → 통과 시 잔액 차감 |

### 🛡 입력 안전성
- `NumberFormatException` 발생 시 재입력 유도 (루프 처리)
- 최대 반복 횟수(100만 회) 초과 시 커스텀 예외(`TooManyIterationsException`) 발생으로 무한루프 방어

---

## 💡 구현 시 고민한 점

**1. Command Pattern으로 분기 로직 제거**
초기에는 `while`문 안에 `if-else`로 메뉴를 분기했으나, 기능이 늘어날수록 컨트롤러가 비대해지는 문제가 발생했습니다. `Map<Integer, MenuCommand>`으로 커맨드를 등록하는 방식으로 전환하여, 새 기능 추가 시 컨트롤러를 수정하지 않아도 되는 개방-폐쇄 원칙(OCP)에 가까운 구조를 구현했습니다.

**2. 관심사 분리의 점진적 적용**
초기 버전에서는 Service 레이어에 `System.out.println`이 혼재되어 있었습니다. View와 Service의 책임 경계를 명확히 하는 리팩토링을 거쳐, Service는 검증과 반환만, View는 출력만 담당하는 구조로 개선했습니다.

**3. 입력 예외의 안전한 처리**
숫자 입력 시 `NumberFormatException`이 발생하면 무한루프로 재입력을 유도하되, 비정상적인 반복을 막기 위해 최대 반복 횟수를 두고 초과 시 커스텀 예외로 시스템을 안전하게 종료하도록 설계했습니다.

---

## 🔄 버전별 개발 히스토리

| 버전 | 주요 변경 내용 |
|------|--------------|
| v0.0.0 | 단일 클래스 기반 기능 구현 (등록·조회·삭제·입출금·종료) |
| v0.1.0 | 반복 입력 로직을 `UserInputView`로 분리하여 코드 재사용성 향상 |
| v0.2.0 | `UserController` 도입 / `MenuCommand` 인터페이스로 분기 로직 제거 / `AppStatus`로 종료 흐름 정리 |
| v1.0.0 | Service에서 출력 로직 제거(관심사 분리 완성) / Command별 `input()` 메서드 독립 부여 |
| v1.0.1 | Command 배열 처리 및 `input()` 관심사 분리 최종 정리 |

---

## 🖥 실행 방법

```bash
# Eclipse에서 실행
1. 프로젝트를 Eclipse로 import
2. src/com/the703/v1/Main.java 실행
```

**메뉴 안내**
```
1. 회원 등록
2. 회원 조회
3. 입금
4. 출금
5. 회원 탈퇴
9. 시스템 종료
```

---

## 📁 프로젝트 구조

```
bankproject/
├── src/
│   └── com/the703/v1/
│       ├── Main.java
│       ├── AppStatus.java
│       ├── InputHandler.java
│       ├── User.java
│       ├── UserController.java
│       ├── UserInputView.java
│       ├── UserService.java
│       ├── UserServiceImpl.java
│       ├── UserRepository.java
│       ├── MenuCommand.java
│       ├── RegisterCommand.java
│       ├── SearchCommand.java
│       ├── DepositCommand.java
│       ├── WithdrawalCommand.java
│       ├── RemoveCommand.java
│       └── ExitCommand.java
└── bin/                  # 컴파일된 클래스 파일
```

---

## ✅ Result

- 계층형 아키텍처(Controller → Service → Repository)를 순수 Java로 직접 설계하여 구조적 사고 능력을 실전에서 검증
- 단일 클래스 구현에서 출발해 5단계 리팩토링을 거쳐 관심사 분리와 Command Pattern이 적용된 구조로 점진적으로 개선
- `UserService` 인터페이스 추상화로 구현체 교체 시 상위 레이어에 영향 없는 유연한 설계 기반 확보
- 이후 Spring Boot + DB 연동 확장을 고려한 서비스 계층 구조 및 도메인 모델 설계를 기반 코드로 확보