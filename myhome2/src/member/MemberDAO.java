package member;

import java.sql.*;

// DAO(Data Access Object)
//     데이터 베이스 접속과 관련된 메서드를 정의
public class MemberDAO {
	private final String table = "member_t";
	private Connection conn = null;
	private Statement stat = null;
	private PreparedStatement pstat = null;
	
	public MemberDAO() {
		this.connect();
	}
	
	public MemberVO login(String userid, String password) {
		MemberVO m = new MemberVO();
		
		String sql = "";
		sql += "SELECT * FROM " + this.table;
		sql += " WHERE userid = ?";
		sql += "   AND password = ?";
		
		try {
			this.pstat = this.conn.prepareStatement(sql);
			this.pstat.setString(1, userid);
			this.pstat.setString(2, password);
			ResultSet res = this.pstat.executeQuery();
			if(res.next()) {
				m.setRecord(res);
			}
			res.close();
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return m;
	}
	
	public MemberVO getRecord(String userid) {
		// SQL 질의문 작성
		String sql = "SELECT * FROM member_t WHERE userid = '" + userid + "'";
		MemberVO m = null;
		try {
			// SQL 질의문 실행
			ResultSet res = this.stat.executeQuery(sql);
			if(res.next()) {
				m = new MemberVO(
					res.getString("userid"), res.getString("password"),
					res.getString("email"), res.getDate("joindate")
				);
			}
			
			res.close();
		} catch (SQLException e) {
			System.out.println("MemberDAO.java -> getRecord() : " + e.getMessage());
		}
		
		return m;
	}
	
	private void connect() {
		try {
			// JDBC 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("JDBC 드라이버 로딩 완료!");
			
			// 접속 정보
			String url = "jdbc:oracle:thin:@localhost:50000:xe";
			String user = "web_admin";
			String password = "admin";
			
			// DB 접속 객체 생성, 접속 시도
			this.conn = DriverManager.getConnection(url, user, password);
			System.out.println("Oracle DB 접속 완료!");
			
			// SQL 구문 작업용 객체 생성
			this.stat = this.conn.createStatement();
			
		} catch (ClassNotFoundException e) {
			System.out.println("MemberDAO.java -> connect() : " + e.getMessage());
		} catch (SQLRecoverableException e) {
			System.out.println("MemberDAO.java -> connect() : " + e.getMessage()); // 접속 IP/Port 오류
		} catch (SQLException e) {
			System.out.println("MemberDAO.java -> connect() : " + e.getMessage());
		}
	}
	
	public void close() {
		// 모든 JDBC 생성 객체 정보 close()
		try {
			this.stat.close();
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
