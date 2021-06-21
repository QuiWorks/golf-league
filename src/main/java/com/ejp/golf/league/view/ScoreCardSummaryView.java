package com.ejp.golf.league.view;

import com.ejp.golf.league.component.GlGolfer;
import com.ejp.golf.league.component.GlReport;
import com.ejp.golf.league.component.GlRound;
import com.ejp.golf.league.component.GlScore;
import com.ejp.golf.league.domain.League;
import com.ejp.golf.league.domain.Score;
import com.ejp.golf.league.domain.Team;
import com.ejp.golf.league.layout.MainLayout;
import com.ejp.golf.league.model.ScoreCardSummary;
import com.ejp.golf.league.service.ScoreCardService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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


    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
        final GlReport glReport =  new ScoreCardService().getScoreCardSummary(sdf.parse("2021-05-12"), 1);
        add(glReport);
    }

}
