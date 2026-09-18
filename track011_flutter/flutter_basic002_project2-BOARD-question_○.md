# 📱 [단계별 실습] Flutter + Spring Boot 풀스택 프로젝트 (Feature-first)

---

## 📝 Step 4. Features: Post (게시판 목록 및 글쓰기 완결)

#### 1. 게시판 상태 관리 `lib/features/post/data/board_provider.dart`

```dart
// 전역상태관리: React의 Redux
import 'package:flutter_riverpod/flutter_riverpod.dart';
// 비동기처리: React의 Axios
import 'package:dio/dio.dart';
// 보안 저장소
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
// 모바일기기에서 갤러리/카메라 접근라이브러리
import 'package:image_picker/image_picker.dart';
// 서버 Base URL: 10.0.0.2 / localhost:8080 / https://thejoa703.ducksdns.org
import '../../../core/network/api_client.dart';

//// part1) 게시판의 상태
class BoardState {
  final List<dynamic> posts;
  final bool loading;
  final String? error;

  const BoardState({
    this.posts = const [],
    this.loading = false,
    this.error,
  });
}

//// part2) Saga, 최신상태 반영
class BoardNotifier extends Notifier<BoardState> {
  @override
  BoardState build() { // Notifier 초기화, reducer 초기상태
    _initDio();
    return const BoardState();
  }

  late final Dio _dio; // late 지연 초기화 Dio 객체초기화
  final _storage = const FlutterSecureStorage(); // 보안 저장소

  void _initDio() {
    _dio = Dio(BaseOptions( // 기본 옵션 설정
      baseUrl: ApiClient.getBaseUrl(), // 10.0.0.2 / localhost:8080 / https://thejoa703.ducksdns.org
    ));

    // 인증 토큰 자동 첨부 + 만료 시 자동 재발급 인터셉터
    _dio.interceptors.add(InterceptorsWrapper( // InterceptorsWrapper
      // 요청
      onRequest: (options, handler) async {
        final token = await _storage.read(key: 'accessToken');
        if (token != null) {
          options.headers['Authorization'] = 'Bearer $token';
        }
        return handler.next(options);
      },
      // 에러
      onError: (DioException e, handler) async {
        // 401(인증 실패, 토큰 만료)일 때만 재발급 시도
        if (e.response?.statusCode == 401) {
          print('⚠️ [board_provider] 401 감지 - 토큰 재발급 시도');
          final refreshed = await _refreshAccessToken(); // 재발급시도
          if (refreshed) {
            final newToken = await _storage.read(key: 'accessToken');
            e.requestOptions.headers['Authorization'] = 'Bearer $newToken';
            try {
              // 새 토큰으로 원래 요청 재시도
              final cloned = await _dio.fetch(e.requestOptions);
              return handler.resolve(cloned);
            } catch (retryErr) {
              print('❌ [board_provider] 재시도 요청도 실패: $retryErr');
              return handler.next(e);
            }
          } else {
            print('❌ [board_provider] 토큰 재발급 실패');
          }
        }
        return handler.next(e);
      },
    ));
  }

  // 리프레시 토큰으로 새 accessToken 재발급
  Future<bool> _refreshAccessToken() async {
    try {
      // 인터셉터 무한루프 방지를 위해 별도 Dio 인스턴스 사용
      final refreshDio = Dio(BaseOptions(baseUrl: ApiClient.getBaseUrl()));
      final response = await refreshDio.post('/auth/refresh');
      final newAccessToken = response.data['accessToken']; //map 토큰 추출
      if (newAccessToken != null) {
        await _storage.write(key: 'accessToken', value: newAccessToken);
        return true;
      }
      return false;
    } catch (err) {
      print('❌ [board_provider] refresh 요청 실패: $err');
      return false;
    }
  }

  // 1. 전체 게시글 조회 (GET /api/posts)
  Future<void> fetchPosts() async {
    state = BoardState(posts: state.posts, loading: true, error: null);
    try {
      final response = await _dio.get('/api/posts'); // boot 게시글 목록 요청
      final List<dynamic> fetchedPosts = response.data is List ? response.data : [];
      state = BoardState(posts: fetchedPosts, loading: false, error: null); // 데이터 업데이트
    } catch (err) {
      state = BoardState(posts: state.posts, loading: false, error: '조회 실패: ${err.toString()}');
    }
  }

  // 2. 게시글 작성 (POST /api/posts - 멀티파트 + 파일 바이트 전송으로 용량 안정화 + userId 누락 방지)
  Future<bool> createPost({
    required String userId,
    required String content,
    String? hashtags,
    List<XFile>? imageFiles,
  }) async {
    try {
      //  작성자(userId)가 누락되어 익명으로 뜨는 문제를 방지하기 위해 빈 값이 아닐 때만 안전하게 포함
      final Map<String, dynamic> dataMap = { // 전송할 Map (Json)
        'content': content,
        if (hashtags != null && hashtags.isNotEmpty) 'hashtags': hashtags,
      };
      
      if (userId.isNotEmpty) {
        dataMap['userId'] = userId;
      }

      FormData formData = FormData.fromMap(dataMap); // multipart/form-data 객체 생성

      //  패키지 오류 없이 XFile의 바이트를 직접 읽어 전송하여 업로드 안정성 확보
      if (imageFiles != null && imageFiles.isNotEmpty) { // 첨부이미지가 있는 경우
        for (var image in imageFiles) {
          final bytes = await image.readAsBytes(); // 파일의 바이너리 바이트 배열 읽기
          formData.files.add(MapEntry(
            'files', // boot - RequestPart("files")
            MultipartFile.fromBytes( // MultipartFile
              bytes,
              filename: image.name.isNotEmpty ? image.name : 'upload.jpg',
            ),
          ));
        }
      }

      await _dio.post('/api/posts', data: formData); // MultipartFile - post 요청 전송
      await fetchPosts(); // 글 다쓰고나서 게시글 목록 새로고침
      return true;
    } on DioException catch (e) {
      print('❌ [createPost DioException]: ${e.response?.statusCode} - ${e.response?.data}');
      return false;
    } catch (err) {
      print('❌ [createPost Unknown Error]: $err');
      return false;
    }
  }


}

final boardProvider = NotifierProvider<BoardNotifier, BoardState>(() {// 1. NotifierProvider 전역 프로바이더 정의
  return BoardNotifier();
});

```

