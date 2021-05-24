package com.web.som.board.db;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import oracle.jdbc.OracleResultSet;
import oracle.sql.CLOB;

public class BoardDAO {
	Connection conn = null;
	PreparedStatement pstat = null;
	
	public BoardDAO() {
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
	
	public List<BoardTypeVO> getBoardTypes() {
		List<BoardTypeVO> res = new ArrayList<BoardTypeVO>();
		String sql = "";
		sql += "SELECT * FROM board_type";
		
		try {
			this.pstat = this.conn.prepareStatement(sql);
			ResultSet rs = this.pstat.executeQuery();
			while(rs.next()) {
				BoardTypeVO data = new BoardTypeVO();
				data.setId(rs.getInt("id"));
				data.setName(rs.getString("name"));
				res.add(data);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public boolean insert(BoardVO data) {
		boolean res = false;
		String sql = "";
		sql += "SELECT board_seq.NEXTVAL as id FROM dual";
		
		try {
			// 미리 게시판 ID 생성
			this.pstat = this.conn.prepareStatement(sql);
			ResultSet id = this.pstat.executeQuery();
			id.next();
			data.setId(id.getInt("id"));
			id.close();
			this.pstat.close();
			
			// CLOB에 데이터를 저장할 때 미리 EMPTY_CLOB() 으로 값을 저장해 두어야 함.
			sql = "";
			sql += "INSERT INTO board(id, btype, aid, title, contents, nodel)";
			sql += " VALUES(?, ?, ?, ?, EMPTY_CLOB(), ?)";
			
			// CLOB 작업을 할 때는 자동 커밋 비활성화 해야 함.
			this.conn.setAutoCommit(false);
			this.pstat = this.conn.prepareStatement(sql);
			this.pstat.setInt(1, data.getId());
			this.pstat.setInt(2, data.getBtype());
			this.pstat.setInt(3, data.getAid());
			this.pstat.setString(4, data.getTitle());
			this.pstat.setString(5, data.getNodel());
			
			int rs = this.pstat.executeUpdate();
			this.pstat.close();
			
			// 저장 CLOB 데이터를 제외한 나머지 데이터 저장 후 CLOB 저장을 위한 처리를 수행
			if(rs == 1) {
				sql = "";
				sql += "SELECT contents FROM board WHERE id=? FOR UPDATE";
				this.pstat = this.conn.prepareStatement(sql);
				this.pstat.setInt(1, data.getId());
				ResultSet rs2 = this.pstat.executeQuery();
				
				rs2.next();
				CLOB clob = (CLOB)rs2.getClob("contents");
				Writer w = clob.getCharacterOutputStream();
				Reader r = new CharArrayReader(data.getContents().toCharArray());
				char[] buf = new char[1024];
				int read = 0;
				
				while((read = r.read(buf, 0, 1024)) != -1) {
					w.write(buf, 0, read);
				}
				
				res = true;
				
				r.close();	w.close();	rs2.close();
				this.conn.commit();
			} else {
				this.conn.rollback();
			}
			this.conn.setAutoCommit(true);
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
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
