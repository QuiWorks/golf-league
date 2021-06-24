package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "team")
@IdClass(TeamMatchPK.class)
public class TeamPK implements Serializable {
    private int teamId;
    private int leagueId;
    private int flightId;

    public TeamPK() {
    }

    public TeamPK(int teamId, int leagueId, int flightId) {
        this.teamId = teamId;
        this.leagueId = leagueId;
        this.flightId = flightId;
    }

    @Id
    @Column(name = "team_id", nullable = false)
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int id) {
        this.teamId = id;
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
    @Column(name = "league_id", nullable = false)
    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamPK teamPK = (TeamPK) o;
        return teamId == teamPK.teamId && leagueId == teamPK.leagueId && flightId == teamPK.flightId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, leagueId, flightId);
    }
}
