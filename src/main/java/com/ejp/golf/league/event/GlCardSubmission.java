package com.ejp.golf.league.event;

import com.ejp.golf.league.component.GlCard;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@DomEvent("gl-card-submission")
public class GlCardSubmission extends ComponentEvent<GlCard> {
    private final int match;
    private final int week;
    private final int flight;
    private final int slot;
    private final int team;
    private final String nine;
    private final String comment;
    private final Map<Integer, Integer[]> scores;

    public GlCardSubmission(GlCard source, boolean fromClient,
                            @EventData("event.detail.match") int match,
                            @EventData("event.detail.week") int week,
                            @EventData("event.detail.flight") int flight,
                            @EventData("event.detail.slott") int slot,
                            @EventData("event.detail.team") int team,
                            @EventData("event.detail.nine") String nine,
                            @EventData("event.detail.comment") String comment,
                            @EventData("event.detail.scores") String scores
    ) {
        super(source, fromClient);
        this.match = match;
        this.week = week;
        this.flight = flight;
        this.slot = slot;
        this.team = team;
        this.nine = nine;
        this.comment = comment;
        String[] temp = scores.substring(2, scores.length() -2).split("\\],\\[");
        this.scores = Arrays.stream(temp)
                .map(string -> string.split(","))
                .collect(Collectors.toMap(
                        str -> Integer.parseInt(str[0]),
                        str -> new Integer[]{Integer.parseInt(str[1]),
                                Integer.parseInt(str[2]),
                                Integer.parseInt(str[3]),
                                Integer.parseInt(str[4]),
                                Integer.parseInt(str[5]),
                                Integer.parseInt(str[6]),
                                Integer.parseInt(str[7]),
                                Integer.parseInt(str[8]),
                                Integer.parseInt(str[9])}));
    }

    public int getMatch() {
        return match;
    }

    public int getSlot() {
        return slot;
    }

    public int getFlight() {
        return flight;
    }

    public int getWeek() {
        return week;
    }

    public int getTeam() {
        return team;
    }

    public String getNine() {
        return nine;
    }

    public String getComment() {
        return comment;
    }

    public Map<Integer,Integer[]> getScores() {
        return scores;
    }
}
