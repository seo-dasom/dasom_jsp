package nexa.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NexaJoinDAO {
	private Connection conn;
	private PreparedStatement pstat;

	public NexaJoinDAO() {
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
	
	public boolean insert(NexaJoinVO data) {
		boolean res = false;
		
		String sql = "";
		sql += "INSERT INTO JOIN_T VALUES(?, ?, ?, ?, ?)";
		
		try {
			this.pstat = this.conn.prepareStatement(sql);
			this.pstat.setString(1, data.getId());
			this.pstat.setString(2, data.getPassword());
			this.pstat.setString(3, data.getName());
			this.pstat.setString(4, data.getPhone());
			this.pstat.setString(5, data.getEmail());
			int cnt = this.pstat.executeUpdate();
			res = cnt == 1 ? true : false;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return res;
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
