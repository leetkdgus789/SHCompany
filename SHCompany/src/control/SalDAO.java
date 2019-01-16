package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.EmployeeVO;
import model.SalaryVO;

public class SalDAO {

	public ArrayList<SalaryVO> getSalList() {

		ArrayList<SalaryVO> list = new ArrayList<SalaryVO>();

		String sql = "select * from employee order by code";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SalaryVO sVo = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
			}
		}
		return list;
	}

	// DB ��� �޼ҵ�
	public SalaryVO getSalOk(SalaryVO svo) throws Exception {
		String resignDate = svo.getResigndate().toString();
		
		String sql = "insert into salary "
				+ "(no, year, month, code, name, basepay, bonus, overpay, sumpay, pension, health, empment, withtax, localtax, sumtax, actualpay, rrnum, joindate, resigndate) "
				+ "values " + "(salary_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement ps = null;
		SalaryVO result = null;

		try {
			con = DBUtil.getConnection();

			ps = con.prepareStatement(sql);
			ps.setInt(1, svo.getYear());
			ps.setInt(2, svo.getMonth());
			ps.setInt(3, svo.getCode());
			ps.setString(4, svo.getName());
			ps.setString(5, svo.getBasepay());
			ps.setString(6, svo.getBonus());
			ps.setString(7, svo.getOverpay());
			ps.setString(8, svo.getSumpay());
			ps.setString(9, svo.getPension());
			ps.setString(10, svo.getHealth());
			ps.setString(11, svo.getEmpment());
			ps.setString(12, svo.getWithtax());
			ps.setString(13, svo.getLocaltax());
			ps.setString(14, svo.getSumtax());
			ps.setString(15, svo.getActualpay());
			ps.setString(16, svo.getRrnum());
			ps.setString(17, svo.getJoindate());
			ps.setString(18, resignDate);

			int i = ps.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Ȯ��");
				alert.setHeaderText("�޿� ���");
				alert.setContentText(svo.getName() + " ��� " + svo.getMonth()+ "��" +"�޿� ��� ����");
				alert.showAndWait();

				result = new SalaryVO();

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("����");
				alert.setHeaderText("�޿� ���");
				alert.setContentText("�޿� ��� ����");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("����");
			alert.setHeaderText("�޿� ���");
			alert.setContentText("�޿� ��� ����");
			alert.showAndWait();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				// �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}

		return result;
	}

	// DB ��� �޼ҵ�
		public SalaryVO getSalOk2(SalaryVO svo) throws Exception {
			
			String sql = "insert into salary "
					+ "(no, year, month, code, name, basepay, bonus, overpay, sumpay, pension, health, empment, withtax, localtax, sumtax, actualpay, rrnum, joindate) "
					+ "values " + "(salary2_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			Connection con = null;
			PreparedStatement ps = null;
			SalaryVO result = null;

			try {
				con = DBUtil.getConnection();

				ps = con.prepareStatement(sql);
				ps.setInt(1, svo.getYear());
				ps.setInt(2, svo.getMonth());
				ps.setInt(3, svo.getCode());
				ps.setString(4, svo.getName());
				ps.setString(5, svo.getBasepay());
				ps.setString(6, svo.getBonus());
				ps.setString(7, svo.getOverpay());
				ps.setString(8, svo.getSumpay());
				ps.setString(9, svo.getPension());
				ps.setString(10, svo.getHealth());
				ps.setString(11, svo.getEmpment());
				ps.setString(12, svo.getWithtax());
				ps.setString(13, svo.getLocaltax());
				ps.setString(14, svo.getSumtax());
				ps.setString(15, svo.getActualpay());
				ps.setString(16, svo.getRrnum());
				ps.setString(17, svo.getJoindate());

				int i = ps.executeUpdate();

				if (i == 1) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Ȯ��");
					alert.setHeaderText("�޿� ���");
					alert.setContentText(svo.getName() + " ��� " + svo.getMonth()+ "��" +"�޿� ��� ����");
					alert.showAndWait();

					result = new SalaryVO();

				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("����");
					alert.setHeaderText("�޿� ���");
					alert.setContentText("�޿� ��� ����");
					alert.showAndWait();
				}
			} catch (SQLException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("����");
				alert.setHeaderText("�޿� ���");
				alert.setContentText("�޿� ��� ����");
				alert.showAndWait();
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					// �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
					if (ps != null)
						ps.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
				}
			}

			return result;
		}
	
	// DB ���� �޼ҵ�
	public SalaryVO getSalEdit(SalaryVO svo) throws Exception {
		String sql = "update salary set " + "year=?, month=?, code=?, name=?, "
				+ "basepay=?, bonus=?, overpay=?, sumpay=?, " + "pension=?, health=?, empment=?, "
				+ "withtax=?, localtax=?, sumtax=?, actualpay=? " + "where year=? and month=? and code=?";
		Connection con = null;
		PreparedStatement ps = null;
		SalaryVO re = null;

		try {
			con = DBUtil.getConnection();

			ps = con.prepareStatement(sql);
			ps.setInt(1, svo.getYear());
			ps.setInt(2, svo.getMonth());
			ps.setInt(3, svo.getCode());
			ps.setString(4, svo.getName());
			ps.setString(5, svo.getBasepay());
			ps.setString(6, svo.getBonus());
			ps.setString(7, svo.getOverpay());
			ps.setString(8, svo.getSumpay());
			ps.setString(9, svo.getPension());
			ps.setString(10, svo.getHealth());
			ps.setString(11, svo.getEmpment());
			ps.setString(12, svo.getWithtax());
			ps.setString(13, svo.getLocaltax());
			ps.setString(14, svo.getSumtax());
			ps.setString(15, svo.getActualpay());
			ps.setInt(16, svo.getYear());
			ps.setInt(17, svo.getMonth());
			ps.setInt(18, svo.getCode());

			int i = ps.executeUpdate();
			System.out.println(i);

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Ȯ��");
				alert.setHeaderText("�޿� ����");
				alert.setContentText(svo.getName() + " ��� " + svo.getMonth()+ "��" +"�޿� ���� ����");
				alert.showAndWait();

				re = new SalaryVO();

			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("����");
				alert.setHeaderText("�޿� ����");
				alert.setContentText("�޿� ���� ����");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("����");
			alert.setHeaderText("�޿� ����");
			alert.setContentText("�޿� ���� ����");
			alert.showAndWait();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				// �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}

		return re;
	}
	
	// DB ����
	public SalaryVO getSalDelete(SalaryVO svo) throws Exception {
		// 2. ������ ó���� ���� SQL ��
		String sql = "delete from salary where year=? and month=? and code=? ";
		Connection con = null;
		PreparedStatement ps = null;
		SalaryVO re = null;
		try {
			// 3. DBUtil �̶�� Ŭ������ getConnection() �޼ҵ�� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// 4. SQL���� ���� �� ó�� ����� ����
			ps = con.prepareStatement(sql);
			ps.setInt(1, svo.getYear());
			ps.setInt(2, svo.getMonth());
			ps.setInt(3, svo.getCode());

			// 5. SQL���� ���� �� ó�� ����� ����
			int i = ps.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Ȯ��");
				alert.setHeaderText("�޿� ����");
				alert.setContentText(svo.getName() + " ��� " + svo.getMonth()+ "��" +"�޿� ���� ����");
				alert.showAndWait();

				re = new SalaryVO();
				
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("����");
				alert.setHeaderText("�޿� ����");
				alert.setContentText("�޿� ���� ����");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("e[" + e + "]");
		} catch (Exception e) {
			System.out.println("e[" + e + "]");
		} finally {
			try {
				// 6. �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return re;
	}

	// �޿� ����Ʈ �ҷ����� (column)
	public ArrayList<String> getColumnName() {
		// columnName(�л� ���̺� Į����) ��ü �迭 ����
		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "select * from salary";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// ResultSetMetaData ��ü ���� ����
		ResultSetMetaData rsmd = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for (int i = 1; i <= cols; i++) {
				columnName.add(rsmd.getColumnName(i));
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return columnName;
	}

	// �޿� ����Ʈ �ҷ����� (row)
	public ArrayList<SalaryVO> getSalRow(int txtYear, int cbMonth) {
		ArrayList<SalaryVO> list = new ArrayList<SalaryVO>();
		String sql = "select no, year, month, code, name, " 
				+ " to_char(basepay, '99,999,999'), to_char(bonus, '99,999,999'), " 
				+ " to_char(overpay, '99,999,999'), to_char(sumpay, '99,999,999'), " 
				+ " to_char(pension, '99,999,999'), to_char(health, '99,999,999'), " 
				+ " to_char(empment, '99,999,999'), to_char(withtax, '99,999,999'), " 
				+ " to_char(localtax, '99,999,999'), to_char(sumtax, '99,999,999'), " 
				+ " to_char(actualpay, '99,999,999'), rrnum, joindate, resigndate "
				+ " from Salary "
				+ " where year=? and month=? " 
				+ " order by code";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SalaryVO sVo = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, txtYear);
			ps.setInt(2, cbMonth);
			rs = ps.executeQuery();
			while (rs.next()) {
				sVo = new SalaryVO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
						rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19));
				list.add(sVo);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		return list;
	}
	
	// ��� column
	public ArrayList<String> getTotalColumn() {
		// columnName(�л� ���̺� Į����) ��ü �迭 ����
		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "select * from salary";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// ResultSetMetaData ��ü ���� ����
		ResultSetMetaData rsmd = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			for (int i = 1; i <= cols; i++) {
				columnName.add(rsmd.getColumnName(i));
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return columnName;
	}

	// ��� ������ (row)
	public ArrayList<SalaryVO> getTotalRow() {
		ArrayList<SalaryVO> list = new ArrayList<SalaryVO>();
		String sql = "select year, month, count(code), to_char(sum(basepay), '99,999,999'),"
				+ " to_char(sum(bonus), '99,999,999'), to_char(sum(overpay), '99,999,999'),"
				+ " to_char(sum(sumpay), '99,999,999'), to_char(sum(pension), '99,999,999'),"
				+ " to_char(sum(health), '99,999,999'), to_char(sum(empment), '99,999,999'),"
				+ " to_char(sum(withtax), '99,999,999'), to_char(sum(localtax), '99,999,999'),"
				+ " to_char(sum(sumtax), '99,999,999'), to_char(sum(actualpay), '99,999,999') " 
				+ " from Salary "
				+ " group by year, month "
				+ " order by year, month";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SalaryVO sVo = null;
		
		
		
		NumberFormat a = NumberFormat.getInstance();
		a.setGroupingUsed(false);
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			
			while (rs.next()) {
				
				sVo = new SalaryVO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5),rs.getString(6),
						rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13),rs.getString(14));
				list.add(sVo);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		return list;
	}
	
}
