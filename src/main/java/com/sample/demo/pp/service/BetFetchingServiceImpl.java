package com.sample.demo.pp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.demo.pp.data.model.BettingDTO;
import com.sample.demo.pp.data.model.BettingReport2;
import com.sample.demo.pp.data.repo.BettingRepo;
import com.sample.demo.pp.entity.BetDetails;

@Service
@Transactional
public class BetFetchingServiceImpl implements BetFetchingService {

	@Autowired
	private BettingRepo bettingRepo;
	
	@Override
	public List<BetDetails> listAllBettingDetails() {
		return bettingRepo.findAll();
	}

	public List<BettingDTO> listSelectedBetting() {
		// TODO Auto-generated method stub
		return bettingRepo.find();
	}

	@Override
	public List<BettingReport2> listSelectBettingReport2() {
		// TODO Auto-generated method stub
		return bettingRepo.findReportTwo();
	}

	
	
	
}
