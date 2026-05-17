package cyj.tracker.user;

import cyj.tracker.basic.TrackerController;
import cyj.tracker.basic.TrackerFunction;
import cyj.tracker.basic.TrackerService;
import cyj.tracker.food.FoodDeleteFunction;
import cyj.tracker.food.FoodRegisterFunction;
import cyj.tracker.food.FoodSearchFunction;

public class UserController implements TrackerController {
	private final TrackerService service;
	private TrackerFunction trackFunc;
	private UserView view = new UserView();

	public UserController(TrackerService service) {
		this.service = service;
	}

	@Override
	public void run() {
		int num;
		String curUserEmail = service.getAuthService().getCurrentUser().getEmail();

		if (curUserEmail.equals("admin"))
			view.initAdmin();
		else
			view.init();

		num = view.getNum();

		if (num != 9) {
			if (curUserEmail.equals("admin")) useFunctionByAdmin(num);
			else useFunctionByUser(num);
		} else {
			service.getAuthService().logout();
			view.printLogout();
		}
	}

	private void useFunctionByAdmin(int num) {
		if (num == 1) {// 회원 정보 등록
			trackFunc = new UserRegisterFunction(service);
		} else if (num == 2) {// 회원 정보 조회
			trackFunc = new UserSearchFunction(service);
		} else if (num == 3) { // 회원 정보 수정
			trackFunc = new UserUpdateFunction(service);
		} else if (num == 4) { // 회원 정보 삭제
			trackFunc = new UserDeleteFunction(service);
		} else if (num == 5) { // 음식 기록 하기
			trackFunc = new FoodRegisterFunction(service);
		} else if (num == 6) { // 음식 목록 조회하기
			trackFunc = new FoodSearchFunction(service);
		} else if (num == 7) { // 음식 목록 삭제하기
			trackFunc = new FoodDeleteFunction(service);
		} else if (num == 8) {
			trackFunc = new TargetAnalysisFunction(service);
		} else {
			trackFunc = null;
		}
		
		if (trackFunc != null) {
			trackFunc.input();
			trackFunc.execute();
		} else {
			view.printShowNotExistFunction();
		}
	}
	
	private void useFunctionByUser(int num) {
		if (num == 1) {// 회원 정보 등록
			trackFunc = new UserRegisterFunction(service);
		} else if (num == 5) { // 음식 기록 하기
			trackFunc = new FoodRegisterFunction(service);
		} else if (num == 6) { // 음식 목록 조회하기
			trackFunc = new FoodSearchFunction(service);
		} else if (num == 7) { // 음식 목록 삭제하기
			trackFunc = new FoodDeleteFunction(service);
		} else if (num == 8) {
			trackFunc = new TargetAnalysisFunction(service);
		} else {
			trackFunc = null;
		}
		
		if (trackFunc != null) {
			trackFunc.input();
			trackFunc.execute();
		} else {
			view.printShowNotExistFunction();
		}
	}
}
