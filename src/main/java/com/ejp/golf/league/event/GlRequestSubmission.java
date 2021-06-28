package com.ejp.golf.league.event;

import com.ejp.golf.league.component.GlFliter;
import com.ejp.golf.league.component.GlReport;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;

@DomEvent("gl-request-submission")
public class GlRequestSubmission extends ComponentEvent<GlFliter> {
    private final int flight;
    private final int team;
    private final int week;

    public GlRequestSubmission(GlFliter source, boolean fromClient,
                               @EventData("event.detail.flight") int flight,
                               @EventData("event.detail.team") int team,
                               @EventData("event.detail.week") int week) {
        super(source, fromClient);
        this.flight = flight;
        this.team = team;
        this.week = week;
    }

    public int getFlight() {
        return flight;
    }

    public int getTeam() {
        return team;
    }

    public int getWeek() {
        return week;
    }
}
