package member;

import java.sql.*;

// DAO(Data Access Object)
//     데이터 베이스 접속과 관련된 메서드를 정의
public class MemberDAO {
	
	public MemberDAO() {
		this.connect();
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
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("Oracle DB 접속 완료!");
			
			// SQL 질의문 작성
			String sql = "SELECT * FROM member_t";
			
			// SQL 구문 작업용 객체 생성
			Statement stat = conn.createStatement();
			
			// SQL 질의문 실행
			ResultSet res = stat.executeQuery(sql);
			while(res.next()) {
				MemberVO m = new MemberVO(
					res.getString("userid"),
					res.getString("password"),
					res.getString("email"),
					res.getDate("joindate")
				);
			}
			System.out.println("SQL 질의문 실행 완료!");
			
			// 모든 커넥션 정보 close()
			res.close();
			stat.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
