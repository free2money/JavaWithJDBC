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
		// �߸��� �Է��� ���� �� ���ܸ� �߻���Ű�� �ʱ�����
		// �ݺ����� ����Ͽ� �Է� ������ ������.
		do {
			System.out.println("-----------------");
			System.out.println("    ���� �޴�");
			System.out.println("-----------------\n");
			System.out.println("1. ȸ�� ����");
			System.out.println("2. �Խñ� ����");
			System.out.println("3. ����\n");
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
				System.out.println("�߸��� �Է��Դϴ�.");
				mainMenu();
				break;
			}
		} while (num != 3);
		sc.close();
	}

	private static void quit() {
		System.out.println("����� �����մϴ�.");
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
//		System.out.println("<ȸ�� ���>");
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
//		System.out.print("1. ��ȸ\t2. ���\t3. �����޴��� > ");
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
//			System.out.println("�߸��� �Է��Դϴ�.");
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
//		System.out.println("*�� �ʼ� �Է»����Դϴ�.");
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
//		// commit�� �ڵ����� ���ֳ�?
//
//		System.out.println("--------------------------------------------------------------------");
//		System.out.println("��ϵǾ����ϴ�.");
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
//		System.out.println("<�Խñ� ���>");
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
//		System.out.print("1. ��ȸ\t2. �˻�\t3. ���\t4. �����޴��� > ");
//		Scanner sc = new Scanner(System.in);
//		switch (sc.nextInt()) {
//		case 1:
//			System.out.print("�ڵ� > ");
//			int num = sc.nextInt();
//			printNoticeDetail(num); // �ϳ��� ���븸 ���
//			break;
//		case 2:
//			System.out.print("�˻��� ������ �Է��ϼ���. > ");
//			String key = sc.next();
//			printNoticeList(key); // �˻��� ����� ���
//			break;
//		case 3:
//			regNotice();
//			break;
//		case 4:
//			mainMenu();
//			break;
//		default:
//			System.out.println("�߸��� �Է��Դϴ�.");
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
//		System.out.println("<�Խñ� ���>");
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
//		System.out.print("1. ��ȸ\t2. �˻�\t3. ���\t4. �����޴��� > ");
//		Scanner sc = new Scanner(System.in);
//		switch (sc.nextInt()) {
//		case 1:
//			System.out.print("�ڵ� > ");
//			int num = sc.nextInt();
//			printNoticeDetail(num); // �ϳ��� ���븸 ���
//			break;
//		case 2:
//			System.out.print("�˻��� ������ �Է��ϼ���. > ");
//			String key1 = sc.next();
//			printNoticeList(key1); // �˻��� ����� ���
//			break;
//		case 3:
//			regNotice();
//			break;
//		case 4:
//			mainMenu();
//			break;
//		default:
//			System.out.println("�߸��� �Է��Դϴ�.");
//			printNoticeList();
//			break;
//		}
//
//	}
//
//	private static void regNotice() throws ClassNotFoundException, SQLException {
//		Scanner sc = new Scanner(System.in);
//		Notice n = new Notice();
//		System.out.println("*�� �ʼ� �Է»����Դϴ�.");
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
//		System.out.println("��ϵǾ����ϴ�.");
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
//		System.out.println("�����Խñ� ����");
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
//				System.out.println("��ȣ : " + n.getId());
//				System.out.println("���� : " + n.getTitle());
//				System.out.println("�ۼ��� : " + n.getWriter_id());
//				System.out.println("�ۼ��� : " + n.getRegdate());
//				System.out.println("��ȸ�� : " + n.getHit());
//				System.out.print("���� : ");
//				System.out.println(n.getContent());
//				System.out.println("---------------------------------------------------------------");
//			} else {
//				System.out.println("�߸��� ������ �Է��Ͽ����ϴ�.");
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
//		System.out.print("1. ��۵��\t2. ���\t3. ����\t4. ���� > ");
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
//			System.out.println("�߸��� �Է��Դϴ�.");
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
//		System.out.println("��� ���");
//		String sql = "select content, writer_id, regdate from \"COMMENT101\" where notice_id = "
//				+ Integer.toString(num);
//		ResultSet rs = st.executeQuery(sql);
//		Comment c = new Comment();
//		while (rs.next()) {
//			c.setContent(rs.getString(1));
//			c.setWid(rs.getString(2));
//			c.setRegdate(rs.getString(3));
//			System.out.println("�� " + c.getContent() + "[" + c.getWid() + "] " + c.getRegdate());
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
//		System.out.println("�����Ǿ����ϴ�.");
//		printNoticeList();
//	}
//
//	private static void updateNotice(int num) throws ClassNotFoundException, SQLException {
//		System.out.println("�Խñ��� �����մϴ�.");
//	}
//
//	private static void regComment(int num) throws ClassNotFoundException, SQLException {
//		Scanner sc = new Scanner(System.in);
//		Comment c = new Comment();
//		System.out.println("*�� �ʼ� �Է»����Դϴ�.");
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
//		System.out.println("��ϵǾ����ϴ�.");
//		printNoticeDetail(num);
//	}
}
