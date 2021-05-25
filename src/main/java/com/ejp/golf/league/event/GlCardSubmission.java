package com.ejp.golf.league.event;

import com.ejp.golf.league.component.GlCard;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;

@DomEvent("gl-card-submission")
public class GlCardSubmission extends ComponentEvent<GlCard> {
    private final int flight;

    public GlCardSubmission(GlCard source, boolean fromClient, @EventData("event.detail.flight") int flight) {
        super(source, fromClient);
        this.flight = flight;
    }

    public int getFlight() {
        return flight;
    }
}
