package com.ejp.golf.league.service;

import com.ejp.golf.league.legacy.DatabaseMigrator;
import com.ejp.golf.league.legacy.domain.ScoreCard;
import com.ejp.golf.league.legacy.domain.ScoreCardList;
import com.ejp.golf.league.model.ScoreCardSummary;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreCardServiceTest {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    void name() throws ParseException {
        ScoreCardService scoreCardService = new ScoreCardService();
//        List<ScoreCardSummary> scoreCardSummary = scoreCardService.getScoreCardSummary(sdf.parse("2021-05-12"));
        List<ScoreCardSummary> scoreCardSummary = scoreCardService.getScoreCardSummary();

        System.out.println(scoreCardSummary);
    }

    @Test
    void name2() {
        ScoreCardList scoreCardList = getLegacyList(DatabaseMigrator.LegacyData.SCORE_CARD.getUrl(), ScoreCardList.class);
        List<ScoreCard> scoreCard = scoreCardList.getScoreCard();
        System.out.println("done");
    }

    private <LEGACY_LIST> LEGACY_LIST getLegacyList(String url, Class<LEGACY_LIST> legacyListClass) {
        try {
            JAXBContext context = JAXBContext.newInstance(legacyListClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return legacyListClass.cast(unmarshaller.unmarshal(new File(url)));
        } catch (JAXBException e) {
            throw new RuntimeException("Could not read legacy data: " + legacyListClass + " from file: " + url, e);
        }
    }
}