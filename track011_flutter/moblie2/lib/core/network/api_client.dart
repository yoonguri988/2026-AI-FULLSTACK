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