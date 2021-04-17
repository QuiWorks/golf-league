package com.ejp.golf;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;

public class MainLayout extends AppLayout
{
	public MainLayout()
	{
		addToDrawer(new Button("TESTING"));
		setDrawerOpened(true);
	}
}
