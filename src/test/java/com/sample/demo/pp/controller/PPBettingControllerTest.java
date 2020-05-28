package com.sample.demo.pp.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sample.demo.pp.service.BetFetchingService;

import com.sample.demo.pp.service.ReadFileService;


@RunWith(SpringRunner.class)
@WebMvcTest(PPBettingController.class)
public class PPBettingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;
	
	@MockBean
	private BetFetchingService betFetchingService;
	
	
	@MockBean
	private ReadFileService readFileService;
	

	   @Test
	   public void shouldCheckRedirect() throws Exception {

	        MockMultipartFile jsonFile = new MockMultipartFile("json", "", "application/json", "{\"json\": \"someValue\"}".getBytes());
	        MockMultipartFile secondFile = new MockMultipartFile("data", "file.xml", "text/xml", "some other type".getBytes());
	        
	        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	        mockMvc.perform(MockMvcRequestBuilders.multipart("/fileupload")
	                        .file(jsonFile)
	                        .file(secondFile))
	                    .andExpect(status().is(302));
	   }
	   
	   @Test
	   public void shouldCheckFileUpload() throws Exception {

	        MockMultipartFile file = new MockMultipartFile("test.json", "", "application/json", 
	        		"{\"betId\": \"Bet-10\",\"betTimestamp\": 123767,\"selectionId\": 1,\"selectionName\": \" My Fair Lady\",\"stake\": 0.5,\"price\": 6,\"currency\": \" GBP\"}"	.getBytes()); 
	        
	        assertEquals(false,file.isEmpty());
	       
	        
	      
	       
	   }
	   

}
