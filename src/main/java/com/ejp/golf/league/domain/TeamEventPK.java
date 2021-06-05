package com.ejp.golf.league.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TeamEventPK implements Serializable {
    private int eventId;
    private int teamId;

    @Column(name = "event_id", nullable = false)
    @Id
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Column(name = "team_id", nullable = false)
    @Id
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamEventPK that = (TeamEventPK) o;
        return eventId == that.eventId && teamId == that.teamId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, teamId);
    }
}