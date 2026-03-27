package com.the703.v1;

public class UserService implements UserServiceImpl {
	private final UserRepository userRepo;
	
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    
	@Override
	public boolean isExists(String userId, String password) {
		User user = userRepo.selectOneById(userId);
		if(user != null && user.getPassword().equals(password)) {
			return true;
		} else {
			System.out.println("=== 아이디 및 비밀번호를 다시 확인해주세요.");
			return false;
		}
	}

	@Override
	public boolean isEmpty(String userId, long account) {
		User user = userRepo.selectOneById(userId);
		if(user != null && user.getAccount() > account) {
			return true;
		} else {
			System.out.println("=== 계좌에 돈이 부족합니다.");
			return false;
		}
	}

	@Override
	public void showUserInfo(String userId) {
		User user = userRepo.selectOneById(userId);
		
		System.out.println("=== 계좌 조회");
		System.out.println("ID: " + user.getUserId());
		System.out.println("PASS: " + user.getPassword());
		System.out.println("나이: " + user.getAge());
		System.out.println("잔액: " + user.getAccount());
	}
	
	@Override
	public User addUser(String userId, User user) {
		return userRepo.insertUser(userId, user);
	}

	@Override
	public User removeUserById(String userId, String password) {
		System.out.println("=== 계좌가 삭제되었습니다.");
		return userRepo.deleteUserById(userId, password);
	}
	
	@Override
	public User depositAccountById(String userId, long account) {
		User user = userRepo.updateAccountById(userId, account);
		System.out.println("=== 입금 완료");
		System.out.println("잔액: "+ user.getAccount());
		
		return user;
	}
	
	@Override
	public User withdrawalAccountById(String userId, long account) {
		User user = userRepo.updateAccountById(userId, account * (-1));
		System.out.println("=== 출금 완료");
		System.out.println("잔액: "+ user.getAccount());
		
		return user;
	}

}
