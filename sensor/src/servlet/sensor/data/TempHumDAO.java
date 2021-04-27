package servlet.sensor.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	public int totalRow() {
		int res = 0;
		
		String sql = "";
		sql += "SELECT count(*) FROM temp_hum_sensor";
		
		try {
			this.pstat = this.conn.prepareStatement(sql);
			ResultSet rs = this.pstat.executeQuery();
			
			rs.next();
			res = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public ArrayList<TempHumVO> getPaging(int page, int count) {
		ArrayList<TempHumVO> res = new ArrayList<TempHumVO>();
		
		String sql = "";
		sql += "SELECT * ";
		sql += "  FROM (SELECT ROWNUM as rnum, id, temp, hum, sdate FROM temp_hum_sensor";
		sql += "        ORDER BY sdate DESC";
		sql += "  WHERE rnum BETWEEN ? AND ?";
		
		try {
			int total = totalRow();
			this.pstat = this.conn.prepareStatement(sql);
			//                    590      10   *   2   - 1   -   10  + 1 = 571
			this.pstat.setInt(1, total - (count * (page - 1)) - count + 1);
			this.pstat.setInt(2, total - (count * (page - 1)));
			//                    590      10   *   2   - 1   = 580
			ResultSet rs = this.pstat.executeQuery();
			
			while(rs.next()) {
				TempHumVO vo = new TempHumVO();
				vo.setId(rs.getInt("id"));
				vo.setTemp(rs.getDouble("temp"));
				vo.setHum(rs.getInt("hum"));
				vo.setSdate(rs.getTimestamp("sdate"));
				res.add(vo);
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
