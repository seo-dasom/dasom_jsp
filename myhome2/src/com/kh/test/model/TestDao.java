package com.kh.test.model;

import java.sql.*;
import java.util.ArrayList;

public class TestDao {
	private final String table = "test";
	private Connection conn = null;
	private Statement stat = null;
	private PreparedStatement pstat = null;
	
	public TestDao() {
		this.connect();
	}
	
	public Test getRecord(int seq) {
		Test record = new Test();
		
		String sql = "";
		sql += "SELECT * FROM " + this.table;
		sql += " WHERE id=?";
		
		try {
			this.setPreStatement(sql);
			this.pstat.setInt(1, seq);
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
	
	public ArrayList<Test> selectList() {
		ArrayList<Test> select = new ArrayList<Test>();
		
		String sql = "";
		sql += "SELECT * FROM " + this.table;
		sql += " ORDER BY seq DESC";
		
		try {
			this.setPreStatement(sql);
			ResultSet result = this.pstat.executeQuery();
			while(result.next()) {
				Test record = new Test();
				record.setRecord(result);
				select.add(record);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return select;
	}
	
	private void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@192.168.10.3:1521:xe";
			String user = "kh";
			String password = "kh";
			
			this.conn = DriverManager.getConnection(url, user, password);

			this.stat = this.conn.createStatement();
			
		} catch (ClassNotFoundException e) {
			System.out.println("TestDao.java -> connect() : " + e.getMessage());
		} catch (SQLRecoverableException e) {
			System.out.println("TestDao.java -> connect() : " + e.getMessage()); // 접속 IP/Port 오류
		} catch (SQLException e) {
			System.out.println("TestDao.java -> connect() : " + e.getMessage());
		}
	}
	
	private void setPreStatement(String sql) throws SQLException {
		this.pstat = this.conn.prepareStatement(sql);
	}
	
	public void close() {
		try {
			this.stat.close();
			this.conn.close();
			this.pstat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
