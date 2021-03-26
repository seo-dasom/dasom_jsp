package visit;

import java.sql.Date;

public class VisitVO {
	private int id;
	private String author;
	private String context;
	private Date create_date;
	
	public VisitVO(String author, String context) {
		this.id = -1;
		this.author = author;
		this.context = context;
		this.create_date = null;
	}
	
	public VisitVO(int id, String author, String context, Date create_date) {
		this.id = id;
		this.author = author;
		this.context = context;
		this.create_date = create_date;
	}

	public int getId() {
		return id;
	}

//	public void setId(int id) {
//		자동증가로 사용하기 위해서 수정을 하지 못하게 막는다.	
//		this.id = id;
//	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getCreateDate() {
		return create_date;
	}

	public void setCreateDate(Date create_date) {
		this.create_date = create_date;
	}
	
	
}
