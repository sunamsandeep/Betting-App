package com.sample.demo.pp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sample.demo.pp.data.model.BettingDTO;
import com.sample.demo.pp.data.model.BettingReport2;
import com.sample.demo.pp.entity.BetDetails;

public interface BetFetchingService {
	
	public List<BetDetails> listAllBettingDetails();
	public List<BettingDTO> listSelectedBetting();
	public List<BettingReport2> listSelectBettingReport2();

}
