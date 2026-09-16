# 📱 [단계별 실습] Flutter + Spring Boot 풀스택 프로젝트 (Feature-first)

---

## 🛠️ Step 0. 개발 환경 설정 및 터미널 구동

### 1. Windows 개발자 모드 활성화 (최초 1회)

* `Win + R` 입력 후 `ms-settings:developers` 실행 ➔ **개발자 모드 켬**

### 2. 프로젝트 생성 및 1차 구동 테스트

```bash
# 1. 프로젝트 생성
 

# 2. 프로젝트 디렉토리 이동
 
# 3. 프로젝트 기본 실행 (Windows 타겟 선택)
 
 
1

```

```bash
# Flutter 빌드 캐시 및 임시 파일 삭제
flutter _______

# pubspec.yaml 파일의 패키지 의존성 다운로드
flutter _______ _______

# Windows 네이티브 타겟으로 디버그 빌드 구동
flutter run -d _______

```

> **👨‍🏫 선생님 팁:** `1`번(Windows)을 선택하여 구동합니다. 웹 브라우저(Chrome)의 CORS 에러를 회피하고 에뮬레이터 없이 빠른 C++ 네이티브 빌드로 테스트하기 위함입니다.

> **🧪 중간 테스트:**
> * 터미널에서 `flutter run` 실행 후 `1`을 눌렀을 때 기본 템플릿 앱(카운터 앱) 창이 성공적으로 뜨는지 확인합니다.
> 
> 

### 3. 필수 패키지 설치 및 환경 동기화

```bash
# Riverpod, Dio, Secure Storage, Image Picker 설치
flutter pub add _______
flutter pub add _______
flutter pub add _______
flutter pub add _______

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
# 답: flutter run -d (                     )

# Q2. Axios처럼 HTTP Interceptor 기능을 지원하는 Flutter 비동기 통신 라이브러리는 무엇인가요?
# 답: (                     )

```

---

## 🚀 Step 1. 앱의 시작점 및 뼈대 검증

#### 1. `lib/main.dart`

```dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'app.dart';

void main() {
  // Flutter 엔진과 플랫폼 바인딩 초기화
  WidgetsFlutterBinding._______();
  
  // ProviderScope: Riverpod 전역 상태를 앱 전체에 주입
  runApp(const _______(child: App()));
}

```

> **🧪 중간 테스트:**
> * 이 시점에는 `app.dart`가 아직 없거나 연결되지 않아 문법 에러가 뜰 수 있으므로, 아래 `lib/app.dart`까지 작성 후 함께 실행 테스트를 진행합니다.
> 
> 

#### 2. `lib/app.dart` (뼈대 테스트)

```dart
import 'package:flutter/material.dart';

class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return _______(
      title: '나의 소셜 앱',
      debugShowCheckedModeBanner: false,
      initialRoute: '/',
      routes: {
        // 루트 경로 매핑
        '/': (context) => _______(
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
// 답: (                     )

// Q2. Flutter에서 상단 헤더바, 본문, 하단 탭 등의 기본 뼈대를 제공해 주는 Layout 구조 위젯은 무엇인가요?
// 답: (                     )

```

---

## 🌐 Step 2. Core & Shared (임시 레이아웃 검증)

#### 1. `lib/core/network/api_client.dart`

```dart
import 'package:flutter/foundation.dart';
import 'dart:io' show Platform;

class ApiClient {
  static String getBaseUrl() {
    if (kIsWeb) return 'http://localhost:8080';
    try {
      // Android 에뮬레이터 접속 우회 IP
      if (Platform.isAndroid) return 'http://_______:8080';
    } catch (_) {}
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
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

// Riverpod 상태를 지켜보기 위해 ConsumerWidget 상속
class AppLayout extends _______ {
  final Widget child;
  const AppLayout({super.key, required this.child});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    // 임시 테스트용 로그인 여부 플래그
    final bool isLogined = DateTime.now().year < 2000;

    return Scaffold(
      appBar: AppBar(
        title: const Text('나의 소셜 앱'),
        actions: [
          if (isLogined) ...[
            const Center(
              child: Padding(
                padding: EdgeInsets.symmetric(horizontal: 8.0),
                child: Text('테스트유저님 환영합니다!'),
              ),
            ),
          ] else ...[
            TextButton(
              // /login 화면으로 페이지 이동
              onPressed: () => Navigator._______(context, '/login'),
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
        '/': (context) => const _______(
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
// 답: http://(                     ):8080

// Q2. React의 useSelector처럼 Riverpod에서 전역 상태를 관찰(구독)할 수 있도록 StatelessWidget 대신 상속받는 위젯 클래스는 무엇인가요?
// 답: (                     )

```

---

## 🔐 Step 3. Features: Auth (인증 기능 완성)

#### 1. `lib/features/auth/data/auth_provider.dart`

