package com.ejp.golf.league.service;

import com.ejp.golf.league.domain.*;
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
//        List<GolferMatch> golferMatches = entityManager.createQuery(
//                        "SELECT gm FROM golfer_match gm " +
//                                "JOIN event_match em ON gm.matchId = em.id " +
//                                "JOIN event e ON em.eventId = e.id " +
//                                "JOIN season s ON e.seasonId = s.id " +
//                                "WHERE s.leagueId = " + league.getId() +
//                                " AND em.flightId = " + flight +
//                                " AND e.day = " + matchDate,
//                        GolferMatch.class)
//                .getResultList();
        entityManager.close();

//        List<RoundSummary> roundSummaries = golferMatches.stream()
//                .map(golferMatch -> getRoundSummary(golferMatch.getMatchId(), flight, golferMatch.getGolferId()))
//                .collect(Collectors.toList());

//        return roundSummaries.stream()
//                .collect(Collectors.groupingBy(RoundSummary::getMatchId))
//                .values().stream()
//                .map(ScoreCardSummary::new)
//                .collect(Collectors.toList());
        return null;
    }

    private RoundSummary getRoundSummary(int matchId, int flight, int golferId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.find(TeamMatch, new TeamMatchPK(matchId, ))
        return null;
    }

}
