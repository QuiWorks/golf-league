package com.ejp.golf.league.service;

import com.ejp.golf.league.component.GlGolfer;
import com.ejp.golf.league.component.GlReport;
import com.ejp.golf.league.component.GlRound;
import com.ejp.golf.league.component.GlScore;
import com.ejp.golf.league.domain.*;
import com.ejp.golf.league.model.RoundSummary;
import com.ejp.golf.league.model.ScoreCardSummary;
import com.vaadin.flow.component.html.Div;
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
    //TODO remove hard coding.
    public final League league = new League().build(l -> l
            .set(l::id, 1)
            .set(l::name, "Territory Wednesday Mens League"));

    public ScoreCardService() {
        //TODO create producer methods for this stuff so it can be injected:
        entityManagerFactory = Persistence.createEntityManagerFactory("golf_league");
    }

    public GlReport getScoreCardSummary() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        GlReport glReport = generateReport(getScoreCardSummaries(getRounds(entityManager)));
        entityManager.close();
        return glReport;
    }

    public GlReport getScoreCardSummary(int week) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        GlReport glReport = generateReport(getScoreCardSummaries(getRounds(entityManager, week)));
        entityManager.close();
        return glReport;
    }

    public GlReport getScoreCardSummary(int week, int flight) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        GlReport glReport = generateReport(getScoreCardSummaries(getRounds(entityManager, week, flight)));
        entityManager.close();
        return glReport;
    }

    public GlReport getScoreCardSummary(int week, int flight, int slot) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        GlReport glReport = generateReport(getScoreCardSummaries(getRounds(entityManager, week, flight, slot)));
        entityManager.close();
        return glReport;
    }

    private GlReport generateReport(List<ScoreCardSummary> scoreCardSummaries) {
        final GlReport glReport = new GlReport();
        scoreCardSummaries.stream().findAny().ifPresent(summary -> {
            glReport.setWeek(summary.getWeek());
            glReport.setFlight(summary.getFlight());
            glReport.setSlott(summary.getSlot());
        });
        scoreCardSummaries.forEach(scoreCardSummary -> {
            Div matchContainer = new Div();
            matchContainer.getElement().getStyle().set("border-bottom", "1px solid green");
            scoreCardSummary.getAll().stream()
                    .filter(roundSummary -> !roundSummary.getGolfer().getLastName().equals("Dummy"))
                    .forEach(roundSummary -> {
                        GlGolfer glGolfer = new GlGolfer();
                        glGolfer.setHandicap(roundSummary.getHandicap());
                        glGolfer.setName(roundSummary.getGolfer().fullName());
                        glGolfer.setSub(false);
                        roundSummary.getGolfer().teamForLeague(league).map(Team::getId).ifPresent(glGolfer::setTeam);
                        glGolfer.setInline(true);

                        GlRound glRound = new GlRound();
                        glRound.setGrossScore(roundSummary.getGrossScore());
                        glRound.setNetScore(roundSummary.getNetScore());
                        glRound.setHandicap(roundSummary.getHandicap());
                        glRound.setNetPoints(roundSummary.getNetPoints());
                        glRound.setMatchPoints(roundSummary.getMatchPoints());
                        glRound.setTeamNet((int) roundSummary.getTeamNet());

                        roundSummary.getGrossScores().stream()
                                .map(grossScore -> toComponent(grossScore, "grossScore"))
                                .forEach(score -> glRound.getElement().appendChild(score.getElement()));
                        roundSummary.getNetScores().stream()
                                .map(netScore -> toComponent(netScore, "netScore"))
                                .forEach(score -> glRound.getElement().appendChild(score.getElement()));

                        glGolfer.getElement().appendChild(glRound.getElement());
                        matchContainer.getElement().appendChild(glGolfer.getElement());
                    });
            glReport.getElement().appendChild(matchContainer.getElement());
        });
        return glReport;
    }

    private GlScore toComponent(Score score, String slot) {
        int num = score.getHole().getHoleNumber() > 9 ? 18 - score.getHole().getHoleNumber() : score.getHole().getHoleNumber();
        final GlScore glScore = new GlScore();
        glScore.setNumber(score.getHole().getHoleNumber());
        glScore.setPar(score.getHole().getPar());
        glScore.setHandicap(score.getRound().getHandicap());
        glScore.setScore(score.getScore());
        glScore.getElement().setAttribute("slot", slot + num);
        return glScore;
    }

    private List<Round> getRounds(EntityManager entityManager) {
        //TODO need repo classes
        TypedQuery<Round> query = entityManager.createQuery(
                "SELECT r FROM round r " +
                        "JOIN event_match em ON r.matchId = em.id " +
                        "JOIN event e ON em.eventId = e.id " +
                        "JOIN season s ON e.seasonId = s.id " +
                        "WHERE s.leagueId = :leagueId",
                Round.class);
        query.setParameter("leagueId", league.getId());
        return query.getResultList();
    }

    private List<Round> getRounds(EntityManager entityManager, int week) {
        //TODO need repo classes
        TypedQuery<Round> query = entityManager.createQuery(
                "SELECT r FROM round r " +
                        "JOIN event_match em ON r.matchId = em.id " +
                        "JOIN event e ON em.eventId = e.id " +
                        "JOIN season s ON e.seasonId = s.id " +
                        "WHERE s.leagueId = :leagueId" +
                        " AND e.week = :week",
                Round.class);
        query.setParameter("leagueId", league.getId());
        query.setParameter("week", week);
        return query.getResultList();
    }

    private List<Round> getRounds(EntityManager entityManager, int week, int flight) {
        //TODO need repo classes
        TypedQuery<Round> query = entityManager.createQuery(
                "SELECT r FROM round r " +
                        "JOIN event_match em ON r.matchId = em.id " +
                        "JOIN event e ON em.eventId = e.id " +
                        "JOIN season s ON e.seasonId = s.id " +
                        "WHERE s.leagueId = :leagueId" +
                        " AND em.flightId = :flightId" +
                        " AND e.week = :week",
                Round.class);
        query.setParameter("leagueId", league.getId());
        query.setParameter("flightId", flight);
        query.setParameter("week", week);
        return query.getResultList();
    }

    private List<Round> getRounds(EntityManager entityManager, int week, int flight, int slot) {
        //TODO need repo classes
        TypedQuery<Round> query = entityManager.createQuery(
                "SELECT r FROM round r " +
                        "JOIN event_match em ON r.matchId = em.id " +
                        "JOIN event e ON em.eventId = e.id " +
                        "JOIN season s ON e.seasonId = s.id " +
                        "WHERE s.leagueId = :leagueId" +
                        " AND em.flightId = :flightId" +
                        " AND e.week = :week" +
                        " AND em.slot = :slot",
                Round.class);
        query.setParameter("leagueId", league.getId());
        query.setParameter("flightId", flight);
        query.setParameter("week", week);
        query.setParameter("slot", slot);
        List<Round> resultList = query.getResultList();
        return resultList;
    }

    private List<ScoreCardSummary> getScoreCardSummaries(List<Round> rounds) {
        return rounds.stream()
                .map(RoundSummary::new)
                .collect(Collectors.groupingBy(RoundSummary::getMatchId))
                .values().stream()
                .map(ScoreCardSummary::new)
                .collect(Collectors.toList());
    }


}
