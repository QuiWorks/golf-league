package com.ejp.golf.league.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TeamMemberPK implements Serializable {
    private int teamId;
    private int golferId;

    @Column(name = "team_id", nullable = false)
    @Id
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Column(name = "golfer_id", nullable = false)
    @Id
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
        TeamMemberPK that = (TeamMemberPK) o;
        return teamId == that.teamId && golferId == that.golferId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, golferId);
    }
}