package com.ejp.golf.league.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name = "golfer_match")
@IdClass(GolferMatchPK.class)
public class GolferMatch {
    private int matchId;
    private int golferId;
    private int handicap;

    @Id
    @Column(name = "match_id", nullable = false)
    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    @Id
    @Column(name = "golfer_id", nullable = false)
    public int getGolferId() {
        return golferId;
    }

    public void setGolferId(int teamId) {
        this.golferId = teamId;
    }

    @Column(name = "handicap", nullable = false)
    public int getHandicap() {
        return handicap;
    }

    public void setHandicap(int handicap) {
        this.handicap = handicap;
    }


}
