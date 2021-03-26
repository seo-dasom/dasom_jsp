package visit;

import java.sql.*;
import java.util.ArrayList;

public class VisitDAO {
	private Connection conn = null;
	private Statement stat = null;
	private PreparedStatement pstat = null;
	
	public VisitDAO() {
		this.connect();
	}
	
	private void connect() {
		try {
			// JDBC 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("JDBC 드라이버 로딩 완료!");
			
			// 접속 정보 작성
			String url = "jdbc:oracle:thin:@localhost:50000:xe";
			String user = "web_admin";
			String password = "admin";
			
			// DB 접속 객체 생성 및 접속 시도
			this.conn = DriverManager.getConnection(url, user, password);
			System.out.println("Oracle DB 접속 완료!");
			
			// SQL 구문 작업용 객체 생성
			this.stat = this.conn.createStatement();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public VisitVO getRecord(int id) {
		String sql = "SELECT * FROM visit_t WHERE id = ?";
		VisitVO record = null;
		try {
			pstat = this.conn.prepareStatement(sql);
			pstat.setInt(1, id);
			
			ResultSet res = this.pstat.executeQuery(sql);
			if(res.next()) {
				record = new VisitVO(
					res.getInt("id"), res.getString("author"),
					res.getString("context"), res.getDate("create_date")
				);
			}
			res.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return record;
	}
	
	public ArrayList<VisitVO> getRecords(Date date) {
		String sql = "";
		sql += "SELECT * FROM visit_t";
		sql += " WHERE TO_CHAR(create_date, 'YYYY-MM-DD') = '" + date + "'";
		sql += " ORDER BY id DESC";
		
		ArrayList<VisitVO> records = new ArrayList<VisitVO>();
		try {
			ResultSet res = this.stat.executeQuery(sql);
			while(res.next()) {
				records.add(new VisitVO(
						res.getInt("id"), res.getString("author"),
						res.getString("context"), res.getDate("create_date")
				));
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}
	
	public ArrayList<VisitVO> getAll() {
		// SQL 질의문 작성
		String sql = "";
		sql += "SELECT * FROM visit_t";
		sql += " ORDER BY id DESC";
		ArrayList<VisitVO> records = new ArrayList<VisitVO>();
		try {
			// SQL 질의문 실행
			ResultSet res = this.stat.executeQuery(sql);
			while(res.next()) {
				records.add(new VisitVO(
						res.getInt("id"), res.getString("author"),
						res.getString("context"), res.getDate("create_date")
				));
			}
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}
	
	public int saveData(VisitVO data) {
		/**
		 * visit_t 테이블에 데이터를 추가하는 메서드
		 *     - VisitVO 에는 추가할 데이터의 정보가 담겨 있다.
		 *     - VisitVO 의 id 는 Database 에서 자동증가로 저장하기 때문에
		 *       별도의 설정은 필요 없음
		 *     - VisitVO 의 create_date는 Database 의 SYSDATE 로
		 *       저장하기 때문에 별도의 설정은 필요 없음
		 */
		int result = 0;  // 저장 처리 유무를 판별
		String sql = "";
		sql += "INSERT INTO visit_t (id, author, context, create_date)";
		// sql += "     VALUES(visit_seq.NEXTVAL, '" + data.getAuthor() + "', '" + data.getContext() + "', SYSDATE)";
		sql += "     VALUES(visit_seq.NEXTVAL, ?, ?, SYSDATE)";
		
		try {
			pstat = this.conn.prepareStatement(sql);
			pstat.setString(1, data.getAuthor());
			pstat.setString(2, data.getContext());
			
			result = this.stat.executeUpdate(sql);  // 저장 처리가 완료 되면 1 반환
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deleteData(int id) {
		/**
		 * visit_t 테이블에 데이터를 삭제하는 메서드
		 *     - 삭제할 데이터의 id 만을 가지고 삭제 처리 한다.
		 *     - visit_t 테이블에서 id 컬럼은 primary key 로 사용하기 때문에
		 *       오직 하나의 데이터만 삭제 할 수 있다.
		 */
		int result = 0;     // 삭제 처리 유무를 판별
		String sql = "";
		sql += "DELETE FROM visit_t";
		sql += " WHERE id = " + id;
		
		try {
			result = this.stat.executeUpdate(sql);    // 삭제 처리가 완료 되면 1 반환
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void close() {
		//모든 JDBC 관련 생성 객체 정보 close()
		try {
			this.stat.close();
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
