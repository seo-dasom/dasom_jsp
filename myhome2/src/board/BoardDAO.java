package board;

import java.sql.*;
import java.util.ArrayList;
import main.DBConnection;

public class BoardDAO {
	private final String table = "board_t";
	private Connection connection = null;
	private PreparedStatement pstat = null;
	
	public BoardDAO() {
//		DBConnection db_conn = new DBConnection("50000", "web_admin", "admin");
//		this.connection = db_conn.getConnect();
		this.connection = new DBConnection("50000", "web_admin", "admin").getConnect();
	}
	
	public BoardVO getRecord(int id) {
		BoardVO record = new BoardVO();
		
		String sql = "";
		sql += "SELECT * FROM " + this.table;
		sql += " WHERE id=?";
		
		try {
			this.setPreStatement(sql);
			this.pstat.setInt(1, id);
			ResultSet result = this.pstat.executeQuery();
			if(result.next()) {
				record.setRecord(result);
			} else {
				System.out.println("조회 결과가 없습니다.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return record;
	}
	
	public ArrayList<BoardVO> getAll() {
		ArrayList<BoardVO> records = new ArrayList<BoardVO>();
		
		String sql = "";
		sql += "SELECT * FROM " + this.table;
		sql += " ORDER BY id DESC";
		
		try {
			this.setPreStatement(sql);
			ResultSet result = this.pstat.executeQuery();
			while(result.next()) {
				BoardVO record = new BoardVO();
				record.setRecord(result);
				records.add(record);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return records;
	}
	
	private void setPreStatement(String sql) throws SQLException {
		this.pstat = this.connection.prepareStatement(sql);
	}
}
