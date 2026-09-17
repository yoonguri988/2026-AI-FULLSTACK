# 📱 [단계별 실습] Flutter + Spring Boot 풀스택 프로젝트 (Feature-first)

---

## 🛠️ Step 0. 개발 환경 설정 및 터미널 구동

### 1. Windows 개발자 모드 활성화 (최초 1회)

* `Win + R` 입력 후 `ms-settings:developers` 실행 ➔ **개발자 모드 켬**

### 2. 프로젝트 생성 및 1차 구동 테스트

```bash
# 1. 프로젝트 생성
flutter create moblie2

# 2. 프로젝트 디렉토리 이동
cd moblie2

# 3. 프로젝트 기본 실행 (Windows 타겟 선택)
flutter run
1

```

```bash
# Flutter 빌드 캐시 및 임시 파일 삭제
flutter clean

# pubspec.yaml 파일의 패키지 의존성 다운로드
flutter pub get

# Windows 네이티브 타겟으로 디버그 빌드 구동
flutter run -d windows

```

> **👨‍🏫 선생님 팁:** `1`번(Windows)을 선택하여 구동합니다. 웹 브라우저(Chrome)의 CORS 에러를 회피하고 에뮬레이터 없이 빠른 C++ 네이티브 빌드로 테스트하기 위함입니다.

> **🧪 중간 테스트:**
> * 터미널에서 `flutter run` 실행 후 `1`을 눌렀을 때 기본 템플릿 앱(카운터 앱) 창이 성공적으로 뜨는지 확인합니다.
> 
> 

### 3. 필수 패키지 설치 및 환경 동기화

```bash
# Riverpod, Dio, Secure Storage, Image Picker 설치
# 1) flutter_riverpod : redux 처럼 전역상태관리 라이브러리
# 2) dio : Axios처럼 비동기 http 통신 라이브러리 (Intercepter 기능 제공)
# 3) flutter_secure_storage : 브라우저의 localStorage/Cookies 대신 모바일 암호화영역 jwt 저장
# 4) image_picker : <input type="file"> 같은 갤러리/카메라 접근패키지
flutter pub add flutter_riverpod
flutter pub add dio
flutter pub add flutter_secure_storage
flutter pub add image_picker

# 환경 동기화
flutter clean
flutter pub get

```

> **🧪 중간 테스트:**
> * `pubspec.yaml` 파일의 `dependencies` 항목에 `flutter_riverpod`, `dio`, `flutter_secure_storage`가 정상적으로 추가되었는지 확인합니다.
> 
> 

```bash
# ✏️ 연습문제 & 개념 점검 [Step 0]
# Q1. Windows 환경에서 C++ 네이티브 창으로 디버그 앱을 구동할 때 사용하는 flutter run 옵션은 무엇인가요?
# 답: flutter run -d ( windows )

# Q2. Axios처럼 HTTP Interceptor 기능을 지원하는 Flutter 비동기 통신 라이브러리는 무엇인가요?
# 답: ( dio )

```

---

## 🚀 Step 1. 앱의 시작점 및 뼈대 검증

#### 📂 프로젝트 전체 디렉터리 구조
```bash
mobile2/
└── lib/
    ├── main.dart                       #  앱 진입점 (ProviderScope 전역 상태 주입)
    ├── app.dart                        #  라우팅 테이블 & 테마 관리 (MaterialApp)
    ├── core/                           #  앱 공통 핵심 설정
    │   └── network/
    │       └── api_client.dart         #  백엔드 Base URL (안드로이드/Windows IP 분기)
    ├── shared/                         #  재사용 공통 UI
    │   └── components/
    │       └── app_layout.dart         #  공통 상단바(AppBar) & 반응형 헤더 레이아웃
    └── features/                       #  도메인 중심 기능 모듈 (Feature-first)
        ├── auth/                       # [  인증 도메인]
        │   ├── data/
        │   │   └── auth_provider.dart  #  인증 상태, JWT 저장소, Dio 인터셉터
        │   └── presentation/
        │       ├── login_page.dart     #  로그인 폼 화면
        │       ├── signup_page.dart    #  중복검사 포함 회원가입 화면
        │       └── users_page.dart     #  내 정보 마이페이지
        └── post/                       # [  게시판 도메인]
            ├── data/
            │   └── board_provider.dart #  게시글 CRUD & FormData 멀티파트 상태관리
            └── presentation/
                ├── post_list_page.dart #  게시글 목록 화면 (카드 형태 UI)
                ├── post_write_page.dart #  이미지 다중 선택(image_picker) & 글 작성
                ├── post_detail_page.dart #  게시글 상세 보기 (작성자 검증)
                └── post_update_page.dart #  게시글 및 이미지 수정 화면
```


