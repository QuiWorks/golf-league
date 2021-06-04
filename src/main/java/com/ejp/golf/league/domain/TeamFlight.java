package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "team_flight")
@IdClass(TeamFlightPK.class)
public class TeamFlight {
    private int teamId;
    private int flightId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamFlight that = (TeamFlight) o;
        return teamId == that.teamId && flightId == that.flightId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, flightId);
    }
}