> **🧪 중간 테스트:**
> * 게시판 전역 상태 파일입니다. 구문 오타가 없는지 확인 후 UI 페이지 연결로 넘어갑니다.
> 
> 

#### 2. 글쓰기 `lib/features/post/presentation/post_write_page.dart`

```dart
import 'dart:typed_data';
import 'package:flutter/foundation.dart'; // 기본유틸
import 'package:flutter/material.dart'; // ui 컴포넌트 모음
import 'package:flutter_riverpod/flutter_riverpod.dart'; // riverpod 전역상태관리
import 'package:image_picker/image_picker.dart'; // 이미지선택
import '../data/board_provider.dart'; // 전역상태 + 서버연동 데이터 가져오는 기능 (boardProvider)
import '../../auth/data/auth_provider.dart'; // 인증상태 프로바이더

class PostWritePage extends ConsumerStatefulWidget { // ConsumerStatefulWidget
  const PostWritePage({super.key});

  @override
  ConsumerState<PostWritePage> createState() => _PostWritePageState();
}

class _PostWritePageState extends ConsumerState<PostWritePage> {
  final _contentController = TextEditingController(); // 입력컨트롤러
  final _hashtagController = TextEditingController();
  
  final ImagePicker _picker = ImagePicker(); // 이미지선택
  List<XFile> _selectedImages = [];
  
  final Map<String, Uint8List> _imageBytesCache = {};

  @override
  void dispose() { // 위젯 메모리 해제
    _contentController.dispose();
    _hashtagController.dispose();
    super.dispose();
  }

  Future<void> _pickImages() async {
    final List<XFile> images = await _picker.pickMultiImage(); // 다중 이미지 선택 - pickMultiImage
    if (images.isNotEmpty) {
      for (var image in images) { // 선택된 파일들
        final bytes = await image.readAsBytes(); // 바이트 데이터 추출
        _imageBytesCache[image.path] = bytes; // 경로를 바이트 저장
      }
      setState(() { // ui 그리기
        _selectedImages = images;
      });
    }
  }

  void _handleSubmit() async {
    final content = _contentController.text.trim(); // 본문텍스트 공백제거
    final hashtags = _hashtagController.text.trim();
    
    // 유저 ID 안전 추출
    final user = ref.read(authProvider).user; // redux + saga = provider 기능의 user 가져오기
    final rawId = user?['id'] ?? user?['userId'] ?? user?['memberId'] ?? '1'; // 백엔드 필드 대응 null-aware 키 추출
    final userId = rawId.toString();

    if (content.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('내용을 입력해주세요.')),
      );
      return;
    }

    // react: redux + saga = provider => boot 요청
    final success = await ref.read(boardProvider.notifier).createPost(
      userId: userId,
      content: content,
      hashtags: hashtags,
      imageFiles: _selectedImages,
    );

    if (success && mounted) {
      // 등록 성공 알림 띄우기 (화면 상단/하단 SnackBar)
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('게시글이 성공적으로 등록되었습니다! 🎉'),
          duration: Duration(seconds: 2),
          behavior: SnackBarBehavior.floating, // 바닥에 붙지 않고 떠있는 깔끔한 스타일
        ),
      );
      Navigator.pop(context); // 현재 화면 닫고 전화면 이동
    }
  }
/////////////////////////////////////////////////////////
  @override
  Widget build(BuildContext context) {
    return Scaffold( // 앱의 기본골격
      appBar: AppBar(title: const Text('새 글 작성')), // 상단앱바
      body: Padding( // 부품 Padding: 여백 레이이아웃
        padding: const EdgeInsets.all(16.0),
        child: ListView( // 부품 ListView: 스크롤가능한 리스트뷰
          children: [
            TextField( // 부품 TextField: 입력폼 위젯 
              controller: _contentController, // 컨트롤러 바인딩
              decoration: const InputDecoration(labelText: '내용 입력'), // 라벨
              maxLines: 5,// 줄 공간 확보
            ),
            const SizedBox(height: 12), // 세로 사이즈 12px
            TextField(
              controller: _hashtagController,
              decoration: const InputDecoration(labelText: '해시태그 (예: #flutter, #spring)'),
            ),
            const SizedBox(height: 20),
            
            ElevatedButton.icon(
              onPressed: _pickImages,
              icon: const Icon(Icons.image),
              label: Text('이미지 첨부하기 (${_selectedImages.length}장 선택됨)'),
            ),
            const SizedBox(height: 12),

            if (_selectedImages.isNotEmpty)
              SizedBox(
                height: 100, // 프리뷰 영역 100px 제한
                child: ListView.builder( // 동적 리스트 생성
                  scrollDirection: Axis.horizontal, // 스크롤바 가로
                  itemCount: _selectedImages.length, // 선택 갯수
                  itemBuilder: (context, index) {
                    final image = _selectedImages[index];
                    final bytes = _imageBytesCache[image.path];

                    return Padding(
                      padding: const EdgeInsets.only(right: 8.0), // 오른쪽 여백
                      child: Stack( // 부품: 위젯겹치는 배치 레이아웃
                        children: [
                          bytes != null
                              ? Image.memory(bytes, width: 100, height: 100, fit: BoxFit.cover)
                              : Container(width: 100, height: 100, color: Colors.grey[300]),
                          Positioned(
                            top: 0,
                            right: 0,
                            child: IconButton(
                              icon: const Icon(Icons.remove_circle, color: Colors.red),
                              onPressed: () {
                                setState(() {
                                  _imageBytesCache.remove(image.path);
                                  _selectedImages.removeAt(index);
                                });
                              },
                            ),
                          ),
                        ],
                      ),
                    );
                  },
                ),
              ),

            const SizedBox(height: 24),
            SizedBox(
              width: double.infinity,
              child: ElevatedButton(
                style: ElevatedButton.styleFrom(backgroundColor: Colors.blue, foregroundColor: Colors.white),
                onPressed: _handleSubmit,
                child: const Text('등록하기', style: TextStyle(fontSize: 16)),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

```

