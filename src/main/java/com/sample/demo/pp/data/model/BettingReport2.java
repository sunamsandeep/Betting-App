package com.sample.demo.pp.data.model;

public class BettingReport2 {
	private String currency;
	private Long numOfBets;
	private Double totalStake;
	private Double totalLiability;
	
	public BettingReport2() {}

	public BettingReport2(String currency, Long numOfBets, Double totalStake, Double totalLiability) {
		super();
		this.currency = currency;
		this.numOfBets = numOfBets;
		this.totalStake = totalStake;
		this.totalLiability = totalLiability;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getNumOfBets() {
		return numOfBets;
	}

	public void setNumOfBets(Long numOfBets) {
		this.numOfBets = numOfBets;
	}

	public Double getTotalStake() {
		return totalStake;
	}

	public void setTotalStake(Double totalStake) {
		this.totalStake = totalStake;
	}

	public Double getTotalLiability() {
		return totalLiability;
	}

	public void setTotalLiability(Double totalLiability) {
		this.totalLiability = totalLiability;
	}
	
	
}
