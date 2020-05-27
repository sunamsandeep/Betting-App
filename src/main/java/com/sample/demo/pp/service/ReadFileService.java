package com.sample.demo.pp.service;

import org.springframework.web.multipart.MultipartFile;

public interface ReadFileService {

	public boolean saveData(MultipartFile file);
	
}
