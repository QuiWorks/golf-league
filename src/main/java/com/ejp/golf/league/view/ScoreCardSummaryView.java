package com.ejp.golf.league.view;

import com.ejp.golf.league.component.*;
import com.ejp.golf.league.layout.MainLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

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

    /**
     * Construct a new Vaadin view.
     *
     * Build the initial UI state for the user accessing the application.
     *
     */
    public ScoreCardSummaryView() {
        addClassName("centered-content");

        final GlGolfer golfer = new GlGolfer();
        golfer.setHandicap(2);
        golfer.setName("Gary Pederson");
        golfer.setSub(false);
        golfer.setTeam(3);

        final GlGolfer golfer2 = new GlGolfer();
        golfer2.setHandicap(6);
        golfer2.setName("Dave Thompson");
        golfer2.setSub(false);
        golfer2.setTeam(3);

        final GlCard glCard = new GlCard();
        glCard.setFlight(6);
        glCard.setNine("Back");
        //TODO handle dates.
//        glCard.setDate(new Date());

        final GlRound glRound = new GlRound();
        final GlRound glRound2 = new GlRound();


        IntStream.range(1,10).mapToObj(i -> addScore(i, "grossScore")).forEach(hole -> glRound.getElement().appendChild(hole.getElement()));
        IntStream.range(1,10).mapToObj(i -> addScore(i, "grossScore")).forEach(hole -> glRound2.getElement().appendChild(hole.getElement()));
        IntStream.range(1,10).mapToObj(i -> addScore(i, "netScore")).forEach(hole -> glRound.getElement().appendChild(hole.getElement()));
        IntStream.range(1,10).mapToObj(i -> addScore(i, "netScore")).forEach(hole -> glRound2.getElement().appendChild(hole.getElement()));
        golfer.getElement().appendChild(glRound.getElement());
        golfer2.getElement().appendChild(glRound2.getElement());
        glCard.getElement().appendChild(golfer.getElement());
        glCard.getElement().appendChild(golfer2.getElement());

        add(glCard);
    }

    private GlScore addScore(int num, String slot)
    {
        final GlScore glHole = new GlScore();
        glHole.setNumber(num);
        glHole.setPar(4);
        glHole.setHandicap(2);
        glHole.setScore(5);
        glHole.getElement().setAttribute("slot",slot+num);
        return glHole;
    }

}
