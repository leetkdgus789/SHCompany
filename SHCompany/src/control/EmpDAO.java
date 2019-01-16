package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.EmployeeVO;
import model.SalaryVO;

public class EmpDAO {

	// 사원 등록
	public EmployeeVO getEmpOk(EmployeeVO evo) throws Exception {
		String sql = "insert into employee " + "(joindate, code, name, rrnum, addr, addr2, email) " + "values "
				+ "(?, ?, ?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement ps = null;
		EmployeeVO result = null;

		try {
			con = DBUtil.getConnection();

			ps = con.prepareStatement(sql);
			ps.setString(1, evo.getJoindate().toString());
			ps.setInt(2, evo.getCode());
			ps.setString(3, evo.getName());
			ps.setString(4, evo.getRrnum());
			ps.setString(5, evo.getAddr());
			ps.setString(6, evo.getAddr2());
			ps.setString(7, evo.getEmail());

			int i = ps.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("확인");
				alert.setHeaderText("사원 등록");
				alert.setContentText(evo.getName() + "사원 등록 완료");
				alert.showAndWait();

				result = new EmployeeVO();

			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("확인");
				alert.setHeaderText("사원 삭제");
				alert.setContentText(evo.getName() + "사원 삭제 완료");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("에러");
			alert.setHeaderText("사원 등록 실패");
			alert.setContentText("사원 정보를 다시 확인하시오.");
			alert.showAndWait();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}

		return result;

	}

