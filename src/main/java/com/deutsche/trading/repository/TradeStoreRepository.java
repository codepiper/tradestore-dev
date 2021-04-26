package com.deutsche.trading.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deutsche.trading.models.Expired;
import com.deutsche.trading.models.TradeStore;
/**
 * 
 * @author amohamad
 * To execute any DML changes 
 */
@Repository
public interface TradeStoreRepository extends CrudRepository<TradeStore, Long>  
{

	/**
	 * Validation 1 - pre check  
	 */
	List<TradeStore> findFirstByTradeIdOrderByTradeVersionDesc(String trade_id);

	
	/**
	 * Validation 1 - post action : update   
	 * Same can be achieved through @DynamicUpdate(value=true) or @SelectBeforeUpdate(value=true)
	 */
	@Transactional
	@Modifying
	@Query("UPDATE TradeStore SET tradeCounterPartyId=?2, tradeBookingId=?3 , tradeMaturityDate =?4 ,tradeCreatedDate =?5, tradeExpiredStatus = ?6 WHERE Id=?1 ")
	void updateExistingTrade(Long Id, String tradeCounterPartyId, String tradeBookingId, Date tradeMaturityDate, Date tradeCreatedDate, Expired tradeExpiredStatus );
	

	/**
	 * Used by scheduler for marking trades as expired once they below current date
	 */
	@Transactional
	@Modifying
	@Query("UPDATE TradeStore SET tradeExpiredStatus = 'Y' WHERE tradeMaturityDate < now() AND tradeExpiredStatus = 'N'")
	void tradeExpiryUpdate();
	
}

