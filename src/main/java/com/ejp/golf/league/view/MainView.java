package com.ejp.golf.league.view;

import java.util.Date;
import java.util.stream.IntStream;
import javax.sound.sampled.FloatControl;

import com.ejp.golf.league.component.GlCard;
import com.ejp.golf.league.component.GlGolfer;
import com.ejp.golf.league.component.GlHole;
import com.ejp.golf.league.component.GlRound;
import com.ejp.golf.league.layout.MainLayout;
import com.ejp.golf.league.service.GreetService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

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
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainView(@Autowired GreetService service) {
        addClassName("centered-content");

        final GlGolfer golfer = new GlGolfer();
        golfer.setHandicap(2);
        golfer.setName("Gary Pederson");
        golfer.setSub(false);
        golfer.setTeam(3);

        final GlCard glCard = new GlCard();
        glCard.setFlight(6);
        glCard.setNine("Back");
//        glCard.setDate(new Date());

        final GlRound glRound = new GlRound();


        IntStream.range(1,10).mapToObj(this::addHole).forEach(hole -> glRound.getElement().appendChild(hole.getElement()));
        glCard.getElement().appendChild(glRound.getElement());
        golfer.getElement().appendChild(glCard.getElement());

        add(golfer);
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
