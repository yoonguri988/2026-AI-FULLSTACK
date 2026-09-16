# 🦝 최윤정 | "왜?"로 시작해서 "됐다!"로 끝내는 풀스택 개발자

> 문제해결 · 실행력 · 일단 해보자.
> 막히면 일단 손을 움직여서 원인을 찾고, 원인을 찾으면 끝까지 고쳐서 "됐다!"를 만들어냅니다.

<br>

## 💙 개인정보

<table>
  <tr>
    <td><img src="its_me.jpg" alt="본인 사진" style="width: 200px" /></td>
    <td>
      <p>
        <a href="mailto:cyjjeong98@gmail.com">
          <img src="https://img.shields.io/badge/EMAIL-cyjjeong98%40gmail.com-EA4335?style=for-the-badge&logo=gmail&logoColor=white" alt="Email" />
        </a>
        <br/>
        <a href="https://app.notion.com/p/4c6038944be7827d96c8815724615b5c?source=copy_link">
          <img src="https://img.shields.io/badge/NOTION-포트폴리오-000000?style=for-the-badge&logo=notion&logoColor=white" alt="Notion" />
        </a>
        <a href="https://github.com/yoonguri988">
          <img src="https://img.shields.io/badge/GITHUB-yoonguri988-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub" />
        </a>
        <a href="https://yoonguri988.github.io/2026-AI-FULLSTACK/">
          <img src="https://img.shields.io/badge/GITHUB%20PAGES-포트폴리오%20웹페이지-222222?style=for-the-badge&logo=githubpages&logoColor=white" alt="GitHub Pages" />
        </a>
      </p>
    </td>
  </tr>
</table>

<br>

## 📖 이 레포는?

**[취업기업확대] AI활용 풀스택(프론트엔드·백엔드) 부트캠프(자바·파이썬·플러터)** 과정을 진행하며 쌓은 학습 기록 저장소입니다.

- `days/` : `day001`부터 하루하루 쓴 학습 일지. 오늘 뭘 배웠고, 뭘 실습했고, 다음엔 뭘 해야 하는지를 그날그날 정리했습니다.
- `track001_git` ~ `track010_python_django` : 주제별 실습 코드와 미니 프로젝트. Git으로 시작해 Python/Django로 마무리되는 10개 트랙에 300개가 훌쩍 넘는 실습 파일이 쌓여 있습니다.
- `docs/` : 포트폴리오 웹페이지 소스 (GitHub Pages로 배포).

한 줄로 요약하면, **"왜 안 되지?"로 시작한 하루가 "됐다!"로 끝나는 과정을 매일 반복하며 쌓아온 기록**입니다.

<br>

## 👋 ABOUT

처음 `git init`을 배우던 날과, 마지막으로 Spring Boot 4 + JPA + JWT + Redis + OAuth2.0 + React/Next.js로 팀 프로젝트를 완성한 날 사이에는 10개의 기술 트랙과 셀 수 없는 에러 로그가 있습니다.

저는 이론을 먼저 완벽히 이해하고 시작하는 스타일이 아닙니다. **일단 해보고, 안 되는 지점에서 "왜 안 될까"를 파고들며 배우는 쪽**입니다. 콘솔 기반 은행 프로젝트를 단일 클래스로 먼저 완성한 뒤 계층형 구조로 리팩토링했던 것도, Spring Boot 예제를 `boot0 → boot1 → boot2 → boot3`로 조금씩 기능을 얹어가며 완성한 것도 같은 방식이었습니다. 완벽한 설계도를 먼저 그리기보다, 돌아가는 것을 먼저 만들고 문제가 보이면 그 자리에서 구조를 바꿨습니다.

그 결과 Git/HTML부터 Java, DB, JSP, Spring, Spring Boot, React, Linux/AWS, Python/Django, 그리고 RAG 기반 생성형 AI 연동까지 — 풀스택의 한 사이클을 처음부터 끝까지 직접 손으로 통과했습니다. 지금은 그 경험을 팀 프로젝트(전사적 자원관리 ERP, spring-breeze-erp)로 확장해, 근태 · 연차 · 평가 · 급여로 이어지는 실무형 데이터 흐름을 백엔드 팀장으로서 설계하고 있습니다.

