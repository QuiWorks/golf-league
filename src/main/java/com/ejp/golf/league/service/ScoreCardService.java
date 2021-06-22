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
        GlReport glReport = generateReport(getScoreCardSummary(entityManager));
        entityManager.close();
        return glReport;
    }

    public GlReport getScoreCardSummary(Date date) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        GlReport glReport = generateReport(getScoreCardSummary(entityManager, date));
        entityManager.close();
        return glReport;
    }

    public GlReport getScoreCardSummary(Date date, int flight) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        GlReport glReport = generateReport(getScoreCardSummary(entityManager, date, flight));
        entityManager.close();
        return glReport;
    }

    private GlReport generateReport(List<ScoreCardSummary> scoreCardSummaries) {
        final GlReport glReport = new GlReport();
        scoreCardSummaries.stream().findAny().ifPresent(summary -> {
            glReport.setFlight(summary.getFlight());
            glReport.setSlot(summary.getSlot());
            glReport.setNine(summary.getNine());
        });
        //TODO use WEEKS.
//        glCard.setDate(new Date());

        scoreCardSummaries.forEach(scoreCardSummary -> {
            Div matchContainer = new Div();
            matchContainer.getElement().getStyle().set("border-bottom", "1px solid black");
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

    List<ScoreCardSummary> getScoreCardSummary(EntityManager entityManager) {
        //TODO need repo classes
        TypedQuery<Event> eventsQuery = entityManager.createQuery(
                "SELECT e FROM event e " +
                        " JOIN season s on s.id = e.seasonId" +
                        " WHERE s.leagueId = :leagueId",
                Event.class);
        eventsQuery.setParameter("leagueId", league.getId());
        List<Event> events = eventsQuery.getResultList();
        List<ScoreCardSummary> collect = events.stream()
                .map(Event::getDay)
                .map(day -> Date.from(day.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .map(date -> getScoreCardSummary(entityManager, date))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return collect;
    }

    List<ScoreCardSummary> getScoreCardSummary(EntityManager entityManager, Date matchDate) {
        //TODO need repo classes
        List<Flight> flights = entityManager.createQuery(
                        "SELECT f FROM flight f WHERE f.leagueId = " + league.getId(),
                        Flight.class)
                .getResultList();
        return flights.stream()
                .map(flight -> getScoreCardSummary(entityManager, matchDate, flight.getId()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    List<ScoreCardSummary> getScoreCardSummary(EntityManager entityManager, Date matchDate, int flight) {
        //TODO need repo classes
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
        return rounds.stream()
                .map(RoundSummary::new)
                .collect(Collectors.groupingBy(RoundSummary::getMatchId))
                .values().stream()
                .map(ScoreCardSummary::new)
                .collect(Collectors.toList());
    }
}
