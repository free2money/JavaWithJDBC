package management;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.newlec.web.service.NoticeService;
import com.newlec.web.service.jdbc.JdbcNoticeService;

public class Pogram {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		inputMainMenu();
	}

	public static void inputMainMenu() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);

		System.out.println("���θ޴�");
		System.out.println("--------------------------");
		System.out.println("1.ȸ������");
		System.out.println("2.�Խñ۰���");
		System.out.println("3.����");
		System.out.print("> ");
		int num = sc.nextInt();

		while (true) {
			switch (num) {
			case 1:
				printListMember();
				break;
			case 2:
				printListNotice();
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("�ٽ� �Է����ּ���");
			}
		}
	}

	private static void printListMember() {
		// TODO Auto-generated method stub

	}

	private static void printListNotice() throws SQLException, ClassNotFoundException {

		NoticeService service = new JdbcNoticeService();
		Scanner sc = new Scanner(System.in);

		List<com.newlec.web.entity.Notice> list = service.getNoticeList();

		for (com.newlec.web.entity.Notice n : list) {
			System.out.println(n.toString());
		}

		while (true) {
			System.out.println("-----------------------------");
			System.out.println("1.��ȸ 2.�����޴��� 3.���");
			System.out.print(">");

			int num = sc.nextInt();
			switch (num) {
			case 1:
				printNoticeDetail();
				break;
			case 2:
				inputMainMenu();
				break;
			case 3:
				enrollNotice();
				break;
			default:
				System.out.println("�ٽ� �Է����ּ���");
			}
		}
	}

	private static void printNoticeDetail() {
		// TODO Auto-generated method stub

	}

	private static void enrollNotice() {
		// TODO Auto-generated method stub

	}
}