#### 3. 글리스트 `lib/features/post/presentation/post_list_page.dart`

```dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../../../shared/components/app_layout.dart';
import '../data/board_provider.dart';
import '../../auth/data/auth_provider.dart';
import 'post_detail_page.dart';
import '../../../core/network/api_client.dart';

class PostListPage extends ConsumerStatefulWidget {
  const PostListPage({super.key});

  @override
  ConsumerState<PostListPage> createState() => _PostListPageState();
}

class _PostListPageState extends ConsumerState<PostListPage> {
  // 이미지 URL을 안전하게 완성하는 헬퍼 메서드
  String _resolveImageUrl(String url) {
    if (url.startsWith('http://') || url.startsWith('https://')) {
      return url;
    }
    final String serverBaseUrl = ApiClient.getBaseUrl();
    final cleanBase = serverBaseUrl.endsWith('/')
        ? serverBaseUrl.substring(0, serverBaseUrl.length - 1)
        : serverBaseUrl;
    final cleanUrl = url.startsWith('/') ? url : '/$url';
    return '$cleanBase$cleanUrl';
  }

  @override
  void initState() {
    super.initState();
    // 화면 그림 그리기
    Future.microtask(() => ref.read(boardProvider.notifier).fetchPosts());
  } // react useEffect(..., []) 1번 읽어들일께

  @override
  Widget build(BuildContext context) {
    final boardState = ref.watch(boardProvider); // watch 지속적 확인 useEffect ( ..., [user])
    final authState = ref.watch(authProvider); // 로그인 상태 감지

    return AppLayout(
      child: Scaffold(
        body: boardState.loading && boardState.posts.isEmpty // 로딩중
            ? const Center(child: CircularProgressIndicator()) // 로딩화면
            : boardState.posts.isEmpty
                ? const Center(child: Text('등록된 게시글이 없습니다.'))
                : RefreshIndicator(
                    onRefresh: () async {
                      await ref.read(boardProvider.notifier).fetchPosts();
                    },
                    child: ListView.builder(
                      itemCount: boardState.posts.length,
                      itemBuilder: (context, index) {
                        final post = boardState.posts[index];

                        // 백엔드 API 응답 키(authorNickname 최우선) 안전하게 추출
                        final String nickname = post['authorNickname'] ??
                            post['userNickname'] ??
                            post['nickname'] ??
                            post['writerNickname'] ??
                            post['user']?['nickname'] ??
                            '익명';

                        final String content = post['content'] ?? '';
                        final List<dynamic> hashtags = post['hashtags'] ?? [];
                        final List<dynamic> imageUrls = post['imageUrls'] ?? [];

                        return Card(
                          margin: const EdgeInsets.symmetric(
                              horizontal: 12, vertical: 6),
                          child: InkWell(
                            onTap: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (context) =>
                                      PostDetailPage(post: post),
                                ),
                              );
                            },
                            child: Padding(
                              padding: const EdgeInsets.all(12.0),
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    '작성자: $nickname',
                                    style: const TextStyle(
                                      fontWeight: FontWeight.bold,
                                      color: Colors.blue,
                                    ),
                                  ),
                                  const SizedBox(height: 6),
                                  Text(
                                    content,
                                    style: const TextStyle(fontSize: 16),
                                    maxLines: 2, // 최대 2줄만 보이게
                                    overflow: TextOverflow.ellipsis, // 말줄임표
                                  ),
                                  const SizedBox(height: 6),
                                  if (hashtags.isNotEmpty)
                                    Wrap( // flex-wrap
                                      spacing: 6.0, // 요소간의 간격
                                      children: hashtags
                                          .map(
                                            (tag) => Text(
                                              tag.toString().startsWith('#')
                                                  ? tag.toString()
                                                  : '#$tag',
                                              style: const TextStyle(
                                                  color: Colors.indigo),
                                            ),
                                          )
                                          .toList(),
                                    ),
                                  if (imageUrls.isNotEmpty) ...[
                                    const SizedBox(height: 8),
                                    ClipRRect( // 자식 이미지 모서리 둥글게
                                      borderRadius: BorderRadius.circular(6.0),
                                      child: Image.network(
                                        _resolveImageUrl(
                                            imageUrls.first.toString()), // 이미지 주소
                                        height: 120,
                                        width: double.infinity,
                                        fit: BoxFit.cover, // 이미지 비율 꽉 채우기 object-fit: cover
                                        errorBuilder: // 이미지 실패시
                                            (context, error, stackTrace) =>
                                                Container(
                                          height: 120,
                                          color: Colors.grey[200],
                                          alignment: Alignment.center,
                                          child: const Text(
                                            '이미지를 불러올 수 없습니다.',
                                            style: TextStyle(
                                                color: Colors.grey,
                                                fontSize: 12),
                                          ),
                                        ),
                                      ),
                                    ),
                                  ]
                                ],
                              ),
                            ),
                          ),
                        );
                      },
                    ),
                  ),
        floatingActionButton: FloatingActionButton( // 화면 우측 하단 플로팅 글쓰기 버튼
          onPressed: () { // 버튼 클릭시
            if (authState.user == null && authState.accessToken == null) {
              ScaffoldMessenger.of(context).showSnackBar(
                const SnackBar(content: Text('로그인이 필요한 서비스입니다.')), //pushNamed - 로그인 화면용
              );
              Navigator.pushNamed(context, '/login'); //
            } else {
              Navigator.pushNamed(context, '/post-write'); //
            }
          },
          child: const Icon(Icons.create),
        ),
      ),
    );
  }
}

```

