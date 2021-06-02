package com.web.som.account.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class AccountMybatis {
	private SqlSession sess = null;
	
	public AccountMybatis() {
		this.connect();
	}
	
	private void connect() {
		try {
			String resource = "resources/mybatis-config.xml";
			InputStream is = Resources.getResourceAsStream(resource);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(is);
			sess = factory.openSession();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public AccountVO select(int id) {
		AccountVO res = this.sess.selectOne("accountMapper.selectAccount", id);
		
		return res;
	}
	
	/** 
	 * @param String nickname
	 * @return boolean
	 * 이미 등록된 닉네임이 있는지 확인하는 메서드
	 * 등록된 닉네임이 있는 경우 true 반환, 등록된 닉네임이 없는 경우 false 반환
	 */
	public boolean usedNickname(String nickname) {
		boolean res = true;
		
		int cnt = this.sess.selectOne("accountMapper.checkNickname", nickname);
		if(cnt == 0) {
			res = false;
		}
		return res;
	}
	
	/**
	 * @param String email
	 * @return boolean
	 * 이미 등록된 이메일 주소가 있는지 확인하는 메서드
	 * 등록된 이메일 주소가 있는 경우 true 반환, 등록된 이메일 주소가 없는 경우 false 반환
	 */
	public boolean usedEmail(String email) {
		boolean res = true;
		
		int cnt = this.sess.selectOne("accountMapper.checkEmail", email);
		if(cnt == 0) {
			res = false;
		}
		return res;
	}
	
	public void loginCheck(AccountVO data) {
		AccountVO res = this.sess.selectOne("accountMapper.checkLogin", data);
		data.setId(res.getId());
		data.setUsername(res.getUsername());
		data.setNickname(res.getNickname());
		data.setPassword("");
		data.setGender(res.getGender());
		data.setAge(res.getAge());
		data.setJoindate(res.getJoindate());
		data.setLogindate(res.getLogindate());
		data.setExpiredate(res.getExpiredate());
	}
	
	public boolean join(AccountVO data) {
		boolean res = false;
		
		int rs = this.sess.insert("accountMapper.insertAccount", data);
		if(rs == 1) {
			res = true;
			this.sess.commit();		// 커밋 DB에 반영된 내용 저장
		} else {
			this.sess.rollback();	// 롤백 DB에 반영된 내용 취소
		}
		
		return res;
	}
	
	public boolean update(AccountVO data) {
		boolean res = false;
		
		int rs = this.sess.insert("accountMapper.updateAccount", data);
		if(rs == 1) {
			res = true;
			this.sess.commit();		// 커밋 DB에 반영된 내용 저장
		} else {
			this.sess.rollback();	// 롤백 DB에 반영된 내용 취소
		}
		
		return res;
	}
	
	public boolean expire(int id) {
		boolean res = false;
		
		int rs = this.sess.update("accountMapper.expireAccount", id);
		if(rs == 1) {
			res = true;
			this.sess.commit();		// 커밋 DB에 반영된 내용 저장
		} else {
			this.sess.rollback();	// 롤백 DB에 반영된 내용 취소
		}
		
		return res;
	}
	
	public void close() {
		this.sess.close();
	}
	
}
