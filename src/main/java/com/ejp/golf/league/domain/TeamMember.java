package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(TeamMemberPK.class)
@Table(name = "team_member", schema = "golf_league", catalog = "")
public class TeamMember {
    private int teamId;
    private int golferId;
    private int leagueId;
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
    @Column(name = "golfer_id", nullable = false)
    public int getGolferId() {
        return golferId;
    }

    public void setGolferId(int golferId) {
        this.golferId = golferId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamMember that = (TeamMember) o;
        return teamId == that.teamId && golferId == that.golferId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, golferId);
    }

    @Id
    @Column(name = "league_id", nullable = false)
    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    @Id
    @Column(name = "flight_id", nullable = false)
    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }
}
