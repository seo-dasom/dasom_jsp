package com.web.som.upload.dao;

import java.util.HashMap;

public interface FileUploadRepository {
	public int insert(int id, HashMap<String, String> data) throws Exception;
}
