package com.the703.v1;

public class UserServiceImpl implements UserService {
	private final UserRepository userRepo;
	
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    
	@Override
	public boolean isExists(User user) {
		User existUser = userRepo.selectByUserId(user.getUserId());
		if(existUser != null && existUser.getPassword().equals(user.getPassword())) {
			return true;
		} else {
			System.out.println("=== 아이디 및 비밀번호를 다시 확인해주세요.");
			return false;
		}
	}

	@Override
	public boolean isEmpty(User user) {
		String userId = user.getUserId();
		User existUser = userRepo.selectByUserId(userId);
		if(user != null && existUser.getAccount() > user.getAccount()) {
			return true;
		} else {
			System.out.println("=== 계좌에 돈이 부족합니다.");
			return false;
		}
	}

	@Override
	public User showUserInfo(String userId) {
		User existUser = userRepo.selectByUserId(userId);
		
		System.out.println("=== 계좌 조회");
		System.out.println("ID: " + existUser.getUserId());
		System.out.println("PASS: " + existUser.getPassword());
		System.out.println("나이: " + existUser.getAge());
		System.out.println("잔액: " + existUser.getAccount());
		
		return existUser;
	}
	
	@Override
	public int regUser(User user) {
		User existUser = userRepo.insertUser(user);

		return existUser != null ? 1 : 0;
	}

	@Override
	public int delUser(User user) {
		User delUser = userRepo.deleteByUserId(user.getUserId());
		System.out.println("=== 계좌가 삭제되었습니다.");
		
		return delUser != null ? 1 : 0;
	}
	
	@Override
	public int depositAccountByUserId(User user) {
		User existUser = userRepo.updateAccountByUserId(user);
		System.out.println("=== 입금 완료");
		System.out.println("잔액: "+ existUser.getAccount());
		
		return existUser != null ? 1 : 0;
	}
	
	@Override
	public int withdrawalAccountByUserId(User user) {
		user.setAccount(user.getAccount() * (-1));
		
		User existUser = userRepo.updateAccountByUserId(user);
		System.out.println("=== 출금 완료");
		System.out.println("잔액: "+ existUser.getAccount());
		
		return existUser != null ? 1 : 0;
	}

}
