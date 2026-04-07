package com.the703.v1;

public class UserServiceImpl implements UserService {
	private final UserRepository userRepo;
	
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    
	@Override
	public boolean isExists(User user) {
		User existUser = userRepo.selectByUserId(user.getUserId());
		return existUser != null && existUser.getPassword().equals(user.getPassword());
	}

	@Override
	public boolean isEmpty(User user) {
		User existUser = userRepo.selectByUserId(user.getUserId());
		return existUser != null && existUser.getAccount() < user.getAccount();
	}

	@Override
	public User showUserInfo(User user) {
		User existUser = userRepo.selectByUserId(user.getUserId());
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
		return delUser != null ? 1 : 0;
	}
	
	@Override
	public User depositAccountByUserId(User user) {
		User existUser = userRepo.updateAccountByUserId(user);
		return existUser;
	}
	
	@Override
	public User withdrawalAccountByUserId(User user) {
		User existUser = userRepo.updateAccountByUserId(user);
		return existUser;
	}

}
