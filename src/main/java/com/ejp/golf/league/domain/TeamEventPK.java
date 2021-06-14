package com.ejp.golf.league.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TeamEventPK implements Serializable {
    private int matchId;
    private int teamId;

    public TeamEventPK() {
    }

    public TeamEventPK(int matchId, int teamId) {
        this.matchId = matchId;
        this.teamId = teamId;
    }

    @Column(name = "match_id", nullable = false)
    @Id
    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int eventId) {
        this.matchId = eventId;
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
        return matchId == that.matchId && teamId == that.teamId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, teamId);
    }
}
