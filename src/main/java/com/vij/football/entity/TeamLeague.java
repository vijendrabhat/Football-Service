package com.vij.football.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@ApiModel(description = "Teams League details")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Getter
@Setter
@ToString(includeFieldNames = true)
public class TeamLeague {

	private static final long serialVersionUID = 1L;

	@NotNull
	@ApiModelProperty(value = "Country Id", name = "country_id")
	@JsonAlias("country_id")
	private int countryId;

	@NotNull
	@ApiModelProperty(value = "Country Id", name = "country_name")
	@JsonAlias("country_name")
	private String countryName;

	@NotNull
	@ApiModelProperty(value = "League Id", name = "league_id")
	@JsonAlias("league_id")
	private int leagueId;
	
	@NotNull
	@ApiModelProperty(value = "League Name", name = "league_name")
	@JsonAlias("league_name")
	private String leagueName;

}
