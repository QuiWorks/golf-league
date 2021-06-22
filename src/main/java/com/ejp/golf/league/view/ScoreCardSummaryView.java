package com.ejp.golf.league.view;

import com.ejp.golf.league.component.*;
import com.ejp.golf.league.domain.League;
import com.ejp.golf.league.event.GlRequestSubmission;
import com.ejp.golf.league.layout.MainLayout;
import com.ejp.golf.league.service.ScoreCardService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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


    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final Date TEST_DATE = sdf.parse("2021-05-12");
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
        GlReport glReport =  new ScoreCardService().getScoreCardSummary(TEST_DATE, 1);
        glReport.addRequestSubmissionListener(event -> handleRequestSubmission(TEST_DATE, event));
        add(glReport);
    }

    private void handleRequestSubmission(Date date, GlRequestSubmission event) {
        event.unregisterListener();
        GlReport report = event.getSource();
        int week = event.getWeek();
        int flight = event.getFlight();
        int slot = event.getSlot();
        GlReport requestedReport = new ScoreCardService().getScoreCardSummary(date, flight);
        requestedReport.addRequestSubmissionListener(e -> handleRequestSubmission(TEST_DATE, e));
        remove(report);
        add(requestedReport);
    }

}
