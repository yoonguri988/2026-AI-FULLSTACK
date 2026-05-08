package com.the703.basic015;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambda004 {
	public static void main(String[] args) {
		// (t) -> {} accept 받는 용도
		// void java.util.function.Consumer.accept( T t )
		Consumer<String> consumer = (t) -> {System.out.println("Hello:) "+ t); };
		consumer.accept("moi");
		consumer.accept("므메미무");
		//ver-1 람다식 정리
		Consumer<String> c1 = (t) -> System.out.println("Hello:) "+ t);
		c1.accept("람다식:: 므메미무");
		//ver-2 참조형 가능
		Consumer<String> c2 = System.out::println;
		c2.accept("Hello:) 참조형:: 므메미무");
		
		// () -> { return T; } get 주는 용도
		//T java.util.function.Supplier.get()
		Supplier<String> supplier = () -> { return "Hello"; };
		System.out.println(supplier.get());
		
		//ver-1 람다식 정리
		Supplier<String> s1 = () -> "Hello ;)";
		System.out.println("람다식::" + s1.get());

		//(t) -> { return true/false; } test 판단용도(가능?여부)
		//boolean java.util.function.Predicate.test( T t )
		Predicate<Integer> predicate = (t) -> { return t < 0; };
		System.out.println(predicate.test(10)); // false
		System.out.println(predicate.test(-10));// true
		
		//ver-1 람다식 정리
		Predicate<Integer> p1 = (t) -> t < 0;
		System.out.println(p1.test(10)); // false
		System.out.println(p1.test(-10));// true
		
		// (t) -> { return r } apply 처리용도
		// R java.util.function.Function.apply( T t )
		//Function<T, R>
		Function<String, Integer> function = (t) -> {return Integer.parseInt(t); };
		System.out.println(function.apply("10")+3);
		
		//ver-1 람다식 정리
		Function<String, Integer> fc1 = (t) -> Integer.parseInt(t);
		System.out.println(fc1.apply("10")+3);
		
		//ver-2 참조형 가능
		Function<String, Integer> fc2 = Integer::parseInt;
		System.out.println(fc2.apply("10")+3);
		
		//(l, r) -> { return 0; } applyAsInt 연산용도
		//int java.util.function.IntBinaryOperator.applyAsInt( int left, int right )
		IntBinaryOperator  operator = (l, r) -> { return l > r? l : r; };
		System.out.println(operator.applyAsInt(10, 3));
		System.out.println(operator.applyAsInt(3, 10));

		//ver-1 람다식 정리
		IntBinaryOperator  op1 = (l, r) -> l > r? l : r;
		System.out.println(op1.applyAsInt(10, 3));
		System.out.println(op1.applyAsInt(3, 10));
	}
}
/*
자바 API 함수형 인터페이스
1.   Consumer  받는용도
2.   Supplier  제공용도   
3.   Predicate 판단용도
4.   Function  처리용도
5.   Operator  연산용도
*/