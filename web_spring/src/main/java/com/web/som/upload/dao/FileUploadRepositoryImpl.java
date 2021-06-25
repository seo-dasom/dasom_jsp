package com.web.som.upload.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FileUploadRepositoryImpl implements FileUploadRepository {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insert(int id, HashMap<String, String> data) throws Exception {
		data.put("bid", Integer.toString(id));
		int res = sqlSession.insert("fileupload.insert", data);
		return res;
	}

}
