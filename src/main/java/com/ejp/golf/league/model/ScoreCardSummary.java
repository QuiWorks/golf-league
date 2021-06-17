package com.ejp.golf.league.model;

import com.ejp.golf.league.domain.Score;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScoreCardSummary {
    private final List<RoundSummary> roundSummaries;

    public ScoreCardSummary(List<RoundSummary> roundSummaries) {
        this.roundSummaries = roundSummaries;
        setNetScores(getHomeTeamHighHandicapGolfer(), getAwayTeamHighHandicapGolfer());
        setNetScores(getHomeTeamLowHandicapGolfer(), getAwayTeamLowHandicapGolfer());
        setNetPoints(getHomeTeamHighHandicapGolfer(), getAwayTeamHighHandicapGolfer());
        setNetPoints(getHomeTeamLowHandicapGolfer(), getAwayTeamLowHandicapGolfer());
        setMatchPoints(getHomeTeamHighHandicapGolfer(), getAwayTeamHighHandicapGolfer());
        setMatchPoints(getHomeTeamLowHandicapGolfer(), getAwayTeamLowHandicapGolfer());
        setTeamNetPoints();
    }

    private void setTeamNetPoints() {
        Integer homeTeamNetScore = getHomeTeam().stream()
                .map(RoundSummary::getNetScore)
                .reduce(Integer::sum)
                .orElse(Integer.MAX_VALUE);
        Integer awayTeamNetScore = getAwayTeam().stream()
                .map(RoundSummary::getNetScore)
                .reduce(Integer::sum)
                .orElse(Integer.MAX_VALUE);
        if(homeTeamNetScore < awayTeamNetScore) {
            getHomeTeam().forEach(roundSummary -> roundSummary.setTeamNet(1f));
        } else if(homeTeamNetScore.equals(awayTeamNetScore)){
            roundSummaries.forEach(roundSummary -> roundSummary.setTeamNet(0f));
        }else{
            getAwayTeam().forEach(roundSummary -> roundSummary.setTeamNet(1f));
        }
    }

    private void setNetScores(RoundSummary homeTeamGolfer, RoundSummary awayTeamGolfer) {
        // Identify low and high handicap.
        RoundSummary higherHandicapGolfer = getHigherHandicapGolfer(homeTeamGolfer, awayTeamGolfer);
        RoundSummary lowerHandicapGolfer = getLowerHandicapGolfer(homeTeamGolfer, awayTeamGolfer);

        // Set net scores
        higherHandicapGolfer.setNetScores(higherHandicapGolfer.getGrossScores().stream()
                .peek(score -> {
                    int grossScore = score.getScore();
                    int golferHandicap = higherHandicapGolfer.getHandicap();
                    int opponentHandicap = lowerHandicapGolfer.getHandicap();
                    int holeHandicap = score.getHole().getHandicap();
                    int netScore = grossScore - (golferHandicap - opponentHandicap) - holeHandicap;
                    boolean win = netScore < lowerHandicapGolfer.getGrossScores().stream()
                            .filter(lowerScore -> lowerScore.getHole().getId() == score.getHole().getId())
                            .map(Score::getScore)
                            .findAny().orElseThrow(() -> new RuntimeException("err"));
                    score.setWin(win);
                    score.setScore(netScore);
                })
                .collect(Collectors.toList()));

        lowerHandicapGolfer.setNetScores(new ArrayList<>(lowerHandicapGolfer.getGrossScores()).stream()
                .peek(score -> score.setWin(score.getScore() < higherHandicapGolfer.getNetScores().stream()
                        .filter(higherScore -> higherScore.getHole().getId() == score.getHole().getId())
                        .map(Score::getScore)
                        .findAny().orElseThrow(() -> new RuntimeException("exception"))))
                .collect(Collectors.toList()));
    }


    private void setNetPoints(RoundSummary homeTeamGolfer, RoundSummary awayTeamGolfer) {
        // Identify low and high handicap.
        RoundSummary higherHandicapGolfer = getHigherHandicapGolfer(homeTeamGolfer, awayTeamGolfer);
        RoundSummary lowerHandicapGolfer = getLowerHandicapGolfer(homeTeamGolfer, awayTeamGolfer);

        // Set net points
        if (lowerHandicapGolfer.getNetScore() < higherHandicapGolfer.getNetScore()) {
            lowerHandicapGolfer.setNetPoints(2);
            higherHandicapGolfer.setNetPoints(0);
        } else if (lowerHandicapGolfer.getNetScores() == higherHandicapGolfer.getNetScores()) {
            lowerHandicapGolfer.setNetPoints(1);
            higherHandicapGolfer.setNetPoints(1);
        } else {
            lowerHandicapGolfer.setNetPoints(0);
            higherHandicapGolfer.setNetPoints(2);
        }
    }

    private void setMatchPoints(RoundSummary homeTeamGolfer, RoundSummary awayTeamGolfer) {
        // Identify low and high handicap.
        RoundSummary higherHandicapGolfer = getHigherHandicapGolfer(homeTeamGolfer, awayTeamGolfer);
        RoundSummary lowerHandicapGolfer = getLowerHandicapGolfer(homeTeamGolfer, awayTeamGolfer);

        // Set match points
        long lowerHandicapGolferHoleWins = lowerHandicapGolfer.getNetScores().stream()
                .filter(Score::isWin).count();
        long higherHandicapGolferHoleWins = higherHandicapGolfer.getNetScores().stream()
                .filter(Score::isWin).count();

        if (lowerHandicapGolferHoleWins > higherHandicapGolferHoleWins) {
            lowerHandicapGolfer.setMatchPoints(2);
            higherHandicapGolfer.setMatchPoints(0);
        } else if (lowerHandicapGolferHoleWins == higherHandicapGolferHoleWins) {
            lowerHandicapGolfer.setMatchPoints(1);
            higherHandicapGolfer.setMatchPoints(1);
        } else {
            lowerHandicapGolfer.setMatchPoints(0);
            higherHandicapGolfer.setMatchPoints(2);
        }
    }

    private RoundSummary getLowerHandicapGolfer(RoundSummary homeTeamGolfer, RoundSummary awayTeamGolfer) {
        return Stream.of(homeTeamGolfer, awayTeamGolfer)
                .min(Comparator.comparing(RoundSummary::getHandicap))
                .orElseThrow(() -> new RuntimeException("error"));
    }

    private RoundSummary getHigherHandicapGolfer(RoundSummary homeTeamGolfer, RoundSummary awayTeamGolfer) {
        return Stream.of(homeTeamGolfer, awayTeamGolfer)
                .max(Comparator.comparing(RoundSummary::getHandicap))
                .orElseThrow(() -> new RuntimeException("error"));
    }

    public List<RoundSummary> getRoundSummaries() {
        return roundSummaries;
    }

    public List<RoundSummary> getHomeTeam() {
        return roundSummaries.stream()
                .filter(RoundSummary::isHomeTeam)
                .collect(Collectors.toList());
    }

    public List<RoundSummary> getAwayTeam() {
        return roundSummaries.stream()
                .filter(Predicate.not(RoundSummary::isHomeTeam))
                .collect(Collectors.toList());
    }

    public RoundSummary getHomeTeamHighHandicapGolfer() {
        return roundSummaries.stream()
                .filter(RoundSummary::isHomeTeam)
                .max(Comparator.comparing(RoundSummary::getHandicap))
                .orElseThrow(() -> new RuntimeException("Unable to find home team high handicap player."));
    }

    public RoundSummary getHomeTeamLowHandicapGolfer() {
        return roundSummaries.stream()
                .filter(RoundSummary::isHomeTeam)
                .min(Comparator.comparing(RoundSummary::getHandicap))
                .orElseThrow(() -> new RuntimeException("Unable to find home team low handicap player."));
    }

    public RoundSummary getAwayTeamHighHandicapGolfer() {
        return roundSummaries.stream()
                .filter(Predicate.not(RoundSummary::isHomeTeam))
                .max(Comparator.comparing(RoundSummary::getHandicap))
                .orElseThrow(() -> new RuntimeException("Unable to find away team high handicap player."));
    }

    public RoundSummary getAwayTeamLowHandicapGolfer() {
        return roundSummaries.stream()
                .filter(Predicate.not(RoundSummary::isHomeTeam))
                .min(Comparator.comparing(RoundSummary::getHandicap))
                .orElseThrow(() -> new RuntimeException("Unable to find away team low handicap player."));
    }
}
