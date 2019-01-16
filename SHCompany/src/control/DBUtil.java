package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static final String driver = "oracle.jdbc.driver.OracleDriver";
	static final String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	static Connection con = null;

	public static Connection getConnection() {
		try {
		Class.forName(driver);
		System.out.println("드라이버 적재 성공");
		con = DriverManager.getConnection(url, "scott", "tiger");
		System.out.println("DB 연결 성공");
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
		}
		return con;
	}
}
