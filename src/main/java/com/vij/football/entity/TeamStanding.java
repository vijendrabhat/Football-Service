package com.vij.football.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@ApiModel(description = "Teams Request details")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Getter
@Setter
@ToString(includeFieldNames = true)
public class TeamStanding {
	
	private static final long serialVersionUID = 1L;

	private int countryId;

	@NotBlank
	@ApiModelProperty(value = "Country Id", name = "country_name")
	@JsonAlias("country_name")
	private String countryName;

	@NotNull
	@ApiModelProperty(value = "League Id", name = "league_id")
	@JsonAlias("league_id")
	private int leagueId;
	
	@NotBlank
	@ApiModelProperty(value = "League Name", name = "league_name")
	@JsonAlias("league_name")
	private String leagueName;
	
	@NotNull
	@ApiModelProperty(value = "Team Id", name = "team_id")
	@JsonAlias("team_id")
	private int teamId;

	@NotBlank
	@ApiModelProperty(value = "Team Name", name = "team_name")
	@JsonAlias("team_name")
	private String teamName;

	@NotNull
	@ApiModelProperty(value = "Overall league position", name = "overall_league_position")
	@JsonAlias("overall_league_position")
	private int overallLeaguePosition;

}
