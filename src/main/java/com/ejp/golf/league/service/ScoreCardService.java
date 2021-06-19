package com.ejp.golf.league.service;

import com.ejp.golf.league.domain.*;
import com.ejp.golf.league.model.RoundSummary;
import com.ejp.golf.league.model.ScoreCardSummary;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreCardService implements Serializable {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final EntityManagerFactory entityManagerFactory;
    private final League league;

    public ScoreCardService() {
        //TODO create producer methods for this stuff so it can be injected:
        league = new League();
        league.setId(1);
        league.setName("Territory Wednesday Mens League");
        entityManagerFactory = Persistence.createEntityManagerFactory("golf_league");
    }

    public List<ScoreCardSummary> getScoreCardSummary() {
        //TODO need repo classes
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Event> eventsQuery = entityManager.createQuery(
                "SELECT e FROM event e " +
                        " JOIN season s on s.id = e.seasonId" +
                        " WHERE s.leagueId = :leagueId",
                Event.class);
        eventsQuery.setParameter("leagueId", league.getId());
        List<Event> events = eventsQuery.getResultList();
        entityManager.close();
        List<ScoreCardSummary> collect = events.stream()
                .map(Event::getDay)
                .map(day -> Date.from(day.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .map(this::getScoreCardSummary)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return collect;
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
        TypedQuery<Round> query = entityManager.createQuery(
                "SELECT r FROM round r " +
                        "JOIN event_match em ON r.matchId = em.id " +
                        "JOIN event e ON em.eventId = e.id " +
                        "JOIN season s ON e.seasonId = s.id " +
                        "WHERE s.leagueId = :leagueId" +
                        " AND em.flightId = :flightId" +
                        " AND e.day = :matchDate",
                Round.class);
        query.setParameter("leagueId", league.getId());
        query.setParameter("flightId", flight);
        query.setParameter("matchDate", matchDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        List<Round> rounds = query.getResultList();
        entityManager.close();
        return rounds.stream()
                .map(RoundSummary::new)
                .collect(Collectors.groupingBy(RoundSummary::getMatchId))
                .values().stream()
                .map(ScoreCardSummary::new)
                .collect(Collectors.toList());
    }
}
