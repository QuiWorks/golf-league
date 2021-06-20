package com.ejp.golf.league.model;

import com.ejp.golf.league.domain.Score;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScoreCardSummary {
    private final RoundSummary lowHomeGolfer;
    private final RoundSummary highHomeGolfer;
    private final RoundSummary lowAwayGolfer;
    private final RoundSummary highAwayGolfer;

    public ScoreCardSummary(List<RoundSummary> roundSummaries) {
        if(roundSummaries.size() != 4)
        {
            throw new RuntimeException("Incorrect number of rounds on score card: " + roundSummaries.size());
        }

        List<RoundSummary> homeTeam = roundSummaries.stream()
                .filter(RoundSummary::isHomeTeam)
                .sorted(Comparator.comparing(RoundSummary::getHandicap))
                .collect(Collectors.toList());
        lowHomeGolfer = homeTeam.get(0);
        highHomeGolfer = homeTeam.get(1);

        List<RoundSummary> awayTeam = roundSummaries.stream()
                .filter(Predicate.not(RoundSummary::isHomeTeam))
                .sorted(Comparator.comparing(RoundSummary::getHandicap))
                .collect(Collectors.toList());
        lowAwayGolfer = awayTeam.get(0);
        highAwayGolfer = awayTeam.get(1);

        setNetScores(lowHomeGolfer, lowAwayGolfer);
        setNetScores(highHomeGolfer, highAwayGolfer);
        setNetPoints(lowHomeGolfer, lowAwayGolfer);
        setNetPoints(highHomeGolfer, highAwayGolfer);
        setMatchPoints(lowHomeGolfer, lowAwayGolfer);
        setMatchPoints(highHomeGolfer, highAwayGolfer);
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
            getAll().forEach(roundSummary -> roundSummary.setTeamNet(0f));
        }else{
            getAwayTeam().forEach(roundSummary -> roundSummary.setTeamNet(1f));
        }
    }

    private void setNetScores(RoundSummary homeTeamGolfer, RoundSummary awayTeamGolfer) {
        // Identify low and high handicap.
        List<RoundSummary> sortedPair = Stream.of(homeTeamGolfer, awayTeamGolfer).sorted(Comparator.comparing(RoundSummary::getHandicap)).collect(Collectors.toList());
        RoundSummary lowerHandicapGolfer = sortedPair.get(0);
        RoundSummary higherHandicapGolfer = sortedPair.get(1);

        // Set net scores
        higherHandicapGolfer.setNetScores(new ArrayList<>(higherHandicapGolfer.getGrossScores()).stream()
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
        List<RoundSummary> sortedPair = Stream.of(homeTeamGolfer, awayTeamGolfer)
                .sorted(Comparator.comparing(RoundSummary::getHandicap))
                .collect(Collectors.toList());
        RoundSummary lowerHandicapGolfer = sortedPair.get(0);
        RoundSummary higherHandicapGolfer = sortedPair.get(1);

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
        List<RoundSummary> sortedPair = Stream.of(homeTeamGolfer, awayTeamGolfer).sorted(Comparator.comparing(RoundSummary::getHandicap)).collect(Collectors.toList());
        RoundSummary lowerHandicapGolfer = sortedPair.get(0);
        RoundSummary higherHandicapGolfer = sortedPair.get(1);

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

    public List<RoundSummary> getHomeTeam() {
        return List.of(lowHomeGolfer, highHomeGolfer);
    }

    public List<RoundSummary> getAwayTeam() {
        return List.of(lowAwayGolfer, highAwayGolfer);
    }

    public List<RoundSummary> getAll()
    {
        return List.of(lowHomeGolfer, lowAwayGolfer, highHomeGolfer, highAwayGolfer);
    }

    @Override
    public String toString() {
        return "ScoreCardSummary{" +
                "lowHomeGolfer=" + lowHomeGolfer +
                ", highHomeGolfer=" + highHomeGolfer +
                ", lowAwayGolfer=" + lowAwayGolfer +
                ", highAwayGolfer=" + highAwayGolfer +
                '}';
    }
}
