import 'package:flutter/material.dart';
import 'package:moblie2/features/auth/presentation/login_page.dart';
import 'package:moblie2/features/auth/presentation/signup_page.dart';
import 'package:moblie2/features/auth/presentation/users_page.dart';
// import 'shared/components/app_layout.dart';
// 공통 상단바 메이아웃 (AppLayout)
class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: '나의 소셜 앱',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        useMaterial3: true, // 구글 최신 Meterial Design 3 테마 적용
        appBarTheme: const AppBarTheme(
          backgroundColor: Colors.blue,  // 파란색 테마 헤더
          foregroundColor: Colors.white, // 글자 하얀색
        ),
      ),
      initialRoute: '/',
      routes: {
        '/': (context) => const UsersPage(),        // 메인페이지(게시글 목록)
        '/login': (context) => const LoginPage(),   // 로그인
        '/signup': (context) => const SignupPage(), // 회원가입
        '/users': (context) => const UsersPage(),   // 마이페이지
        // '/': (context) => const AppLayout(
        //       child: Center(
        //         child: Text(
        //           'BLUE상단바 🎉',
        //           style:r TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
        //         ),
        //       ),
        //     ),
      },
    );
  }
}