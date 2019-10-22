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

			System.out.println("<�Խñ� ���>");
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
			System.out.print("1. ��ȸ\t2. �˻�\t3. ���\t4. �����޴��� > ");
			Scanner sc = new Scanner(System.in);
			switch (sc.nextInt()) {
			case 1:
				System.out.print("�ڵ� > ");
				int num = sc.nextInt();
				printDetail(num); // �ϳ��� ���븸 ���
				break;
			case 2:
				System.out.print("�˻��� ������ �Է��ϼ���. > ");
				String key = sc.next();
				printList(key); // �˻��� ����� ���
				break;
			case 3:
				enroll();
				break;
			case 4:
				Main.mainMenu();
				break;
			default:
				System.out.println("�߸��� �Է��Դϴ�.");
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

			System.out.println("<�Խñ� ���>");
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
			System.out.print("1. ��ȸ\t2. �˻�\t3. ���\t4. �����޴��� > ");
			Scanner sc = new Scanner(System.in);
			switch (sc.nextInt()) {
			case 1:
				System.out.print("�ڵ� > ");
				int num = sc.nextInt();
				printDetail(num); // �ϳ��� ���븸 ���
				break;
			case 2:
				System.out.print("�˻��� ������ �Է��ϼ���. > ");
				String keyTitle = sc.next();
				printList(keyTitle); // �˻��� ����� ���
				break;
			case 3:
				enroll();
				break;
			case 4:
				Main.mainMenu();
				break;
			default:
				System.out.println("�߸��� �Է��Դϴ�.");
				printList();
				break;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void enroll() {
		Scanner sc = new Scanner(System.in);
		System.out.println("*�� �ʼ� �Է»����Դϴ�.");
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
				System.out.println("��� �ȉ���!");
			}
			if (result == 1) {
				System.out.println("--------------------------------------------------------------------");
				System.out.println("��ϵǾ����ϴ�.");
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

			System.out.println("�����Խñ� ����");
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
					System.out.println("��ȣ : " + id);
					System.out.println("���� : " + title);
					System.out.println("�ۼ��� : " + writer_id);
					System.out.println("�ۼ��� : " + regdate);
					System.out.println("��ȸ�� : " + hit);
					System.out.print("���� : ");
					System.out.println(content);
					System.out.println("---------------------------------------------------------------");
				} else {
					System.out.println("�߸��� ������ �Է��Ͽ����ϴ�.");
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
		System.out.print("1. ��۵��\t2. ���\t3. ����\t4. ���� > ");
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
			System.out.println("�߸��� �Է��Դϴ�.");
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
		System.out.println("�����Ǿ����ϴ�.");
		printList();

	}
}