<br>

## 💜 VALUES

#### 🔥 일단 해보자, 그다음에 고친다

완벽한 설계보다 동작하는 코드를 먼저 만듭니다. `boot0`부터 `boot3`까지, `front1`부터 `front3`까지 — 작게 돌려보고 한 겹씩 쌓아 올리는 방식으로 실전 스택(JWT·Redis·OAuth2.0)을 몸에 익혔습니다.

#### 🎯 "왜?"에서 시작해 "됐다!"로 끝낸다

에러가 나면 일단 원인을 끝까지 추적합니다. 소셜 로그인 3사(구글·카카오·네이버)가 사용자 정보를 각각 다른 키(`sub` / `id` / `response`)로 내려준다는 것도, 직접 응답을 찍어보며 비교해서 알아냈습니다.

#### 🧩 문제해결은 구조로 한다

같은 실수를 반복하지 않기 위해 구조를 바꿉니다. 단일 클래스로 짠 은행 프로젝트를 Controller → Service → Repository 계층으로, 그리고 Command 패턴으로 리팩토링하며 "기능이 늘어도 무너지지 않는 구조"를 체득했습니다.

#### 🤝 실행력으로 협업까지 증명한다

배운 걸 개인 실습에서 끝내지 않고, 4인 팀 프로젝트(ERP 시스템)의 백엔드 팀장으로서 실제 서비스 수준까지 끌고 갑니다. 배움 → 실습 → 팀 프로젝트로 이어지는 실행력이 제 강점입니다.

<br>

---

<br>

## 🛠 기술스택

### BE

