package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
		String sql = "select TITLE, HIT+1 HIT FROM NOTICE WHERE HIT > 100";
		// Driver driver = new oracle.jdbc.driver.OracleDriver();
		// DriverManager.registerDriver(driver);

		// new�� ���Ƴ��� ���� : ��ܽ����� �������� �������� �ʿ䰡 �ֱ� ������.

		// ����̹� �ε�
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// DB�� ����
		Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
		// ��ɾ� ���� ���� ����.
		Statement st = con.createStatement();
		// ���� ������ ����� ������ ResultSet�� ����.
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String title = rs.getString("title");
//			String id = rs.getString("id");
			int hit2 = rs.getInt("hit");
			System.out.printf("%s\t%s\n", title, hit2);
		}
	}
}
