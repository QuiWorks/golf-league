package com.ejp.golf.league.view;

import com.ejp.golf.league.component.*;
import com.ejp.golf.league.domain.Score;
import com.ejp.golf.league.layout.MainLayout;
import com.ejp.golf.league.model.ScoreCardSummary;
import com.ejp.golf.league.service.ScoreCardService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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
@Route(value = "summary", layout = MainLayout.class)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class ScoreCardSummaryView extends VerticalLayout {


    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Construct a new Vaadin view.
     *
     * Build the initial UI state for the user accessing the application.
     *
     */
    public ScoreCardSummaryView() throws ParseException {
        addClassName("centered-content");

        List<ScoreCardSummary> scoreCardSummary = new ScoreCardService().getScoreCardSummary(sdf.parse("2021-05-12"), 1);

        final GlCard glCard = new GlCard();
        glCard.setFlight(6);
        glCard.setNine("Back");
        //TODO handle dates.
//        glCard.setDate(new Date());

        ScoreCardSummary scoreCardSummary1 = scoreCardSummary.get(0);
        scoreCardSummary1.getAll().forEach(roundSummary -> {
            GlGolfer golfer3 = new GlGolfer();
            golfer3.setHandicap(roundSummary.getHandicap());
            golfer3.setName(roundSummary.getGolfer().getFirstName() + roundSummary.getGolfer().getLastName());
            golfer3.setSub(false);
            golfer3.setTeam(3);

            GlRound glRound1 = new GlRound();
            roundSummary.getGrossScores().stream()
                    .map(grossScore -> toComponent(grossScore, "grossScore"))
                    .forEach(score -> glRound1.getElement().appendChild(score.getElement()));
            roundSummary.getNetScores().stream()
                    .map(netScore -> toComponent(netScore, "netScore"))
                    .forEach(score -> glRound1.getElement().appendChild(score.getElement()));

            golfer3.getElement().appendChild(glRound1.getElement());
            glCard.getElement().appendChild(golfer3.getElement());
        });

        add(glCard);
    }

    private GlScore toComponent(Score score, String slot)
    {
        int num = score.getHole().getHoleNumber() > 9 ? 18 - score.getHole().getHoleNumber() : score.getHole().getHoleNumber();
        final GlScore glHole = new GlScore();
        glHole.setNumber(score.getHole().getHoleNumber());
        glHole.setPar(score.getHole().getPar());
        glHole.setHandicap(score.getRound().getHandicap());
        glHole.setScore(score.getScore());
        glHole.getElement().setAttribute("slot",slot+num);
        return glHole;
    }

}
