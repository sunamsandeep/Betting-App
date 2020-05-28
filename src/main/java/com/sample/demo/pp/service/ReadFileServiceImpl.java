package com.sample.demo.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.sample.demo.pp.data.repo.BettingRepo;
import com.sample.demo.pp.entity.BetDetails;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;

@Service
@Transactional
public class ReadFileServiceImpl implements ReadFileService {
	
	/*
	 * This service is responsible to read input files  
	 * and save data
	 * 
	 * 
	 * 
	 */

	@Autowired
	private BettingRepo bettingRepo;

	@Override
	public boolean saveData(MultipartFile file) {
		boolean isFlag = false;
		
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		if (ext.equalsIgnoreCase("json")) {
			isFlag = isJsonFile(file);
		} else if (ext.equalsIgnoreCase("csv")) {
			isFlag = isCsvFile(file);
		}

		return isFlag;
	}

	private boolean isCsvFile(MultipartFile file) {
		try {
			bettingRepo.deleteAll();//deleting all the record before inserting new record
			
			InputStreamReader inputStream = new InputStreamReader(file.getInputStream());
			CSVReader csvReader = new CSVReaderBuilder(inputStream).withSkipLines(1).build();
			List<String[]> rows = csvReader.readAll();
			for (String[] row : rows) {
				bettingRepo
						.save(new BetDetails(row[0].trim(), new BigInteger(row[1].trim()), new Integer(row[2].trim()),
								row[3], new Double(row[4].trim()), new Double(row[5].trim()), row[6].trim()));
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean isJsonFile(MultipartFile file) {
		try {
			
			bettingRepo.deleteAll();//deleting all the record before inserting new record
			
			InputStream inputStream = file.getInputStream();
			ObjectMapper mapper = new ObjectMapper();
			
			List<BetDetails> betDetails = mapper.reader()
				      .forType(new TypeReference<List<BetDetails>>() {})
				      .readValue(inputStream);

			if (betDetails != null && betDetails.size() >= 0) {
				for (BetDetails betDet : betDetails) {
					bettingRepo.save(betDet);
				}
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
