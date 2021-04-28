package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "team_event", schema = "golf_league")
@IdClass(TeamEventPK.class)
public class TeamEvent {
    private int eventId;
    private int teamId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamEvent teamEvent = (TeamEvent) o;
        return eventId == teamEvent.eventId && teamId == teamEvent.teamId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, teamId);
    }
}
