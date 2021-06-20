package com.ejp.golf.league.view;

import com.ejp.golf.league.domain.Round;
import com.ejp.golf.league.layout.MainLayout;
import com.ejp.golf.league.model.RoundSummary;
import com.ejp.golf.league.model.ScoreCardSummary;
import com.ejp.golf.league.service.ScoreCardService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

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
@Route(value = "summary", layout = MainLayout.class)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class ScoreCardSummaryView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     *
     * Build the initial UI state for the user accessing the application.
     *
     */
    public ScoreCardSummaryView() {
        addClassName("centered-content");

        ScoreCardService scoreCardService = new ScoreCardService();
        List<ScoreCardSummary> scoreCardSummary = scoreCardService.getScoreCardSummary();
        Round round = new Round();
        RoundSummary roundSummary = new RoundSummary(round);
//        scoreCardSummary.forEach(summary -> add(new Label(summary.toString())));
        add(new Label("testy"));
    }

}
