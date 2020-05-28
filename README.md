# Betting-App

This application is deployed on AWS cloud using Elastic BeansStalk

Link to the Live instance --> 

Source code --> https://github.com/sunamsandeep/Betting-App

Sample Data files --> https://github.com/sunamsandeep/Betting-App/blob/master/test.csv
		      https://github.com/sunamsandeep/Betting-App/blob/master/test.json
		

## pom.xml

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.3.0.RELEASE</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.sample.demo.pp</groupId>
	<artifactId>PP-Betting-System-Demo</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>war</packaging>
	<name>PP-Betting-System-Demo</name>
	<description>Betting System</description>

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>

		<!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>2.6</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/com.opencsv/opencsv -->
		<dependency>
			<groupId>com.opencsv</groupId>
			<artifactId>opencsv</artifactId>
			<version>5.2</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
		</dependency>

		<!-- https://mvnrepository.com/artifact/junit/junit -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```
## View 

```html
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout" th:lang="en">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>PP Betting Dashboard</title>
</head>
<body>

	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1>Betting Dashboard</h1>
			<p>Please upload your file and click on Submit button</p>
			<p>note: only JSON or CSV files are accepted</p>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="alert alert-success alert-dismissible"
					th:if="${successmessage}">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<strong th:text="${successmessage}"></strong>
				</div>

				<div class="alert alert-danger alert-dismissible"
					th:if="${errormessage}">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<strong th:text="${errormessage}"></strong>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">

				<form action="" th:action="@{'/fileupload'}"
					th:object="${betDetails}" method="post"
					enctype="multipart/form-data">
					<div class="form-group">
						<input type="file" class="form-control" id="file" name="file">
					</div>
					<button type="submit" class="btn btn-success">Submit</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h2>Selection Liability by Currency</h2>
				<div style="margin-top: 12px">
					<table class="table table-striped table-bordered">
						<thead class="thead-dark">
							<tr>
								<th>Selection Name</th>
								<th>Currency</th>
								<th>Num Bets</th>
								<th>Total Stakes</th>
								<th>Total Liability</th>
							</tr>
						</thead>
						<tbody>
							<tr th:each="bet : ${bettingReport1}">
								<td data-th-text="${bet.selectionName}"></td>
								<td data-th-text="${bet.currency}"></td>
								<td data-th-text="${bet.numOfBets}"></td>
								<td
									data-th-text="${bet.currency}==GBP?'£' + ${bet.totalStake}:'€' + ${bet.totalStake}"></td>
								<td
									data-th-text="${bet.currency}==GBP?'£' + ${bet.totalLiability}:'€' + ${bet.totalLiability}"></td>
							</tr>
						</tbody>
					</table>

				</div>
			</div>

		</div>
		<div class="row">
			<div class="col-md-12">
				<h2>Total Liability by Currency</h2>
				<div style="margin-top: 12px">
					<table class="table table-striped table-bordered">
						<thead class="thead-dark">
							<tr>
								<th>Currency</th>
								<th>Num Bets</th>
								<th>Total Stakes</th>
								<th>Total Liability</th>
							</tr>
						</thead>
						<tbody>
							<tr th:each="bet : ${bettingReport2}">
								<td data-th-text="${bet.currency}"></td>
								<td data-th-text="${bet.numOfBets}"></td>
								<td
									data-th-text="${bet.currency}==GBP?'£' + ${bet.totalStake}:'€' + ${bet.totalStake}"></td>
								<td
									data-th-text="${bet.currency}==GBP?'£' + ${bet.totalLiability}:'€' + ${bet.totalLiability}"></td>
							</tr>
						</tbody>
					</table>

				</div>
			</div>

		</div>

	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>

```







## Main Controller

```java
package com.sample.demo.pp.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sample.demo.pp.entity.BetDetails;
import com.sample.demo.pp.service.BetFetchingService;
import com.sample.demo.pp.service.ReadFileService;

@Controller
@RequestMapping("/")
public class PPBettingController {
	
	/*
	 * Main Controller 
	 * 
	 * 
	 */
	
	@Autowired
	private BetFetchingService betFetchingService;
	
	@Autowired
	private ReadFileService readFileService;
	
	
	@GetMapping
	public String getBetDetails(Model model){
		model.addAttribute("bettingReport1", betFetchingService.listSelectBettingReport1());
		model.addAttribute("bettingReport2", betFetchingService.listSelectBettingReport2());
		return "view/betdetails";
		}
	
	
	@PostMapping("/fileupload")
	public String uploadFile(@ModelAttribute BetDetails betDetails, RedirectAttributes redirectAttributes) {
		
		boolean isFlag = readFileService.saveData(betDetails.getFile());
		
		if(isFlag) {
			redirectAttributes.addFlashAttribute("successmessage","Upload Successful");
		}else {
			redirectAttributes.addFlashAttribute("errormessage","File not uploaded, Please check the data/format and try again");
		}
		
		return "redirect:/";
		
	}
	
}

```
## Service Implementation for Reading files

```java
package com.sample.demo.pp.service;

import org.springframework.web.multipart.MultipartFile;

public interface ReadFileService {

	public boolean saveData(MultipartFile file);
	
}


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


```

## Service created for fetching records from database
```java

package com.sample.demo.pp.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sample.demo.pp.data.model.BettingReport1;
import com.sample.demo.pp.data.model.BettingReport2;

public interface BetFetchingService {
	
	/*
	 * Main Service Interface used for fetching records from database
	 * 
	 * 
	 */
	
	public List<BettingReport1> listSelectBettingReport1();
	public List<BettingReport2> listSelectBettingReport2();

}


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

```

## Repository Implementation for querying database entity

```java
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

```

## Database Model Entity

```java
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
	
	
	/*
	 * 
	 * The main model the which JPA uses to bind 
	 * to the database table.
	 * 
	 * 
	 */

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


```

## Models used to populate report data

```java
package com.sample.demo.pp.data.model;


public class BettingReport1 {

	private String selectionName;
	private String currency;
	private Long numOfBets;
	private Double totalStake;
	private Double totalLiability;

	public BettingReport1() {
	}
	

	public BettingReport1(String selectionName, String currency, Long numOfBets, Double totalStake, Double totalLiability) {
		super();
		this.selectionName = selectionName;
		this.currency = currency;
		this.numOfBets = numOfBets;
		this.totalStake = totalStake;
		this.totalLiability = totalLiability;
	}
	
	

	public String getSelectionName() {
		return selectionName;
	}

	public void setSelectionName(String selectionName) {
		this.selectionName = selectionName;
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






package com.sample.demo.pp.data.model;

public class BettingReport2 {
	
	
	/*
	 * 
	 * POJO used to populate Report 2 records
	 * 
	 * 
	 */
	
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



```