#### 1. `lib/main.dart`

```dart
// ProviderScope 최상위 앱(App) 전역상태 공유가능
import 'package:flutter/material.dart';
// Riverpod 전역상태관리 라이브러리 임포트
import 'package:flutter_riverpod/flutter_riverpod.dart';
// 메인앱설정
import 'app.dart';

void main() {
  // Flutter 엔진과 플랫폼 바인딩 초기화
  WidgetsFlutterBinding.ensureInitialized();
  
  // ProviderScope: Riverpod 전역 상태를 앱 전체에 주입 (Redux의 <Provider> 역할)
  runApp(const ProviderScope(child: App()));
}

```

> **🧪 중간 테스트:**
> * 이 시점에는 `app.dart`가 아직 없거나 연결되지 않아 문법 에러가 뜰 수 있으므로, 아래 `lib/app.dart`까지 작성 후 함께 실행 테스트를 진행합니다.


#### 2. `lib/app.dart` (뼈대 테스트)

```dart
import 'package:flutter/material.dart';

class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    // MaterialApp: 앱 전체 테마와 라우팅 테이블을 관리하는 루트 위젯
    return MaterialApp(
      title: '나의 소셜 앱',
      debugShowCheckedModeBanner: false,
      initialRoute: '/',
      routes: {
        // 루트 경로 매핑 (Scaffold: 기본 레이아웃 뼈대 제공 위젯)
        '/': (context) => Scaffold(
              appBar: AppBar(title: const Text('1단계: 라우팅 테스트')),
              body: const Center(child: Text('앱이 정상적으로 실행되었습니다! 🎉')),
            ),
      },
    );
  }
}

```

> **🧪 중간 테스트:**
> * `flutter run` 실행 후 상단바에 **"1단계: 라우팅 테스트"**, 화면 중앙에 **"앱이 정상적으로 실행되었습니다! 🎉"** 문구가 뜨는지 확인합니다.
> 
> 

```dart
// ✏️ 연습문제 & 개념 점검 [Step 1]
// Q1. Riverpod 전역 상태를 앱 레이어 전체에 바인딩하기 위해 최상위 앱을 감싸주는 위젯은 무엇인가요?
// 답: ( ProviderScope )

// Q2. Flutter에서 상단 헤더바, 본문, 하단 탭 등의 기본 뼈대를 제공해 주는 Layout 구조 위젯은 무엇인가요?
// 답: ( Scaffold )

```

---

## 🌐 Step 2. Core & Shared (임시 레이아웃 검증)

#### 1. `lib/core/network/api_client.dart`

```dart
// spring boot 에서 (8080) 와 통신할 서버 base_url

// web 플랫폼 판단용
import 'package:flutter/foundation.dart';
// os 플랫폼 (Android, iOS, Windows 등 ) 감지 라이브러리
import 'dart:io' show Platform;

class ApiClient {
  static String getBaseUrl() {
    //1. 웹 브라우저 실행시
    if (kIsWeb) return 'http://localhost:8080';
    try {
      // 2. Android 에뮬레이터에서 PC 서버(localhost) 접속 우회 전용 IP ###
      if (Platform.isAndroid) return 'http://10.0.2.2:8080';
    } catch (_) {}
    //3. window 데스크톱 네이티브 앱 실행
    return 'http://localhost:8080';
  }
}

```

> **🧪 중간 테스트:**
> * 비즈니스 로직 및 설정 파일입니다. IDE 상에서 Red Squiggle(빨간 줄 에러)이 뜨지 않는지 확인합니다.
> 
> 

#### 2. `lib/shared/components/app_layout.dart` (임시 레이아웃 ver-1)

