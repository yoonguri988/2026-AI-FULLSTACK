
# 📖 Flutter 개발 환경 구축 및 기초 실습 가이드 (Windows 기준)

---

## 1. 개발 환경 설정 및 Flutter SDK 설치

### 1-1. Flutter SDK 다운로드 (Git 활용)

```bash
# Git을 이용하여 지정된 경로(C:\flutter)에 stable 채널 SDK 클론
git clone https://github.com/flutter/flutter.git -b stable C:\\flutter
```

### 1-2. 환경 변수(Path) 설정 및 실행 테스트

* `Path` 추가: `C:\flutter\bin`
* 환경 변수가 정상 등록되었는지 SDK 상태 및 의존성을 전체 점검합니다.

```bash
# Flutter 환경 점검 명령어
flutter doctor

# (참고) 실행 실패 시 직접 실행 파일 호출
C:\flutter\bin\flutter.bat doctor
```

```bash
# ✏️ 연습문제 & 개념 점검 [Section 1]
# Q1. Flutter SDK 상태, 설치된 개발 도구, 접속 기기 등을 종합 점검하는 CLI 명령어는 무엇인가요?
# 답: (     flutter doctor      )

# Q2. Windows 환경에서 Flutter 명령어를 어디서나 실행할 수 있도록 시스템 변수에 추가해야 하는 폴더 경로는 어디인가요?
# 답: C:\flutter\(     bin    )

```

---

## 2. VS Code 설치 & 확장 프로그램 설정

### 2-1. VS Code 설치 필수 옵션

* VS Code 공식 사이트 설치 시 포함 필수 항목:
`C++를 사용한 데스크톱 개발 (Desktop development with C++)`

### 2-2. 필수 확장 프로그램 (Ctrl + Shift + X)

```text
1. Flutter (Dart 확장 자동 포함)
2. Dart

```

```text
# ✏️ 연습문제 & 개념 점검 [Section 2]
# Q1. VS Code에서 확장 프로그램(Extensions) 마켓플레이스를 열기 위한 단축키는 무엇인가요?
# 답: Ctrl + ( shift ) + ( x )


```

---

## 3. 앱 실행 환경 구축 (Android Studio & 라이선스 설정)

### 3-1. Android Studio 설정

* **SDK Platforms:** 최신 API 레벨 설치 (예: Android 14)
* **SDK Tools 필수 체크:**
* Command-line Tools
* Emulator
* Platform-Tools
* Build-Tools



### 3-2. 안드로이드 라이선스 동의 및 기기 확인

```bash
# 안드로이드 SDK 라이선스 동의 명령어 [! 뜨면 할것]
flutter doctor --android-licenses

# 현재 연결된 실행 가능 기기 목록 확인 (Chrome, Windows, Emulator 등)
flutter devices

```

```bash
# ✏️ 연습문제 & 개념 점검 [Section 3]
# Q1. flutter doctor 실행 시 'Some Android licenses not accepted' 경고(!) 발생 해결 명령어는 무엇인가요?
# 답: flutter doctor --(                                 )

# Q2. 현재 내 PC에 연결되어 앱을 구동할 수 있는 디바이스 리스트를 조회하는 명령어는 무엇인가요?
# 답: flutter (                                         )

```

---

## 4. 첫 프로젝트 생성, 코드 구조 및 Hot Reload

### 4-1. 프로젝트 생성 및 구동

```bash
# 1. mobile1 이름으로 첫 프로젝트 생성 
flutter create moblie1

# 2. 프로젝트 디렉토리 이동 
cd moblie

# 3. 앱 실행 (또는 Chrome 웹 브라우저 타겟 실행)
flutter run

```

### 4-2. 메인 코드 수정 (`lib/main.dart`)

```dart
import 'package:flutter/material.dart';

void main() {
  runApp(
    MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('내 첫 Flutter 앱'),
        ),
        body: Center(
          child: Text('Hello Flutter!'),
        ),
      ),
    ),
  );
}

```

### 4-3. 핵심 위젯 및 속성 개념 정의

| 위젯 / 속성 | 주요 역할 및 담당 기능 |
| --- | --- |
| **`home`** | 앱이 실행될 때 가장 먼저 화면에 보여주는 기본 시작 페이지 지정 |
| **`Scaffold`** | 화면의 전체적인 기본 뼈대(상단바, 본문, 하단 탭 등)를 제공하는 기본 위젯 |
| **`appBar`** | 앱 화면 최상단에 위치하는 헤더 영역 (`title`에 `Text` 위젯 탑재) |
| **`body`** | 화면의 실제 콘텐츠가 배치되는 본문 영역 |
| **`Center`** | 자식(`child`) 위젯을 화면 정중앙에 배치하는 위젯 |
| **`child`** | 단일 자식 위젯을 포함할 때 사용하는 속성 |

