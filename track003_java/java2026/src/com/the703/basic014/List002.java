package com.the703.basic014;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//1. 클래스는 부품객체
//2. 상태(멤버볌수: 클래스변수, 인스턴스변수)와 행위 (멤버함수)
class UserDto {
	//멤버 변수
	private static int cnt = 0; //클래스 변수 (static) - method area - 각각 x - 공용
	private final int no;       //final 수정 X
	private String email;       //인스턴스 변수 - heap area - new o - 생성자 o - this 각각
	//멤버 함수                   //기본값 - 명시적 초기화 - 초기화 블록 - 생성자
	//alt + shift + s (생성자, toString, getters/setters)
	public UserDto() { this.no = ++cnt; } // 유저 번호 = 값; 값 1개 증가 넣고
	public UserDto(int no, String email) { super(); this.no = no; this.email = email; }
	public UserDto(String email) { this(); this.email = email; } /* this() : 자기 자신의 생성자 */
	
	@Override
	public String toString() { return "UserDto [no=" + no + ", email=" + email + "]"; }
	
	public static int getCnt() { return cnt; }
	public static void setCnt(int cnt) { UserDto.cnt = cnt; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public int getNo() { return no; }
	
	// 1. 클래스가 맞는지 확인
	@Override
	public int hashCode() { // 객체가 가지고 있는 컨텐츠가 맞니?
		return Objects.hash(email); // 객체들의 값
	}
	// 2. 객체 안의 값이 같은지 확인
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		return Objects.equals(email, other.email);
	}
}

public class List002 {
	public static void main(String[] args) {
		// add, get, size, remove, contains
		List<UserDto> users = new ArrayList<>();
		
		users.add(new UserDto("aaa@gmail.com"));
		users.add(new UserDto("bbb@gmail.com"));
		
		System.out.println(users);
		System.out.println("1. get > " + users.get(0));
		System.out.println("2. size > " + users.size());
		System.out.println("3. remove > " + users.remove(1));
		// 주소값이 달라서 contains -> false :: hashCode(), equals(Object obj)
		System.out.println("4. contains > " + users.contains(new UserDto("aaa@gmail.com")));
		System.out.println("4. contains > " + users.contains(new UserDto("bbb@gmail.com")));
		
		
		UserDto dto = users.get(0);
		System.out.println("INFO"+ (0+1) + ":" + dto.getNo() + "/" + dto.getEmail());
		
		int cnt = 0;
		for(UserDto user: users) {
			System.out.println("INFO" + ++cnt + ":"+user.getNo()+"/"+user.getEmail());
		}
	}
}

