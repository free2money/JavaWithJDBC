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

		System.out.println("<게시글 목록>");
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
		System.out.print("1. 조회\t2. 검색\t3. 등록\t4. 상위메뉴로 > ");
		Scanner sc = new Scanner(System.in);
		switch (sc.nextInt()) {
		case 1:
			System.out.print("코드 > ");
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
		System.out.println("자유게시글 내용");
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			int id = rs.getInt("id");
			String title = rs.getString("title");
			int hit = rs.getInt("hit");
			String wid = rs.getString("writer_id");
			Date regdate = rs.getDate("regdate");
			String str = rs.getString("content");

			System.out.println("번호 : " + id);
			System.out.println("제목 : " + title);
			System.out.println("작성자 : " + wid);
			System.out.println("작성일 : " + regdate);
			System.out.println("조회수 : " + hit);
			System.out.print("내용 : " + str);
			System.out.println("-------------------------------------------------------");
		}
		System.out.print("1. 댓글등록\t2. 목록\t 3. 수정\t4. 삭제 > ");
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
