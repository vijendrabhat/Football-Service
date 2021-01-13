package com.vij.football.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.vij.football.entity.TeamCountry;
import com.vij.football.entity.TeamLeague;
import com.vij.football.entity.TeamStanding;
import com.vij.football.exception.FootballServiceException;

@Component
@SuppressWarnings("unused")
public class FootballLeagueClientHandler {

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	private RestTemplate restTemplate;

	private static final String APIKEY = "APIkey";

	private String baseUrl;

	private String standingsAction;

	private String countriesAction;

	private String leaguesAction;

	private String api;

	@Bean
	public void getProperties() {
		api = ctx.getEnvironment().getProperty("football.league.token");
		baseUrl = ctx.getEnvironment().getProperty("football.league.base.url");
		countriesAction = ctx.getEnvironment().getProperty("football.league.action.get.countries");
		leaguesAction = ctx.getEnvironment().getProperty("football.league.action.get.leagues");
		standingsAction = ctx.getEnvironment().getProperty("football.league.action.get.standings");
	}
	
	@Bean
	public RestTemplate restTemplate() {
	      return new RestTemplate();
	}

	private UriComponentsBuilder getUriComponentsBuilder(String url, Map<String, String> queryParams) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam(APIKEY, api);
		queryParams.forEach(builder::queryParam);
		return builder;
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}

	@HystrixCommand(fallbackMethod = "callGetCountriesFallback",  
	commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") })
	public TeamCountry[] getCountries() throws FootballServiceException {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("action", countriesAction);
		try {
	    UriComponentsBuilder builder = getUriComponentsBuilder(baseUrl, queryParams);
	    return this.restTemplate
	        .exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()),
	        		TeamCountry[].class).getBody();
		}
		catch(RestClientException e) {
			throw new FootballServiceException("Get Countries failed over :" + baseUrl, e);
		}
	}
	
    private TeamCountry[] callGetCountriesFallback() {
 
        System.out.println("CIRCUIT BREAKER ENABLED!!! Foot ball base URL is down!!! fallback route enabled...");
 
        return new TeamCountry[]{new TeamCountry()};
    }
    
	@HystrixCommand(fallbackMethod = "callGetLeagueFallback",  
	commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") })
	public TeamLeague[] getLeagues(int countryId) throws FootballServiceException {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("action", leaguesAction);
		queryParams.put("country_id", String.valueOf(countryId));
		try {
		UriComponentsBuilder builder = getUriComponentsBuilder(baseUrl, queryParams);
		return this.restTemplate
				.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()), TeamLeague[].class)
				.getBody();
		}
		catch(RestClientException e) {
			throw new FootballServiceException("Get League failed over :" + baseUrl, e);
		}
	}
	
	private TeamLeague[] callGetLeagueFallback(int countryId) {
    	 
		System.out.println("CIRCUIT BREAKER ENABLED!!! Foot ball base URL is down!!! fallback route enabled...");
 
        return new TeamLeague[]{new TeamLeague()};
    }
	
	@HystrixCommand(fallbackMethod = "callGetTeamStandingFallback",  
	commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") })
	public TeamStanding[] getTeamStanding(int leagueId) throws FootballServiceException {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("action", standingsAction);
		queryParams.put("league_id", String.valueOf(leagueId));
		try {
		UriComponentsBuilder builder = getUriComponentsBuilder(baseUrl, queryParams);
		return this.restTemplate
				.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()), TeamStanding[].class)
				.getBody();
		}
		catch(RestClientException e) {
			throw new FootballServiceException("Get Team standing failed over :" + baseUrl, e);
		}
	}
	
    private TeamStanding[] callGetTeamStandingFallback(int leagueId) {
    	 
		System.out.println("CIRCUIT BREAKER ENABLED!!! Foot ball base URL is down!!! fallback route enabled...");
 
        return new TeamStanding[]{new TeamStanding()};
    }

}
