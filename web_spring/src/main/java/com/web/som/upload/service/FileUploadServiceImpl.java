package com.web.som.upload.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.som.upload.dao.FileUploadRepository;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	@Autowired
	FileUploadRepository dao;

	@Override
	public int save(int id, HashMap<String, String> data) throws Exception {
		int res = dao.insert(id, data);
		return res;
	}

}
