package com.web.som.account.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AccountDAO {
	Connection conn = null;
	PreparedStatement pstat = null;
	
	public AccountDAO() {
		this.connect();
	}
	
	private void connect() {
		try {
			InitialContext init_context = new InitialContext();
			DataSource data_source = (DataSource)init_context.lookup("java:comp/env/webfinal");
			this.conn = data_source.getConnection();
		} catch(NamingException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param String nickname
	 * @return boolean
	 * 이미 등록된 닉네임이 있는지 확인하는 메서드
	 * 등록된 닉네임이 있는 경우 true 반환, 등록된 닉네임이 없는 경우 false 반환
	 */
	public boolean usedNickname(String nickname) {
		boolean res = true;
		String sql = "";
		sql += "SELECT count(*) as cnt FROM account WHERE nickname=?";
		
		try {
			this.pstat = this.conn.prepareStatement(sql);
			this.pstat.setString(1, nickname);
			ResultSet rs = this.pstat.executeQuery();
			rs.next();
			if(rs.getInt("cnt") == 0) {
				res = false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 
	 * @param String email
	 * @return boolean
	 * 이미 등록된 이메일 주소가 있는지 확인하는 메서드
	 * 등록된 이메일 주소가 있는 경우 true 반환, 등록된 이메일 주소가 없는 경우 false 반환
	 */
	public boolean usedEmail(String email) {
		boolean res = true;
		String sql = "";
		sql += "SELECT count(*) as cnt FROM account WHERE email=?";
		
		try {
			this.pstat = this.conn.prepareStatement(sql);
			this.pstat.setString(1, email);
			ResultSet rs = this.pstat.executeQuery();
			rs.next();
			if(rs.getInt("cnt") == 0) {
				res = false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public void close() {
		try {
			this.conn.close();
			this.pstat.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
