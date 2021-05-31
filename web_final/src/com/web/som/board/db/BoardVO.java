package com.web.som.board.db;

import java.sql.Date;

public class BoardVO {
	private int id;
	private int btype;
	private int aid;
	private String title;
	private String contents;
	private int vcnt;
	private int gcnt;
	private int bcnt;
	private Date cdate;
	private Date udate;
	private String nodel;
	private String deleted;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}
	
	public int getBtype() {
		return btype;
	}
	
	public void setBtype(int btype) {
		this.btype = btype;
	}
	
	public void setBtype(String btype) {
		this.btype = Integer.parseInt(btype);
	}
	
	public int getAid() {
		return aid;
	}
	
	public void setAid(int aid) {
		this.aid = aid;
	}
	
	public void setAid(String aid) {
		this.aid = Integer.parseInt(aid);
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public int getVcnt() {
		return vcnt;
	}
	
	public void setVcnt(int vcnt) {
		this.vcnt = vcnt;
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
	
	public String getNodel() {
		return nodel;
	}
	
	public void setNodel(String nodel) {
		if(nodel == null) {
			this.nodel = "n";
		} else if(nodel.equals("on") || nodel.equals("y")) {
			this.nodel = "y";
		} else {
			this.nodel = "n";
		}
	}
	
	public String getDeleted() {
		return deleted;
	}
	
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
}