#### 4. 글상세 `lib/features/post/presentation/post_detail_page.dart`

```dart
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import '../data/board_provider.dart';
import '../../auth/data/auth_provider.dart';
import '../../../core/network/api_client.dart';

class PostDetailPage extends ConsumerWidget {
  final Map<String, dynamic> post;

  const PostDetailPage({super.key, required this.post});

  // 이미지 URL 정합성 맞추기 헬퍼 메서드
  String _resolveImageUrl(String url) {
    
    if (url.startsWith('http://') || url.startsWith('https://')) {
      return url;
    }
    final String serverBaseUrl = ApiClient.getBaseUrl(); // https://d2big.ducksdns.org
    final cleanBase = serverBaseUrl.endsWith('/')
        ? serverBaseUrl.substring(0, serverBaseUrl.length - 1)
        : serverBaseUrl;
    final cleanUrl = url.startsWith('/') ? url : '/$url';
    return '$cleanBase$cleanUrl';
  }

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final postId = post['id'];

    // [수정 포인트]: boardState가 BoardState 객체이므로 내부 리스트(posts, boardList, items)를 감지하여 순회
    final boardState = ref.watch(boardProvider);
    Map<String, dynamic> currentPost = post; // 찾지 못할 경우 기본 post 사용

    final dynamic rawPosts = (boardState as dynamic).posts ??
        (boardState as dynamic).boardList ??
        (boardState as dynamic).items;

    if (rawPosts is List) {
      for (final item in rawPosts) {
        if (item is Map && item['id'] == postId) {
          currentPost = Map<String, dynamic>.from(item);
          break;
        }
      }
    }

    // 널 안전성(Null Safety) 및 타입 안전 추출
    final String postNickname = (currentPost['authorNickname'] ??
            currentPost['userNickname'] ??
            currentPost['nickname'] ??
            currentPost['writerNickname'] ??
            currentPost['user']?['nickname'] ??
            currentPost['writer']?['nickname'] ??
            '')
        .toString();

    final String content = (currentPost['content'] ?? '').toString();
    final List<dynamic> hashtags =
        currentPost['hashtags'] is List ? currentPost['hashtags'] : [];
    final List<dynamic> imageUrls =
        currentPost['imageUrls'] is List ? currentPost['imageUrls'] : [];
    final String createdAt = (currentPost['createdAt'] ?? '').toString();

    // 현재 로그인한 사용자 정보 감지
    final authState = ref.watch(authProvider);
    final currentUser = authState.user;

    // 작성자 본인 판별 로직
    bool isMyPost = false;

    if (currentUser != null) {
      final postUserId = currentPost['authorId'] ??
          currentPost['userId'] ??
          currentPost['user_id'] ??
          currentPost['memberId'] ??
          currentPost['writerId'];

      final currentUserId = currentUser['id'] ??
          currentUser['userId'] ??
          currentUser['memberId'] ??
          currentUser['authorId'];

      final currentNickname = currentUser['nickname'] ??
          currentUser['authorNickname'] ??
          currentUser['userNickname'] ??
          currentUser['name'] ??
          '';

      if (postUserId != null && currentUserId != null) {
        isMyPost =
            postUserId.toString().trim() == currentUserId.toString().trim();
      }

      if (!isMyPost && postNickname.isNotEmpty && currentNickname.isNotEmpty) {
        isMyPost = postNickname.trim() == currentNickname.trim();
      }
    }

    return Scaffold(
      appBar: AppBar(
        title: const Text('게시글 상세보기'),
        actions: [
        ],
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: ListView(
          children: [
            Text(
              '작성자: ${postNickname.isEmpty ? '익명' : postNickname}',
              style: const TextStyle(
                fontSize: 16,
                fontWeight: FontWeight.bold,
                color: Colors.blue,
              ),
            ),
            const SizedBox(height: 4),
            if (createdAt.isNotEmpty)
              Text(
                '작성일: $createdAt',
                style: const TextStyle(color: Colors.grey, fontSize: 12),
              ),
            const Divider(height: 24),
            Text(
              content,
              style: const TextStyle(fontSize: 18),
            ),
            const SizedBox(height: 16),
            if (hashtags.isNotEmpty)
              Wrap(
                spacing: 6.0,
                children: hashtags
                    .map(
                      (tag) => Text(
                        tag.toString().startsWith('#')
                            ? tag.toString()
                            : '#$tag',
                        style: const TextStyle(
                          color: Colors.indigo,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    )
                    .toList(),
              ),
            const SizedBox(height: 16),
            if (imageUrls.isNotEmpty)
              ...imageUrls.map((url) {
                final resolvedUrl = _resolveImageUrl(url.toString());
                return Padding(
                  padding: const EdgeInsets.only(bottom: 12.0),
                  child: ClipRRect(
                    borderRadius: BorderRadius.circular(8.0),
                    child: Image.network(
                      Uri.encodeFull(resolvedUrl),
                      fit: BoxFit.cover,
                      errorBuilder: (context, error, stackTrace) => Container(
                        padding: const EdgeInsets.all(16),
                        color: Colors.grey[200],
                        child: Text(
                          '이미지를 불러올 수 없습니다.\n($resolvedUrl)',
                          textAlign: TextAlign.center,
                          style:
                              const TextStyle(color: Colors.red, fontSize: 11),
                        ),
                      ),
                    ),
                  ),
                );
              }),
          ],
        ),
      ),
    );
  }
}

```

