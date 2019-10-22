package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Comment {
	private int id;
	private String content;
	private String regdate;
	private String wid;
	private int nid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public void printList(int num) {
		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
			Statement st = con.createStatement();
			System.out.println("댓글 목록");
			String sql = "select content, writer_id, regdate from \"COMMENT101\" where notice_id = "
					+ Integer.toString(num);
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				content = rs.getString(1);
				wid = rs.getString(2);
				regdate = rs.getString(3);

				System.out.println("└ " + content + "[" + wid + "] " + regdate);
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void enroll(int num) {
		Scanner sc = new Scanner(System.in);
		System.out.println("*은 필수 입력사항입니다.");
		System.out.print("*writer_id : ");
		wid = sc.next();
		System.out.print("content : ");
		content = sc.next();
		nid = num;

		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
			Statement st = con.createStatement();
			String sql = "insert into comment101 (writer_id,content,notice_id) values (\'" + wid + "\',\'" + content
					+ "\',\'" + nid + "\')";
			st.execute(sql);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println("--------------------------------------------------------------------");
		System.out.println("등록되었습니다.");

		Notice n = new Notice();
		n.printDetail(num);
	}

}
