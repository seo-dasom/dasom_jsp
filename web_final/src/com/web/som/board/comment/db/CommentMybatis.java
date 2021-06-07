package com.web.som.board.comment.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CommentMybatis {
	private SqlSession sess = null;
	
	public CommentMybatis() {
		this.connect();
	}
	
	private void connect() {
		try {
			String resource = "resources/mybatis-config.xml";
			InputStream is = Resources.getResourceAsStream(resource);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = builder.build(is);
			this.sess = factory.openSession();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void good(int id) {
		this.sess.update("commentMapper.incgood", id);
	}
	
	public void bad(int id) {
		this.sess.update("commentMapper.incbad", id);
	}
	
	public int goodCount(int id) {
		CommentVO data = this.sess.selectOne("commentMapper.goodcount", id);
		return data.getGcnt();
	}
	
	public int badCount(int id) {
		CommentVO data = this.sess.selectOne("commentMapper.badcount", id);
		return data.getBcnt();
	}
	
	public boolean delete(int id) {
		boolean res = false;
		int rs = this.sess.update("commentMapper.delete", id);
		if(rs == 1) {
			res = true;
			this.sess.commit();
		} else {
			this.sess.rollback();
		}
		return res;
	}
	
	public void commit() {
		this.sess.commit();
	}
	
	public void close() {
		this.sess.close();
	}
}
