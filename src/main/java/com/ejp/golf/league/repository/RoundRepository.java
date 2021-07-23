package com.ejp.golf.league.repository;

import com.ejp.golf.league.domain.Round;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class RoundRepository extends AbstractRepository {

    public List<Round> getRounds(EntityManager entityManager, int leagueId) {
        TypedQuery<Round> query = entityManager.createQuery(
                "SELECT r FROM round r " +
                        "JOIN event_match em ON r.eventMatch.id = em.id " +
                        "JOIN event e ON em.event.id = e.id " +
                        "JOIN season s ON e.seasonId = s.id " +
                        "WHERE s.leagueId = :leagueId",
                Round.class);
        query.setParameter("leagueId", leagueId);
        return query.getResultList();
    }

    public List<Round> getRounds(EntityManager entityManager,int leagueId, int week) {
        TypedQuery<Round> query = entityManager.createQuery(
                "SELECT r FROM round r " +
                        "JOIN event_match em ON r.eventMatch.id = em.id " +
                        "JOIN event e ON em.event.id = e.id " +
                        "JOIN season s ON e.seasonId = s.id " +
                        "WHERE s.leagueId = :leagueId" +
                        " AND e.week = :week",
                Round.class);
        query.setParameter("leagueId", leagueId);
        query.setParameter("week", week);
        return query.getResultList();
    }

    public List<Round> getRounds(EntityManager entityManager,int leagueId, int week, int flight) {
        TypedQuery<Round> query = entityManager.createQuery(
                "SELECT r FROM round r " +
                        "JOIN event_match em ON r.eventMatch.id = em.id " +
                        "JOIN event e ON em.event.id = e.id " +
                        "JOIN season s ON e.seasonId = s.id " +
                        "WHERE s.leagueId = :leagueId" +
                        " AND em.flightId = :flightId" +
                        " AND e.week = :week",
                Round.class);
        query.setParameter("leagueId", leagueId);
        query.setParameter("flightId", flight);
        query.setParameter("week", week);
        return query.getResultList();
    }

    public List<Round> getRounds(EntityManager entityManager,int leagueId, int week, int flight, int team) {
        int teamId = getTeamId(flight, team);
        TypedQuery<Round> query = entityManager.createQuery(
                "SELECT r FROM round r " +
                        "JOIN event_match em ON r.eventMatch.id = em.id " +
                        "JOIN team_match tm ON em.id = tm.matchId " +
                        "JOIN event e ON em.event.id = e.id " +
                        "JOIN season s ON e.seasonId = s.id " +
                        "WHERE s.leagueId = :leagueId" +
                        " AND em.flightId = :flightId" +
                        " AND e.week = :week" +
                        " AND tm.teamId = :teamId" +
                        " AND r.datePlayed = (select max(rr.datePlayed) from round as rr where rr.eventMatch.id = r.eventMatch.id and rr.golfer.id = r.golfer.id)",
                Round.class);
        query.setParameter("leagueId", leagueId);
        query.setParameter("flightId", flight);
        query.setParameter("week", week);
        query.setParameter("teamId", teamId);
        List<Round> resultList = query.getResultList();
        while(resultList.size() > 4)
        {
            resultList.stream()
                    .filter(round -> round.getGolfer().getSubstitute())
                    .findAny()
                    .ifPresent(resultList::remove);
        }
        return resultList;
    }
}
