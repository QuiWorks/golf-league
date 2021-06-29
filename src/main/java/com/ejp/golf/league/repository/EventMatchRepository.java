package com.ejp.golf.league.repository;

import com.ejp.golf.league.domain.EventMatch;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EventMatchRepository extends AbstractRepository{
    public EventMatch getMatch(EntityManager entityManager, int leagueId, int week, int flight, int teamNumber) {
        TypedQuery<EventMatch> query = entityManager.createQuery(
                "SELECT em FROM event_match em " +
                        "JOIN event e ON em.event.id = e.id " +
                        "JOIN team_match tm ON em.id = tm.matchId " +
                        "JOIN season s ON e.seasonId = s.id " +
                        "WHERE s.leagueId = :leagueId" +
                        " AND e.week = :week" +
                        " AND em.flightId = :flight" +
                        " AND tm.teamId = :team",
                EventMatch.class);
        query.setParameter("leagueId", leagueId);
        query.setParameter("week", week);
        query.setParameter("flight", flight);
        query.setParameter("team", getTeamId(flight, teamNumber));
        List<EventMatch> eventMatches = query.getResultList();
        return eventMatches.get(0);
    }

    public boolean isHome(EntityManager entityManager, int leagueId, int golferId, int matchId)
    {
        TypedQuery<Boolean> query = entityManager.createQuery(
                "SELECT tm.home FROM team_match tm " +
                        "JOIN team_member m ON m.teamId = tm.teamId " +
                        "JOIN team t ON t.id = tm.teamId " +
                        "WHERE t.leagueId = :leagueId" +
                        " AND tm.matchId = :matchId" +
                        " AND m.golferId = :golferId",
                Boolean.class);
        query.setParameter("leagueId", leagueId);
        query.setParameter("golferId", golferId);
        query.setParameter("matchId", matchId);
        return query.getResultList().stream().findFirst().orElse(false);
    }

    public int getTeamCount(EntityManager entityManager, int leagueId)
    {
        TypedQuery<Integer> query = entityManager.createQuery(
                "SELECT t.id FROM team t " +
                        "WHERE t.leagueId = :leagueId",
                Integer.class);
        query.setParameter("leagueId",leagueId);
        return query.getResultList().size();
    }
}
