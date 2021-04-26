package servlet.sensor.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.database.DBConnection;

public class TempHumDAO {
	Connection conn = null;
	PreparedStatement pstat = null;
	
	public TempHumDAO() {
		this.conn = (new DBConnection()).getConnection();
	}
	
	public boolean insertData(TempHumVO data) {
		boolean res = false;
		
		String sql = "";
		sql += "INSERT INTO temp_hum_sensor VALUES(sensor_seq.NEXTVAL, ?, ?, SYSDATE)";
		
		try {
			this.pstat = this.conn.prepareStatement(sql);
			this.pstat.setDouble(1, data.getTemp());
			this.pstat.setInt(2, data.getHum());
			
			int rows = this.pstat.executeUpdate();
			
			if(rows == 1) {
				res = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public void close() {
		try {
			this.pstat.close();
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