```dart
import 'package0.flutter_riverpod/flutter_riverpod.dart';
import 'package:dio/dio.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import '../../../core/network/api_client.dart';

// 인증 상태 구조체
class AuthState {
  final Map<String, dynamic>? user;
  final String? accessToken;
  final bool loading;
  final String? error;

  const AuthState({
    this.user,
    this.accessToken,
    this.loading = false,
    this.error,
  });
}

// Riverpod Notifier 클래스 정의
class AuthNotifier extends _______<AuthState> {
  @override
  AuthState build() {
    _initDio();
    return const AuthState();
  }

  late final Dio _dio;
  final _storage = const _______();

  void _initDio() {
    _dio = Dio(BaseOptions(
      baseUrl: ApiClient.getBaseUrl(),
      headers: {'Content-Type': 'application/json'},
    ));

    // Dio Request/Response Interceptor 주입
    _dio._______ .add(InterceptorsWrapper(
      onRequest: (options, handler) async {
        final token = await _storage.read(key: 'accessToken');
        if (token != null) {
          options.headers['Authorization'] = 'Bearer $token';
        }
        return handler.next(options);
      },
      onError: (DioException e, handler) async {
        if (e.response?.statusCode == 401) {
          final success = await _refreshAccessToken();
          if (success) {
            final token = await _storage.read(key: 'accessToken');
            e.requestOptions.headers['Authorization'] = 'Bearer $token';
            final clonedRequest = await _dio.fetch(e.requestOptions);
            return handler.resolve(clonedRequest);
          }
        }
        return handler.next(e);
      },
    ));
  }

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

  // 로그인 비동기 액션
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

  // 로그아웃 액션
  Future<void> logout() async {
    try {
      await _dio.post('/auth/logout');
    } catch (_) {}
    await _storage.delete(key: 'accessToken');
    state = const AuthState();
  }

  // 회원가입 비동기 액션 (Multipart/FormData)
  Future<bool> signup(Map<String, dynamic> data) async {
    state = AuthState(
      user: state.user,
      accessToken: state.accessToken,
      loading: true,
      error: null,
    );

    try {
      final formData = _______ .fromMap({
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

  // 이메일 중복 체크
  Future<bool> checkEmailDuplicate(String email) async {
    try {
      final response = await _dio.get('/auth/check-email', queryParameters: {'email': email});
      return response.data;
    } catch (e) {
      return false;
    }
  }

  // 닉네임 중복 체크
  Future<bool> checkNicknameDuplicate(String nickname) async {
    try {
      final response = await _dio.get('/auth/check-nickname', queryParameters: {'nickname': nickname});
      return response.data;
    } catch (e) {
      return false;
    }
  }
}

// Provider 등록
final authProvider = _______<AuthNotifier, AuthState>(() {
  return AuthNotifier();
});

```

> **🧪 중간 테스트:**
> * 전역 상태 로직(Provider) 작성 단계입니다. 터미널 및 에디터에서 구문 에러가 없는지 체크하고 UI 페이지 작성으로 진행합니다.
> 
> 

#### 2. `lib/features/auth/presentation/login_page.dart`

```dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../data/auth_provider.dart';

class LoginPage extends _______ {
  const LoginPage({super.key});

  @override
  ConsumerState<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends ConsumerState<LoginPage> {
  // 입력 제어용 컨트롤러
  final _emailController = _______();
  final _passwordController = TextEditingController();

  @override
  void dispose() {
    _emailController.dispose();
    _passwordController.dispose();
    super.dispose();
  }

  void _handleLogin() async {
    final email = _emailController.text.trim();
    final password = _passwordController.text.trim();

    if (email.isEmpty || password.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('이메일과 비밀번호를 입력해주세요.')),
      );
      return;
    }

    // Provider 메서드 실행 (비동기 처리)
    final success = await ref.read(authProvider.notifier).login({'email': email, 'password': password});
    if (success && mounted) {
      // 히스토리 남기지 않고 메인 페이지 이동
      Navigator._______(context, '/', (route) => false);
    }
  }

  @override
  Widget build(BuildContext context) {
    // authProvider 전역 상태 구독
    final authState = ref._______(authProvider);

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
      _______(() => _isEmailChecked = false);
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
      Navigator._______(context); 
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
                _______(
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
                        child: _______(
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
        
        // 플로팅 글쓰기 버튼
        floatingActionButton: user != null
            ? _______ .extended(
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
    final authState = ref.watch(authProvider);
    final bool isLogined = authState.accessToken != null && authState.user != null;
    final userNickname = authState.user?['nickname'] ?? '유저';

    return Scaffold(
      appBar: AppBar(
        title: const Text('나의 소셜 앱'),
        actions: [
          if (isLogined) ...[
            Center(
              child: _______(
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

#### 6. `lib/app.dart`

```dart
import 'package:flutter/material.dart';
import 'features/auth/presentation/login_page.dart';
import 'features/auth/presentation/signup_page.dart';
import 'features/board/presentation/post_list_page.dart';
import 'features/board/presentation/post_write_page.dart';
import 'features/board/presentation/post_update_page.dart';
import 'features/auth/presentation/users_page.dart'; 

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
        '/': (context) => const PostListPage(),
        '/login': (context) => const LoginPage(),
        '/signup': (context) => const SignupPage(),
        '/users': (context) => const UsersPage(), 
      },
    );
  }
}

```

> **🧪 중간 테스트:**
> * 상단바 인증 정보 반영 레이아웃입니다. Step 5에서 최종 라우팅을 연결해 로그인 전/후 상단바 변화를 전체적으로 검증합니다.
> 
> 

```dart
// ✏️ 연습문제 & 개념 점검 [Step 3]
// Q1. JWT 토큰을 브라우저 LocalStorage보다 안전하게 스마트폰 OS 암호화 영역에 저장해 주는 패키지 클래스는 무엇인가요?
// 답: (                     )

// Q2. 로그인 후 router.replace('/') 처럼 뒤로 가기 스택을 지우고 메인 경로로 이동해 주는 Navigator 메서드는 무엇인가요?
// 답: Navigator.(                     )

```