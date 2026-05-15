package cyj.tracker.basic;

import cyj.tracker.auth.AuthController;
import cyj.tracker.user.UserController;

public class TrackerControllerMangerImpl implements TrackerControllerManger {
	private final TrackerController authController;
    private final TrackerController userController;
    private final TrackerService service;

    public TrackerControllerMangerImpl(AppStatus status, TrackerService service) {
        this.service = service;
        this.authController = new AuthController(status, service);
        this.userController = new UserController(service);
    }

    @Override
    public TrackerController getController() {
        // 서비스의 로그인 상태에 따라 다른 컨트롤러 반환
        if (!service.getAuthService().isLoggedIn()) {
            return authController;
        }
        return userController;
    }
}
