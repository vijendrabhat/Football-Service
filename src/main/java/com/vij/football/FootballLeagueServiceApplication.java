package com.vij.football;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
@EnableCircuitBreaker
public class FootballLeagueServiceApplication {

	  static final Logger LOGGER = LogManager.getLogger(FootballLeagueServiceApplication.class.getName());

	  public static void main(String[] args) {
	    SpringApplication.run(FootballLeagueServiceApplication.class, args);
	  }
	  
}
