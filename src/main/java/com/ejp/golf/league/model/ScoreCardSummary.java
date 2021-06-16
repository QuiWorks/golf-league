package com.ejp.golf.league.model;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ScoreCardSummary {
    private List<RoundSummary> roundSummaries;

    //TODO add method to run calculations for points

    public ScoreCardSummary(List<RoundSummary> roundSummaries) {
        this.roundSummaries = roundSummaries;
        List<RoundSummary> homeTeam = roundSummaries.stream()
                .filter(RoundSummary::isHomeTeam)
                .sorted(Comparator.comparing(RoundSummary::getHandicap))
                .collect(Collectors.toList());
        List<RoundSummary> awayTeam = roundSummaries.stream()
                .filter(Predicate.not(RoundSummary::isHomeTeam))
                .sorted(Comparator.comparing(RoundSummary::getHandicap))
                .collect(Collectors.toList());
        RoundSummary homeTeamLow = homeTeam.get(0);
        RoundSummary awayTeamLow = awayTeam.get(0);
        RoundSummary homeTeamHigh = homeTeam.get(1);
        RoundSummary awayTeamHigh = awayTeam.get(1);
    }


    private void setPoints(RoundSummary homeTeamGolfer, RoundSummary awayTeamGolfer)
    {

    }
}
