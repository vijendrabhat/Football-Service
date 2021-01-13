package com.vij.football.controller;

import java.net.HttpURLConnection;

import javax.validation.Valid;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vij.football.exception.FootballServiceException;
import com.vij.football.io.TeamStandingRequest;
import com.vij.football.io.TeamStandingResponse;
import com.vij.football.response.FootballResponse;
import com.vij.football.response.FootballServiceResponseMessage;
import com.vij.football.response.HttpStatusMessages;
import com.vij.football.service.FootballLeagueServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class FootballLeagueServiceController {

	@Autowired
	private FootballLeagueServiceImpl teamStandingService;
	
	public static final String BASE_URL = "/api/service/v1/team";
	
	static final Logger LOGGER = LogManager.getLogger(FootballLeagueServiceController.class.getName());

	@ApiOperation(value = "Get Team standing API", produces = MediaType.APPLICATION_JSON_VALUE,
		      consumes = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
	@ApiResponses(value = {@ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST,
		      message = HttpStatusMessages.MESSAGE_400, response = FootballServiceResponseMessage.class), @ApiResponse(
		          code = HttpURLConnection.HTTP_UNAUTHORIZED, message = HttpStatusMessages.MESSAGE_401,
		          response = FootballResponse.class), @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND,
		              message = HttpStatusMessages.MESSAGE_404, response = FootballServiceResponseMessage.class), @ApiResponse(
		                  code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = HttpStatusMessages.MESSAGE_500,
		                  response = FootballResponse.class)})
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = BASE_URL + "/standing", headers = "Accept=application/json", method = RequestMethod.GET)
	public ResponseEntity<TeamStandingResponse> getStandings(@Valid TeamStandingRequest teamStandingRequest) throws FootballServiceException {
		LOGGER.log(Level.INFO, "Get teams standing for - {}", teamStandingRequest);
		// Validate the input request params if required
		return ResponseEntity.status(HttpStatus.OK).body(teamStandingService.getTeamStanding(teamStandingRequest));
	}

}
