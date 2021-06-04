package com.ejp.golf.league.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TeamFlightPK implements Serializable {
    private int teamId;
    private int flightId;

    @Column(name = "team_id", nullable = false)
    @Id
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Column(name = "flight_id", nullable = false)
    @Id
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
        TeamFlightPK that = (TeamFlightPK) o;
        return teamId == that.teamId && flightId == that.flightId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, flightId);
    }
}
