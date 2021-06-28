package com.ejp.golf.league.view;


import com.ejp.golf.league.component.GlFliter;
import com.ejp.golf.league.layout.MainLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

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
    public MainView() {
        addClassName("centered-content");


        GlFliter glFliter = new GlFliter();
        glFliter.setWeek(1);
        glFliter.setWeeks(19);
        glFliter.setFlight(1);
        glFliter.setFlights(4);
        glFliter.setTeam(1);
        glFliter.setTeams(19);
        add(glFliter);
        add(new Label("A prototype for version 2 of the TerritoryGL.com"));
        add(new Anchor("http://www.territorygl.com", "Click here to visit the original site."));
    }
}
