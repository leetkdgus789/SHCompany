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
		System.out.println("����̹� ���� ����");
		con = DriverManager.getConnection(url, "scott", "tiger");
		System.out.println("DB ���� ����");
		} catch(ClassNotFoundException e) {
			System.out.println("����̹��� ã�� �� �����ϴ�.");
		} catch (SQLException e) {
			System.out.println("DB ���� ����");
		}
		return con;
	}
}
