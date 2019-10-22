package labbergame;

import java.util.Scanner;

public class Main {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int playNum = 0;

		while (true) {
			try {
				playNum = setNumber();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			String[][] sadariField = new String[Sadari.HEIGHT][playNum * Sadari.FINAL_LINE];
			String[] user = new String[playNum];
			String[] result = new String[playNum];

			setField(sadariField, playNum);
			setInput(user, "�÷��̾�");
			setInput(result, "���");
			setBridge(sadariField, playNum);
			result(sadariField, playNum, user, result);
			break;
		}
	}//////////// main

	private static int setNumber() throws Exception {
		System.out.println("�÷����Ϸ��� �ο��� ����Դϱ�?");
		int tmp = Integer.parseInt(sc.nextLine());
		if (tmp <= 0 || tmp > 20) {
			Exception e = new Exception("�÷��� �ο��� 1~20������ �����ϼ���.");
			throw e;
		}
		System.out.println("��ٸ� ������ " + tmp + "���� ���� �Ǿ����ϴ�.");
		return tmp;
	}

	private static void setField(String[][] arr, int num) {
		for (int q = 0; q < Sadari.HEIGHT; q++) {
			for (int w = 0; w < Sadari.FINAL_LINE * num; w++) {
				if (w % 4 == Sadari.STANDARD_LINE - 1) {
					arr[q][w] = Sadari.SHAPE_ARR[0];
				} else {
					arr[q][w] = "   ";
				}
			}
		}
	}// setSadari

	private static void setInput(String[] userArr, String msg) {
		for (int q = 0; q < userArr.length; q++) {
			System.out.print((q + 1) + "��° " + msg + "�� �Է��ϼ���. ");
			userArr[q] = sc.nextLine();
		}
	}

	private static void setBridge(String[][] arr, int num) {
		int lineNumber = arr[0].length / Sadari.FINAL_LINE;
		int[] checkLine = new int[lineNumber];

		for (int q = 1; q < arr.length - 1; q++) {
			for (int w = 0; w < lineNumber - 1; w++) {
				if ((int) (Math.random() * 2) == 0)
					checkLine[w] += checkLine[w] < 3 ? setBridgeDetail(arr, q, w * Sadari.FINAL_LINE) : 0;
			}

			if (q == arr.length - 2)
				for (int w = 0; w < lineNumber - 1; w++) {
					if (checkLine[w] == 0) {
						q = 1;
						break;
					}
				}
		}
	}

	private static int setBridgeDetail(String[][] sadari, int q, int w) {

		if (sadari[q][w].equals(Sadari.SHAPE_ARR[0]) && sadari[q][w + Sadari.FINAL_LINE].equals(Sadari.SHAPE_ARR[0])) {
			sadari[q][w] = Sadari.SHAPE_ARR[3];

			for (int e = 1; e < Sadari.FINAL_LINE + 1; e++) {
				if (e < Sadari.FINAL_LINE) {
					sadari[q][w + e] = Sadari.SHAPE_ARR[1];
				} else {
					sadari[q][w + e] = Sadari.SHAPE_ARR[2];
				}
			}
			return 1;
		}
		return 0;
	}

	private static void result(String[][] arr, int sadariNum, String[] user, String[] result) {
		realTimeScreenDraw(arr, "");
		String tmp = "";

		for (int q = 0; q < arr[0].length; q += Sadari.FINAL_LINE) {
			int x = 0, y = q;

			for (int w = 0; w < arr.length; w++) {
				if (arr[x][y].equals(Sadari.SHAPE_ARR[3]))
					y += Sadari.FINAL_LINE;
				else if (arr[x][y].equals(Sadari.SHAPE_ARR[2]))
					y -= Sadari.FINAL_LINE;

				// "��"�̵� ����
				tmp = arr[x][y];
				arr[x][y] = "��";
				realTimeScreenDraw(arr, user[q / Sadari.FINAL_LINE]);
				waitTime(250);
				arr[x][y] = tmp;
				x++;
			}
			System.out.println(user[q / Sadari.FINAL_LINE] + "�� ����� " + result[y / Sadari.FINAL_LINE]);
			user[q / Sadari.FINAL_LINE] += "�� ��� -> " + result[y / Sadari.FINAL_LINE];
			waitTime(1500);
		}
//		blank();
		System.out.println("������������ ���â ������������");
		for (String temp : user) {
			System.out.printf("%s %n", temp);
		}
	}

	private static void realTimeScreenDraw(String[][] arr, String user) {
//		blank();
		System.out.println("���� ���� -> " + user);
		for (int q = 0; q < arr.length; q++) {
			for (int w = 0; w < arr[q].length; w++) {
				System.out.print(arr[q][w]);
			}
			System.out.println();
		}
	}

	private static void waitTime(int waitNum) {
		try {
			Thread.sleep(waitNum);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void blank() {
		System.out.printf("%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n");
	}
}// class
