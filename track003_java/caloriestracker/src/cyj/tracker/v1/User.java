package cyj.tracker.v1;

public class User {
	String email;
	String password;
	String name;
	double height;
	double weight;
	// 기초대사량(BMR)에 곱하여 하루 총 에너지 소비량(TDEE)을 계산하는 수치
	int activityLevel; // 활동량 계수
	double targetCalories; // 계산된 하루 권장 칼로리
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public User(String email, String password, String name, double height, double weight) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.height = height;
		this.weight = weight;
	}
	
	int calculateBMR() { // 기초대사량 계산 로직
		return 0;
	} 
	int calculateTargetCalories() {// 활동량을 고려한 목표 칼로리 설정
		return 0;
	} 
	
	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", name=" + name + ", height=" + height + ", weight="
				+ weight + ", activityLevel=" + activityLevel + ", targetCalories=" + targetCalories + "]";
	}
	
	// getter, setter
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHeight() {
		return this.height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return this.weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getActivityLevel() {
		return this.activityLevel;
	}
	public void setActivityLevel(int activityLevel) {
		this.activityLevel = activityLevel;
	}
	public double getTargetCalories() {
		return this.targetCalories;
	}
	public void setTargetCalories(double targetCalories) {
		this.targetCalories = targetCalories;
	}
}