```dart
// React에서 레이아웃: <Layout>{children}</Layout>
// ConsumerWidget 상속 받으면 React에서 useSelector 처럼 전역 인증
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

// Riverpod 상태를 반응형으로 관찰하기 위해 StatelessWidget 대신 상속받는 클래스
class AppLayout extends ConsumerWidget {
  final Widget child;
  const AppLayout({super.key, required this.child});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    // 임시테스트용 - 로그인 여부 테스트
    final bool isLogined = DateTime.now().year < 2000;

    return Scaffold(
      appBar: AppBar(
        title: const Text('나의 소셜 앱'),
        actions: [
          // ... 전개연산자 React {isLogined ? <A/> : <B/>}
          if (isLogined) ...[
            const Center(
              child: Padding(
                padding: EdgeInsets.symmetric(horizontal: 8.0),
                child: Text('테스트유저님 환영합니다!'),
              ),
            ),
          ] else ...[
            TextButton(
              // 지정한 라우트 경로 이름('/login')으로 화면 전환
              onPressed: () => Navigator.pushNamed(context, '/login'),
              child: const Text('로그인', style: TextStyle(color: Colors.white)),
            ),
          ],
        ],
      ),
      body: child,
    );
  }
}

```

> **🧪 중간 테스트:**
> * 공통 껍데기 위젯입니다. 다음 단계인 `lib/app.dart` 수정 후 한꺼번에 레이아웃 렌더링을 확인합니다.
> 
> 

#### 3. `lib/app.dart` 수정 (임시 레이아웃 바인딩)

```dart
import 'package:flutter/material.dart';
import 'shared/components/app_layout.dart';

class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: '나의 소셜 앱',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        useMaterial3: true,
        appBarTheme: const AppBarTheme(
          backgroundColor: Colors.blue,
          foregroundColor: Colors.white,
        ),
      ),
      initialRoute: '/',
      routes: {
        '/': (context) => const AppLayout(
              child: Center(
                child: Text(
                  'BLUE상단바 🎉',
                  style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                ),
              ),
            ),
      },
    );
  }
}

```

> **🧪 중간 테스트:**
> * 실행 시 파란색 상단바(`AppBar`)에 **"나의 소셜 앱"** 및 우측 **"로그인"** 버튼이 뜨고, 본문에 **"BLUE상단바 🎉"** 가 출력되는지 확인합니다.
> 
> 

```dart
// ✏️ 연습문제 & 개념 점검 [Step 2]
// Q1. Android 에뮬레이터에서 PC에 작동 중인 로컬 백엔드 서버(localhost:8080)로 통신할 때 사용하는 전용 IP는 무엇인가요?
// 답: http://(10.0.2.2):8080

// Q2. React의 useSelector처럼 Riverpod에서 전역 상태를 관찰(구독)할 수 있도록 StatelessWidget 대신 상속받는 위젯 클래스는 무엇인가요?
// 답: ( ConsumerWidget )

```

---

## 🔐 Step 3. Features: Auth (인증 기능 완성)

#### 1. `lib/features/auth/data/auth_provider.dart`

