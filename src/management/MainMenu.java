package management;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {
	public static void mainmenu() throws ClassNotFoundException, SQLException {
		System.out.println("���� �޴�");
		System.out.println("1. ȸ�� ����");
		System.out.println("2. �Խñ� ����");
		System.out.println("3. ����");
		System.out.print("> ");
		Scanner sc = new Scanner(System.in);
		switch (sc.nextInt()) {
		case 1:
			break;
		case 2:
			Noticemanage.load();
			break;
		case 3:
			System.exit(0);
			break;
		default:
			break;
		}
	}
}
