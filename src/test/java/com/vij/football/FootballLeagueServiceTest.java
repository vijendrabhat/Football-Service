package com.vij.football;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.vij.football.common.FootballLeagueCommon;
import com.vij.football.entity.TeamCountry;
import com.vij.football.entity.TeamLeague;
import com.vij.football.entity.TeamStanding;
import com.vij.football.exception.FootballServiceException;
import com.vij.football.io.TeamStandingRequest;
import com.vij.football.io.TeamStandingResponse;
import com.vij.football.service.FootballLeagueService;
import com.vij.football.service.FootballLeagueServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class FootballLeagueServiceTest {

	@InjectMocks
	private FootballLeagueService service = new FootballLeagueServiceImpl();
	
	@Mock
	private RestTemplate restTemplate;
	
	@Mock
	private FootballLeagueCommon common;
	
	@InjectMocks
	private FootballLeagueCommon footballLeagueCommon;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void getStandings() throws FootballServiceException {
		TeamStanding teamStanding = new TeamStanding(41,"England",149,"Championship",2641,"Norwich",1);
		TeamStandingResponse response = footballLeagueCommon.buildResponse(teamStanding);
		Mockito.lenient() 
          .when(restTemplate.getForEntity(
            "http://localhost:8085/api/service/v1/team/standing?teamName=Norwich&countryName=England&leagueName=Championship", 
            TeamStandingResponse.class))
          .thenReturn(new ResponseEntity<TeamStandingResponse>(response, HttpStatus.OK));
        
        TeamStandingRequest teamStandingRequest = new TeamStandingRequest("England","Championship","Norwich");
        
        List<TeamCountry> countriesList = new ArrayList<TeamCountry>();
        TeamCountry teamCountry = new TeamCountry(41, "England");
        countriesList.add(teamCountry);
        Mockito.when(common.getCountries()).thenReturn(countriesList);
        
        Mockito.when(common.getCountryByName(teamStandingRequest, countriesList)).thenReturn(teamCountry);
        
        List<TeamLeague> leagueList = new ArrayList<TeamLeague>();
        TeamLeague teamLeague = new TeamLeague(41,"England",149,"Championship");
        leagueList.add(teamLeague);
        Mockito.when(common.getLeagues(Mockito.anyInt())).thenReturn(leagueList);
        
        Mockito.when(common.getLeaguesByName(teamStandingRequest, leagueList)).thenReturn(teamLeague);
        
        List<TeamStanding> teamStandingList = new ArrayList<TeamStanding>();
        teamStandingList.add(teamStanding);
        Mockito.when(common.getTeamStanding(Mockito.anyInt())).thenReturn(teamStandingList);
        
        Mockito.when(common.getStandingForTeam(teamStandingRequest, teamStandingList)).thenReturn(teamStanding);
        
        TeamStandingResponse response1 = new TeamStandingResponse();
        response1.setCountry("(" + teamStanding.getCountryId() + ") - " + teamStanding.getCountryName());
        response1.setLeague("(" + teamStanding.getLeagueId() + ") - " + teamStanding.getLeagueName());
        response1.setTeam("(" + teamStanding.getTeamId() + ") - " + teamStanding.getTeamName());
        response1.setOverallLeaguePosition(teamStanding.getOverallLeaguePosition());
		
		Mockito.when(common.buildResponse(Mockito.any(TeamStanding.class))).thenReturn(response1);
        
		TeamStandingResponse response2 = service.getTeamStanding(teamStandingRequest);
		
		Assert.assertEquals(response, response2);
	}

}
