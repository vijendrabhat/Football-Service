FROM maven:latest as build
ADD . / football/
WORKDIR /football
RUN mvn clean install -U -DskipTests

FROM openjdk:8-jre-alpine
RUN mkdir football
WORKDIR /football/
COPY --from=build /football/target/football-league-service-0.0.1-SNAPSHOT.jar .

ENV PORT='8085'
EXPOSE ${PORT}

ENTRYPOINT /bin/sh -c "java -jar /football/football-league-service-0.0.1-SNAPSHOT.jar"