```dart
// ✏️ 연습문제 & 개념 점검 [Step 4]
// Q1. Flutter에서 모바일 기기의 갤러리에 접근하여 이미지를 선택할 때 사용하는 공식 라이브러리는 무엇인가요?
// 답: ( image_picker )

// Q2. 게시글 작성 시 이미지 파일과 일반 텍스트 데이터를 함께 백엔드로 전송하기 위해 Dio에서 사용하는 Multipart 전송 객체는 무엇인가요?
// 답: ( FormData )


```

---

## 📱 Step 5. 프로젝트 완성 및 Android APK 배포 (마지막 단계)

# 👨‍🏫 선생님 설명:
# 1. 실기기 테스트 IP 점검: 실기기에서 PC 백엔드와 통신하려면 localhost 대신 컴퓨터의 실제 IPv4 주소를 설정해야 합니다.
# 2. APK 빌드: CLI에서 `flutter build apk --release` 명령을 실행해 모바일에 직접 설치 가능한 단일 설치 파일(.apk)을 추출합니다.

### 0. 이모티콘

1. **로고 이미지 배치:** 이미지 준비.
1024x1024 해상도의 PNG 이미지 파일을 준비합니다.

```bash
내_플러터_프로젝트/
├── assets/
│   └── images/
│       └── app_logo.png  <-- 여기에 이미지 저장
├── lib/
├── pubspec.yaml
└── ...

```

