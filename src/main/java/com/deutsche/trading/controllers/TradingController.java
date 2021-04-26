package com.deutsche.trading.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deutsche.trading.models.TradeStore;
import com.deutsche.trading.service.TradingService;

/**
 * 
 * @author amohamad
 * Controller to handle all the api requests - trade stream
 */
@RequestMapping("/api/trade")
@RestController
public class TradingController {

	@Autowired
	TradingService tradingService;
	
    @PostMapping(value = "/store", consumes = {"application/json"}, produces = {"application/json"})
	public ResponseEntity<?> createTrade(@RequestBody TradeStore trade) { 

	   tradingService.storeTheTrade(trade);

	   return new ResponseEntity<>("Saved the Trade",HttpStatus.OK); // Rule Engine process is pending
	}
    @GetMapping(value = "/status", produces = {"application/json"})
	public ResponseEntity<?> status() { 
	   return new ResponseEntity<>(HttpStatus.OK); // Rule Engine process is pending
	}

}
