package com.vij.football.service;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vij.football.common.FootballLeagueCommon;
import com.vij.football.entity.TeamCountry;
import com.vij.football.entity.TeamLeague;
import com.vij.football.entity.TeamStanding;
import com.vij.football.exception.FootballServiceException;
import com.vij.football.exception.RecordNotFoundException;
import com.vij.football.io.TeamStandingRequest;
import com.vij.football.io.TeamStandingResponse;

@Service
public class FootballLeagueServiceImpl implements FootballLeagueService {

	@Autowired
	FootballLeagueCommon footballLeagueCommon;

	static final Logger LOGGER = LogManager.getLogger(FootballLeagueServiceImpl.class.getName());

	public TeamStandingResponse getTeamStanding(TeamStandingRequest teamStandingRequest) throws FootballServiceException {
		LOGGER.log(Level.DEBUG, "Get teams standing for - {}", teamStandingRequest);
		List<TeamCountry> countries = footballLeagueCommon.getCountries();

		TeamCountry country = footballLeagueCommon.getCountryByName(teamStandingRequest, countries);
		if (country == null) {
			LOGGER.log(Level.ERROR, "Cant find the country for {}", teamStandingRequest);
			throw new RecordNotFoundException("Cant find the country for " + teamStandingRequest);
		}

		List<TeamLeague> leaguesList = footballLeagueCommon.getLeagues(country.getId());
		TeamLeague leagues = footballLeagueCommon.getLeaguesByName(teamStandingRequest, leaguesList);
		if (leagues == null) {
			LOGGER.log(Level.ERROR, "Cant find the league for country id : {}", country.getId());
			throw new RecordNotFoundException("Cant find the league for country id : " + country.getId());
		}
		List<TeamStanding> teamStandings = footballLeagueCommon.getTeamStanding(leagues.getLeagueId());
		LOGGER.log(Level.DEBUG, "Get teams standing for - {}", teamStandingRequest);

		TeamStanding teamStanding = footballLeagueCommon.getStandingForTeam(teamStandingRequest, teamStandings);
		if (teamStanding == null) {
			LOGGER.log(Level.ERROR, "Cant find the team standing for league id : {}", leagues.getLeagueId());
			throw new RecordNotFoundException("Cant find the team standing for league id : " + leagues.getLeagueId());
		}
		teamStanding.setCountryId(country.getId());
		TeamStandingResponse response  = footballLeagueCommon.buildResponse(teamStanding);
		LOGGER.log(Level.INFO,"Team standing response : " + response);
		return response;
	}

}
