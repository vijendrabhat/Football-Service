package com.vij.football.io;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.vij.football.entity.TeamStanding;

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
@ApiModel(description = "Teams Standing response details")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Getter
@Setter
@ToString(includeFieldNames = true)
public class TeamStandingResponse {

	@ApiModelProperty(value = "Country ID & Name", name = "country")
	@JsonAlias("Country ID & Name")
	private String country;

	@ApiModelProperty(value = "Country Id", name = "league")
	@JsonAlias("League ID & Name")
	private String league;

	@ApiModelProperty(value = "Country Id", name = "team")
	@JsonAlias("Team ID & Name")
	private String team;

	@ApiModelProperty(value = "Country Id", name = "overallLeaguePosition")
	@JsonAlias("Overall League Position")
	private int overallLeaguePosition;

}
