import 'dart:typed_data';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:image_picker/image_picker.dart';
import '../data/board_provider.dart';
import '../../auth/data/auth_provider.dart';

class PostWritePage extends ConsumerStatefulWidget {
  const PostWritePage({super.key});

  @override
  ConsumerState<PostWritePage> createState() => _PostWritePageState();
}

class _PostWritePageState extends ConsumerState<PostWritePage> {
  final _contentController = TextEditingController();
  final _hashtagController = TextEditingController();
  
  final ImagePicker _picker = ImagePicker();
  List<XFile> _selectedImages = [];
  
  final Map<String, Uint8List> _imageBytesCache = {};

  @override
  void dispose() {
    _contentController.dispose();
    _hashtagController.dispose();
    super.dispose();
  }

  Future<void> _pickImages() async {
    final List<XFile> images = await _picker.pickMultiImage();
    if (images.isNotEmpty) {
      for (var image in images) {
        final bytes = await image.readAsBytes();
        _imageBytesCache[image.path] = bytes;
      }
      setState(() {
        _selectedImages = images;
      });
    }
  }

  void _handleSubmit() async {
    final content = _contentController.text.trim();
    final hashtags = _hashtagController.text.trim();
    
    // 유저 ID 안전 추출
    final user = ref.read(authProvider).user;
    final rawId = user?['id'] ?? user?['userId'] ?? user?['memberId'] ?? '1';
    final userId = rawId.toString();

    if (content.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(content: Text('내용을 입력해주세요.')),
      );
      return;
    }

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
      Navigator.pop(context);
    }
  }
/////////////////////////////////////////////////////////
  @override
  Widget build(BuildContext context) {
    return Scaffold( // 앱의 기본골격
      appBar: AppBar(title: const Text('새 글 작성')), // 상단앱바
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: ListView(
          children: [
            TextField(
              controller: _contentController,
              decoration: const InputDecoration(labelText: '내용 입력'),
              maxLines: 5,
            ),
            const SizedBox(height: 12),
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
                height: 100,
                child: ListView.builder(
                  scrollDirection: Axis.horizontal,
                  itemCount: _selectedImages.length,
                  itemBuilder: (context, index) {
                    final image = _selectedImages[index];
                    final bytes = _imageBytesCache[image.path];

                    return Padding(
                      padding: const EdgeInsets.only(right: 8.0),
                      child: Stack(
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