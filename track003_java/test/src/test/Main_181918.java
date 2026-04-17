package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution_181918 {
    public int[] solution(int[] arr) {
        int[] stk = {};
        List<Integer> list = new ArrayList<>();
        int k = 0; // 초기값을 0으로 설정
        
        while(k < arr.length) {        	
        	// 만약 stk가 빈 배열이라면 arr[i]를 stk에 추가하고 k에 1을 더합니다.
        	if(list.isEmpty()) list.add(arr[k++]);
        	else { // stk에 원소가 있고
        		// stk의 마지막 원소가 arr[i]보다 작으면, arr[i]를 stk의 뒤에 추가하고 i에 1을 더합니다.
        		if(list.get(list.size()-1) < arr[k]) list.add(arr[k++]);
        		else list.remove(list.size()-1);
        	}
        }
        
        stk = list.stream().mapToInt(x->x).toArray();
        return stk;
    }
}

public class Main_181918 {
	public static void main(String[] args) {
		Solution_181918 sol = new Solution_181918();

		int[] arr = {1, 4, 2, 5, 3};
		System.out.println(Arrays.toString(sol.solution(arr)));
	}
}
/*
	변수 i를 만들어 초기값을 0으로 설정한 후 i가 arr의 길이보다 작으면 다음 작업을 반복합니다.

	만약 stk가 빈 배열이라면 arr[i]를 stk에 추가하고 i에 1을 더합니다.
	stk에 원소가 있고, stk의 마지막 원소가 arr[i]보다 작으면 arr[i]를 stk의 뒤에 추가하고 i에 1을 더합니다.
	stk에 원소가 있는데 stk의 마지막 원소가 arr[i]보다 크거나 같으면 stk의 마지막 원소를 stk에서 제거합니다.
*/
