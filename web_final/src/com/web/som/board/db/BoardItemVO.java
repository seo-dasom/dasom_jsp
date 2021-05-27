package com.web.som.board.db;

import java.sql.Date;

public class BoardItemVO {
	private int id;
	private int btype;		// 게시판 구분 ID
	private String bname;	// 게시판 구분 이름
	private int aid;		// 작성자 ID
	private String aname;		// 작성자 이름
	private String title;
	private Date cdate;
	private int vcnt;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getBtype() {
		return btype;
	}
	
	public void setBtype(int btype) {
		this.btype = btype;
	}
	
	public String getBname() {
		return bname;
	}
	
	public void setBname(String bname) {
		this.bname = bname;
	}
	
	public int getAid() {
		return aid;
	}
	
	public void setAid(int aid) {
		this.aid = aid;
	}
	
	public String getAname() {
		return aname;
	}
	
	public void setAname(String aname) {
		this.aname = aname;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getCdate() {
		return cdate;
	}
	
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	
	public int getVcnt() {
		return vcnt;
	}
	
	public void setVcnt(int vcnt) {
		this.vcnt = vcnt;
	}
}