![Java](https://img.shields.io/badge/Java%2017-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot%204-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![JPA](https://img.shields.io/badge/JPA%20(Hibernate)-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![MyBatis](https://img.shields.io/badge/MyBatis-000000?style=for-the-badge&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)
![OAuth2.0](https://img.shields.io/badge/OAuth%202.0-3423A6?style=for-the-badge&logo=auth0&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-BC0031?style=for-the-badge&logoColor=white)
![Python](https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white)
![Django](https://img.shields.io/badge/Django-092E20?style=for-the-badge&logo=django&logoColor=white)
![DRF](https://img.shields.io/badge/Django%20REST%20Framework-A30000?style=for-the-badge&logo=django&logoColor=white)
![OpenAI](https://img.shields.io/badge/OpenAI%20GPT--4o--mini%20%2F%20RAG-412991?style=for-the-badge&logo=openai&logoColor=white)
![PDFBox](https://img.shields.io/badge/Apache%20PDFBox-D22128?style=for-the-badge&logo=apache&logoColor=white)
![Naver OCR](https://img.shields.io/badge/Naver%20CLOVA%20OCR-03C75A?style=for-the-badge&logo=naver&logoColor=white)
![CoolSMS](https://img.shields.io/badge/CoolSMS-FF6600?style=for-the-badge&logoColor=white)
![data.go.kr](https://img.shields.io/badge/공공데이터(국세청)-0B6E4F?style=for-the-badge)

### FE

![React](https://img.shields.io/badge/React%2017-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![Next.js](https://img.shields.io/badge/Next.js%20(SSR)-000000?style=for-the-badge&logo=nextdotjs&logoColor=white)
![Ant Design](https://img.shields.io/badge/Ant%20Design-0170FE?style=for-the-badge&logo=antdesign&logoColor=white)
![Redux Toolkit](https://img.shields.io/badge/Redux%20Toolkit-764ABC?style=for-the-badge&logo=redux&logoColor=white)
![Redux Saga](https://img.shields.io/badge/Redux--Saga-999999?style=for-the-badge&logo=redux-saga&logoColor=white)

### DB / Redis

![Oracle](https://img.shields.io/badge/Oracle%2018c%20XE-F80000?style=for-the-badge&logo=oracle&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)

### DevOps

![AWS EC2](https://img.shields.io/badge/AWS%20EC2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)
![PM2](https://img.shields.io/badge/PM2-2B037A?style=for-the-badge&logo=pm2&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Linux](https://img.shields.io/badge/Linux-FCC624?style=for-the-badge&logo=linux&logoColor=black)
![Gunicorn](https://img.shields.io/badge/Gunicorn-499848?style=for-the-badge&logo=gunicorn&logoColor=white)

### Tool

![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)
![Discord](https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=discord&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)
![Figma](https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white)

<br>

---

<br>

## 🗺️ 커리큘럼 로드맵 — 트랙에서 프로젝트로

부트캠프는 "스택 학습 → 미니 실습 → 팀 프로젝트"가 4번 반복되는 구조였고, 그중 3번째 사이클까지 완주했습니다.

| 단계 | 학습 트랙 (레포 경로) | 핵심 스택 | 결과물 |
|:---:|---|---|---|
| 1 | `track001_git` · `track002_html` | Git/GitHub, HTML·CSS·JS, Bootstrap | 정적 페이지, 게시판 UI 레이아웃 |
| 2 | `track003_java` | Java(OOP), 제어문·컬렉션·파일 I/O | CaloriesTracker(RBAC), BankProject(Command 패턴 리팩토링) |
| 3 | `track004_1_mysql` · `track004_2_oracle` | MySQL/Oracle, ERD·정규화·서브쿼리 | 테이블 설계, ERD 실습 |
| 4 | `track005_jsp` · `track006_spring` | JSP, Spring IoC/DI, MyBatis | MVC 게시판(v1~v3), **프로젝트Ⅰ** 미니 프로젝트 |
| 5 | `track007_boot_api` | Spring Boot, REST API, RAG(PDFBox+RestClient+OpenAI) | 파일 기반 RAG 챗봇 실습, **프로젝트Ⅱ**(BtoC 웹서비스) |
| 6 | `track008_1_nodereact` | Node.js/Express, React + Redux-Saga + Passport | Node+React 게시판/투두 |
| 7 | `track008_2_bootreact` | Spring Boot4 + JPA + MyBatis + **JWT + Redis + OAuth2.0** + React/Next + Antd | **프로젝트Ⅲ(project3)** → 팀 캡스톤 ERP `spring-breeze-erp` v3 |
| 8 | `track009_linux_aws_std` | Linux, AWS EC2 | 배포 실습 |
| 9 | `track010_python_django` | Python, Django + Pandas | 데이터 분석 대시보드 |
| 다음 목표 | project4(예정) | Flutter | 매출/생산성 향상 앱 |

> 6~7단계에서 익힌 **Spring Boot4 + JPA + JWT + Redis + OAuth2.0 + React(Redux-Saga+Antd)** 조합이 지금 제 메인 스택입니다. 팀 프로젝트에서는 회사·부서·인증·자원예약·급여 도메인을 맡아 이 스택을 실무 수준까지 밀어붙였습니다.

<br>

---

<br>

## 🎓 교육과정 진행 프로젝트 — spring-breeze-erp (V1 ~ V4)

부트캠프 팀 캡스톤으로 시작한 ERP 시스템을 **V1 → V2 → V3(팀) → V4(개인 확장)**까지 4번에 걸쳐 고도화했습니다. 아래는 제(최윤정) 담당 파트 기준 README와 개인 시연 영상입니다.

| 버전 | 기간 | 핵심 변화 | README | 개인 시연 영상 |
|:---:|:---:|---|:---:|:---:|
| **V1** | 2025.06.11 ~ 06.26 (16일, 6인) | JSP · MySQL 기반 신규 구축 (회사·부서·인증 담당) | [README](https://github.com/yoonguri988/spring-breeze-erp/blob/main/spring-breeze-erp-v1/README.md) | [▶ 시연 영상](https://www.youtube.com/watch?v=nybOKBw8DDE) |
| **V2** | 2026.07.02 ~ 07.15 (14일, 4인) | Spring Boot · Thymeleaf · Oracle 리팩토링, OCR·국세청 API 등 AI/외부 API 8종 연동 (회사·부서·자원예약 담당) | [README](https://github.com/yoonguri988/spring-breeze-erp/blob/main/spring-breeze-erp-v2/README.md) | [▶ 시연 영상](https://youtu.be/IeCpmTbJOy0) |
| **V3** | 2026.08.01 ~ 08.28 (28일, 4인) | Spring Boot 4 · JPA · JWT+Redis · React/Next.js 전면 재구축, 근태·연차·급여·채용 신규 (회사·부서·인증·자원예약·**급여** 담당) | [README](https://github.com/yoonguri988/spring-breeze-erp/blob/main/spring-breeze-erp-v3/README.md) | [▶ 시연 영상](https://youtu.be/AnIZ6A84pB8) |
| **V4** | v3 이후 개인 확장 | AWS CI/CD(GitHub Actions→EC2) 자동 배포 + Python/Django/pandas 급여 분석 서비스 개인 개발 | [README](https://github.com/yoonguri988/sberp/blob/main/README.md) | 영상 없음 (인프라·백엔드 확장 중심 프로젝트) |

> 전체 진화 과정과 팀원 4명의 담당 업무·트러블슈팅 전체 사례는 [spring-breeze-erp 메인 README](https://github.com/yoonguri988/spring-breeze-erp/blob/main/README.md)에서 확인할 수 있습니다.

### 🎬 Demo 하이라이트 — V3 (팀 캡스톤 최종형)

- **급여 산정 엔진**: `SalaryItemCalculator` 인터페이스 기반 계산기 10종을 Spring `List`로 주입, 계산 순서 의존성을 Strategy + DI 구조로 해결
- **급여 규정 AI Q&A(RAG)**: 사내 급여 규정을 조항 단위로 청킹·임베딩해 근거 조항 기반으로만 답변, VectorDB 없이 애플리케이션 레이어에서 코사인 유사도 계산
- **인증·계정 보안**: JWT + Redis 기반 인증, 15분 내 5회 실패 시 계정 잠금, 비밀번호 정책(8자 이상·3종 조합)
- **자원·부서 관리**: 자원 예약 동시성 제어, 회사(`com_id`) 기준 멀티테넌시 구조 유지

[▶ V3 시연 영상 보기](https://youtu.be/AnIZ6A84pB8)

### 🎬 Demo 하이라이트 — V4 (개인 확장)

- **AWS CI/CD 파이프라인**: `main` push 시 backend/frontend/analytics 3개 GitHub Actions job이 병렬 실행되어 EC2 한 대에 자동 배포(pm2 무중단 교체), 분석 서비스 배포 실패가 핵심 서비스에 영향 주지 않도록 job 격리
- **Python + Django + pandas 급여 분석 서비스**: 기존 Spring 서버는 건드리지 않고, Spring이 발급한 JWT를 같은 시크릿으로 검증만 하는 읽기 전용 분석 API를 별도 구축 — 집계는 전부 `pandas.DataFrame.groupby/agg`
- **급여 분석 대시보드**: 부서별 평균급여 · 월별 지급추이 · 항목 구성비 · 지급상태 분포 4개 카드를 API 1회 호출로 채우는 Chart.js 대시보드

> V4는 인프라 자동화와 백엔드 데이터 분석에 집중한 개인 확장이라 별도 시연 영상은 없으며, README에 아키텍처·API 스펙이 상세히 정리되어 있습니다.

<br>

---

<br>

## 💼 개인 프로젝트

| 프로젝트 | 한 줄 요약 | README | 개인 시연 영상 |
|---|---|:---:|:---:|
| **ai-data-domain-classifier** | AI 기반 데이터 표준 도메인 추천 시스템 | [README](https://github.com/yoonguri988/ai-data-domain-classifier) | 준비중 |

> 시연 영상은 촬영 후 이 표에 업데이트할 예정입니다.

<br>

---

<br>

## 🖤 "왜?" → "됐다!" — 문제해결 하이라이트

#### 1. 은행 프로젝트, 단일 클래스에서 계층형 구조로
**왜?** 기능을 하나씩 추가할 때마다 같은 클래스 안 코드가 점점 뒤엉켰습니다.
**됐다!** Controller → Service → Repository로 계층을 나누고 Command 패턴을 적용해, 기능이 늘어도 코드가 무너지지 않는 구조로 리팩토링했습니다. 단일 책임 원칙을 이론이 아니라 손으로 체득한 경험이었습니다.

#### 2. 소셜 로그인 3사, 응답 구조가 제각각인 문제
**왜?** 같은 "로그인 성공"인데 구글은 `sub`, 카카오는 `id`, 네이버는 `response.id`로 식별자를 내려줘서 하나의 로직으로 처리할 수 없었습니다.
**됐다!** 실제 응답을 각각 로그로 찍어 구조를 비교 정리하고, provider별 분기 로직으로 흡수해 하나의 인증 흐름으로 통합했습니다.

#### 3. RAG 없이는 AI가 없는 정보도 지어내는 문제
**왜?** LLM에 사내 문서 기반으로 답을 시키려 하면, 모르는 내용도 그럴듯하게 지어내는 환각이 발생했습니다.
**됐다!** PDF를 텍스트로 추출 → 검색(Retrieval) → 프롬프트에 결합(Augmentation) → 생성(Generation)하는 RAG 3단계를 Apache PDFBox와 RestClient로 직접 구현해, "근거 문서 안에서만 답한다"는 원칙을 코드로 만들었습니다.

#### 4. 팀 프로젝트로 이어진 문제해결 — 급여 계산 순서 의존성
**왜?** 팀 캡스톤 ERP에서 급여 계산기(Strategy 패턴)를 Spring `List`로 주입했는데, "장기요양보험은 건강보험료를 먼저 계산해야 한다"는 식의 순서 의존성이 있어 주입 순서만으로는 실행 순서를 보장할 수 없었습니다.
**됐다!** 오케스트레이터가 순서에 기대지 않도록 바꾸고, 의존하는 계산기가 필요한 선행 계산기를 직접 DI로 주입받아 호출하는 구조로 재설계했습니다. 부트캠프에서 배운 "구조로 문제를 푼다"는 습관이 실무형 프로젝트에서도 그대로 통했습니다.

<br>

## 🚀 성장 포인트

- **"이론부터" → "일단 만들고 고치며"** : 완벽한 설계를 먼저 찾기보다, 동작하는 결과물을 빠르게 만들고 구조를 개선하는 방식으로 전환
- **혼자 실습 → 4인 팀 백엔드 팀장** : 개인 트랙 학습을 팀 캡스톤 프로젝트의 도메인 설계·구현 책임으로 확장
- **기능 구현 → 구조로 문제해결** : 에러가 나면 코드를 땜질하지 않고, 계층·패턴·경계를 다시 설계하는 습관 형성
- **웹 CRUD 개발자 → AI/외부 API를 다루는 풀스택 개발자** : RAG, 소셜 로그인, Redis 캐시까지 다루는 범위로 확장

<br>

## 💪 앞으로의 목표

- `spring-breeze-erp` 팀 프로젝트 고도화 — RAG 규정/이력서 검색을 VectorDB로 이전, 회사별 정책 테이블화
- 단일 서버 배포의 SPOF를 낮추기 위한 로드밸런서 · 다중 인스턴스 · 모니터링 구축 경험
- Django/Flutter 트랙 마무리 및 **project4(Flutter 앱)** 완성으로 4번째 사이클 완주
- AI · 외부 API를 "붙이는 것"을 넘어, 서비스 신뢰도(환각 방지, Fallback 설계)까지 책임지는 개발자로 성장
