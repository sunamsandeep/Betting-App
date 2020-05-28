package com.sample.demo.pp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.demo.pp.data.model.BettingReport1;
import com.sample.demo.pp.data.model.BettingReport2;
import com.sample.demo.pp.data.repo.BettingRepo;
import com.sample.demo.pp.entity.BetDetails;

@Service
@Transactional
public class BetFetchingServiceImpl implements BetFetchingService {
	
	/*
	 * This service is responsible to fetch the processed data from 
	 * database
	 * 
	 * The main controller uses this service to retrieve data from 
	 * database
	 * 
	 */
	

	@Autowired
	private BettingRepo bettingRepo;
	

	public List<BettingReport1> listSelectBettingReport1() {
		return bettingRepo.findReportOne();
	}

	@Override
	public List<BettingReport2> listSelectBettingReport2() {
		return bettingRepo.findReportTwo();
	}

	
	
	
}
