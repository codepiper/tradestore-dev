package com.deutsche.trading.service;

import org.springframework.stereotype.Service;

import com.deutsche.trading.models.TradeStore;

/**
 * 
 * @author amohamad
 * Interface to Service
 */
@Service
public interface TradingService {
	public abstract void storeTheTrade(TradeStore tradeStoreModel);
	public abstract boolean maturityDateValidation(TradeStore receivedTrade);
	
}
