package test;

class Solution_181913 {
    public String solution(String my_string, int[][] queries) {
        String answer = "";
        char[] myArr = my_string.toCharArray();
        
        for (int i = 0; i < queries.length; i++) {
			int start = queries[i][0];
			int end = queries[i][1];
			int mid = (queries[i][1] - start)/2;
			
			for(int j = 0; j <= mid; j++) {
				char tmp = myArr[start+j];
				myArr[start+j] = myArr[end-j];
				myArr[end-j] = tmp;
			}
		}
        
        for(char ch: myArr) {
        	answer += ch;
        }
        return answer;
    }
}

public class Main_181913 {
	public static void main(String[] args) {
		Solution_181913 sol = new Solution_181913();
		
		String my_string = "rermgorpsam";
		int[][] queries = {{2, 3}, {0, 7}, {5, 9}, {6, 10}};
		
		System.out.println(sol.solution(my_string, queries));
	}
}

/*
문자열 my_string과 이차원 정수 배열 queries가 매개변수로 주어집니다. 

queries의 원소는 [s, e] 형태로, my_string의 인덱스 s부터 인덱스 e까지를 뒤집으라는 의미입니다. 

my_string에 queries의 명령을 순서대로 처리한 후의 문자열을 return 하는 solution 함수를 작성해 주세요.
*/