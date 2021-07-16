package com.ejp.golf.league.layout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		final Anchor leagueNotes = new Anchor("./", "League Notes");
		final Anchor scoreCard = new Anchor("./scorecard", "Scorecard");
		final Anchor summaries = new Anchor("./summary", "Scorecard Summary");
		final Anchor login = new Anchor("./login", "Log in");

		//Authed only links
		final Anchor eventCrud = new Anchor("./admin/event", "Event CRUD");
		final Anchor golferCrud = new Anchor("./admin/golfer", "Golfer CRUD");
		final Anchor holeCrud = new Anchor("/.admin/hole", "Hole CRUD");
		final Anchor logout = new Anchor("./logout", "Log out");

		final Div container;
		if(isAdmin())
		{
			container = new Div(leagueNotes, scoreCard, summaries, eventCrud, golferCrud, holeCrud, logout);
		}else{
			container = new Div(leagueNotes, scoreCard, summaries, login);
		}
		container.setClassName("sideDrawerContainer");
		return container;
	}

	private boolean isAdmin() {
		return SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities().stream()
				.anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"));
	}

	private Component getNavBarContent(String leagueName)
	{
		final Button sideDrawerToggleButton = createSideDrawerToggleButton();
		final Component title = createTitle(leagueName);
		title.getElement().getClassList().add("title");
		final Div container = new Div(sideDrawerToggleButton, title, new Div());
		container.setClassName("navBarContainer");
		return container;
	}

	private Component createTitle(String title)
	{
		Label label = new Label(title);
		label.getElement().getClassList().add("title");
		return label;
	}

	private Button createSideDrawerToggleButton()
	{
		final Button drawerToggle = new Button(new Icon(VaadinIcon.GOLF));
		drawerToggle.getElement().getClassList().add("drawer-toggle");
		drawerToggle.addClickListener(event -> setDrawerOpened(!isDrawerOpened()));
		return drawerToggle;
	}
}
