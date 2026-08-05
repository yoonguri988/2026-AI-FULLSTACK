package com.thejoa703.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.thejoa703.dto.LoginRequest;
import com.thejoa703.dto.UserDto.UserRequestDto;
import com.thejoa703.dto.UserDto.UserResponseDto;
import com.thejoa703.entity.AppUser;
import com.thejoa703.exception.ResourceNotFoundException;
import com.thejoa703.repository.AppUserRepository;
import com.thejoa703.util.FileStorageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 데이터 저장(insert)시 rollback / readOnly=true 읽기 전용, 낭비
public class UserService {
	private final AppUserRepository userRepo; //@Autowired 대신
	// 파일 올리기, 비밀번호 암호화
	private final FileStorageService fileStorageService;
	// 보안: 비밀번호 암호화
	
	// Create:회원가입
	//회원가입 (사용자 등록)
	@Transactional
	public UserResponseDto createUser(UserRequestDto request, MultipartFile profileImage) {
		String provider = request.getProvider() != null ? request.getProvider() : "local";
		
		if(userRepo.findByEmailAndProvider(request.getEmail(), provider).isPresent()) {
			throw new ResourceNotFoundException("이미 존재하는 사용자입니다.");
		}
		
		if(userRepo.existsByNickname(request.getNickname())) {
			throw new ResourceNotFoundException("이미 존재하는 닉네임입니다.");
		}
		// ※ 이메일 중복 검사 / 닉네임 중복 검사
        AppUser user = new AppUser();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setNickname(request.getNickname());
        user.setProvider(provider);
        user.setRole("ROLE_USER");
        user.setUfile(profileImage != null && !profileImage.isEmpty()
        			? fileStorageService.upload(profileImage)
        			: "uploads/thejoa703.png");
			
		return new UserResponseDto(userRepo.save(user));
	}
	
	public boolean existsByEmail(String email) {
		return userRepo.existsByEmail(email);
	}
	
	public boolean existsByNickname(String nickname) {
		return userRepo.existsByNickname(nickname);
	}
	
	public UserResponseDto login(LoginRequest request) {
		AppUser user = userRepo.findByEmailAndProvider(request.getEmail(),
				                    request.getProvider() != null ? request.getProvider(): "local")
				               .orElseThrow(()-> new ResourceNotFoundException("사용자 찾을수 없습니다."));
		return UserResponseDto.fromEntity(user);
	}
	
	// 사용자 단건 조회
	public UserResponseDto findById(Long id) { // Optional - 값 1개 가 아니면 null
		AppUser user = userRepo.findById(id)
				                  .orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 사용자 입니다. id="+id));
		return new UserResponseDto(user);
	}
	
	// 전체 사용자수
	public long countUsers() {  return  userRepo.count(); }
	
	// Update: 닉넴 변경
	@Transactional
    public UserResponseDto updateNickname(Long userId, String newNickname) {
		// 닉네임 중복검사
	    if (userRepo.existsByNickname(newNickname)) {
	        throw new ResourceNotFoundException("이미 사용중인 닉네임입니다.");
	    }
	    // 사용자조회 후 
	    AppUser user = userRepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("사용자 찾을수 없습니다.")); 
	    user.setNickname(newNickname); 
	    return UserResponseDto.fromEntity(userRepo.save(user));
	}  
    
	// Update : 프로필 이미지변경
	@Transactional
    public UserResponseDto updateProfileImage(Long userId, MultipartFile profileImage) {
    	// 사용자 조회
        AppUser user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("사용자 찾을수 없습니다."));
        // 새이미지 업로드 또는 기본 이미지 설정
        user.setUfile(profileImage != null && !profileImage.isEmpty()
                ? fileStorageService.upload(profileImage)              
                : "uploads/thejoa703.png");                              
        return UserResponseDto.fromEntity(userRepo.save(user));
    }
    
	// Delete: 회원 탈퇴
	@Transactional
	public void deleteById(Long userId) { 
		if(!userRepo.existsById(userId)) {
			throw new ResourceNotFoundException("삭제할 사용자가 존재하지 않습니다. id=");
		}
		 userRepo.deleteById(userId);  
	}

}
