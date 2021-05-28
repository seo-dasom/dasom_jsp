package com.web.som.board.comment.db;

import java.sql.Date;

public class CommentVO {
	private int id;
	private int bid;
	private int aid;
	private String aname;
	private String contents;
	private int gcnt;
	private int bcnt;
	private Date cdate;
	private Date udate;
	private String deleted;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getBid() {
		return bid;
	}
	
	public void setBid(int bid) {
		this.bid = bid;
	}
	
	public void setBid(String bid) {
		this.bid = Integer.parseInt(bid);
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
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public int getGcnt() {
		return gcnt;
	}
	
	public void setGcnt(int gcnt) {
		this.gcnt = gcnt;
	}
	
	public int getBcnt() {
		return bcnt;
	}
	
	public void setBcnt(int bcnt) {
		this.bcnt = bcnt;
	}
	
	public Date getCdate() {
		return cdate;
	}
	
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	
	public Date getUdate() {
		return udate;
	}
	
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	
	public String getDeleted() {
		return deleted;
	}
	
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
}
