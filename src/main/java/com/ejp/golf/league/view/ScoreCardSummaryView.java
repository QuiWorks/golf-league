package com.ejp.golf.league.view;

import com.ejp.golf.league.component.GlFliter;
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
    private GlReport glReport;

    /**
     * Construct a new Vaadin view.
     *
     * Build the initial UI state for the user accessing the application.
     *
     */
    public ScoreCardSummaryView() throws ParseException {
        addClassName("centered-content");
        GlFliter glFliter = new GlFliter();
        glFliter.setWeek(1);
        glFliter.setWeeks(19);
        glFliter.setFlight(1);
        glFliter.setFlights(4);
        glFliter.setTeam(1);
        glFliter.setTeams(19);
        glFliter.addRequestSubmissionListener(this::handleRequestSubmission);
        glReport =  new ScoreCardService().getScoreCardSummary(1, 1, 1);
        Label label = new Label("Find Match");
        label.getElement().getClassList().add("title");
        add(label);
        add(glFliter);
        add(glReport);
    }

    private void handleRequestSubmission(GlRequestSubmission event) {
        int week = event.getWeek();
        int flight = event.getFlight();
        int team = event.getTeam();
        GlReport requestedReport = new ScoreCardService().getScoreCardSummary(week, flight, team);
        requestedReport.addRequestSubmissionListener(this::handleRequestSubmission);
        remove(this.glReport);
        this.glReport = requestedReport;
        add(requestedReport);
    }

}
