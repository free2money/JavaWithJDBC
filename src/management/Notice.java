package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Notice {
	private int id;
	private String title;
	private String content;
	private String writer_id;
	private String regdate;
	private String files;
	private int hit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void printList() {
		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
			Statement st = con.createStatement();
			String sql = "select id, title, hit, writer_id, regdate, trunc(SYSDATE - cast(regdate as date)) time from notice101 order by regdate desc";
			ResultSet rs = st.executeQuery(sql);

			System.out.println("<게시글 목록>");
			System.out.println("--------------------------------------------------------------------");

			while (rs.next()) {
				id = rs.getInt(1);
				hit = rs.getInt(3);
				writer_id = rs.getString(4);
				regdate = rs.getString(5);
				int time = rs.getInt(6);
				if (time < 2)
					title = "*" + rs.getString(2);
				else
					title = rs.getString(2);
				System.out.println(id + ".\t" + title + "(" + hit + ") [" + writer_id + "] / " + regdate);
			}
			rs.close();
			st.close();
			con.close();

			System.out.println("--------------------------------------------------------------------");
			System.out.print("1. 조회\t2. 검색\t3. 등록\t4. 상위메뉴로 > ");
			Scanner sc = new Scanner(System.in);
			switch (sc.nextInt()) {
			case 1:
				System.out.print("코드 > ");
				int num = sc.nextInt();
				printDetail(num); // 하나의 내용만 출력
				break;
			case 2:
				System.out.print("검색할 제목을 입력하세요. > ");
				String key = sc.next();
				printList(key); // 검색한 목록을 출력
				break;
			case 3:
				enroll();
				break;
			case 4:
				Main.mainMenu();
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				printList();
				break;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void printList(String key) {
		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
			Statement st = con.createStatement();
			String sql = "select id, title, hit, writer_id, regdate, trunc(SYSDATE - cast(regdate as date)) time from notice101 where title like \'%"
					+ key + "%\' order by regdate desc";
			ResultSet rs = st.executeQuery(sql);

			System.out.println("<게시글 목록>");
			System.out.println("--------------------------------------------------------------------");
			while (rs.next()) {
				id = rs.getInt(1);
				hit = rs.getInt(3);
				writer_id = rs.getString(4);
				regdate = rs.getString(5);
				int time = rs.getInt(6);
				if (time < 2)
					title = "*" + rs.getString(2);
				else
					title = rs.getString(2);
				System.out.println(id + ".\t" + title + "(" + hit + ") [" + writer_id + "] / " + regdate);
			}
			rs.close();
			st.close();
			con.close();

			System.out.println("--------------------------------------------------------------------");
			System.out.print("1. 조회\t2. 검색\t3. 등록\t4. 상위메뉴로 > ");
			Scanner sc = new Scanner(System.in);
			switch (sc.nextInt()) {
			case 1:
				System.out.print("코드 > ");
				int num = sc.nextInt();
				printDetail(num); // 하나의 내용만 출력
				break;
			case 2:
				System.out.print("검색할 제목을 입력하세요. > ");
				String keyTitle = sc.next();
				printList(keyTitle); // 검색한 목록을 출력
				break;
			case 3:
				enroll();
				break;
			case 4:
				Main.mainMenu();
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				printList();
				break;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void enroll() {
		Scanner sc = new Scanner(System.in);
		System.out.println("*은 필수 입력사항입니다.");
		System.out.print("*writer_id : ");
		writer_id = sc.next();
		System.out.print("*title : ");
		title = sc.next();
		System.out.print("content : ");
		content = sc.next();
		System.out.print("*files : ");
		files = sc.next();

		String sql = "insert into notice101 (id,title,writer_id,content,files) values ( notice101_seq.nextval,?,?,?,?)";

		try {
			String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(2, title);
			st.setString(3, writer_id);
			st.setString(4, content);
			st.setString(5, files);

			int result = st.executeUpdate();
			if (result == 0) {
				System.out.println("등록 안됬어요!");
			}
			if (result == 1) {
				System.out.println("--------------------------------------------------------------------");
				System.out.println("등록되었습니다.");
			}

			st.close();
			con.close();

			printList();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void printDetail(int num) {
		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
			Statement st = con.createStatement();
			String sql = "select id, title, writer_id, regdate, hit, content, files from notice101 where id = "
					+ Integer.toString(num);
			ResultSet rs = st.executeQuery(sql);

			System.out.println("자유게시글 내용");
			System.out.println("---------------------------------------------------------------");
			do {
				if (rs.next()) {
					id = rs.getInt(1);
					title = rs.getString(2);
					writer_id = rs.getString(3);
					regdate = rs.getString(4);
					hit = rs.getInt(5);
					content = rs.getString(6);
					files = rs.getString(7);
					System.out.println("번호 : " + id);
					System.out.println("제목 : " + title);
					System.out.println("작성자 : " + writer_id);
					System.out.println("작성일 : " + regdate);
					System.out.println("조회수 : " + hit);
					System.out.print("내용 : ");
					System.out.println(content);
					System.out.println("---------------------------------------------------------------");
				} else {
					System.out.println("잘못된 정보를 입력하였습니다.");
					printList();
				}
			} while (rs.next());
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		Comment c = new Comment();

		c.printList(num);

		System.out.println("-------------------------------------------------------------------");
		System.out.print("1. 댓글등록\t2. 목록\t3. 수정\t4. 삭제 > ");
		Scanner sc = new Scanner(System.in);
		switch (sc.nextInt()) {
		case 1:
			c.enroll(num);
			break;
		case 2:
			printList();
			break;
		case 3:
			update(num);
			break;
		case 4:
			delete(num);
			break;
		default:
			System.out.println("잘못된 입력입니다.");
			printDetail(num);
			break;
		}

	}

	private void update(int num) {
		// TODO Auto-generated method stub

	}

	private void delete(int num) {
		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
			Statement st = con.createStatement();
			String sql = "delete from notice101 where id = " + num;

			st.execute(sql);

			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		System.out.println("--------------------------------------------------------------------");
		System.out.println("삭제되었습니다.");
		printList();

	}
}
