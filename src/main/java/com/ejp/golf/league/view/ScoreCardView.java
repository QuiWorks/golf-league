package com.ejp.golf.league.view;

import com.ejp.golf.league.component.*;
import com.ejp.golf.league.event.GlCardRequest;
import com.ejp.golf.league.event.GlCardSubmission;
import com.ejp.golf.league.layout.MainLayout;
import com.ejp.golf.league.service.ScoreCardService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

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

    private final ScoreCardService scoreCardService;

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     */
    public ScoreCardView() {
        addClassName("centered-content");
        scoreCardService = new ScoreCardService();
        int currentWeek = scoreCardService.getCurrentWeek();
        GlCard glCard = scoreCardService.getScoreCard(currentWeek, 1, 1);
        glCard.addCardRequestListener(this::handleScoreCardRequest);
        glCard.addCardSubmissionListener(this::handleScoreCardSubmission);
        add(glCard);
    }

    private void handleScoreCardSubmission(GlCardSubmission event) {
        GlCard source = event.getSource();
        int flight = event.getFlight();
        System.out.println(flight);
    }

    private void handleScoreCardRequest(GlCardRequest event) {
        event.unregisterListener();
        GlCard card = event.getSource();
        int week = event.getWeek();
        int flight = event.getFlight();
        int slot = event.getTeam();
        GlCard requestCard = scoreCardService.getScoreCard(week, flight, slot);
        requestCard.addCardRequestListener(this::handleScoreCardRequest);
        remove(card);
        add(requestCard);
    }

}
