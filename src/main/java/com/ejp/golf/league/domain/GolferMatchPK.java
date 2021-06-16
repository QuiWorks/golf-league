package com.ejp.golf.league.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class GolferMatchPK implements Serializable {
    private int matchId;
    private int golferId;

    public GolferMatchPK() {
    }

    public GolferMatchPK(int matchId, int golferId) {
        this.matchId = matchId;
        this.golferId = golferId;
    }

    @Column(name = "match_id", nullable = false)
    @Id
    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int eventId) {
        this.matchId = eventId;
    }

    @Column(name = "golfer_id", nullable = false)
    @Id
    public int getGolferId() {
        return golferId;
    }

    public void setGolferId(int teamId) {
        this.golferId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GolferMatchPK that = (GolferMatchPK) o;
        return matchId == that.matchId && golferId == that.golferId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, golferId);
    }
}
