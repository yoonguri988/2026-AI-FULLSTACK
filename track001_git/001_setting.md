## Part001
- TODO1: GIT

■ 1. git, github
- git: 내 컴퓨터에서 파일의 변화를 기록
- github: 협업시 저장공간 (원격)

■ 2. 실습
1. Git 설치
    - [Git 공식 사이트](https://git-scm.com)에서 다운로드 후 설치.
    https://git-scm.com
    - 설치 후 git --version 으로 정상 설치 확인.

2. 사용자 정보 설정
      ```
      git config --list // 설정된 정보 리스트 확인
      // 사용자 아이디 설정
      git config --global user.name "깃허브 아이디"
      // 사용자 이메일 설정
      git config --global user.email "깃허브 이메일" 
      ```
3. GitHub 계정 준비
    - GitHub 회원가입 및 로그인.
    - 새 레포지토리(repository) 생성. ex) 2026-AI-FULLSTACK 

4. 레포지토리 연결
    - 새 컴퓨터에서 원하는 폴더로 이동 후: 
    ```
      git clone https://github.com/아이디/레포지토리명.git 
    ```
    ```
      cd D:\cyj // 해당 폴더 위치로 이동
      git clone https://github.com/깃허브아이디/2026-AI-FULLSTACK.git
    ```

    - 이미 만든 프로젝트를 올리고 싶다면: 
    ```
      git init
      git remote add origin https://github.com/아이디/레포지토리명.git
    ```  

5. 작업 파일 올리기
    ```
      -- README.md 파일 작성 --
      git add . // 모든 파일 전부 올릴 꺼야.
      git commit -m "첫 커밋 메시지" // 올릴 파일에 대한 메시지 남기기
      // 원격 저장소에 올릴꺼라고 신호만 준 것.
      git push origin main
    ```
    ⚠️ 여기서 main 대신 master 일 수도 있으니, 레포지토리 기본 브랜치 이름 확인 필요.

6. gitinore
    - Git에게 “이건 안 가져가도 돼!”라고 알려주는 메모지
    - Git = 장난감상자 (모든 장난감을  넣어두는 곳)  
    - 파일들 = 장난감들  
    - .gitignore : “이 장난감은 상자에 넣지 마!”라고 붙여둔 스티커   

※ vs code 설치

---
## Part002
- TODO2: 마크다운 문법


# 제목 (제일 큰 제목)
## 중간 제목
### 작은 제목
#### step4
##### step5
###### step6
