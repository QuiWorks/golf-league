package com.ejp.golf.league.service;

import com.ejp.golf.league.component.*;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreCardService implements Serializable {

    private final EntityManagerFactory entityManagerFactory;
    //TODO remove hard coding.
    public final League league = new League().build(l -> l
            .set(l::id, 1)
            .set(l::name, "Territory Wednesday Mens League"));

    public ScoreCardService() {
        //TODO create producer methods for this stuff so it can be injected:
        entityManagerFactory = Persistence.createEntityManagerFactory("golf_league");
    }

    public int getCurrentWeek() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Integer> query = entityManager.createQuery(
                "SELECT e.week FROM event e " +
                        "JOIN season s ON s.id = e.seasonId " +
                        "JOIN round r ON r.week = e.week " +
                        "WHERE s.leagueId = :leagueId" +
                        " AND r is null",
                Integer.class);
        query.setParameter("leagueId", league.getId());
        return query.getResultList().stream().sorted().findFirst().orElse(1);
    }

    public GlCard getScoreCard(int week, int flight, int teamNumber) {
        int teamId = 0;
        if (String.valueOf(teamId).length() == 1) {
            teamId = Integer.parseInt(flight + "0" + teamNumber);
        } else {
            teamId = Integer.parseInt(String.valueOf(flight) + teamNumber);
        }
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<EventMatch> query = entityManager.createQuery(
                "SELECT em FROM event_match em " +
                        "JOIN event e ON em.eventId = e.id " +
                        "JOIN team_match tm ON em.id = tm.matchId " +
                        "JOIN season s ON e.seasonId = s.id " +
                        "WHERE s.leagueId = :leagueId" +
                        " AND e.week = :week" +
                        " AND em.flightId = :flight" +
                        " AND tm.teamId = :team",
                EventMatch.class);
        query.setParameter("leagueId", league.getId());
        query.setParameter("week", week);
        query.setParameter("flight", flight);
        query.setParameter("team", teamId);
        List<EventMatch> eventMatches = query.getResultList();
        EventMatch match = eventMatches.get(0);

        TypedQuery<Hole> holeQuery = entityManager.createQuery(
                "SELECT h FROM hole h " +
                        "WHERE h.courseId = :courseId" +
                        " AND h.nineName = :nine",
                Hole.class);
        holeQuery.setParameter("courseId", match.getCourseId());
        holeQuery.setParameter("nine", match.getNine());
        List<Hole> holeResultList = holeQuery.getResultList();
        List<Team> teamList = match.getTeamList();

        List<GlGolfer> golferList = new ArrayList<>();
        teamList.forEach(team -> team.getGolferList().forEach(golfer -> {
            GlGolfer glGolfer = new GlGolfer();
            glGolfer.setName(golfer.fullName());
            glGolfer.setTeam(team.getTeamId());
            glGolfer.setHandicap(entityManager.createQuery(
                    "SELECT gh.handicap FROM golfer_handicap gh " +
                            "WHERE gh.golferId = " + golfer.getId(),
                    Integer.class).getResultList().get(0));
            GlRound glRound = new GlRound();
            holeResultList.forEach(hole -> {
                GlHole glHole = new GlHole(hole.getHoleNumber(), hole.getPar(), hole.getYardage(), hole.getHandicap());
                glHole.getElement().setAttribute("slot", "hole" + (hole.getHoleNumber() > 9 ? hole.getHoleNumber() - 9 : hole.getHoleNumber()));
                glRound.getElement().appendChild(glHole.getElement());
            });
            glGolfer.getElement().appendChild(glRound.getElement());
            golferList.add(glGolfer);
        }));

        GlCard glCard = new GlCard();
        glCard.setTeam(teamNumber);
        glCard.setFlight(flight);
        glCard.setWeek(week);
        glCard.setNine(match.getNine());

        golferList.forEach(golfer -> glCard.getElement().appendChild(golfer.getElement()));


        entityManager.close();
        return glCard;
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
                        roundSummary.getGolfer().teamForLeague(league).map(Team::getTeamId).ifPresent(glGolfer::setTeam);
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
