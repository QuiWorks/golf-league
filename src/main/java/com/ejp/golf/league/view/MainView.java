package com.ejp.golf.league.view;

import com.ejp.golf.league.component.GlCard;
import com.ejp.golf.league.component.GlGolfer;
import com.ejp.golf.league.component.GlHole;
import com.ejp.golf.league.component.GlRound;
import com.ejp.golf.league.layout.MainLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

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
@Route(layout = MainLayout.class)
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     */
    public MainView() {
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
        glCard.setSlot(1);
        glCard.setNine("Back");
        //TODO handle dates.
//        glCard.setDate(new Date());

        final GlRound glRound = new GlRound();
        final GlRound glRound2 = new GlRound();


        IntStream.range(1,10).mapToObj(this::addHole).forEach(hole -> glRound.getElement().appendChild(hole.getElement()));
        IntStream.range(1,10).mapToObj(this::addHole).forEach(hole -> glRound2.getElement().appendChild(hole.getElement()));
        golfer.getElement().appendChild(glRound.getElement());
        golfer2.getElement().appendChild(glRound2.getElement());
        glCard.getElement().appendChild(golfer.getElement());
        glCard.getElement().appendChild(golfer2.getElement());

        //TODO handle submission event.
        glCard.addCardSubmissionListener(event -> {
            GlCard source = event.getSource();
            int flight = event.getFlight();
            System.out.println(flight);
        });

        add(glCard);
    }

    private GlHole addHole(int num)
    {
        final GlHole glHole = new GlHole();
        glHole.setNumber(num);
        glHole.setYardage(250);
        glHole.setPar(4);
        glHole.setHandicap(2);
        glHole.setScore(0);
        glHole.getElement().setAttribute("slot","hole"+num);
        return glHole;
    }

}
