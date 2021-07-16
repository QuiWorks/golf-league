package com.ejp.golf.league.view;


import com.ejp.golf.league.component.GlFliter;
import com.ejp.golf.league.layout.MainLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Header;
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
@PWA(name = "Golf League Application",
        shortName = "Golf League",
        description = "Prototype for version 2 of the TerritoryGL.com website.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {
    public MainView() {
        addClassName("centered-content");
        Label label = new Label("Information about this application:");
        label.getElement().getStyle().set("font-weight","bold");
        label.getElement().getStyle().set("text-decoration","underline");
        add(new Header(label));
        add(new Label("You are currently viewing the prototype for version 2 of the TerritoryGL.com"));
        add(new Anchor("http://www.territorygl.com", "Click here to visit the original site."));
        add(new Label("Use the golfer icon in the top left corner to open and close the navigation menu."));
        add(new Label("The scorecard link in the navigation menu will direct you to the new web-based scorecard for the league."));
        add(new Label("The scorecard summary link in the navigation menu will direct you to a report of match results."));
        add(new Label("The login link in the navigation menu will direct you to a login form for accessing the administrator side of the application."));
        add(new Label("The admin username is: 'admin' and the password is 'password'."));
        add(new Label("When logged in other links will appear in the navigation to secure views for maintaining the data in the system."));
        add(new Label("Please use the application and provide feedback to: wade0435@yahoo.com"));
    }
}
