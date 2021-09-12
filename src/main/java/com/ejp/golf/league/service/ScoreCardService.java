package com.ejp.golf.league.service;

import com.ejp.golf.league.component.*;
import com.ejp.golf.league.domain.*;
import com.ejp.golf.league.model.RoundSummary;
import com.ejp.golf.league.model.ScoreCardSummary;
import com.ejp.golf.league.repository.EventMatchRepository;
import com.ejp.golf.league.repository.GolferHandicapRepository;
import com.ejp.golf.league.repository.HoleRepository;
import com.ejp.golf.league.repository.RoundRepository;
import com.vaadin.flow.component.html.Div;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScoreCardService implements Serializable {

    private final EntityManagerFactory entityManagerFactory;
    private final RoundRepository roundRepository;
    private final EventMatchRepository eventMatchRepository;
    private final HoleRepository holeRepository;
    private final GolferHandicapRepository golferHandicapRepository;

    public ScoreCardService(EntityManagerFactory entityManagerFactory,
                            RoundRepository roundRepository,
                            EventMatchRepository eventMatchRepository,
                            HoleRepository holeRepository,
                            GolferHandicapRepository golferHandicapRepository) {

        this.entityManagerFactory = entityManagerFactory;
        this.roundRepository = roundRepository;
        this.eventMatchRepository = eventMatchRepository;
        this.holeRepository = holeRepository;
        this.golferHandicapRepository = golferHandicapRepository;
    }

    public Round saveRound(Round round) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(round);
        entityManager.getTransaction().commit();
        return round;
    }

    public Score saveScore(Score score) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(score);
        entityManager.getTransaction().commit();
        return score;
    }

    public boolean isHome(League league, int golferId, int matchId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        boolean home = eventMatchRepository.isHome(entityManager, league.getId(), golferId, matchId);
        entityManager.close();
        return home;
    }

    public int getHandicap(int golferId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Integer handicap = golferHandicapRepository.getHandicap(entityManager, golferId);
        entityManager.close();
        return handicap;
    }

    public GlCard getScoreCard(League league, int week, int flight, int teamNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EventMatch match = eventMatchRepository.getMatch(entityManager, league.getId(), week, flight, teamNumber);
        List<Hole> holes = holeRepository.getHoles(entityManager, match.getCourseId(), match.getNine());

        List<Team> teamList = match.getTeamList();
        List<GlGolfer> golferList = new ArrayList<>();
        teamList.forEach(team -> team.getGolferList().forEach(golfer -> {
            GlGolfer glGolfer = new GlGolfer();
            glGolfer.setGolfer(golfer.getId());
            glGolfer.setName(golfer.fullName());
            glGolfer.setTeam(team.getTeamId());
            glGolfer.setHandicap(golferHandicapRepository.getHandicap(entityManager, golfer.getId()));
            GlRound glRound = new GlRound();
            holes.forEach(hole -> {
                GlHole glHole = new GlHole(hole.getHoleNumber(), hole.getPar(), hole.getYardage(), hole.getHandicap(), holeRepository.getHoleScore(entityManager, hole.getId(), golfer.getId(), week));
                glHole.getElement().setAttribute("slot", "hole" + (hole.getHoleNumber() > 9 ? hole.getHoleNumber() - 9 : hole.getHoleNumber()));
                glRound.getElement().appendChild(glHole.getElement());
            });
            glGolfer.getElement().appendChild(glRound.getElement());
            glGolfer.getElement().setAttribute("slot", "card");
            golferList.add(glGolfer);
        }));

        GlFliter glFliter = getGlFilter(league, week, flight, teamNumber, entityManager);

        GlCard glCard = new GlCard();
        glCard.setMatch(match.getId());
        glCard.setWeek(week);
        glCard.setFlight(flight);
        glCard.setTeam(teamNumber);
        glCard.setNine(match.getNine());
        glCard.getElement().appendChild(glFliter.getElement());
        golferList.forEach(golfer -> glCard.getElement().appendChild(golfer.getElement()));


        entityManager.close();
        return glCard;
    }

    private GlFliter getGlFilter(League league, int week, int flight, int teamNumber, EntityManager entityManager) {
        GlFliter glFliter = new GlFliter();
        glFliter.setWeek(week);
        glFliter.setFlight(flight);
        glFliter.setTeam(teamNumber);
        glFliter.setWeeks(eventMatchRepository.getWeekCount(entityManager, league.getId()));
        glFliter.setFlights(4);
        glFliter.setTeams(eventMatchRepository.getTeamCount(entityManager, league.getId()));
        glFliter.getElement().setAttribute("slot", "filter");
        return glFliter;
    }

    public GlReport getScoreCardSummary(League league, int week, int flight, int team) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        GlReport glReport = generateReport(league, getScoreCardSummaries(roundRepository.getRounds(entityManager, league.getId(), week, flight, team)), team);
        GlFliter glFliter = getGlFilter(league, week, flight, team, entityManager);
        glReport.getElement().appendChild(glFliter.getElement());
        entityManager.close();
        return glReport;
    }

    private GlReport generateReport(League league, List<ScoreCardSummary> scoreCardSummaries, int teamId) {
        final GlReport glReport = new GlReport();
        scoreCardSummaries.stream().findAny().ifPresent(summary -> {
            glReport.setWeek(summary.getWeek());
            glReport.setFlight(summary.getFlight());
            glReport.setTeam(teamId);
        });

        scoreCardSummaries.forEach(scoreCardSummary -> {
            Div matchContainer = new Div();
            matchContainer.getElement().getStyle().set("border-bottom", "1px solid #335533");
            scoreCardSummary.getAll().stream()
                    .filter(roundSummary -> !roundSummary.getGolfer().getLastName().equals("Dummy")
                            && !roundSummary.getGolfer().getLastName().equals("Bye"))
                    .forEach(roundSummary -> {
                        GlGolfer glGolfer = new GlGolfer();
                        glGolfer.setHandicap(roundSummary.getHandicap());
                        glGolfer.setName(roundSummary.getGolfer().fullName());
                        if (roundSummary.getGolfer().getSubstitute()) {
                            glGolfer.setTeam(getTeamForSubstitute(league, scoreCardSummary, roundSummary));
                        } else {
                            roundSummary.getGolfer().teamForLeague(league).map(Team::getTeamId).ifPresent(glGolfer::setTeam);
                        }
                        glGolfer.setInline(true);
                        glGolfer.setHideHdcp(true);
                        glGolfer.setHideTotal(true);

                        GlRound glRound = new GlRound();
                        glRound.setGrossScore(roundSummary.getGrossScore());
                        glRound.setNetScore(roundSummary.getNetScore());
                        glRound.setHandicap(roundSummary.getHandicap());
                        glRound.setNetPoints(roundSummary.getNetPoints());
                        glRound.setMatchPoints(roundSummary.getMatchPoints());
                        glRound.setTeamNet((int) roundSummary.getTeamNet());

                        roundSummary.getGrossScores().stream()
                                .sorted(Comparator.comparing(score -> score.getHole().getHoleNumber()))
                                .map(grossScore -> toComponent(grossScore, "grossScore", false))
                                .forEach(score -> glRound.getElement().appendChild(score.getElement()));
                        roundSummary.getNetScores().stream()
                                .map(netScore -> toComponent(netScore, "netScore", true))
                                .forEach(score -> glRound.getElement().appendChild(score.getElement()));

                        glGolfer.getElement().appendChild(glRound.getElement());
                        matchContainer.getElement().appendChild(glGolfer.getElement());
                    });
            matchContainer.getElement().setAttribute("slot", "report");
            glReport.getElement().appendChild(matchContainer.getElement());
        });
        return glReport;
    }

    private Integer getTeamForSubstitute(League league, ScoreCardSummary scoreCardSummary, RoundSummary roundSummary) {
        return roundSummary.isHomeTeam()
                ? scoreCardSummary.getHomeTeam().stream().map(rs -> rs.getGolfer().teamForLeague(league)).filter(Optional::isPresent).map(Optional::get).map(Team::getTeamId).findAny().orElse(0)
                : scoreCardSummary.getAwayTeam().stream().map(rs -> rs.getGolfer().teamForLeague(league)).filter(Optional::isPresent).map(Optional::get).map(Team::getTeamId).findAny().orElse(0);
    }

    private GlScore toComponent(Score score, String slot, boolean isNetScore) {
        int num = score.getHole().getHoleNumber() > 9 ? score.getHole().getHoleNumber() - 9 : score.getHole().getHoleNumber();
        final GlScore glScore = new GlScore();
        glScore.setNumber(score.getHole().getHoleNumber());
        glScore.setPar(score.getHole().getPar());
        glScore.setHandicap(score.getRound().getHandicap());
        glScore.setScore(score.getScore());
        glScore.setNet(isNetScore);
        glScore.setWin(score.isWin());
        glScore.getElement().setAttribute("slot", slot + num);
        return glScore;
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
