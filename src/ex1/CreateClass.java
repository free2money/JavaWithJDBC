package ex1;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class CreateClass {
	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// Class : RTTI 클래스
		// Class 객체를 얻는 방법

		ArrayList list = new ArrayList();
		// 1. getClass() 호출
		Class cls = list.getClass();
		// 2. 타입명.Class 호출
		Class cls2 = ArrayList.class;

		// 해당 클래스가 가지고 있는 메소드를 담는 배열(자동 동적배열)
		Method[] methods = cls.getMethods();

		for (Method method : methods) {
			// 배열속 이름을 출력
			System.out.println(method.getName());
		}

		// 문자열로 클래스 생성하기.
		Class cls3 = Class.forName("ex1.Exam");
		Exam exam = (Exam) cls3.newInstance();

		exam.getKor();
		exam.print();
	}
}