```dart
//React [Redux + Saga + api/axios.js(jwt 토큰처리)]
//AuthState - Redux
//_dio.interceptors: api 요청마다 `Bearer ${token}`을 헤더 주입, 401 에러 토큰
import 'package:flutter_riverpod/flutter_riverpod.dart'; // 리덕스
import 'package:dio/dio.dart'; // axios 역할의 비동기 http 통신
import 'package:flutter_secure_storage/flutter_secure_storage.dart'; // jwt 저장라이브러리
import '../../../core/network/api_client.dart';

class AuthState {
  final Map<String, dynamic>? user; // 서버에서 받은 유저정보 dto 객체
  final String? accessToken; // jwt access Token
  final bool loading; // 로딩중
  final String? error; // 에러

  const AuthState({
    this.user,
    this.accessToken,
    this.loading = false,
    this.error,
  });
}

// [핵심] 최신 Riverpod 표준 Notifier 클래스 상속
class AuthNotifier extends Notifier<AuthState> {
  @override
  AuthState build() {
    _initDio(); // Provider 생성시 인터셉터 설정 초기화
    return const AuthState(); // 초기화 상태 반환
  }

  late final Dio _dio; // late (나중에-사용하기 직전에 초기화), final 변경 X
  // OS 암호화 저장소 객체 생성 (localStorage 대신 모바일 보안 영역 사용)
  final _storage = const FlutterSecureStorage();

  void _initDio() {
    _dio = Dio(BaseOptions(
      baseUrl: ApiClient.getBaseUrl(), // 부품객체: ApiClient http://localhost:8080
      headers: {'Content-Type': 'application/json'},
    ));

    // [핵심] Dio Interceptor 설정 (Axios interceptor와 100% 동일)
    _dio.interceptor.add(InterceptorsWrapper(
      // 매 api 요청마다 SecureStorage에서 토큰 읽어와서 Authorization 헤더 주입
      onRequest: (options, handler) async {
        final token = await _storage.read(key: 'accessToken');
        if (token != null) {
          // Authorization 헤더에 Bearer 토큰 주입
          options.headers['Authorization'] = 'Bearer $token';
        }
        return handler.next(options);
      },
      // 에러응답 - 401(토큰만료) -> 재발급시도
      onError: (DioException e, handler) async {
        // [핵심] HTTP 401 Unauthorized 감지 시 토큰 재발급 후 원래 요청 재시도
        if (e.response?.statusCode == 401) {
          final success = await _refreshAccessToken();
          if (success) {
            final token = await _storage.read(key: 'accessToken');
            e.requestOptions.headers['Authorization'] = 'Bearer $token';
            // 기존 실패했던 API 요청 재전송
            final clonedRequest = await _dio.fetch(e.requestOptions); // 원래 요청 재전송
            return handler.resolve(clonedRequest);
          }
        }
        return handler.next(e);
      },
    ));
  }

//jwt 토큰 재발급
  Future<bool> _refreshAccessToken() async {
    try {
      final response = await _dio.post('/auth/refresh');
      final newAccessToken = response.data['accessToken'];
      if (newAccessToken != null) {
        await _storage.write(key: 'accessToken', value: newAccessToken);
        state = AuthState(
          user: state.user,
          accessToken: newAccessToken,
          loading: state.loading,
          error: state.error,
        );
        return true;
      }
    } catch (_) {
      await logout();
    }
    return false;
  }

  Future<bool> login(Map<String, dynamic> credentials) async {
    state = AuthState(
      user: state.user,
      accessToken: state.accessToken,
      loading: true,
      error: null,
    );

    try {
      final response = await _dio.post('/auth/login', data: credentials);
      final accessToken = response.data['accessToken'];
      final user = response.data['user'];

      if (user != null && accessToken != null) {
        await _storage.write(key: 'accessToken', value: accessToken);
        state = AuthState(
          user: user,
          accessToken: accessToken,
          loading: false,
          error: null,
        );
        return true;
      } else {
        state = AuthState(
          user: state.user,
          accessToken: state.accessToken,
          loading: false,
          error: '아이디 또는 비밀번호가 올바르지 않습니다.',
        );
        return false;
      }
    } catch (err) {
      state = AuthState(
        user: state.user,
        accessToken: state.accessToken,
        loading: false,
        error: '로그인 실패: ${err.toString()}',
      );
      return false;
    }
  }

  //로그아웃 액션
  Future<void> logout() async {
    try {
      await _dio.post('/auth/logout');
    } catch (_) {}
    await _storage.delete(key: 'accessToken');
    state = const AuthState();
  }

  // 회원가입
  Future<bool> signup(Map<String, dynamic> data) async {
    state = AuthState(
      user: state.user,
      accessToken: state.accessToken,
      loading: true,
      error: null,
    );

    try {
      // Spring Boot @RequestPart 멀티파트 수신 대응용 FormData 객체 생성
      final formData = FormData.fromMap({
        'email': data['email'],
        'password': data['password'],
        'nickname': data['nickname'],
      });

      await _dio.post(
        '/auth/signup', 
        data: formData,
        options: Options(headers: {'Content-Type': 'multipart/form-data'}),
      );

      state = AuthState(
        user: state.user,
        accessToken: state.accessToken,
        loading: false,
        error: null,
      );
      return true;
    } catch (err) {
      state = AuthState(
        user: state.user,
        accessToken: state.accessToken,
        loading: false,
        error: '회원가입 실패: ${err.toString()}',
      );
      return false;
    }
  }

  // 이메일 중복 체크 api (GET auth/check-email)
  Future<bool> checkEmailDuplicate(String email) async {
    try {
      final response = await _dio.get('/auth/check-email', queryParameters: {'email': email});
      return response.data;
    } catch (e) {
      return false;
    }
  }

  // 닉네임 중복 체크 api (GET auth/check-nickname)
  Future<bool> checkNicknameDuplicate(String nickname) async {
    try {
      final response = await _dio.get('/auth/check-nickname', queryParameters: {'nickname': nickname});
      return response.data;
    } catch (e) {
      return false;
    }
  }
}

// [핵심] Riverpod NotifierProvider 등록
final authProvider = NotifierProvider<AuthNotifier, AuthState>(() {
  return AuthNotifier();
});

```

