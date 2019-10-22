package ex1;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class CreateClass {
	public static void main(String[] args)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		// Class : RTTI Ŭ����
		// Class ��ü�� ��� ���

		ArrayList list = new ArrayList();
		// 1. getClass() ȣ��
		Class cls = list.getClass();
		// 2. Ÿ�Ը�.Class ȣ��
		Class cls2 = ArrayList.class;

		// �ش� Ŭ������ ������ �ִ� �޼ҵ带 ��� �迭(�ڵ� �����迭)
		Method[] methods = cls.getMethods();

		for (Method method : methods) {
			// �迭�� �̸��� ���
			System.out.println(method.getName());
		}

		// ���ڿ��� Ŭ���� �����ϱ�.
		Class cls3 = Class.forName("ex1.Exam");
		Exam exam = (Exam) cls3.newInstance();

		exam.getKor();
		exam.print();
	}
}
