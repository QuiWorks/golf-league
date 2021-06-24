package com.ejp.golf.league.model;

import com.ejp.golf.league.domain.Golfer;
import com.ejp.golf.league.domain.Round;
import com.ejp.golf.league.domain.Score;

import java.util.Comparator;
import java.util.List;

public class RoundSummary {
    //Known data.
    private final Round round;

    // Calculated data.
    private List<Score> netHoleScores;
    private int netPoints;
    private int matchPoints;
    private float teamNet;

    public RoundSummary(Round round) {
        this.round = round;
        netPoints = 0;
        matchPoints = 0;
        teamNet = 0f;
    }

    public Round getRound() {
        return round;
    }

    public Golfer getGolfer()
    {
        return round.getGolfer();
    }

    public int getMatchId() {
        return round.getEventMatch().getId();
    }

    public boolean isHomeTeam() {
        return round.home();
    }

    public List<Score> getGrossScores() {
        return round.getGrossScores();
    }

    public int getGrossScore() {
        return getGrossScores().stream()
                .map(Score::getScore)
                .reduce(Integer::sum)
                .orElseThrow(() ->  new RuntimeException("No gross score"));
    }

    public int getHandicap() {
        return round.getHandicap();
    }

    public List<Score> getNetScores() {
        return netHoleScores;
    }

    public void setNetScores(List<Score> netScores) {
        this.netHoleScores = netScores;
    }

    public int getNetScore() {
        return getNetScores().stream()
                .sorted(Comparator.comparing(score -> score.getHole().getHoleNumber()))
                .map(Score::getScore)
                .reduce(Integer::sum)
                .orElseThrow(() ->  new RuntimeException("No net score"));
    }

    public int getNetPoints() {
        return netPoints;
    }

    public void setNetPoints(int netPoints) {
        this.netPoints = netPoints;
    }

    public int getMatchPoints() {
        return matchPoints;
    }

    public void setMatchPoints(int matchPoints) {
        this.matchPoints = matchPoints;
    }

    public float getTeamNet() {
        return teamNet;
    }

    public void setTeamNet(float teamNet) {
        this.teamNet = teamNet;
    }

    @Override
    public String toString() {
        return "RoundSummary{" +
                "round=" + round +
                ", netHoleScores=" + netHoleScores +
                ", netPoints=" + netPoints +
                ", matchPoints=" + matchPoints +
                ", teamNet=" + teamNet +
                '}';
    }
}
