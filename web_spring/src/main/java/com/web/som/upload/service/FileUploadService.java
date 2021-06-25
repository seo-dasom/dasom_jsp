package com.web.som.upload.service;

import java.util.HashMap;

public interface FileUploadService {
	public int save(int id, HashMap<String, String> data) throws Exception;
}
