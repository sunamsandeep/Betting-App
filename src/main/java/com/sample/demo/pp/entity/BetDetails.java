package com.sample.demo.pp.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "bet_details")
public class BetDetails{

	@Id
	@Column(name = "bet_id")
	private String betId;

	@Column(name = "bet_time_stamp")
	private BigInteger betTimestamp;

	@Column(name = "selection_id")
	private int selectionId;

	@Column(name = "selection_name")
	private String selectionName;

	@Column(name = "stake")
	private Double stake;

	@Column(name = "price")
	private Double price;

	@Column(name = "currency")
	private String currency;
	
	@Transient
	private MultipartFile file;

	public BetDetails() {
	}

	public BetDetails(String betId, BigInteger betTimestamp, int selectionId, String selectionName, Double stake,
			Double price, String currency) {
		super();
		this.betId = betId;
		this.betTimestamp = betTimestamp;
		this.selectionId = selectionId;
		this.selectionName = selectionName;
		this.stake = stake;
		this.price = price;
		this.currency = currency;
	}

	public String getBetId() {
		return betId;
	}

	public void setBetId(String betId) {
		this.betId = betId;
	}

	public BigInteger getBetTimestamp() {
		return betTimestamp;
	}

	public void setBetTimestamp(BigInteger betTimestamp) {
		this.betTimestamp = betTimestamp;
	}

	public int getSelectionId() {
		return selectionId;
	}

	public void setSelectionId(int selectionId) {
		this.selectionId = selectionId;
	}

	public String getSelectionName() {
		return selectionName;
	}

	public void setSelectionName(String selectionName) {
		this.selectionName = selectionName;
	}

	public Double getStake() {
		return stake;
	}

	public void setStake(Double stake) {
		this.stake = stake;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	

}
