package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "team_match")
@IdClass(TeamMatchPK.class)
public class TeamMatch {
    private int matchId;
    private int teamId;
    private int leagueId;
    private int flightId;
    private boolean home;

    @Id
    @Column(name = "match_id", nullable = false)
    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    @Id
    @Column(name = "team_id", nullable = false)
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "home", nullable = false)
    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamMatch teamMatch = (TeamMatch) o;
        return matchId == teamMatch.matchId && teamId == teamMatch.teamId && home == teamMatch.home && leagueId == teamMatch.leagueId && flightId == teamMatch.flightId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, teamId, leagueId, flightId, home);
    }
}
