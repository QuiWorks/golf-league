package com.ejp.golf.league.model;

import java.util.stream.Collectors;

public class DummyRoundSummary extends RoundSummary {
    public DummyRoundSummary(RoundSummary roundSummary) {
        super(roundSummary.getRound());
        roundSummary.getRound().setGrossScores(
                getRound().getGrossScores().stream()
                        .peek(score -> score.setScore(score.getHole().getPar()))
                        .collect(Collectors.toList()));
        roundSummary.setNetScores(
                getRound().getGrossScores().stream()
                        .peek(score -> score.setScore(score.getHole().getPar()))
                        .collect(Collectors.toList()));
    }
}
