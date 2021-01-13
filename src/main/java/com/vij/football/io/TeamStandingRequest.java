package com.vij.football.io;

import javax.validation.constraints.NotBlank;

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
public class TeamStandingRequest {

	@NotBlank
	@ApiModelProperty(value = "Country Name", name = "countryName")
	private String countryName;

	@NotBlank
	@ApiModelProperty(value = "League Name", name = "leagueName")
	private String leagueName;

	@NotBlank
	@ApiModelProperty(value = "Team Name", name = "teamName")
	private String teamName;

}
