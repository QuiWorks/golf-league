package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "team_event")
@IdClass(TeamEventPK.class)
public class TeamEvent {
    private int eventId;
    private int teamId;
    private int flightId;
    private int slot;

    @Id
    @Column(name = "event_id", nullable = false)
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Id
    @Column(name = "team_id", nullable = false)
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Id
    @Column(name = "flight_id", nullable = false)
    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    @Id
    @Column(name = "slot", nullable = false)
    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamEvent teamEvent = (TeamEvent) o;
        return eventId == teamEvent.eventId && teamId == teamEvent.teamId && flightId == teamEvent.flightId && slot == teamEvent.slot;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, teamId, flightId, slot);
    }
}
