package com.web.som.board.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BoardMybatis {
	private SqlSession sess = null;
	
	public BoardMybatis() {
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
	
	public void incView(int id) {
		this.sess.update("boardMapper.incview", id);
	}
	
	public void good(int id) {
		this.sess.update("boardMapper.incgood", id);
	}
	
	public int goodCount(int id) {
		BoardVO data = this.sess.selectOne("boardMapper.goodcount", id);
		return data.getGcnt();
	}
	
	public int badCount(int id) {
		BoardVO data = this.sess.selectOne("boardMapper.badcount", id);
		return data.getBcnt();
	}
	
	public void bad(int id) {
		this.sess.update("boardMapper.incbad", id);
	}
	
	public void commit() {
		this.sess.commit();
	}
	
	public void close() {
		this.sess.close();
	}
}