# tradestore-dev

Tech Stack : 
1) Spring Boot 2.4.x
2) Mysql 7.x
3) JDK 13 
4) Maven
5) Eclipse IDE

Completed :
1) Funcationality - Storing the trade with 3 validations 
2) Scheduler - currently cron runs for every 30 seconds for testing purpose

WIP : 
1) Code coverage / Junit tests
2) Rule engines to make more enhanced version 


API / Endpoint configure is localhost:8080/api/trade/store - POST call

Database used is mysql 
Table name : trade_store
CREATE TABLE `trade_store` (
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


Sameple json request to verify through PostMan
{
"tradeId":"T1", 
"tradeVersion":"2", 
"tradeCounterPartyId":"CP-1", 
"tradeBookingId":"B2", 
"tradeMaturityDate":"2021-05-20", 
"tradeCreatedDate":"2015-03-14", 
"tradeExpiredStatus":"N"
}

