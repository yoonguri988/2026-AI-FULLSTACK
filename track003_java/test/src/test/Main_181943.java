package test;

class Solution_181943 {
    public String solution(String my_string, String overwrite_string, int s) {
        String answer = "";
        char[] myArr = my_string.toCharArray();
        for (int i = s; i < s+overwrite_string.length(); i++) {
			myArr[i] = overwrite_string.charAt(i-s);
		}
        
        for(char ch: myArr) {
        	answer += ch;
        }
        
        return answer;
    }
}

public class Main_181943 {
	public static void main(String[] args) {
		Solution_181943 sol = new Solution_181943();

		String my_string = "He11oWor1d";
		String overwrite_string = "lloWorl";
		int s = 2;

		System.out.println(sol.solution(my_string, overwrite_string, s));
	}
}

/*
문자열 my_string, overwrite_string과 정수 s가 주어집니다. 

문자열 my_string의 인덱스 s부터 overwrite_string의 길이만큼을 
문자열 overwrite_string으로 바꾼 문자열을 return 하는 solution 함수를 작성해 주세요.
 */
