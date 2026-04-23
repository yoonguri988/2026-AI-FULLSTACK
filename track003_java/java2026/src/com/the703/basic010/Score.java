package com.the703.basic010;

public class Score {
	private String name;
	private int kor, eng, math, total;
	private double aver;
	private String p = "불합격", s = "", rank = "";
	
	
	public Score() { super(); }
	public Score(String name, int kor, int eng, int math) {
		super();
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	
	public static void info() {
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n"
				         + "이름\t국어\t영어\t수학\t총점\t평균\t합격여부\t장학생\t랭킹\n"
				         + ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	}
	
	public void sum() {
		total = kor + eng + math;
	}
	public void avg() {
		aver = total / 3.0;
	}
	public void pass() {
		p = aver < 60? "불합격": (kor < 40||eng < 40||math < 40)?"불합격":"합격";
	}
	public void best() {
		s = aver < 95? "":"장학생";
	}
	public void star() {
		for(int i = 0; i < aver/10; i++) {
			rank += '★';
		}
	}
	
	public void show() {
		sum(); avg();
		pass(); best(); star();
		System.out.printf("%s\t%d\t%d\t%d\t%d\t%.2f\t%s\t%s\t%s\n",name,kor,eng,math,total,aver,p,s,rank);
	}
	
	@Override
	public String toString() {
		return "Score [name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", total=" + total
				+ ", aver=" + aver + ", p=" + p + ", s=" + s + ", rank=" + rank + "]";
	}
	
	//getter,setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getAver() {
		return aver;
	}
	public void setAver(double aver) {
		this.aver = aver;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
}
