package com.ejp.golf.league.service;

import com.ejp.golf.league.domain.Flight;
import com.ejp.golf.league.domain.League;
import com.ejp.golf.league.domain.Score;
import com.ejp.golf.league.domain.Team;
import com.ejp.golf.league.model.RoundSummary;
import com.ejp.golf.league.model.ScoreCardSummary;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreCardService implements Serializable {

    private final EntityManagerFactory entityManagerFactory;
    private final League league;

    public ScoreCardService() {
        //TODO create producer methods for this stuff so it can be injected:
        league = new League();
        league.setId(1);
        league.setName("Territory Wednesday Mens League");
        entityManagerFactory = Persistence.createEntityManagerFactory("golf_league");
    }

    public List<ScoreCardSummary> getScoreCardSummary(Date matchDate) {
        //TODO need repo classes
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Flight> leagues = entityManager.createQuery(
                        "SELECT f FROM flight f WHERE f.leagueId = " + league.getId(),
                        Flight.class)
                .getResultList();
        entityManager.close();
        return leagues.stream()
                .map(flight -> getScoreCardSummary(matchDate, flight.getId()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<ScoreCardSummary> getScoreCardSummary(Date matchDate, int flight) {
        //TODO need repo classes
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Team> homeTeams = entityManager.createQuery(
                        "SELECT t FROM team t " +
                                "JOIN team_match tm ON tm.teamId = t.id " +
                                "JOIN event_match em ON tm.matchId = em.id " +
                                "JOIN event e ON em.eventId = e.id " +
                                "WHERE t.leagueId = " + league.getId() +
                                " AND em.flightId = " + flight +
                                " AND tm.home = true" +
                                " AND e.day = " + matchDate,
                        Team.class)
                .getResultList();
        entityManager.close();
        return homeTeams.stream()
                .map(team -> getScoreCardSummary(matchDate, flight, team.getId()))
                .collect(Collectors.toList());
    }

    public ScoreCardSummary getScoreCardSummary(Date matchDate, int flight, int homeTeamNumber) {
        //TODO need repo classes
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // get each of each golfer in the match.

//        List<Score> homeTeams = entityManager.createQuery(
//                        "SELECT s FROM score s " +
//                                "JOIN round r ON r.id = s.roundId " +
//                                "JOIN event_match em ON r.matchId = em.id " +
//                                "JOIN team_match tm ON tm.matchId = em.id " +
//                                "JOIN event e ON em.eventId = e.id " +
//                                "WHERE t.leagueId = " + league.getId() +
//                                " AND em.flightId = " + flight +
//                                " AND tm.home = true" +
//                                " AND e.day = " + matchDate,
//                        Score.class)
//                .getResultList();
        entityManager.close();
        return null;
    }

    private RoundSummary getRoundSummary(Date matchDate, int flight, int golferId) {
        return null;
    }

}