> **🧪 중간 테스트:**
> * 전역 상태 로직(Provider) 작성 단계입니다. 터미널 및 에디터에서 구문 에러가 없는지 체크하고 UI 페이지 작성으로 진행합니다.
> 

#### 2. `lib/features/auth/presentation/login_page.dart`

```dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../data/auth_provider.dart';

// React의 useState + useSelector 기능을 모두 사용하기 위해 상속받는 클래스
class LoginPage extends ConsumerStatefulWidget {
  const LoginPage({super.key});

  @override
  ConsumerState<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends ConsumerState<LoginPage> {
  // [핵심] Flutter의 폼 입력 제어 컨트롤러 (React의 useState/useRef 역할)
  // _변수: 해당 변수를 클래스 내부에서만 접근
  final _emailController = TextEditingController();
  final _passwordController = TextEditingController();

  @override
  void dispose() {
    // [핵심] 메모리 누수 방지를 위한 컨트롤러 객체 해제
    _emailController.dispose();
    _passwordController.dispose();
    super.dispose();
  }

  void _handleLogin() async {
    // TextEditingController에서 텍스트 값 추출
    final email = _emailController.text.trim();
    final password = _passwordController.text.trim();

    // Toast / Alert 대신 사용하는 Flutter 표준 스낵바
    if (email.isEmpty || password.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('이메일과 비밀번호를 입력해주세요.')),
      );
      return;
    }

    // [핵심] Notifier의 메서드를 호출(dispatch)하기 위해 ref.read().notifier 사용
    final success = await ref.read(authProvider.notifier).login({'email': email, 'password': password});
    
    if (success && mounted) {
      // [핵심] 히스토리 스택을 모두 삭제하고 이동 (React Router의 router.replace('/') 효과)
      Navigator.pushNamedAndRemoveUntil(context, '/', (route) => route.isFirst);
    }
  }

  @override
  Widget build(BuildContext context) {
    // [핵심] 전역 AuthState 변화를 구독하여 UI 자동 재빌드 (Redux의 useSelector 역할)
    final authState = ref.watch(authProvider);

    return Scaffold(
      appBar: AppBar(title: const Text('로그인')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            TextField(controller: _emailController, decoration: const InputDecoration(labelText: '이메일')),
            const SizedBox(height: 12),
            TextField(controller: _passwordController, obscureText: true, decoration: const InputDecoration(labelText: '비밀번호')),
            const SizedBox(height: 24),
            // 에러 발생시 조건부 렌더링
            if (authState.error != null)
              Text(authState.error!, style: const TextStyle(color: Colors.red)),
            const SizedBox(height: 12),
            SizedBox(
              width: double.infinity,
              child: ElevatedButton(
                onPressed: authState.loading ? null : _handleLogin,
                child: authState.loading ? const CircularProgressIndicator(color: Colors.white) : const Text('로그인'),
              ),
            ),
            const SizedBox(height: 12),
            TextButton(
              onPressed: () {
                Navigator.pushNamed(context, '/signup');
              },
              child: const Text('계정이 없으신가요? 회원가입하기'),
            ),
          ],
        ),
      ),
    );
  }
}

```

