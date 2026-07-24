/**
 * app.js
 * ---------------------------------
 * Express 서버 진입점
 * - Passport 인증 및 세션/쿠키 설정
 * - Swagger UI로 API 문서 제공
 * - Helmet으로 기본 보안 강화
 * - Https적용( 선택 )
 * - 에러 핸들링 추가
 */

const  express = require('express');         // express - node.js 프레임워크 (웹서버를 쉽게 만들기)
const  session = require('express-session'); // 세션 관리용 미들웨어
const  passport = require('passport');       // Passport 인증모듈
const dotenv = require('dotenv');            // 환경변수(.env) 사용을 위해 dotenv불러오기
const morgan = require('morgan');            // 로그 기록용 미들웨어(개발시 요청/응답 확인)
const cors = require('cors');                // CORS(다른 도메인에서 API 호출 허용) 미들웨어
const helmet = require('helmet');            // 보안 헤더 설정 미들웨어
const fs = require('fs');                    // https 인증서 파일 읽기용
const https = require('https');              // https 서버 모듈
const  swaggerUi    = require('swagger-ui-express'); // Swagger UI 미들웨어
const  swaggerJsdoc = require('swagger-jsdoc'); //Swagger 문서 자동 생성도구


////////////////////////////////////////////////////////// 
dotenv.config();                  // .env 파일 로드 (환경변수 사용 가능하게 함)
const  app = express();           // express 애플리케이션 인스턴스 생성

// ✅ 기본 미들웨어 
app.use(express.json());                        // json 파싱 미들웨어 (POST요청  body를 JSON으로 읽기)
app.use(express.urlencoded({extended: true}));  // URL-encoded 파싱 미들웨어 ( form 데이터 처리)
// app.use(cors());                                // 모든 도메인에 api호출 허용
// app.use(morgan('dev'));                         // 개발모드에서 요청로그출력
// app.use(helmet());                              // 기본보안 헤더 적용( XSS, CSP 등)
app.use(cors({
  origin: 'http://localhost:3000' ,  //프론테엔드 주소를 정확히 명시
  credentials: true                  //쿠키/세션허용  
}));


// ✅ 세션설정 ( 쿠키 기반 세션 관리 )
app.use(session({
  secret: process.env.COOKIE_SECRET,       //.env에 저장된 쿠키 암호화 키
  resave: false ,                          // 매 요청마다 세션 강제 저장 여부(false 권장)
  saveUninitialized: false,                // 초기화되지 않은 세션 저장 여부(false 권장)
  cookie: {httpOnly: true, secure: false}  // httpOnly: JS 접근 불가 , secure: HTTPS만 사용여부
}));

// ✅ Passport 초기화 및 세션설정
app.use(passport.initialize());
app.use(passport.session()); 

// ✅ Passport 설정 적용
const passportConfig = require('./passport');
passportConfig();


// ✅ Swagger 옵션
const swaggerOptions = {
  definition: {
    openapi: '3.0.0',  //Swagger
    info: {
      title: 'User API',   // 문서정보
      version: '1.0.0',     // 문서버젼
      description: '회원가입, 로그인, 사용자 조회/수정/삭제 API 문서', //설명
    },
  },
  apis: ['./routes/*.js'],  // swagger주석이 들어간 라우터 파일경로
};
// Swagger 문서 생성
const swaggerSpecs = swaggerJsdoc(swaggerOptions);

////////////////////////////////////////////////////////// 
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerSpecs));  // Swagger UI 엔드포인트 등록

// ✅ 사용자 라우터 연결 #################
//   /user경로에 사용자 라우터 연결
const  userRouter = require('./routes/user');  //##
//const  postRouter = require('./routes/post'); 예시
 
app.use('/user' , userRouter) // /user 경로에 사용자라우터연결  
app.get('/' , (req, res)=>{ 
    res.send('hello express');
});

// ✅ 에러 핸들링 미들웨어 (모든라우트 뒤에 위치)
app.use((err, req, res, next) => {
  console.error('서버 에러:', err.stack);         
  res.status(500).json({ message: '서버 내부 오류 발생' });  
});

// ✅ 서버실행 (HTTPS 적용)
const PORT = process.env.PORT || 3065;
// HTTPS 인증서가 존재하면 HTTPS서버 실행, 없으면 HTTPS 실행
if (fs.existsSync('./cert/server.key') && fs.existsSync('./cert/server.crt')) {
  const options = {
    key: fs.readFileSync('./cert/server.key'),    
    cert: fs.readFileSync('./cert/server.crt'),  
  };
  https.createServer(options, app).listen(PORT, () => {
    console.log(`✅ HTTPS 서버 실행 중! https://localhost:${PORT}`);
    console.log(`✅ Swagger UI: https://localhost:${PORT}/api-docs`);
  });
} else {
  app.listen(PORT, () => {
    console.log(`✅ HTTP 서버 실행 중! http://localhost:${PORT}`);
    console.log(`✅ Swagger UI: http://localhost:${PORT}/api-docs`);
  });
}




//실행 :  npx  nodemon  app.js  

