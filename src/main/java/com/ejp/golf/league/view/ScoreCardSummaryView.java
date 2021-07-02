package com.ejp.golf.league.view;

import com.ejp.golf.league.component.GlReport;
import com.ejp.golf.league.domain.League;
import com.ejp.golf.league.event.GlRequestSubmission;
import com.ejp.golf.league.layout.MainLayout;
import com.ejp.golf.league.service.ScoreCardService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.text.ParseException;

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


    public final League league = new League().build(l -> l
            .set(l::id, 1)
            .set(l::name, "Territory Wednesday Mens League"));

    /**
     * Construct a new Vaadin view.
     *
     * Build the initial UI state for the user accessing the application.
     *
     */
    public ScoreCardSummaryView() throws ParseException {
        addClassName("centered-content");
        GlReport glReport =  new ScoreCardService().getScoreCardSummary(1, 1, 1);
        glReport.addRequestSubmissionListener(this::handleRequestSubmission);
        Label label = new Label("Find Match");
        label.getElement().getClassList().add("title");
        add(label);
        add(glReport);
    }

    private void handleRequestSubmission(GlRequestSubmission event) {
        GlReport glReport = event.getSource();
        int week = event.getWeek();
        int flight = event.getFlight();
        int team = event.getTeam();
        GlReport requestedReport = new ScoreCardService().getScoreCardSummary(week, flight, team);
        requestedReport.addRequestSubmissionListener(this::handleRequestSubmission);
        remove(glReport);
        add(requestedReport);
    }

}
