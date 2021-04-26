package com.deutsche.trading.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;

/**
 * 
 * @author amohamad
 * To store the trade txn info
 * In the table we can have independent columns like record created time and updated times. 
 * MySql database creation query 
 * CREATE TABLE `trade_store` (
		  `id` int NOT NULL AUTO_INCREMENT,
		  `trade_id` varchar(20) DEFAULT NULL,
		  `trade_version` int DEFAULT NULL,
		  `trade_counter_party_id` varchar(20) DEFAULT NULL,
		  `trade_booking_id` varchar(20) DEFAULT NULL,
		  `trade_maturity_date` date DEFAULT NULL,
		  `trade_created_date` date DEFAULT NULL,
		  `trade_expired_status` varchar(2) DEFAULT NULL,
		  PRIMARY KEY (`id`)
		) ENGINE=InnoDB
 */

@Embeddable
@Table (name = "trade_store")
@Data 
@Entity
public class TradeStore {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
	private String tradeId;
	
    @NotNull
	private Integer tradeVersion;
	
    @NotNull
	private String tradeCounterPartyId;
	
    @NotNull
	private String tradeBookingId;
	
	@NotNull
	private Date tradeMaturityDate;  
	
    @NotNull
	private Date tradeCreatedDate;
	
	@Enumerated(EnumType.STRING)
	private Expired tradeExpiredStatus;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Integer getTradeVersion() {
		return tradeVersion;
	}

	public void setTradeVersion(Integer tradeVersion) {
		this.tradeVersion = tradeVersion;
	}

	public String getTradecounterPartyId() {
		return tradeCounterPartyId;
	}

	public void setTradeCounterPartyId(String tradeCounterPartyId) {
		this.tradeCounterPartyId = tradeCounterPartyId;
	}

	public String getTradeBookingId() {
		return tradeBookingId;
	}

	public void setTradeBookingId(String tradeBookingId) {
		this.tradeBookingId = tradeBookingId;
	}

	public Date getTradeMaturityDate() {
		return tradeMaturityDate;
	}

	public void setTradeMaturityDate(Date tradeMaturityDate) {
		this.tradeMaturityDate = tradeMaturityDate;
	}

	public Date getTradeCreatedDate() {
		return tradeCreatedDate;
	}

	public void setTradeCreatedDate(Date tradeCreatedDate) {
		this.tradeCreatedDate = tradeCreatedDate;
	}

	public Expired getTradeExpiredStatus() {
		return tradeExpiredStatus;
	}

	public void setTradeExpiredStatus(Expired tradeExpiredStatus) {
		this.tradeExpiredStatus = tradeExpiredStatus;
	}
	
}
