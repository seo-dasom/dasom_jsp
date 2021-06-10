package com.web.som.dao.account;

import java.sql.Connection;
import java.sql.DriverManager;

// DB 연결을 위해 만든 임시 클래스
public class DBConnect {
	private Connection con = null;
	public DBConnect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			this.con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:50000:xe",
					"webfinal", "webfinal");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		return con;
	}
}
