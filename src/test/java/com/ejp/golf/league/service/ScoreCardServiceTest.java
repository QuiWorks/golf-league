package com.ejp.golf.league.service;

import com.ejp.golf.league.model.ScoreCardSummary;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreCardServiceTest {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void name() throws ParseException {
        ScoreCardService scoreCardService = new ScoreCardService();
        List<ScoreCardSummary> scoreCardSummary = scoreCardService.getScoreCardSummary(sdf.parse("2021-05-12"));

        System.out.println(scoreCardSummary);
    }
}