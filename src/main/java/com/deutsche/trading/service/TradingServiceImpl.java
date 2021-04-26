package com.deutsche.trading.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deutsche.trading.models.TradeStore;
import com.deutsche.trading.repository.TradeStoreRepository;
/**
 * 
 * @author amohamad
 * Service implementation for Business logic 
 *
 */
@Component
public class TradingServiceImpl implements TradingService {

	@Autowired 
	TradeStoreRepository tradeStoreRepository;
	
	@Override
	public void storeTheTrade(TradeStore receivedTrade) {
		
		Logger logger = LoggerFactory.getLogger(TradingServiceImpl.class); 
		
		boolean proceedwithTrade = true;
		// Two approaches to validate trade document  
		
		// Maturity date validation 
		if(!maturityDateValidation(receivedTrade)) {
			logger.info(" Validation 2 : FAILED :  Trade txn received with maturity date less than today");
			return;
		}
		
		
		/**
		 * 1. Straight forward validation functions.  
		 * 	## First Validation ##: During transmission if the lower version is being received by the store it will reject the trade and 
		 * throw an exception. If the version is same it will override the existing record.
		 */
		
			List<TradeStore> singleTrade = tradeStoreRepository.findFirstByTradeIdOrderByTradeVersionDesc(receivedTrade.getTradeId());	
			
			if(singleTrade.size() > 0 ) { 
				TradeStore existingTrade = singleTrade.get(0);
				if(existingTrade.getTradeVersion() == receivedTrade.getTradeVersion()) {
					logger.info(" Validation 1 : UPDATE : Same version is received, proceed with update "+existingTrade.getId());
					//Maturity date validation is required
					tradeStoreRepository.updateExistingTrade(existingTrade.getId(), receivedTrade.getTradecounterPartyId(), receivedTrade.getTradeBookingId(), receivedTrade.getTradeMaturityDate(), receivedTrade.getTradeCreatedDate(), receivedTrade.getTradeExpiredStatus() );
					return; 
				}
				
				if (receivedTrade.getTradeVersion() < existingTrade.getTradeVersion() ) {
					proceedwithTrade = false;
					logger.info(" Validation 1 : SKIP : Lower version is received ignore the trade ");
				} 
			}
			
				
		// [OR]
		//	2. Send data to go through rules from rules engine.
		// validateTradeRecords.verify(trade);
		
		
		// Post verifying incoming data to database
		if(proceedwithTrade) {
			tradeStoreRepository.save(receivedTrade);
			logger.info(" Saving the Trade : SUCCESS : Succededed ");
		}else {
			logger.info(" Saving the Trade : FAILED : Return with error message ");
		}
	}
	
	/**
	 * Maturity date validation implementation
	 */
	public boolean maturityDateValidation(TradeStore receivedTrade) {
		// ## Second Validation ##: Store should not allow the trade which has less maturity date then today date.
		Date MaturityDate = receivedTrade.getTradeMaturityDate();
		Date TodaysDate = new Date(); // UTC or Timezone may apply
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");   
	    formatter.format(TodaysDate);

		if (! (MaturityDate.compareTo(TodaysDate) > 0) ) {
			return false;
		}		
		return true;
	}
}
