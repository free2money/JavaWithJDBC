package management;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Noticemanage {

	public static void load() throws ClassNotFoundException, SQLException {
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
		Statement st = con.createStatement();

		System.out.println("<�Խñ� ���>");
		String sql = "select id, title, hit, writer_id, regdate from notice101 order by regdate desc";
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			int hit = rs.getInt("hit");
			String wid = rs.getString("writer_id");
			Date regdate = rs.getDate("regdate");
			System.out.println(id + ".\t" + title + "(" + hit + ")[" + wid + "] / " + regdate);
		}
		System.out.print("1. ��ȸ\t2. �˻�\t3. ���\t4. �����޴��� > ");
		Scanner sc = new Scanner(System.in);
		switch (sc.nextInt()) {
		case 1:
			System.out.print("�ڵ� > ");
			sql = "select id, title, writer_id, regdate, hit, content from notice where id = "
					+ Integer.toString(sc.nextInt());
			moreInformation(st, sql);
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			MainMenu.mainmenu();
			break;
		default:
			break;
		}
	}

	private static void moreInformation(Statement st, String sql) throws ClassNotFoundException, SQLException {
		System.out.println("�����Խñ� ����");
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			int hit = rs.getInt("hit");
			String wid = rs.getString("writer_id");
			Date regdate = rs.getDate("regdate");
			String str = rs.getString("content");

			System.out.println("��ȣ : " + id);
			System.out.println("���� : " + title);
			System.out.println("�ۼ��� : " + wid);
			System.out.println("�ۼ��� : " + regdate);
			System.out.println("��ȸ�� : " + hit);
			System.out.print("���� : " + str);
			System.out.println("-------------------------------------------------------");
		}
		System.out.print("1. ��۵��\t2. ���\t 3. ����\t4. ���� > ");
		Scanner sc = new Scanner(System.in);
		switch (sc.nextInt()) {
		case 1:
			break;
		case 2:
			load();
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			break;
		}
	}
}
