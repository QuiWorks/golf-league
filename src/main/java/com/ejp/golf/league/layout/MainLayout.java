package com.ejp.golf.league.layout;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.springframework.beans.factory.annotation.Value;

@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout
{
	public MainLayout(@Value("${league.name}") String leagueName)
	{
		addToNavbar(getNavBarContent(leagueName));
		addToDrawer(getSideDrawerContent());
	}

	private Component getSideDrawerContent()
	{
		final Header index = new Header(new Label("Index"));
		final Anchor leagueNotes = new Anchor("#", "League Notes");
		final Anchor leagueInformation = new Anchor("#", "League Information");
		final Anchor flightTeamHandicapList = new Anchor("#", "Flight Team Handicap List");
		final Button settingToggle = new Button(new Icon(VaadinIcon.COG));
		final Div container = new Div(index, leagueNotes, leagueInformation, flightTeamHandicapList, settingToggle);
		container.setClassName("sideDrawerContainer");
		return container;
	}

	private Component getNavBarContent(String leagueName)
	{
		final Dialog applicationDialog = createApplicationDialog();
		final Button sideDrawerToggleButton = createSideDrawerToggleButton();
		final Component title = createTitle(leagueName);
		title.getElement().getClassList().add("title");
		final Button applicationMenuButton = createApplicationMenuButton(applicationDialog);
		final Div container = new Div(sideDrawerToggleButton, title, applicationDialog,
			applicationMenuButton);
		container.setClassName("navBarContainer");
		return container;
	}

	private Dialog createApplicationDialog()
	{
		final Div container = new Div();
		container.setClassName("column");
		Label title = new Label("EVENT NAME and DATE");
		title.setClassName("title");
		container.add(title);
		container.add(
			IntStream.range(1, 5)
				.mapToObj(i -> new Button("Player " + i))
				.collect(Collectors.toList())
				.toArray(new Button[]{}));
		return new Dialog(container);
	}

	private Button createApplicationMenuButton(Dialog applicationDialog)
	{
		final Button appMenuButton = new Button(new Icon(VaadinIcon.GOLF));
		appMenuButton.addClickListener(event -> applicationDialog.open());
		return appMenuButton;
	}

	private Component createTitle(String leagueName)
	{
		return new Label(leagueName);
	}

	private Button createSideDrawerToggleButton()
	{
		final Button drawerToggle = new Button(new Icon(VaadinIcon.CHART_GRID));
		drawerToggle.addClickListener(event -> setDrawerOpened(!isDrawerOpened()));
		return drawerToggle;
	}
}
