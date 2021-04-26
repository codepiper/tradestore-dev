package com.deutsche.trading;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 
 * @author amohamad
 * Trading app initialization 
 * 
 */
@EnableScheduling
@SpringBootApplication
public class TradingApplication {
	public static void main(String[] args) {
		SpringApplication.run(TradingApplication.class, args);
	}
}