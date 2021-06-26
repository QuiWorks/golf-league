package com.ejp.golf.league.layout;

import com.ejp.golf.league.component.GlReport;
import com.ejp.golf.league.event.GlRequestSubmission;
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
import org.springframework.security.core.context.SecurityContextHolder;

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
		final Anchor leagueNotes = new Anchor("/", "League Notes");
		final Anchor scoreCard = new Anchor("/scorecard", "Score Card");
		final Anchor summaries = new Anchor("/summary", "Score Card Summaries");
		final Anchor login = new Anchor("/login", "Log in");

		//Authed only links
		final Anchor eventCrud = new Anchor("/admin/event", "Event CRUD");
		final Anchor golferCrud = new Anchor("/admin/golfer", "Golfer CRUD");
		final Anchor holeCrud = new Anchor("/admin/hole", "Hole CRUD");
		final Anchor logout = new Anchor("/logout", "Log out");

		final Div container;
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER")))
		{
			container = new Div(index, leagueNotes, scoreCard, summaries, eventCrud, golferCrud, holeCrud, logout);
		}else{
			container = new Div(index, leagueNotes, scoreCard, summaries, login);
		}
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
		container.add(createTitle("Find Match"));
		GlReport glReport = new GlReport();
		glReport.addRequestSubmissionListener(this::handleRequestSubmission);
		container.add(glReport);
		return new Dialog(container);
	}

	private void handleRequestSubmission(GlRequestSubmission glRequestSubmission) {

	}

	private Button createApplicationMenuButton(Dialog applicationDialog)
	{
		final Button appMenuButton = new Button(new Icon(VaadinIcon.GOLF));
		appMenuButton.addClickListener(event -> applicationDialog.open());
		return appMenuButton;
	}

	private Component createTitle(String title)
	{
		Label label = new Label(title);
		label.getElement().getClassList().add("title");
		return label;
	}

	private Button createSideDrawerToggleButton()
	{
		final Button drawerToggle = new Button(new Icon(VaadinIcon.CHART_GRID));
		drawerToggle.addClickListener(event -> setDrawerOpened(!isDrawerOpened()));
		return drawerToggle;
	}
}
