package ex1;

import java.util.Scanner;

public class Exam {
	private int kor;
	private int eng;
	private int math;

	public int getKor() {
		return kor;
	}

	public Exam() {
		this(0, 0, 0);
	}

	public Exam(int kor, int eng, int math) {
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("kor : ");
		String kor_ = sc.next();
		kor = Integer.parseInt(kor_);
	}

	public void print() {
		System.out.println("국어 : " + kor);
		System.out.println("영어" + " : " + eng);
	}

}
