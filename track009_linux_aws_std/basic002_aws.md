## Part002. AWS

■ Step0. 회원가입 / 로그인
■ Step1. EC2
1. 인스턴스 생성
2. public ip
  > 13.124.155.220
3. ssh 클라이언트
  > ssh -i "thejoa703.pem" ubuntu@ec2-13-124-155-220.ap-northeast-2.compute.amazonaws.com

※ thejoa703.pem 보관주의

```bash
chmod 400 "thejoa703.pem" # 소유자(4)r-- 그룹(0)--- 다른사람(0)---
```

```
  # 1. 상속 권한 완전히 제거
  icacls "thejoa703.pem" /inheritance:r /grant:r "$($env:USERNAME):(R)"

  # 2. 혹시 남아있을 수 있는 다른 사용자 권한 강제 삭제
  icacls "thejoa703.pem" /remove "NT AUTHORITY\Authenticated Users"
  icacls "thejoa703.pem" /remove "BUILTIN\Users"
  icacls "thejoa703.pem" /remove "NT AUTHORITY\SYSTEM"
``` 

■4. EC2에서 nginx
- 문지기
- 웹서버연결
- back와 front 연결설정

1. nginx 설치
```
sudo apt update
sudo apt install  nginx  -y
``` 

2. nginx 설정파일 수정
2-1.
```
sudo vi   /etc/nginx/sites-available/default
```

2-2. esc 눌러서 명령모드로 전환 
2-3. :%d 입력한뒤에 enter → 전체삭제
2-4. i 눌러서 입력모드전환  →  붙여넣기
2-5. esc   →  :wq!  저장후 종료
```


server {
    listen 80;
    server_name 54.253.74.183;

    # 프론트엔드 (Next.js SSR 서버)
    location / {
        proxy_pass http://localhost:3000;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection "upgrade";
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;
        proxy_set_header Cookie $http_cookie; 
    }

    # 백엔드 - 유저 인증 (/auth)
    location /auth {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header Cookie $http_cookie;
    }

    # 백엔드 - 일반 API (/api)
    location /api {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header Cookie $http_cookie;
    }

    # 백엔드 - 소셜 로그인 (/oauth2)
    location /oauth2 {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header Cookie $http_cookie;
    }

    # 백엔드 - 카카오/구글 리다이렉트 처리
    location /login/oauth2/ {
        proxy_pass http://localhost:8080;
        proxy_http_version 1.1;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }

    # 프론트엔드에서 처리해야 하는 콜백
    location /oauth2/callback {
        proxy_pass http://localhost:3000;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header Cookie $http_cookie;
    }

    # 정적 파일 경로
    location /uploads/ {
        alias /home/ubuntu/app/back/build/libs/uploads/;
        autoindex off;
    }
}


```
설명)
   location / {    ←  /여기경로로
        proxy_pass http://localhost:3000;   ←   포트번호 3000번호
        proxy_http_version 1.1;    ←  통신시 http 
        proxy_set_header Upgrade $http_upgrade;  ←  헤더 그대로  전달
        proxy_set_header Connection "upgrade";  ←  헤더 강제 설정
        proxy_set_header Host $host;  ← host 백엔드로 전송
        proxy_cache_bypass $http_upgrade;  ←  연결시 캐시 사용안함.
        proxy_set_header Cookie $http_cookie; ←  쿠키백엔드 서버로 전달
    }

3. nginx 실행 및 테스트
```
sudo nginx -t
sudo systemctl restart nginx
```

5. ECR 리포지토리
- 애플리케이션을 docker 이미지로 빌드해서 ECR에 올려두면 어디서든지 가져다가 사용가능하게

※ ECR 검색
1) 리포지토리 이름
2) 이미지 태그 설정 - Mutable (연습용-latest 덮어쓸수 있음)
3) 암호화 설정 - 기본키 그대로
```
349421152175.dkr.ecr.ap-northeast-2.amazonaws.com/thejoa703
```

6. 필수 패키지 설정

