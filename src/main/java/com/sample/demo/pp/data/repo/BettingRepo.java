package com.sample.demo.pp.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sample.demo.pp.data.model.BettingReport1;
import com.sample.demo.pp.data.model.BettingReport2;
import com.sample.demo.pp.entity.BetDetails;

@Repository
public interface BettingRepo extends JpaRepository<BetDetails, String>{

	/*	Query to fetch records from DB
	 * 	Report Data from H2 Database
	 * 	All queries will aggregate the record and is available for the service to consume it.
	 * 
	 * */
	
	@Query("select new com.sample.demo.pp.data.model.BettingReport1(b.selectionName as selectionName,"
			+ "b.currency as currency,"
			+ "count(betId) as numOfBets,"
			+ "round(sum(stake),2) as totalStake,"
			+ "round(sum(stake*price),2) as totalLiability) "
			+ "from BetDetails b "
			+ "group by b.selectionName,b.currency "
			+ "order by b.currency desc,round(sum(b.stake*b.price),2) desc")
	public List<BettingReport1> findReportOne();
	
	
	@Query("select new com.sample.demo.pp.data.model.BettingReport2("
			+ "b.currency as currency,"
			+ "count(betId) as numOfBets,"
			+ "round(sum(stake),2) as totalStake,"
			+ "round(sum(stake*price),2) as totalLiability) "
			+ "from BetDetails b "
			+ "group by b.currency "
			+ "order by b.currency desc,round(sum(b.stake*b.price),2) desc")
	public List<BettingReport2> findReportTwo();


}