2. **패키지 추가 및 설정:** pubspec.yaml.
`pubspec.yaml` 파일 하단에 아래 설정을 추가합니다.

```yaml
name: moblie2
description: "A new Flutter project."
publish_to: 'none'

version: 1.0.0+1

environment:
  sdk: ^3.13.3

dependencies:
  flutter:
    sdk: flutter

  cupertino_icons: ^1.0.8
  flutter_riverpod: ^3.3.2
  dio: ^5.11.1
  flutter_secure_storage: ^11.1.1
  image_picker: ^1.2.3

dev_dependencies:
  flutter_test:
    sdk: flutter
  flutter_lints: ^6.0.0
  flutter_launcher_icons: ^0.14.4  # [수정] 패키지 추가

flutter_launcher_icons:
  android: true
  ios: true
  image_path: "assets/images/app_logo.png"
  min_sdk_android: 21

flutter:
  uses-material-design: true

  #  앱 아이콘 이미지 사용을 위한 assets 등록
  assets:
    - assets/images/


```

3. **아이콘 생성 및 명령어 실행:** 터미널 실행.
터미널에서 아래 명령어를 순서대로 실행합니다.

```bash
flutter pub get
dart run flutter_launcher_icons


```

4. INTERNET 권한 추가할 파일

moblie\android\app\src\main\AndroidManifest.xml