1) 시스템 업데이트
```
sudo apt update && sudo apt upgrade -y
```
2) java 17 설치
```
sudo apt install openjdk-17-jdk -y
java -version
```
3) git 설치
```
sudo apt install git -y
```
4) docker 설치
```bash
sudo apt install docker.io -y
sudo systemctl enable docker && sudo systemctl start docker
sudo usermod -aG docker $USER
# 사용자 계정  시스템그룹 docker
# 현재 로그인한 사용자에게 docker 그룹권한 줘서 sudo 없이 docker 명령어 사용가능
```
5) node.js & npm 설치
```
curl -fsSL https://deb.nodesource.com/setup_24.x | sudo -E bash -
sudo apt install -y nodejs
```
6) pm2 설치 (계속 실행 - 무중간 자동 재실행)
```
sudo npm install -g pm2
```
7) nginx 설치 (위에서 설치)
```
sudo apt install nginx -y
```
8) 실행 디렉토리 생성
```bash
#1. 상위폴더 및 uploads 폴더 까지 한번에 생성 (-p 옵션)
mkdir -p /home/ubuntu/app/back/build/libs/uploads
#2. 홈 프로젝트 기본 디렉토리 권한 설정 (소유자는 모든 권한, 그룹/타인은 읽기 실행 권한)
chmod 755 /home/ubuntu
chmod 755 /home/ubuntu/app
chmod 755 /home/ubuntu/app/back/build/libs/uploads
chmod 644 /home/ubuntu/app/back/build/libs/uploads/* # 이미 생성
```
9) swap
```bash
  sudo fallocate -l 2G /swapfile
  sudo chmod 600 /swapfile
  sudo mkswap /swapfile
  sudo swapon /swapfile
  echo '/swapfile none swap sw 0 0' | sudo tee -a /etc/fstab
  free -h

  # sudo fallocate -l 2G /swapfile <- 2GB 파일 생성
  # sudo chmod 600 /swapfile <- 권한 유저(r:읽기 w:쓰기 x:실행)
  # sudo mkswap /swapfile <- 스왑 초기화
  # sudo swapon /swapfile <- 스왑 활성화
  # echo '/swapfile none swap sw 0 0' | sudo tee -a /etc/fstab <- 설정파일 끝에 추가
  # free -h <- 메모리 확인
```
10) docker - oracle 컨테이너 실행
```bash
sudo docker run -d --name oracle-xe -p 1521:1521 -p 5500:5500 -e ORACLE_PASSWORD=1234 gvenzl/oracle-xe:18-slim
```
11) 접속 확인
```bash
        # 1. 오라클이 완전히 실행될 때까지 로그 확인
        sudo docker logs -f oracle-xe | grep "DATABASE IS READY TO USE"

        # 2. system 계정으로 접속 (비밀번호 변경 반영)
        sudo docker exec -it oracle-xe sqlplus system/1234@XE

        # --- (이후 sqlplus 프롬프트 안에서 아래 명령어들을 한 줄씩 실행) ---
        CREATE USER scott IDENTIFIED BY tiger;
        GRANT CONNECT, RESOURCE TO scott;

        CREATE USER boot IDENTIFIED BY react;
        GRANT CONNECT, RESOURCE TO boot;
        exit;

        # 3. 새로 만든 scott 계정으로 접속 확인
        sudo docker exec -it oracle-xe sqlplus boot/react@XE
```
12) docker - redis
```bash
sudo docker run  -d --name redis   -p 6379:6379   --restart=always   redis:7
```
13) 컨테이너 상태 확인
```bash
sudo docker ps
```
14) ping 테스트
```bash
sudo docker exec -it redis redis-cli ping
```
15) ec2 자체에서 자동 실행 설정
```bash
sudo docker update --restart=always oracle-xe 
sudo docker update --restart=always redis
```


