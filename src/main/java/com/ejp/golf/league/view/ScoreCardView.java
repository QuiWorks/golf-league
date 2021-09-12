package com.ejp.golf.league.view;

import com.ejp.golf.league.component.*;
import com.ejp.golf.league.domain.*;
import com.ejp.golf.league.event.GlCardRequest;
import com.ejp.golf.league.event.GlCardSubmission;
import com.ejp.golf.league.layout.MainLayout;
import com.ejp.golf.league.service.ScoreCardService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route(value = "scorecard", layout = MainLayout.class)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class ScoreCardView extends VerticalLayout {

    private final League league;
    private final ScoreCardService scoreCardService;

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     */
    @Autowired
    public ScoreCardView(League league, ScoreCardService scoreCardService) {
        this.league = league;
        this.scoreCardService = scoreCardService;
        addClassName("centered-content");
        GlCard glCard = scoreCardService.getScoreCard(this.league, 1, 1, 1);
        glCard.addCardRequestListener(this::handleScoreCardRequest);
        glCard.addCardSubmissionListener(this::handleScoreCardSubmission);
        add(glCard);
    }

    private void handleScoreCardSubmission(GlCardSubmission event) {
        event.getScores().forEach((key, value) -> {
            Round round = new Round();
            Golfer golfer = new Golfer();
            golfer.setId(key);
            round.setGolfer(golfer);
            round.setSlot(event.getSlot());
            round.setFlightId(event.getFlight());
            round.setHandicap(scoreCardService.getHandicap(key));
            round.setNine(event.getNine());
            round.setDatePlayed(new Timestamp(System.currentTimeMillis()));
            round.setHome(scoreCardService.isHome(league, key, event.getMatch()));
            EventMatch eventMatch = new EventMatch();
            eventMatch.setId(event.getMatch());
            round.setEventMatch(eventMatch);

            Round savedRound = scoreCardService.saveRound(round);

            int start = event.getNine().equals("front")? 1 : 10;
            int end = event.getNine().equals("front")? 10 : 19;

            IntStream.range(start, end)
                    .mapToObj(holeNum -> {
                        int index = event.getNine().equals("front") ? holeNum - 1 : holeNum - 10;
                        Score score = new Score();
                        score.setScore(value[index]);
                        score.setRound(savedRound);
                        Hole hole = new Hole();
                        hole.setId(holeNum);
                        score.setHole(hole);
                        return score;
                    }).forEach(scoreCardService::saveScore);
        });
    }

    private void handleScoreCardRequest(GlCardRequest event) {
        event.unregisterListener();
        GlCard card = event.getSource();
        int week = event.getWeek();
        int flight = event.getFlight();
        int slot = event.getTeam();
        GlCard requestCard = scoreCardService.getScoreCard(league, week, flight, slot);
        requestCard.addCardRequestListener(this::handleScoreCardRequest);
        requestCard.addCardSubmissionListener(this::handleScoreCardSubmission);
        remove(card);
        add(requestCard);
    }

}
