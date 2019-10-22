package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		mainMenu();
	}

	public static void mainMenu() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		int num = 0;
		// 잘못된 입력이 있을 때 예외를 발생시키지 않기위해
		// 반복문을 사용하여 입력 범위를 제한함.
		do {
			System.out.println("-----------------");
			System.out.println("    메인 메뉴");
			System.out.println("-----------------\n");
			System.out.println("1. 회원 관리");
			System.out.println("2. 게시글 관리");
			System.out.println("3. 종료\n");
			System.out.print("> ");
			num = sc.nextInt();
			switch (num) {
			case 1:
				Member m = new Member();
				m.printList();
				break;
			case 2:
				Notice n = new Notice();
				n.printList();
				break;
			case 3:
				quit();
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				mainMenu();
				break;
			}
		} while (num != 3);
		sc.close();
	}

	private static void quit() {
		System.out.println("사용을 종료합니다.");
		System.exit(0);
	}

//	private static void printMemberList() throws ClassNotFoundException, SQLException {
//		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
//		Statement st = con.createStatement();
//		String sql = "select id, name, gender,"
//				+ "trunc(months_between(sysdate,to_date(birthday,'yyyy-mm-dd'))/12)+1 age, "
//				+ "birthday, phone, email, regdate from member101 order by regdate desc";
//		ResultSet rs = st.executeQuery(sql);
//
//		System.out.println("<회원 목록>");
//		System.out.println("--------------------------------------------------------------------");
//		Member m = new Member();
//
//		while (rs.next()) {
//			m.setId(rs.getString(1));
//			m.setName(rs.getString(2));
//			m.setGender(rs.getString(3));
//			m.setAge(rs.getInt(4));
//			m.setBirthday(rs.getString(5));
//			m.setPhone(rs.getString(6));
//			m.setEmail(rs.getString(7));
//			m.setRegdate(rs.getString(8));
//			System.out.println(m.getId() + " : " + m.getName() + " " + m.getRegdate());
//		}
//		rs.close();
//		st.close();
//		con.close();
//		System.out.println("--------------------------------------------------------------------");
//		System.out.print("1. 조회\t2. 등록\t3. 상위메뉴로 > ");
//		Scanner sc = new Scanner(System.in);
//		switch (sc.nextInt()) {
//		case 1:
//			String id = sc.next();
//			printMemberDetail(id);
//			break;
//		case 2:
//			regMember();
//			break;
//		case 3:
//			mainMenu();
//			break;
//		default:
//			System.out.println("잘못된 입력입니다.");
//			printMemberList();
//			break;
//
//		}
//
//	}
//
//	private static void regMember() throws ClassNotFoundException, SQLException {
//		Scanner sc = new Scanner(System.in);
//		Member m = new Member();
//		System.out.println("*은 필수 입력사항입니다.");
//		System.out.print("*id : ");
//		m.setId(sc.next());
//		System.out.print("*pwd : ");
//		m.setPwd(sc.next());
//		System.out.print("*name : ");
//		m.setName(sc.next());
//		System.out.print("phone : ");
//		m.setPhone(sc.next());
//		System.out.print("*birthday(yyyy-mm-dd) : ");
//		m.setBirthday(sc.next());
//		System.out.print("*gender : ");
//		m.setGender(sc.next());
//		System.out.print("*email : ");
//		m.setEmail(sc.next());
//
//		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
//		Statement st = con.createStatement();
//		String sql = "insert into member101 (id,pwd,name,phone,birthday,gender,email) values (\'" + m.getId() + "\',\'"
//				+ m.getPwd() + "\',\'" + m.getName() + "\',\'" + m.getPhone() + "\',\'" + m.getBirthday() + "\',\'"
//				+ m.getGender() + "\',\'" + m.getEmail() + "\')";
//		st.execute(sql);
//		// commit를 자동으로 해주네?
//
//		System.out.println("--------------------------------------------------------------------");
//		System.out.println("등록되었습니다.");
//		printMemberList();
//	}
//
//	private static void printMemberDetail(String id) {
//
//	}
//
//	private static void printNoticeList() throws SQLException, ClassNotFoundException {
//		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
//		Statement st = con.createStatement();
//		String sql = "select id, title, hit, writer_id, regdate, trunc(SYSDATE - cast(regdate as date)) time from notice101 order by regdate desc";
//		ResultSet rs = st.executeQuery(sql);
//
//		System.out.println("<게시글 목록>");
//		System.out.println("--------------------------------------------------------------------");
//		Notice n = new Notice();
//
//		while (rs.next()) {
//			n.setId(rs.getInt("id"));
//			int time = rs.getInt("time");
//			if (time < 2)
//				n.setTitle("*" + rs.getString("title"));
//			else
//				n.setTitle(rs.getString("title"));
//			n.setWriter_id(rs.getString("writer_id"));
//			n.setRegdate(rs.getString("regdate"));
//			n.setHit(rs.getInt("hit"));
//			System.out.println(n.getId() + ".\t" + n.getTitle() + "(" + n.getHit() + ")[" + n.getWriter_id() + "] / "
//					+ n.getRegdate());
//		}
//		rs.close();
//		st.close();
//		con.close();
//
//		System.out.println("--------------------------------------------------------------------");
//		System.out.print("1. 조회\t2. 검색\t3. 등록\t4. 상위메뉴로 > ");
//		Scanner sc = new Scanner(System.in);
//		switch (sc.nextInt()) {
//		case 1:
//			System.out.print("코드 > ");
//			int num = sc.nextInt();
//			printNoticeDetail(num); // 하나의 내용만 출력
//			break;
//		case 2:
//			System.out.print("검색할 제목을 입력하세요. > ");
//			String key = sc.next();
//			printNoticeList(key); // 검색한 목록을 출력
//			break;
//		case 3:
//			regNotice();
//			break;
//		case 4:
//			mainMenu();
//			break;
//		default:
//			System.out.println("잘못된 입력입니다.");
//			printNoticeList();
//			break;
//		}
//	}
//
//	private static void printNoticeList(String key) throws SQLException, ClassNotFoundException {
//		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
//		Statement st = con.createStatement();
//		String sql = "select id, title, hit, writer_id, regdate, trunc(SYSDATE - cast(regdate as date)) time from notice101 where title = \'"
//				+ key + "\' order by regdate desc";
//		ResultSet rs = st.executeQuery(sql);
//
//		System.out.println("<게시글 목록>");
//		System.out.println("--------------------------------------------------------------------");
//		Notice n = new Notice();
//		while (rs.next()) {
//			n.setId(rs.getInt("id"));
//			int time = rs.getInt("time");
//			if (time < 2)
//				n.setTitle("*" + rs.getString("title"));
//			else
//				n.setTitle(rs.getString("title"));
//			n.setWriter_id(rs.getString("writer_id"));
//			n.setRegdate(rs.getString("regdate"));
//			n.setHit(rs.getInt("hit"));
//			System.out.println(n.getId() + ".\t" + n.getTitle() + "(" + n.getHit() + ")[" + n.getWriter_id() + "] / "
//					+ n.getRegdate());
//		}
//		rs.close();
//		st.close();
//		con.close();
//
//		System.out.println("--------------------------------------------------------------------");
//		System.out.print("1. 조회\t2. 검색\t3. 등록\t4. 상위메뉴로 > ");
//		Scanner sc = new Scanner(System.in);
//		switch (sc.nextInt()) {
//		case 1:
//			System.out.print("코드 > ");
//			int num = sc.nextInt();
//			printNoticeDetail(num); // 하나의 내용만 출력
//			break;
//		case 2:
//			System.out.print("검색할 제목을 입력하세요. > ");
//			String key1 = sc.next();
//			printNoticeList(key1); // 검색한 목록을 출력
//			break;
//		case 3:
//			regNotice();
//			break;
//		case 4:
//			mainMenu();
//			break;
//		default:
//			System.out.println("잘못된 입력입니다.");
//			printNoticeList();
//			break;
//		}
//
//	}
//
//	private static void regNotice() throws ClassNotFoundException, SQLException {
//		Scanner sc = new Scanner(System.in);
//		Notice n = new Notice();
//		System.out.println("*은 필수 입력사항입니다.");
//		System.out.print("*id(num) : ");
//		n.setId(sc.nextInt());
//		System.out.print("*writer_id : ");
//		n.setWriter_id(sc.next());
//		System.out.print("*title : ");
//		n.setTitle(sc.next());
//		System.out.print("content : ");
//		n.setContent(sc.next());
//		System.out.print("*files : ");
//		n.setFiles(sc.next());
//
//		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
//		Statement st = con.createStatement();
//		String sql = "insert into notice101 (id,title,writer_id,content,files) values (" + n.getId() + ",\'"
//				+ n.getTitle() + "\',\'" + n.getWriter_id() + "\',\'" + n.getContent() + "\',\'" + n.getFiles() + "\')";
//		st.execute(sql);
//
//		System.out.println("--------------------------------------------------------------------");
//		System.out.println("등록되었습니다.");
//		printNoticeList();
//	}
//
//	private static void printNoticeDetail(int num) throws ClassNotFoundException, SQLException {
//		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
//		Statement st = con.createStatement();
//		String sql = "select id, title, writer_id, regdate, hit, content from notice101 where id = "
//				+ Integer.toString(num);
//		ResultSet rs = st.executeQuery(sql);
//
//		System.out.println("자유게시글 내용");
//		System.out.println("---------------------------------------------------------------");
//		Notice n = new Notice();
//		do {
//			if (rs.next()) {
//				n.setId(rs.getInt("id"));
//				n.setTitle(rs.getString(2));
//				n.setHit(rs.getInt(5));
//				n.setWriter_id(rs.getString("writer_id"));
//				n.setRegdate(rs.getString(4));
//				n.setContent(rs.getString(6));
//				System.out.println("번호 : " + n.getId());
//				System.out.println("제목 : " + n.getTitle());
//				System.out.println("작성자 : " + n.getWriter_id());
//				System.out.println("작성일 : " + n.getRegdate());
//				System.out.println("조회수 : " + n.getHit());
//				System.out.print("내용 : ");
//				System.out.println(n.getContent());
//				System.out.println("---------------------------------------------------------------");
//			} else {
//				System.out.println("잘못된 정보를 입력하였습니다.");
//				printNoticeList();
//			}
//		} while (rs.next());
//		rs.close();
//		st.close();
//		con.close();
//
//		printCommentList(num);
//
//		System.out.println("-------------------------------------------------------------------");
//		System.out.print("1. 댓글등록\t2. 목록\t3. 수정\t4. 삭제 > ");
//		Scanner sc = new Scanner(System.in);
//		switch (sc.nextInt()) {
//		case 1:
//			regComment(num);
//			break;
//		case 2:
//			printNoticeList();
//			break;
//		case 3:
//			updateNotice(num);
//			break;
//		case 4:
//			deleteNotice(num);
//			break;
//		default:
//			System.out.println("잘못된 입력입니다.");
//			printNoticeDetail(num);
//			break;
//		}
//	}
//
//	private static void printCommentList(int num) throws SQLException, ClassNotFoundException {
//		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
//		Statement st = con.createStatement();
//		System.out.println("댓글 목록");
//		String sql = "select content, writer_id, regdate from \"COMMENT101\" where notice_id = "
//				+ Integer.toString(num);
//		ResultSet rs = st.executeQuery(sql);
//		Comment c = new Comment();
//		while (rs.next()) {
//			c.setContent(rs.getString(1));
//			c.setWid(rs.getString(2));
//			c.setRegdate(rs.getString(3));
//			System.out.println("└ " + c.getContent() + "[" + c.getWid() + "] " + c.getRegdate());
//		}
//		rs.close();
//		st.close();
//		con.close();
//
//	}
//
//	private static void deleteNotice(int num) throws ClassNotFoundException, SQLException {
//		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
//		Statement st = con.createStatement();
//		String sql = "delete from notice101 where id = " + num;
//		st.execute(sql);
//
//		System.out.println("--------------------------------------------------------------------");
//		System.out.println("삭제되었습니다.");
//		printNoticeList();
//	}
//
//	private static void updateNotice(int num) throws ClassNotFoundException, SQLException {
//		System.out.println("게시글을 수정합니다.");
//	}
//
//	private static void regComment(int num) throws ClassNotFoundException, SQLException {
//		Scanner sc = new Scanner(System.in);
//		Comment c = new Comment();
//		System.out.println("*은 필수 입력사항입니다.");
//		System.out.print("*id : ");
//		c.setId(sc.nextInt());
//		System.out.print("*writer_id : ");
//		c.setWid(sc.next());
//		System.out.print("content : ");
//		c.setContent(sc.next());
//		c.setNid(num);
//
//		String url = "jdbc:oracle:thin:@112.223.37.243:1521/xepdb1";
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
//		Statement st = con.createStatement();
//		String sql = "insert into comment101 (id,writer_id,content,notice_id) values (" + c.getId() + ",\'" + c.getWid()
//				+ "\',\'" + c.getContent() + "\',\'" + c.getNid() + "\')";
//		st.execute(sql);
//
//		System.out.println("--------------------------------------------------------------------");
//		System.out.println("등록되었습니다.");
//		printNoticeDetail(num);
//	}
}