### 4-4. 터미널 단축키 기능

* **`r` (Hot Reload):** 앱의 상태(State)를 유지하면서 변경된 UI 코드만 즉시 화면에 반영
* **`R` (Hot Restart):** 앱을 완전히 재시작하여 초기 화면 및 상태부터 다시 빌드

```dart
// ✏️ 연습문제 & 개념 점검 [Section 4]
// Q1. 앱 화면의 전체적인 뼈대(상단바, 본문 등)를 잡아주는 표준 구조 위젯은 무엇인가요?
// 답: ( Scaffold )

// Q2. 앱을 껐다 켜지 않고 기존 상태를 유지하며 UI 수정 사항을 즉시 반영하는 단축키는 무엇인가요?
// 답: ( r )

// Q3. 앱의 모든 상태를 초기화하고 첫 화면부터 다시 구동하는 단축키는 무엇인가요?
// 답: ( R )

```

---

## 5. 필수 패키지 관리 (`pubspec.yaml`)

### 5-1. 환경 설정 파일 수정 (`pubspec.yaml`)

```yaml
name: mobile1
description: "A new Flutter project."
publish_to: 'none'

environment:
  sdk: '>=3.0.0 <4.0.0'

dependencies:
  flutter:
    sdk: flutter 
  http: ^1.1.0 
  provider: ^6.1.1 
  shared_preferences: ^2.2.2 
  go_router: ^12.1.3

```

### 5-2. CLI를 통한 패키지 설치

```bash
# pubspec.yaml에 명시된 모든 패키지 일괄 구출/설치


# 명령어로 직접 특정 패키지 추가
flutter pub add http
flutter pub add provider

```

### 5-3. 주요 패키지 역할 정리

* **`http`**: 서버와 REST API 통신(GET, POST 등)으로 데이터를 가져올 때 사용
* **`provider`**: 앱 내 상태(로그인 정보, 장바구니 등)를 효율적으로 관리하고 UI 자동 업데이트 제공
* **`shared_preferences`**: 앱 종료 후에도 유지되어야 하는 설정값, 토큰 등을 로컬 저장소에 저장
* **`go_router`**: URL 기반 경로 처리 및 화면 전환(Navigation)을 용이하게 관리

```yaml
# ✏️ 연습문제 & 개념 점검 [Section 5]
# Q1. Flutter 프로젝트의 환경 설정 및 외부 의존성 패키지를 정의하는 파일 이름은 무엇인가요?
# 답: ( pubsepc.yaml )

# Q2. pubspec.yaml에 작성된 패키지를 다운로드받아 설치하는 CLI 명령어는 무엇인가요?
# 답: flutter pub ( get )

# Q3. 앱을 껐다 켜도 다크모드 설정이나 로그인 토큰 값을 로컬 저장소에 저장해주는 패키지는 무엇인가요?
# 답: ( shared_preferences )

```

---

## 6. Dart 문법 실습 코드 (`lib/basic.dart`)

```dart
// 1. 실행법: 터미널에서 dart run lib/basic.dart 입력
// Flutter/Dart 기본 문법 연습용 종합 예제

void main() {
  // 1. 변수 선언 및 데이터 타입
 
  // 2. 리스트(List) - 순서가 있는 목록
 

  // 3. 맵(Map) - Key-Value 쌍 구조
 

  // 4. 조건문
 

  // 5. 반복문
 

  // 6. 함수(Function)
 

  // 7. 클래스(Class) 객체 생성 및 메서드 호출
 

  // 8. 비동기 처리 (Future, async/await)
 
}

// 클래스 정의
class Person {
 
}

// 비동기 함수 정의 (2초 후 완료)
 

```

```dart
// ✏️ 연습문제 & 개념 점검 [Section 6]
// Q1. Dart에서 미래에 완료될 비동기 작업 결과값을 표현할 때 사용하는 객체 타입은 무엇인가요?
// 답: (Future)

// Q2. 비동기 작업 완료를 기다리기 위해 함수 선언부에 ( A ), 호출부에 ( B ) 키워드를 작성합니다.
// 답: A: (async) / B: (await)

```

---

## 7. Windows 및 개발 팁 정리

### 7-1. PowerShell 실행 정책 변경 (Windows 7/10 필수)

```powershell
# 스크립트 실행 권한 부여
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser

```

### 7-2. VS Code 주요 단축키

* `F5`: 디버그 모드로 앱 실행
* `Ctrl + F5`: 디버그 없이 실행
* `Ctrl + Shift + P`: 명령어 팔레트 실행 (`Flutter: New Project` 등 실행)

 