이 파일 열어서  태그 바로 안쪽,  태그보다 위에 추가하면 됩니다:

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:label="TheJoa703"
        ...>

```

### 5.  Android APK 빌드 명령어 실행

터미널에서 아래 명령어를 입력하여 실행 가능한 APK 파일을 생성합니다.

```bash
flutter build apk --release
```

> 오류나면  NDK (1)

* Android Studio → Settings → Languages & Frameworks → Android SDK → SDK Tools 탭 → 우측 하단 Show Package Details 체크 → NDK (Side by side) 목록에서 28.2.13676358 체크 → Apply
* 설치 후 C:\Users\tj-bu-702-13\AppData\Local\Android\sdk\ndk\28.2.13676358 폴더가 생겼는지 확인하세요.  안에 내용물들어가 있는지 확인

```bash
flutter clean
flutter pub get
flutter build apk --release

```

> 오류나면 드라이브문제 (2)

```
# android/gradle.properties

kotlin.incremental=false
kotlin.incremental.useClasspathSnapshot=false

```

```
flutter clean 
flutter pub get
flutter build apk --release

```

> **🧪 중간 테스트:**
> * 빌드가 완료되면 터미널 하단에 다음과 같은 출력 결과가 나오며 APK 파일이 생성됩니다.
> * `Built build\app\outputs\flutter-apk\app-release.apk (20.5MB)`
> 
> 

---

### 6. 추출된 APK 파일 확인 및 스마트폰 설치

1. **파일 위치:** 프로젝트 폴더 내 `build\app\outputs\flutter-apk\` 경로로 이동합니다.
2. **`app-release.apk` 파일 추출:** 해당 파일을 카카오톡 나에게 보내기, 구글 드라이브, 또는 USB 케이블을 통해 본인의 Android 스마트폰으로 전송합니다.
3. **앱 설치:** 핸드폰에서 파일 클릭 ➔ **'출처를 알 수 없는 앱 설치 허용'** 진행 후 앱을 직접 구동해 봅니다.

---

### 💡 수업 마무리 팁

* **Play Store 등록(스토어 배포):** 구글 개발자 계정 결제($25) 및 앱 심사 과정이 필요하므로 수업 시간엔 "APK 추출 및 기기 설치"까지만 진행해도 수업의 목표를 100% 달성하게 됩니다.
* **iOS(아이폰):** 맥북(Mac) 환경과 가상머신/개발자 계정이 필요하므로, 수업에서는 **Android APK 추출**을 메인으로 진행하시는 것이 가장 깔끔합니다.

```bash
# ✏️ 연습문제 & 개념 점검 [Step 5]
# Q1. Flutter 프로젝트에서 배포용 Android APK 파일을 빌드할 때 사용하는 터미널 명령어 구문은 무엇인가요?
# 답: flutter build ( __________ ) --release

# Q2. 실기기 스마트폰에서 PC에 띄워둔 백엔드 서버와 통신하고자 할 때 api_client.dart에 작성해야 하는 IP 형태는 무엇인가요?
# 답: PC의 실제 ( __________ )

 