package com.ejp.golf.league.model;

import com.ejp.golf.league.domain.Hole;
import com.ejp.golf.league.domain.Score;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ScoreCardSummary {
    private final int flight;
    private final int slot;
    private final String nine;
    private final RoundSummary lowHomeGolfer;
    private final RoundSummary highHomeGolfer;
    private final RoundSummary lowAwayGolfer;
    private final RoundSummary highAwayGolfer;

    public ScoreCardSummary(List<RoundSummary> roundSummaries) {
        if (roundSummaries.size() != 4) {
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
        setTeamNetPoints(lowHomeGolfer, lowAwayGolfer, highHomeGolfer, highAwayGolfer);

        flight = lowHomeGolfer.getRound().getFlightId();
        slot = lowHomeGolfer.getRound().getSlot();
        nine = lowHomeGolfer.getRound().getNine();
    }

    public int getFlight() {
        return flight;
    }

    public int getSlot() {
        return slot;
    }

    public String getNine() {
        return nine;
    }

    private void setTeamNetPoints(RoundSummary lowHomeGolfer, RoundSummary lowAwayGolfer, RoundSummary highHomeGolfer, RoundSummary highAwayGolfer) {
        Integer homeTeamNetScore = getHomeTeam().stream()
                .map(RoundSummary::getNetScore)
                .reduce(Integer::sum)
                .orElse(Integer.MAX_VALUE);
        Integer awayTeamNetScore = getAwayTeam().stream()
                .map(RoundSummary::getNetScore)
                .reduce(Integer::sum)
                .orElse(Integer.MAX_VALUE);
        if (homeTeamNetScore < awayTeamNetScore) {
            lowHomeGolfer.setTeamNet(1f);
            highHomeGolfer.setTeamNet(1f);
        } else if (homeTeamNetScore.equals(awayTeamNetScore)) {
            lowHomeGolfer.setTeamNet(.5f);
            highHomeGolfer.setTeamNet(.5f);
            lowAwayGolfer.setTeamNet(.5f);
            highAwayGolfer.setTeamNet(.5f);
        } else {
            lowAwayGolfer.setTeamNet(1f);
            highAwayGolfer.setTeamNet(1f);
        }

        dummyCheck(lowHomeGolfer, lowAwayGolfer);
        dummyCheck(highHomeGolfer, highAwayGolfer);
    }

    private void dummyCheck(RoundSummary golfer1, RoundSummary golfer2) {
        if(golfer2.getGrossScore() == 0)
        {
            golfer1.setTeamNet(1);
            golfer2.setTeamNet(0);
        }else if(golfer1.getGrossScore() == 0)
        {
            golfer1.setTeamNet(0);
            golfer2.setTeamNet(1);
        }
    }

    private RoundSummary getHigherHandicap(RoundSummary rs1, RoundSummary rs2) {
        List<RoundSummary> sortedPair = Stream.of(rs1, rs2).sorted(Comparator.comparing(RoundSummary::getHandicap)).collect(Collectors.toList());
        return sortedPair.get(1);
    }

    private RoundSummary getLowerHandicap(RoundSummary rs1, RoundSummary rs2) {
        List<RoundSummary> sortedPair = Stream.of(rs1, rs2).sorted(Comparator.comparing(RoundSummary::getHandicap)).collect(Collectors.toList());
        return sortedPair.get(0);
    }

    private void setNetScores(RoundSummary homeTeamGolfer, RoundSummary awayTeamGolfer) {
        // Identify low and high handicap.
        RoundSummary lowerHandicapGolfer = getLowerHandicap(homeTeamGolfer, awayTeamGolfer);
        RoundSummary higherHandicapGolfer = getHigherHandicap(homeTeamGolfer, awayTeamGolfer);
        List<Hole> holesByHandicap = lowerHandicapGolfer.getGrossScores().stream()
                .map(Score::getHole)
                .sorted(Comparator.comparingInt(Hole::getHandicap))
                .collect(Collectors.toList());

        higherHandicapGolfer.setNetScores(higherHandicapGolfer.getGrossScores().stream()
                .map(Score::clone)
                .collect(Collectors.toList()));

        IntStream.range(0, higherHandicapGolfer.getHandicap())
                .forEach(i -> higherHandicapGolfer.getNetScores().stream()
                        .filter(s -> s.getHole().equals(holesByHandicap.get(i > 8 ? i - 9 : i)))
                        .findAny()
                        .ifPresent(score -> score.setScore(score.getScore() - 1)));

        lowerHandicapGolfer.setNetScores(lowerHandicapGolfer.getGrossScores().stream()
                .map(Score::clone)
                .peek(score -> score.setWin(score.getScore() < higherHandicapGolfer.getNetScores().stream()
                        .filter(higherScore -> higherScore.getHole().getId() == score.getHole().getId())
                        .map(Score::getScore)
                        .findAny().orElseThrow(() -> new RuntimeException("exception"))))
                .collect(Collectors.toList()));

        IntStream.range(0, lowerHandicapGolfer.getHandicap())
                .forEach(i -> lowerHandicapGolfer.getNetScores().stream()
                        .filter(s -> s.getHole().equals(holesByHandicap.get(i > 8 ? i - 9 : i)))
                        .findAny()
                        .ifPresent(score -> score.setScore(score.getScore() - 1)));

        higherHandicapGolfer.getNetScores()
                .forEach(score -> score.setWin(score.getScore() < lowerHandicapGolfer.getNetScores().stream()
                        .filter(higherScore -> higherScore.getHole().getId() == score.getHole().getId())
                        .map(Score::getScore)
                        .findAny().orElseThrow(() -> new RuntimeException("exception"))));
    }


    private void setNetPoints(RoundSummary homeTeamGolfer, RoundSummary awayTeamGolfer) {
        // Identify low and high handicap.
        RoundSummary lowerHandicapGolfer = getLowerHandicap(homeTeamGolfer, awayTeamGolfer);
        RoundSummary higherHandicapGolfer = getHigherHandicap(homeTeamGolfer, awayTeamGolfer);

        // Set net points
        if ((lowerHandicapGolfer.getNetScore() < higherHandicapGolfer.getNetScore() && !lowerHandicapGolfer.getGolfer().getLastName().equals("Dummy"))
                || higherHandicapGolfer.getGolfer().getLastName().equals("Dummy")) {
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
        RoundSummary lowerHandicapGolfer = getLowerHandicap(homeTeamGolfer, awayTeamGolfer);
        RoundSummary higherHandicapGolfer = getHigherHandicap(homeTeamGolfer, awayTeamGolfer);

        // Set match points
        long lowerHandicapGolferHoleWins = lowerHandicapGolfer.getNetScores().stream()
                .filter(Score::isWin).count();
        long higherHandicapGolferHoleWins = higherHandicapGolfer.getNetScores().stream()
                .filter(Score::isWin).count();

        if ((lowerHandicapGolferHoleWins > higherHandicapGolferHoleWins && !lowerHandicapGolfer.getGolfer().getLastName().equals("Dummy"))
                || higherHandicapGolfer.getGolfer().getLastName().equals("Dummy")) {
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

    public List<RoundSummary> getAll() {
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