	// 선택한 학생의 점수 수정 및 등록일 수정
	public EmployeeVO getEmpEdit(EmployeeVO svo, int code) throws Exception {
		// 2. 데이터 처리를 위한 SQL 문

		String joinDate = svo.getJoindate().toString();

		String sql = "update employee set " + " joindate=?, name=?, rrnum=?, addr=?, addr2=?, email=? where code = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		EmployeeVO result = null;

		try {
			// 3. DBUtil 이라는 클래스의 getConnection() 메소드로 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 4. 사원 정보를 수정하기 위하여 SQL문장을 생성
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, joinDate);
			pstmt.setString(2, svo.getName());
			pstmt.setString(3, svo.getRrnum());
			pstmt.setString(4, svo.getAddr());
			pstmt.setString(5, svo.getAddr2());
			pstmt.setString(6, svo.getEmail());
			pstmt.setInt(7, code);

			// 5. SQL문을 수행 후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("확인");
				alert.setHeaderText("사원 정보 수정");
				alert.setContentText("수정 완료");
				alert.showAndWait();

				result = new EmployeeVO();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("에러");
				alert.setHeaderText("사원 정보 수정");
				alert.setContentText("수정 실패");
				alert.showAndWait();
			}

		} catch (SQLException e) {
			System.out.println("e[" + e + "]");
		} catch (Exception e) {
			System.out.println("e[" + e + "]");
		} finally {
			try {
				// 6. 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return result;
	}

	public EmployeeVO getEmpEdit2(EmployeeVO svo, int code) throws Exception {
		// 2. 데이터 처리를 위한 SQL 문

		String joinDate = svo.getJoindate().toString();
		String resignDate = svo.getResigndate().toString();

		String sql = "update employee set "
				+ " joindate=?, name=?, rrnum=?, addr=?, addr2=?, email=?, resigndate=? where code=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		EmployeeVO result = null;

		try {
			// 3. DBUtil 이라는 클래스의 getConnection() 메소드로 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 4. 사원 정보를 수정하기 위하여 SQL문장을 생성
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, joinDate);
			pstmt.setString(2, svo.getName());
			pstmt.setString(3, svo.getRrnum());
			pstmt.setString(4, svo.getAddr());
			pstmt.setString(5, svo.getAddr2());
			pstmt.setString(6, svo.getEmail());
			pstmt.setString(7, resignDate);
			pstmt.setInt(8, code);

			// 5. SQL문을 수행 후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("확인");
				alert.setHeaderText("사원 정보 수정");
				alert.setContentText("수정 완료");
				alert.showAndWait();

				result = new EmployeeVO();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("에러");
				alert.setHeaderText("사원 정보 수정");
				alert.setContentText("수정 실패");
				alert.showAndWait();
			}

		} catch (SQLException e) {
			System.out.println("e[" + e + "]");
		} catch (Exception e) {
			System.out.println("e[" + e + "]");
		} finally {
			try {
				// 6. 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return result;
	}

	// DB 삭제
	public EmployeeVO getEmpDelete(EmployeeVO evo) throws Exception {
		// 2. 데이터 처리를 위한 SQL 문
		String sql = "delete from employee where code=? ";
		Connection con = null;
		PreparedStatement ps = null;
		EmployeeVO re = null;
		try {
			// 3. DBUtil 이라는 클래스의 getConnection() 메소드로 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 4. SQL문을 수행 후 처리 결과를 얻어옴
			ps = con.prepareStatement(sql);
			ps.setInt(1, evo.getCode());

			// 5. SQL문을 수행 후 처리 결과를 얻어옴
			int i = ps.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("확인");
				alert.setHeaderText("사원 삭제");
				alert.setContentText("사원 삭제 완료");
				alert.showAndWait();

				re = new EmployeeVO();

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("에러");
				alert.setHeaderText("사원 삭제");
				alert.setContentText("사원 삭제 실패");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("e[" + e + "]");
		} catch (Exception e) {
			System.out.println("e[" + e + "]");
		} finally {
			try {
				// 6. 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return re;
	}

	// 퇴사취소처리 SQL문
	public EmployeeVO getResigndateCancel(EmployeeVO svo, int code) throws Exception {
		// 2. 데이터 처리를 위한 SQL 문

		String joinDate = svo.getJoindate().toString();
		String resignDate = svo.getResigndate().toString();

		String sql = "update employee set " + " resigndate=null where code=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		EmployeeVO result = null;

		try {
			// 3. DBUtil 이라는 클래스의 getConnection() 메소드로 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 4. 수정한 학생 정보를 수정하기 위하여 SQL문장을 생성
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);

			// 5. SQL문을 수행 후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("확인");
				alert.setHeaderText("퇴사 취소 처리");
				alert.setContentText("퇴사 취소 완료");
				alert.showAndWait();

				result = new EmployeeVO();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("에러");
				alert.setHeaderText("퇴사 취소 처리");
				alert.setContentText("퇴사 취소 실패");
				alert.showAndWait();
			}

		} catch (SQLException e) {
			System.out.println("e[" + e + "]");
		} catch (Exception e) {
			System.out.println("e[" + e + "]");
		} finally {
			try {
				// 6. 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return result;
	}

	public ArrayList<EmployeeVO> getEmpTotal() {
		ArrayList<EmployeeVO> list = new ArrayList<EmployeeVO>();
		String sql = "select * from employee order by code";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		EmployeeVO eVo = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getDate(8) != null) {
					eVo = new EmployeeVO(rs.getDate(1).toLocalDate(), rs.getInt(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8).toLocalDate());
					list.add(eVo);
				} else {
					eVo = new EmployeeVO(rs.getDate(1).toLocalDate(), rs.getInt(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7));
					list.add(eVo);
				}
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

	public ArrayList<String> getColumnName() {
		// columnName(학생 테이블 칼럼명) 객체 배열 생성
		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "select * from employee";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// ResultSetMetaData 객체 변수 선언
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
			} catch (SQLException se) {
			}
		}
		return columnName;
	}

	public ArrayList<String> getSalColumn() {
		// columnName(학생 테이블 칼럼명) 객체 배열 생성
		ArrayList<String> columnName = new ArrayList<String>();

		String sql = "select * from employee";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// ResultSetMetaData 객체 변수 선언
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
			} catch (SQLException se) {
			}
		}
		return columnName;
	}

	public ArrayList<EmployeeVO> getSalRow(String txtYear, String cbMonth) {
		ArrayList<EmployeeVO> list = new ArrayList<EmployeeVO>();
		String sql = // "select joindate, code, name, SUBSTR(rrnum,0,6) || '-' || substr(rrnum,7,14)
						// as RRNUM, addr, addr2, email, resigndate "
				"select * " + "from employee " + "where " + "(substr(joindate, 1, 5) <= ? and resigndate is null) "
						+ "or " + "(substr(joindate, 1, 5) <= ? and substr(resigndate, 1, 5) >= ?) " + "order by code";
		String date = txtYear + "/" + cbMonth;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		EmployeeVO eVo = null;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, date);
			ps.setString(2, date);
			ps.setString(3, date);
			// ps.setString(2, "08");

			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getDate(8) != null) {
					eVo = new EmployeeVO(rs.getDate(1).toLocalDate(), rs.getInt(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8).toLocalDate());
					list.add(eVo);
				} else {
					eVo = new EmployeeVO(rs.getDate(1).toLocalDate(), rs.getInt(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7));
					list.add(eVo);
				}
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
