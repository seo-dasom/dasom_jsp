package com.web.som.board.dto;

public class BoardSearchDTO {
	private int boardtype;
	private String searchtype;
	private String search;
	
	public int getBoardtype() {
		return boardtype;
	}
	
	public void setBoardtype(int boardtype) {
		this.boardtype = boardtype;
	}
	
	public String getSearchtype() {
		return searchtype;
	}
	
	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}
	
	public String getSearch() {
		return search;
	}
	
	public void setSearch(String search) {
		this.search = search;
	}
	
}
