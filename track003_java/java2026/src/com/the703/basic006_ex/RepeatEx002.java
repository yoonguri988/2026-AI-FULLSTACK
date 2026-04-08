package com.the703.basic006_ex;

public class RepeatEx002 {
	public static void main(String[] args) {
		int sum = 0;
		for(int i = 1; i <= 10; i++) {
			if(i % 3 == 0) sum += i; 
		}
		System.out.println(sum);
		
		sum = 0;
		int z = 1;
		while(z <= 10) {
			if(z % 3 == 0) sum += z;
			z++;
		}
		System.out.println(sum);
		
		sum = 0;
		int x = 1;
		do {
			if(x % 3 == 0) sum += x;
			x++;
		}while(x <= 10);
		System.out.println(sum);
	}
}
/*
for , while , do while 3가지 버젼으로 
1~10까지 3의 배수의 합 : 18

힌트)
ver-1)
1이  3의 배수라면  합을더해주변수에누적
2가  3의 배수라면  합을더해주변수에누적
3이  3의 배수라면  합을더해주변수에누적

ver-2)
if( 1이  3의 배수라면 ){ 합을더해주변수에누적 }
if( 2가  3의 배수라면 ){ 합을더해주변수에누적 }
if( 3이  3의 배수라면 ){ 합을더해주변수에누적 }
*/
