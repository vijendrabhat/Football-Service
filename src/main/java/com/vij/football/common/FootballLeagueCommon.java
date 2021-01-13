package com.vij.football.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vij.football.client.FootballLeagueClientHandler;
import com.vij.football.entity.TeamCountry;
import com.vij.football.entity.TeamLeague;
import com.vij.football.entity.TeamStanding;
import com.vij.football.exception.FootballServiceException;
import com.vij.football.io.TeamStandingRequest;
import com.vij.football.io.TeamStandingResponse;

@Component
public class FootballLeagueCommon {
	
	@Autowired
	private FootballLeagueClientHandler footballClientHandler;
	
	public List<TeamCountry> getCountries() throws FootballServiceException {
		return new ArrayList<>(Arrays.asList(footballClientHandler.getCountries()));
	}

	public List<TeamLeague> getLeagues(int countryId) throws FootballServiceException {
		return new ArrayList<>(Arrays.asList(footballClientHandler.getLeagues(countryId)));
	}

	public List<TeamStanding> getTeamStanding(int leagueId) throws FootballServiceException {
		return new ArrayList<>(Arrays.asList(footballClientHandler.getTeamStanding(leagueId)));
	}
	
	public TeamCountry getCountryByName(TeamStandingRequest teamStandingRequest, List<TeamCountry> countries) {
		return countries.stream().filter(c -> teamStandingRequest.getCountryName().equalsIgnoreCase(c.getCountryName()))
				.findFirst().orElse(null);
	}

	public TeamLeague getLeaguesByName(TeamStandingRequest teamStandingRequest, List<TeamLeague> leaguesList) {
		return leaguesList.stream().filter(l -> teamStandingRequest.getLeagueName().equalsIgnoreCase(l.getLeagueName()))
				.findFirst().orElse(null);
	}

	public TeamStanding getStandingForTeam(TeamStandingRequest teamStandingRequest,
			List<TeamStanding> teamStandings) {
		return teamStandings.stream().filter(t -> teamStandingRequest.getTeamName().equalsIgnoreCase(t.getTeamName()))
				.findFirst().orElse(null);
	}

	public TeamStandingResponse buildResponse(TeamStanding teamStanding) {
		TeamStandingResponse response = new TeamStandingResponse();
		if (Objects.nonNull(teamStanding)) {
			response.setCountry("(" + teamStanding.getCountryId() + ") - " + teamStanding.getCountryName());
			response.setLeague("(" + teamStanding.getLeagueId() + ") - " + teamStanding.getLeagueName());
			response.setTeam("(" + teamStanding.getTeamId() + ") - " + teamStanding.getTeamName());
			response.setOverallLeaguePosition(teamStanding.getOverallLeaguePosition());
		}
		return response;
	}
}
