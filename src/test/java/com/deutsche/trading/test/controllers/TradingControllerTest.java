package com.deutsche.trading.test.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner; 

@RunWith(SpringRunner.class) 
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) 


public class TradingControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void isServiceUp() {
		ResponseEntity<TradingControllerTest> response = restTemplate.getForEntity("/api/trade/status", TradingControllerTest.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);	
	}
	
	

}
