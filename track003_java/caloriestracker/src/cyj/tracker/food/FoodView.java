package cyj.tracker.food;

import java.util.List;

import cyj.tracker.basic.InputHandler;
import cyj.tracker.user.User;

public class FoodView {
	private InputHandler handler = new InputHandler();

	public String getName() {
		return handler.getString("👉 음식의 이름을 입력하세요 > ");
	}
	public double getCalories() {
		return handler.getDouble("👉 칼로리를 입력하세요 > ");
	}
	public double getCarbs() {
		return handler.getDouble("👉 탄수화물 함량을 입력하세요 > ");
	}
	public double getProtein() {
		return handler.getDouble("👉 단백질 함량을 입력하세요 > ");
	}
	public double getFat() {
		return handler.getDouble("👉 지방 함량을 입력하세요 > ");
	}
	public void printFoodList(List<Food> foodList, User currentUser) {
		System.out.println("🍚"+currentUser.getName()+"의 음식 기록 내용🍚");
		int num = 1;
		for (Food food : foodList) {
			System.out.println(num++ +". "+food.getSummmary());
		}
	}
	public String getIsCheck() {
		return handler.getString("정말로 음식 기록을 삭제하시겠습니까? (y/n) ");
	}
	public void printDelSuccess() {
		System.out.println("음식기록이 삭제되었습니다.");
	}
	public void printDelFail() {
		System.out.println("삭제가 취소되었습니다.");
	}
	public void printWrongFood() {
		System.out.println("입력하신 음식은 존재하지 않습니다.");
	}
}
