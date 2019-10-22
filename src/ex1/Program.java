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

		// new를 막아놓은 이유 : 계단식으로 변수들을 만들어야할 필요가 있기 떄문에.

		// 드라이버 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// DB와 연결
		Connection con = DriverManager.getConnection(url, "ACORN", "newlec");
		// 명령어 실행 버퍼 생성.
		Statement st = con.createStatement();
		// 문장 실행한 결과를 한줄을 ResultSet에 저장.
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String title = rs.getString("title");
//			String id = rs.getString("id");
			int hit2 = rs.getInt("hit");
			System.out.printf("%s\t%s\n", title, hit2);
		}
	}
}
