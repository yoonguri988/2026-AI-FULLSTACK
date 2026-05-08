package com.the703.days;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Player {
	private String name;
	private int score;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public int hashCode() {
		return Objects.hash(name, score);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(name, other.name) && score == other.score;
	}
	@Override
	public String toString() {
		return "Player [name=" + name + ", score=" + score + "]";
	}
	public Player(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}
	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}
}

interface PlayerSort{
	void sort(List<Player> list);
}

public class Day029 {
	public static void main(String[] args) {
		/*
		 * List  : 기차  (순서O, 중복O) add, get, size, remove, contains;
		 * Set   : 주머니 (순서X, 중복X) add, 향상된 for문/iterator, size, remove, contains;
		 * Map   : 사전  (key-value쌍 엔트리) put, get(key), size, remove, containsKey, containValue;
		 */
		List<Player> players = new ArrayList<>();
		
		players.add(new Player("Mario", 1200));
		players.add(new Player("Luigi", 1500));
		players.add(new Player("Peach", 1800));
		players.add(new Player("Bowser", 900));
		players.add(new Player("Bowser", 900));
		
		// List코드에서 익명 클래스로 점수 오름차순 정렬
		PlayerSort ps1 = new PlayerSort() {
			@Override
			public void sort(List<Player> list) {
				list.sort((o1, o2)-> o1.getScore() - o2.getScore());
			}
		};
		ps1.sort(players);
		
		//람다식으로 점수 오름차순 정렬
		// o1.getScore() - o2.getScore() : 오름차순
		// o2.getScore() - o1.getScore() : 내림차순
		PlayerSort ps2 = (list) -> list.sort((o1, o2)-> o1.getScore() - o2.getScore());
		ps2.sort(players);
		
		//메서드 참조로 점수 오름차순 정렬
		players.sort(Comparator.comparingInt(Player::getScore));
		
		// 내림차순 상위 스코어 3명?
		List<Player> rankPlayers = players.subList(0, players.size());
		rankPlayers.sort((o1, o2)-> o2.getScore() - o1.getScore());
		rankPlayers = rankPlayers.subList(0, 3);
		
		for (int i = 0; i < rankPlayers.size(); i++) {
			System.out.printf("%d   %s    %d\n",i+1, rankPlayers.get(i).getName(), rankPlayers.get(i).getScore());
		}
		System.out.println();		System.out.println();
		
		// stream version
		List<Player> sp2 = players.stream().sorted((o1, o2) -> o2.getScore()-o1.getScore()).limit(3).collect(Collectors.toList());
		for (int i = 0; i < sp2.size(); i++) {
			System.out.printf("%d   %s    %d\n",i+1, sp2.get(i).getName(), sp2.get(i).getScore());
		}
		System.out.println();		System.out.println();

		
		System.out.println(">>>");
		for (int i = 0; i < players.size(); i++) {
			System.out.printf("%d   %s    %d\n",i+1, players.get(i).getName(), players.get(i).getScore());
		}
		//Q3. List에서 출력을 보면 Bowser   900  라는 같은데이터를 넣었는데 2개가 나옴. 이유는?
		// list는 중복을 허용한다.
		System.out.println();		System.out.println();
		
		Set<Player> setPlayers = new HashSet<>();
		
		setPlayers.add(new Player("Mario", 1200));
		setPlayers.add(new Player("Luigi", 1500));
		setPlayers.add(new Player("Peach", 1800));
		setPlayers.add(new Player("Bowser", 900));
		setPlayers.add(new Player("Bowser", 900));
		
		Iterator<Player> iter = setPlayers.iterator();
		int k = 0;
		while(iter.hasNext()) {
			Player p = iter.next();
			System.out.printf("%d   %s    %d\n",++k, p.getName(), p.getScore());
		}
		
		Map<String, Player> mapPlayers = new HashMap<String, Player>();
		
		mapPlayers.put("mario", new Player("Mario", 1200));
		mapPlayers.put("luigi", new Player("Luigi", 1500));
		mapPlayers.put("peach", new Player("Peach", 1800));
		mapPlayers.put("bowser", new Player("Bowser", 900));
		
		for (Entry<String, Player> entry : mapPlayers.entrySet()) {
			String key = entry.getKey();
			Player val = entry.getValue();
			System.out.printf("%s  %s   %d\n",key, val.getName(), val.getScore());
		}
    }
}

/*
Q1. Player DTO 클래스 만들기
속성:
private String name;
private int score;

Q2. List (ArrayList) 출력
2-1. players 이름으로 ArrayList 만들기
2-2. 데이터 추가:
new Player("Mario", 1200),
new Player("Luigi", 1500),
new Player("Peach", 1800),
new Player("Bowser", 900)
new Player("Bowser", 900)

2-3. for + size 이용해서 출력

출력 예시
1   Mario    1200
2   Luigi    1500
3   Peach    1800
4   Bowser   900
5   Bowser   900

Q3. List에서 출력을 보면 Bowser   900  라는 같은데이터를 넣었는데 2개가 나옴. 이유는?
4   Bowser   900
5   Bowser   900


Q4. Set (HashSet) 출력
4-1. setPlayers 이름으로 HashSet 만들기
4-2. 동일한 데이터 넣기 (중복 허용 안됨)
4-3. Iterator 이용해서 출력
출력 예시
1   Mario    1200
2   Luigi    1500
3   Peach    1800
4   Bowser   900

Q5. Map (HashMap) 출력
5-1. mapPlayers 이름으로 HashMap 만들기
5-2.  데이터 넣기 (Key-Value 구조)
mapPlayers.put("mario", new Player("Mario", 1200));
mapPlayers.put("luigi", new Player("Luigi", 1500));
mapPlayers.put("peach", new Player("Peach", 1800));
mapPlayers.put("bowser", new Player("Bowser", 900));
5-3. for-each + entrySet 이용해서 출력
출력 예시
mario   Mario    1200
luigi   Luigi    1500
peach   Peach    1800
bowser  Bowser   900

Q6. 정렬 문제
6-1. List코드에서 익명 클래스로 점수 오름차순 정렬
6-2. 람다식으로 점수 오름차순 정렬
6-3. 메서드 참조로 점수 오름차순 정렬

출력 예시 (오름차순)
코드
Bowser   900
Bowser   900
Mario    1200
Luigi    1500
Peach    1800
*/