> **🧪 중간 테스트 (`lib/app.dart` 임시 연결하여 화면 띄우기):**
> * 바로 화면을 띄워보기 위해 `lib/app.dart` 파일의 routes 구문을 아래와 같이 임시 수정합니다.
> 
> 
> ```dart
> import 'features/auth/presentation/login_page.dart';
> routes: {
>   '/': (context) => const LoginPage(),
> }
> 
> ```
> 
> 
> * 앱을 실행하여 이메일/비밀번호 입력 폼과 로그인 버튼이 깔끔하게 그려지는지 확인합니다.
> 
> 

#### 3. `lib/features/auth/presentation/signup_page.dart`

```dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../data/auth_provider.dart';

class SignupPage extends ConsumerStatefulWidget {
  const SignupPage({super.key});

  @override
  ConsumerState<SignupPage> createState() => _SignupPageState();
}

class _SignupPageState extends ConsumerState<SignupPage> {
  final _emailController = TextEditingController();
  final _passwordController = TextEditingController();
  final _nicknameController = TextEditingController();

  bool _isEmailChecked = false;
  bool _isNicknameChecked = false;

  @override
  void dispose() {
    _emailController.dispose();
    _passwordController.dispose();
    _nicknameController.dispose();
    super.dispose();
  }

  void _checkEmail() async {
    final email = _emailController.text.trim();
    if (email.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text('이메일을 입력해주세요.')));
      return;
    }

    final exists = await ref.read(authProvider.notifier).checkEmailDuplicate(email);
    if (exists) {
      if (mounted) ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text('이미 사용 중인 이메일입니다.')));
      // [핵심] Flutter의 local state 변경 및 UI 트리 재렌더링 트리거 함수
      setState(() => _isEmailChecked = false);
    } else {
      if (mounted) ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text('사용 가능한 이메일입니다.')));
      setState(() => _isEmailChecked = true);
    }
  }

  void _checkNickname() async {
    final nickname = _nicknameController.text.trim();
    if (nickname.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text('닉네임을 입력해주세요.')));
      return;
    }

    final exists = await ref.read(authProvider.notifier).checkNicknameDuplicate(nickname);
    if (exists) {
      if (mounted) ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text('이미 사용 중인 닉네임입니다.')));
      setState(() => _isNicknameChecked = false);
    } else {
      if (mounted) ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text('사용 가능한 닉네임입니다.')));
      setState(() => _isNicknameChecked = true);
    }
  }

  void _handleSignup() async {
    if (!_isEmailChecked) {
      ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text('이메일 중복 확인을 해주세요.')));
      return;
    }
    if (!_isNicknameChecked) {
      ScaffoldMessenger.of(context).showSnackBar(const SnackBar(content: Text('닉네임 중복 확인을 해주세요.')));
      return;
    }

    final data = {
      'email': _emailController.text.trim(),
      'password': _passwordController.text.trim(),
      'nickname': _nicknameController.text.trim(),
    };

    final success = await ref.read(authProvider.notifier).signup(data);
    if (success && mounted) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('회원가입 완료! 로그인해주세요.')),
      );
      // [핵심] 현재 페이지를 스택에서 제거하고 이전 페이지로 복귀 (React Router의 router.back())
      Navigator.pop(context); 
    }
  }

  @override
  Widget build(BuildContext context) {
    final authState = ref.watch(authProvider);

    return Scaffold(
      appBar: AppBar(title: const Text('회원가입')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            Row(
              children: [
                // [핵심] Flex Layout 내에서 입력창이 남은 공간을 차지하도록 확장해 주는 위젯
                Expanded(
                  child: TextField(
                    controller: _emailController, 
                    decoration: const InputDecoration(labelText: '이메일'),
                    onChanged: (_) => setState(() => _isEmailChecked = false),
                  ),
                ),
                const SizedBox(width: 8),
                ElevatedButton(onPressed: _checkEmail, child: const Text('중복확인')),
              ],
            ),
            const SizedBox(height: 12),
            TextField(controller: _passwordController, obscureText: true, decoration: const InputDecoration(labelText: '비밀번호')),
            const SizedBox(height: 12),
            Row(
              children: [
                Expanded(
                  child: TextField(
                    controller: _nicknameController, 
                    decoration: const InputDecoration(labelText: '닉네임'),
                    onChanged: (_) => setState(() => _isNicknameChecked = false),
                  ),
                ),
                const SizedBox(width: 8),
                ElevatedButton(onPressed: _checkNickname, child: const Text('중복확인')),
              ],
            ),
            const SizedBox(height: 16),
            if (authState.error != null)
              Text(authState.error!, style: const TextStyle(color: Colors.red)),
            const SizedBox(height: 24),
            SizedBox(
              width: double.infinity,
              child: ElevatedButton(
                onPressed: authState.loading ? null : _handleSignup,
                child: authState.loading 
                    ? const CircularProgressIndicator(color: Colors.white) 
                    : const Text('가입하기'),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

```

