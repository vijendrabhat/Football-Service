package com.vij.football.service;

import com.vij.football.exception.FootballServiceException;
import com.vij.football.io.TeamStandingRequest;
import com.vij.football.io.TeamStandingResponse;

public interface FootballLeagueService {
	
	public TeamStandingResponse getTeamStanding(TeamStandingRequest teamStandingRequest) throws FootballServiceException;

}
