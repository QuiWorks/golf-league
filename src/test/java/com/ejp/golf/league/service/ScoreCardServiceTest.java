package com.ejp.golf.league.service;

import com.ejp.golf.league.legacy.DatabaseMigrator;
import com.ejp.golf.league.legacy.domain.ScoreCard;
import com.ejp.golf.league.legacy.domain.ScoreCardList;
import com.ejp.golf.league.model.ScoreCardSummary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class ScoreCardServiceTest {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("golf_league");
    }

    @Test
    void name() throws ParseException {
        ScoreCardService scoreCardService = new ScoreCardService();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<ScoreCardSummary> scoreCardSummary = scoreCardService.getScoreCardSummary(entityManager,sdf.parse("2021-05-12"));
        entityManager.close();
        System.out.println(scoreCardSummary);
    }

    @Test
    void name3() {
        LocalDate now = LocalDate.now();
        java.util.Date from = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(from);
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