# Football-Service

Develop, Test and Deploy a microservice to find standings of a team playing league football match using country name, league name and team name. 

Sample Api - 

http://localhost:8085/api/service/v1/team/standing?countryName=England&leagueName=Championship&teamName=Norwich

O/p - 

{
"country": "(41) - England",
"league": "(149) - Championship",
"team": "(2641) - Norwich",
"overallLeaguePosition": 1
}

username/password - admin/pass123

Swagger documentation available at - http://localhost:8085/swagger-ui.html#/

Hystrics dashboard available at - http://localhost:8085/actuator/hystrix.stream
