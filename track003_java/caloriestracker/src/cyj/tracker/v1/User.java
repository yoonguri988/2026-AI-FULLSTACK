package cyj.tracker.v1;

public class User {
	private String email;
	private String password;
	private String name;
	private double height;
	private double weight;
	// 기초대사량(BMR)에 곱하여 하루 총 에너지 소비량(TDEE)을 계산하는 수치
	private int activityLevel; // 활동량 계수
	private double targetCalories; // 계산된 하루 권장 칼로리
	
	
	public User() { super(); }
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
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(int activityLevel) {
		this.activityLevel = activityLevel;
	}
	public double getTargetCalories() {
		return targetCalories;
	}
	public void setTargetCalories(double targetCalories) {
		this.targetCalories = targetCalories;
	}
}
