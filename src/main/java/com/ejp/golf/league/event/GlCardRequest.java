package com.ejp.golf.league.event;

import com.ejp.golf.league.component.GlCard;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;

@DomEvent("gl-card-request")
public class GlCardRequest extends ComponentEvent<GlCard> {
    private final int flight;
    private final int team;
    private final int week;

    public GlCardRequest(GlCard source, boolean fromClient,
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
