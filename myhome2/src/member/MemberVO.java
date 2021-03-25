package member;

import java.sql.Date;

// VO(Value Object)
//    데이터 베이스에 보내거나 받을 레코드 데이터에 대해
//    객체로 정의하여 Java 에서 다루기 위해 정의 (DTO - Data Transfer Object)
public class MemberVO {
	private String userid = null;
	private String password = null;
	private String email = null;
	private Date joindate = null;
	
	public MemberVO(String userid, String password, String email, Date joindate) {
		this.userid = userid;
		this.password = password;
		this.email = email;
		this.joindate = joindate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	
}
