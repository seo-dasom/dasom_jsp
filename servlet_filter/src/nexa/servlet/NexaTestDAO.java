package nexa.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NexaTestDAO {
	private Connection conn;
	private PreparedStatement pstat;

	public NexaTestDAO() {
		String url = "jdbc:oracle:thin:@localhost:50000:xe";
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			this.conn = DriverManager.getConnection(url, "sensor_admin", "sensor_admin");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<NexaTestVO> selectAll() {
		ArrayList<NexaTestVO> res = new ArrayList<NexaTestVO>();
		
		String sql = "";
		sql += "SELECT * FROM TEST_T";
		
		try {
			this.pstat = this.conn.prepareStatement(sql);
			ResultSet record = this.pstat.executeQuery();
			while(record.next()) {
				NexaTestVO row_data = new NexaTestVO();
				row_data.setId(record.getInt("id"));
				row_data.setName(record.getString("name"));
				row_data.setAge(record.getInt("age"));
				res.add(row_data);
			}
			record.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public boolean insert(NexaTestVO data) {
		boolean res = false;
		
		String sql = "";
		sql += "INSERT INTO TEST_T VALUES(TEST_SEQ.NEXTVAL, ?, ?)";
		
		try {
			this.pstat = this.conn.prepareStatement(sql);
			this.pstat.setString(1, data.getName());
			this.pstat.setInt(2, data.getAge());
			int cnt = this.pstat.executeUpdate();
			res = cnt == 1 ? true : false;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public boolean update(NexaTestVO data) {
		boolean res = false;
		
		String sql = "";
		sql += "UPDATE TEST_T SET name=?, age=? WHERE id=?";
		
		try {
			this.pstat = this.conn.prepareStatement(sql);
			this.pstat.setString(1, data.getName());
			this.pstat.setInt(2, data.getAge());
			this.pstat.setInt(3, data.getId());
			int cnt = this.pstat.executeUpdate();
			res = cnt == 1 ? true : false;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public boolean delete(NexaTestVO data) {
		boolean res = false;
		
		String sql = "";
		sql += "DELETE FROM TEST_T WHERE id=?";
		
		try {
			this.pstat = this.conn.prepareStatement(sql);
			this.pstat.setInt(1, data.getId());
			int cnt = this.pstat.executeUpdate();
			res = cnt == 1 ? true : false;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public boolean isConnected() {
		return this.conn == null ? false : true;
	}
	
	public void close() {
		try {
			this.conn.close();
			this.pstat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
