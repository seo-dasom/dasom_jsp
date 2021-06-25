package com.web.som.common;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	private String root;
	private String location;
	private String url;
	private int maxFileCount = 5;
	private int maxFileSize = 5 * 1024 * 1024;
	private ArrayList<String> fileExtPermit;
	private ArrayList<HashMap> saveFiles;
	
	public ArrayList<HashMap> getSaveFiles() {
		return saveFiles;
	}

	public FileUpload(String root, String location, String url) {
		this.root = root;
		this.location = location;
		this.url = url;
		
		this.fileExtPermit = new ArrayList<>();
		this.setFileExtPermit("zip");
		this.setFileExtPermit("txt");
		this.setFileExtPermit("png");
		this.setFileExtPermit("jpg");
		
		this.saveFiles = new ArrayList<>();
	}
	
	public int save(int id, MultipartFile[] files) throws Exception {
		int fcode = 0;
		if(files.length > this.maxFileCount) {
			fcode = -1;		// 최대 업로드 수량 초과.
		} else {
			for(MultipartFile file: files) {
				fcode = this.isValidFile(file);
				if(fcode != 1) {
					return fcode;
				}
			}
			
			for(MultipartFile file: files) {
				this.save(id, file);
			}
		}
		return fcode;
	}
	
	private int save(int id, MultipartFile file) throws Exception {
		int fcode = 1;
		
		UUID uuid = UUID.randomUUID();
		String origin_name = file.getOriginalFilename();
		String change_name = uuid.toString() + "_" + origin_name;
		File save_path = new File(this.root + this.location);
		
		if(!save_path.exists()) {
			Files.createDirectories(save_path.toPath());
		}
		
		save_path = new File(save_path.toPath() + "/" + change_name);
		file.transferTo(save_path);
		
		HashMap<String, String> saveFile = new HashMap<>();
		saveFile.put("filename", origin_name);
		saveFile.put("location", this.location + change_name);
		saveFile.put("url", this.url + change_name);
		this.saveFiles.add(saveFile);
		
		return fcode;
	}
	
	private int isValidFile(MultipartFile file) {
		if(this.maxFileSize < file.getSize()) {
			return -2;	// 최대 업로드 용량 초과
		} else if(!this.fileExtPermit.contains(
				FilenameUtils.getExtension(
						file.getOriginalFilename()))) {
			return -3;	// 허용하지 않은 확장자
		}
		return 1;
	}

	public int getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(int maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public int getMaxFileCount() {
		return maxFileCount;
	}

	public void setMaxFileCount(int maxFileCount) {
		this.maxFileCount = maxFileCount;
	}

	public String getFileExtPermit() {
		return fileExtPermit.toString();
	}

	public void setFileExtPermit(String fileExt) {
		this.fileExtPermit.add(fileExt);
	}

}