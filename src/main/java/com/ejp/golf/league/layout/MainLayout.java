package com.ejp.golf.league.layout;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
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
		addToDrawer(new Button("TESTING"));
	}

	private Component getNavBarContent(String leagueName)
	{
		final Dialog applicationDialog = createApplicationDialog();
		final Button sideDrawerToggleButton = createSideDrawerToggleButton();
		final Component title = createTitle(leagueName);
		final Button applicationMenuButton = createApplicationMenuButton(applicationDialog);
		final Div container = new Div(sideDrawerToggleButton, title, applicationDialog,
			applicationMenuButton);
		container.setClassName("navBarContainer");
		return container;
	}

	private Dialog createApplicationDialog()
	{
		return new Dialog(new Button("TESTiNG DIALOG"));
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
		final Button drawerToggle = new Button(new Icon(VaadinIcon.MENU));
		drawerToggle.addClickListener(event -> setDrawerOpened(!isDrawerOpened()));
		return drawerToggle;
	}
}