7. IM 사용자/역할 생성
1) IAM콘솔 -> 사용자 추가
2) 권한정책: `AmazonEC2FullAccess`, `AmazonECS_FullAccess`, `AmazonEC2ContainerRegistryFullAccess`
3) Access Key / Secret Key 발급
```
Access Key : 
Secret Key : 
```
4) Github Secrets 에 저장
- `AmazonEC2FullAccess`   →  EC2 인스턴스 관리 
- `AmazonECS_FullAccess`     →  ECS/Faragate 서비스관리
- `AmazonEC2ContainerRegistryFullAccess`   →  Docker이미지를 푸시/풀 할수 있게. 레지스트리 접근  


■ Step2. Github
> CI/CD
CI: 지속적 통합
- 공용 저장소에 자주 병합
- 자동 빌드/테스트를 통해서 조기에 버그 발견
CD: 지속적 제공/배포
- 자동으로 프로덕션 환경에 배포

1. git repository 새로 만들기
> https://github.com/yoonguri988/track009_aws.git

2. Actions secrets and variables 시크릿 키 설정

```bash
Name: EC2_HOST      
Secret: 13.124.155.220 
# 현재 EC2 퍼블릭 ip

Name: EC2_USER
Secret: ubuntu

Name: EC2_SSH_KEY
Secret:  
# .pem 파일의 내용을 그대로 붙여넣기
 
Name: AWS_ACCESS_KEY_ID
Secret: 
# IAM 발급받은 Access Key

Name: AWS_SECRET_ACCESS_KEY
Secret: 
# IAM 발급받은 Secret Key

Name: AWS_REGION
Secret: ap-northeast-2

Name: AWS_ACCOUNT_ID
Secret: 349421152175
# 계정 ID 12자 숫자

Name: ECR_REPO
Secret: thejoa703
# ECR 저장소 이름
 
Name: DB_USERNAME
Secret: boot

Name: DB_PASSWORD
Secret: react

Name: JWT_SECRET
Secret: this-is-a-very-long-random-secret-key-64chars-minimum-1234567890!@#$%^&*()

Name: GOOGLE_CLIENT_ID
Secret: 

Name: GOOGLE_CLIENT_SECRET
Secret: 

Name: KAKAO_CLIENT_ID 
Secret: 

Name: NAVER_CLIENT_ID
Secret: 

Name: NAVER_CLIENT_SECRET
Secret: 

Name: NEXT_PUBLIC_API_BASE_URL    
Secret: http://13.124.155.220
# public ip
```


※ ssh 클라이언트 
  > ssh -i "thejoa703.pem" ubuntu@ec2-13-124-155-220.ap-northeast-2.compute.amazonaws.com

=============

■ Step3. 워크 플로우 작성 및 프로젝트 올리기

1. 구조확인
```
thejoa703/                ← 깃허브 저장소 루트
├── .git                  ← Git 저장소 메타데이터
├── .gitignore            ← 불필요한 파일 제외 설정
├── BACK/                 ← 백엔드 (Spring Boot)
│   ├── src/              ← 소스 코드
│   ├── build.gradle      ← Gradle 빌드 설정
│   └── ...               ← 기타 설정/리소스
├── FRONT/                ← 프론트엔드 (React + Next.js)
│   ├── src/              ← 소스 코드
│   ├── package.json      ← npm 의존성 관리
│   └── ...               ← 기타 설정/리소스
└── .github/
    └── workflows/
        └── deploy.yml    ← GitHub Actions 워크플로우 파일
```


> git clone https://github.com/yoonguri988/track009_aws.git

1) back 파일 수정
SecurityConfig.java
application.yml
application_oauth.yml

2) front 파일수정
> 이미지 파일 생성

3) jar 파일 - back
```bash
./gradlew clean build -x test --refresh-dependencies
```
[back]-[build]-[libs]

..........................................
4) deploy.yml


...........................................
5) 빌드
```
git add .
git commit -m "test deploy-1"
git push origin main
```

6) 외부테스트
http://13.124.155.220/

```
ssh 접속
pm2 list
pm2 logs backend
```

■ Step4. HTTPS + DOMAIN