> **🧪 중간 테스트 (`lib/app.dart` 임시 라우트 연결):**
> * `lib/app.dart`에 `/signup` 경로를 임시 추가합니다.
> 
> 
> ```dart
> import 'features/auth/presentation/signup_page.dart';
> routes: {
>   '/': (context) => const LoginPage(),
>   '/signup': (context) => const SignupPage(),
> }
> 
> ```
> 
> 
> * 로그인 화면 하단 **"계정이 없으신가요? 회원가입하기"** 버튼 클릭 시 회원가입 페이지로 화면이 전환되는지 확인합니다.
> 
> 

#### 4. `lib/features/auth/presentation/users_page.dart`

```dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../shared/components/app_layout.dart';
import '../data/auth_provider.dart';

class UsersPage extends ConsumerWidget {
  const UsersPage({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    // [핵심] 읽어온 유저 데이터 가져오기
    final user = ref.watch(authProvider).user;

    return AppLayout(
      child: Scaffold(
        body: Padding(
          padding: const EdgeInsets.all(16.0),
          child: user == null
              ? const Center(child: Text('로그인이 필요합니다.'))
              : Column(
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  children: [
                    Card(
                      elevation: 2,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(12),
                      ),
                      child: Padding(
                        padding: const EdgeInsets.all(12.0),
                        // HTML의 <table> 태그 역할을 해주는 Flutter 내장 표 위젯
                        child: DataTable(
                          columnSpacing: 24.0,
                          columns: const [
                            DataColumn(
                              label: Text(
                                'NO',
                                style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16),
                              ),
                            ),
                            DataColumn(
                              label: Text(
                                'MYINFO',
                                style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16),
                              ),
                            ),
                          ],
                          rows: [
                            DataRow(cells: [
                              const DataCell(Text('닉네임', style: TextStyle(fontWeight: FontWeight.w600))),
                              DataCell(Text('${user['nickname'] ?? '-'}')),
                            ]),
                            DataRow(cells: [
                              const DataCell(Text('이메일', style: TextStyle(fontWeight: FontWeight.w600))),
                              DataCell(Text('${user['email'] ?? '-'}')),
                            ]),
                          ],
                        ),
                      ),
                    ),
                    const SizedBox(height: 24),
                    OutlinedButton.icon(
                      onPressed: () {
                        Navigator.pushNamedAndRemoveUntil(context, '/', (route) => false);
                      },
                      style: OutlinedButton.styleFrom(
                        padding: const EdgeInsets.symmetric(vertical: 14),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(8),
                        ),
                      ),
                      icon: const Icon(Icons.list),
                      label: const Text('게시글 목록으로 이동', style: TextStyle(fontSize: 16)),
                    ),
                  ],
                ),
        ),
        
        // 로그인 된 상태, 화면 우측 하단 둥근 플로팅 글쓰기 버튼 (FAB)
        floatingActionButton: user != null
            ? FloatingActionButton.extended(
                onPressed: () {
                  Navigator.pushNamed(context, '/post-write');
                },
                icon: const Icon(Icons.edit),
                label: const Text('글쓰기'),
              )
            : null,
      ),
    );
  }
}

```

