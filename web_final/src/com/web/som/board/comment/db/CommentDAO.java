package com.web.som.board.comment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.web.som.board.db.BoardVO;

public class CommentDAO {
	Connection conn = null;
	PreparedStatement pstat = null;
	
	public CommentDAO() {
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
	
	public void selectItem(CommentVO item) {
		String sql = "";
		sql += "SELECT a.id"
			+  "     , a.bid"
			+  "     , a.aid"
			+  "     , (SELECT nickname FROM account WHERE id = a.aid) AS aname"
			+  "     , a.contents"
			+  "     , a.gcnt"
			+  "     , a.bcnt"
			+  "     , a.cdate"
			+  "     , a.udate"
			+  "     , a.deleted"
			+  "  FROM comments a"
			+  " WHERE id = ?";
		
		try {
			this.pstat = this.conn.prepareStatement(sql);
			this.pstat.setInt(1, item.getId());
			ResultSet rs = this.pstat.executeQuery();
			item.setId(-1);
			while(rs.next()) {
				item.setId(rs.getInt("id"));
				item.setBid(rs.getInt("bid"));
				item.setAid(rs.getInt("aid"));
				item.setAname(rs.getString("aname"));
				item.setContents(rs.getString("contents"));
				item.setGcnt(rs.getInt("gcnt"));
				item.setBcnt(rs.getInt("bcnt"));
				item.setCdate(rs.getDate("cdate"));
				item.setUdate(rs.getDate("udate"));
				item.setDeleted(rs.getString("deleted"));
			}
			rs.close();
			this.pstat.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<CommentVO> selectList(BoardVO data) {
		List<CommentVO> res = new ArrayList<CommentVO>();
		String sql = "";
		sql += "SELECT a.id"
			+  "     , a.bid"
			+  "     , a.aid"
			+  "     , (SELECT nickname FROM account WHERE id = a.aid) AS aname"
			+  "     , a.contents"
			+  "     , a.gcnt"
			+  "     , a.bcnt"
			+  "     , a.cdate"
			+  "     , a.udate"
			+  "     , a.deleted"
			+  "  FROM comments a"
			+  " WHERE bid = ?"
			+  " ORDER BY a.id DESC";
		
		try {
			this.pstat = this.conn.prepareStatement(sql);
			this.pstat.setInt(1, data.getId());
			ResultSet rs = this.pstat.executeQuery();
			
			while(rs.next()) {
				CommentVO c_data = new CommentVO();
				c_data.setId(rs.getInt("id"));
				c_data.setBid(rs.getInt("bid"));
				c_data.setAid(rs.getInt("aid"));
				c_data.setAname(rs.getString("aname"));
				c_data.setContents(rs.getString("contents"));
				c_data.setGcnt(rs.getInt("gcnt"));
				c_data.setBcnt(rs.getInt("bcnt"));
				c_data.setCdate(rs.getDate("cdate"));
				c_data.setUdate(rs.getDate("udate"));
				c_data.setDeleted(rs.getString("deleted"));
				
				res.add(c_data);
			}
			//rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public boolean insert(CommentVO data) {
		boolean res = false;
		String sql = "";
		sql += "SELECT comments_seq.NEXTVAL as id FROM dual";
		
		try {
			this.pstat = this.conn.prepareStatement(sql);
			ResultSet id = this.pstat.executeQuery();
			id.next();
			data.setId(id.getInt("id"));
			id.close();
			
			sql = "INSERT INTO comments(id, bid, aid, contents)"
				+  " VALUES(?, ?, ?, ?)";
			
			this.pstat = this.conn.prepareStatement(sql);
			this.pstat.setInt(1, data.getId());
			this.pstat.setInt(2, data.getBid());
			this.pstat.setInt(3, data.getAid());
			this.pstat.setString(4, data.getContents());
			
			int rs = this.pstat.executeUpdate();
			if(rs == 1) {
				res = true;
			}
			this.pstat.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public void close() {
		try {
			this.conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
