package com.deutsche.trading.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.deutsche.trading.repository.TradeStoreRepository;
/**
 * 
 * @author amohamad
 * Scheduler to mark trades as expired where maturity date is less than today
 * 
 */
@Configuration
@Component
public class TradeMaturityScheduler { //https://www.tutorialspoint.com/spring_boot/spring_boot_scheduling.htm
	
	@Autowired
	TradeStoreRepository tradeStoreRepository;

	Logger logger = LoggerFactory.getLogger(TradeMaturityScheduler.class); 
	
	/**
	 * 
	 */
	
	@Scheduled(cron = "${cronjob.timing}") //currently configured at every 2 minutes to verify.
	public void updateExpirtyFlag() {
	    long now = System.currentTimeMillis() / 1000;
	    logger.info("Scheduled Job running at :  " + now);
	    
	    //1. Pull all the records for today and mark them as expired for each
	    //2. Using update statement : QUERY : UPDATE trade_store SET trade_expired_status = 'Y' WHERE trade_maturity_date > now(); 
	    //3. Rule engine to pull query and execute
	    // Updated_time can be kept in database to record/log
	    tradeStoreRepository.tradeExpiryUpdate();
	}
	
}