> **🧪 중간 테스트 (`lib/app.dart` 임시 연결):**
> * `lib/app.dart`에서 메인 경로 `'/'`에 `UsersPage`를 임시 바인딩해 봅니다.
> 
> 
> ```dart
> import 'features/auth/presentation/users_page.dart';
> routes: {
>   '/': (context) => const UsersPage(),
> }
> 
> ```
> 
> 
> * 현재는 로그인 전 상태이므로 화면 중앙에 **"로그인이 필요합니다."**가 올바르게 나오는지 확인합니다.
> 
> 

#### 5. `lib/shared/components/app_layout.dart` (실제 연동 ver-2 교체)

```dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../features/auth/data/auth_provider.dart';

class AppLayout extends ConsumerWidget {
  final Widget child;
  const AppLayout({super.key, required this.child});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final authState = ref.watch(authProvider); // 전역 인증 상태
    final bool isLogined = authState.accessToken != null && authState.user != null;
    final userNickname = authState.user?['nickname'] ?? '유저';

    return Scaffold(
      appBar: AppBar(
        title: const Text('마이페이지'),
        actions: [ // {isLogined? <A/> : <B/>}
          if (isLogined) ...[
            Center(
              // [핵심] 일반 Text/Container 영역을 클릭 및 물결 터치(Ripple) 반응형으로 만들어 주는 위젯
              child: InkWell(
                onTap: () {
                  Navigator.pushNamed(context, '/users');
                },
                borderRadius: BorderRadius.circular(4.0),
                child: Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 8.0, vertical: 4.0),
                  child: Text(
                    '$userNickname님 환영합니다!',
                    style: const TextStyle(
                      decoration: TextDecoration.underline,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
              ),
            ),
            const SizedBox(width: 4),
            TextButton(
              onPressed: () {
                // [핵심] 읽기 전용으로 Notifier의 메서드 실행
                ref.read(authProvider.notifier).logout();
                ScaffoldMessenger.of(context).showSnackBar(
                  const SnackBar(content: Text('로그아웃 되었습니다.')),
                );
              },
              child: const Text('로그아웃', style: TextStyle(color: Colors.white)),
            ),
          ] else ...[
            TextButton(
              onPressed: () => Navigator.pushNamed(context, '/login'),
              child: const Text('로그인', style: TextStyle(color: Colors.white)),
            ),
          ],
        ],
      ),
      body: child,
    );
  }
}

```

#### 6. `lib/app.dart` (Step 3 완료 기준 라우터)

```dart
import 'package:flutter/material.dart';
import 'features/auth/presentation/login_page.dart';
import 'features/auth/presentation/signup_page.dart';
import 'features/auth/presentation/users_page.dart'; 
// 💡 Step 4~5 게시판 모듈 작성 후 아래 주석 해제
// import 'features/board/presentation/post_list_page.dart';
// import 'features/board/presentation/post_write_page.dart';
// import 'features/board/presentation/post_update_page.dart';

class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: '나의 소셜 앱',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        useMaterial3: true,
        appBarTheme: const AppBarTheme(
          backgroundColor: Colors.blue,
          foregroundColor: Colors.white,
        ),
      ),
      initialRoute: '/',
      routes: {
        '/': (context) => const UsersPage(), // 게시판 완성 전 임시 메인화면
        '/login': (context) => const LoginPage(),
        '/signup': (context) => const SignupPage(),
        '/users': (context) => const UsersPage(), 
      },
    );
  }
}

```

> **🧪 중간 테스트:**
> * 상단바 인증 정보 반영 레이아웃입니다. Step 5에서 게시판 모듈 완충 후 최종 라우팅을 연결해 로그인 전/후 상단바 변화를 전체적으로 검증합니다.
> 
> 

```dart
// ✏️ 연습문제 & 개념 점검 [Step 3]
// Q1. JWT 토큰을 브라우저 LocalStorage보다 안전하게 스마트폰 OS 암호화 영역에 저장해 주는 패키지 클래스는 무엇인가요?
// 답: ( FlutterSecureStorage )

// Q2. 로그인 후 router.replace('/') 처럼 뒤로 가기 스택을 지우고 메인 경로로 이동해 주는 Navigator 메서드는 무엇인가요?
// 답: Navigator.( pushNamedAndRemoveUntil )

```