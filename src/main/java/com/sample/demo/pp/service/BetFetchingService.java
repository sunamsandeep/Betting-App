package com.sample.demo.pp.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sample.demo.pp.data.model.BettingReport1;
import com.sample.demo.pp.data.model.BettingReport2;

@Component
public interface BetFetchingService {
	
	/*
	 * Main Service Interface used for fetching records from database
	 * 
	 * 
	 */
	
	public List<BettingReport1> listSelectBettingReport1();
	public List<BettingReport2> listSelectBettingReport2();

}
