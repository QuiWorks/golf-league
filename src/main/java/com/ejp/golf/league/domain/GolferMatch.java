package com.ejp.golf.league.domain;

import javax.persistence.*;

@Entity(name = "golfer_match")
public class GolferMatch {
    private GolferMatchPK pk;
    private int handicap;

    @EmbeddedId
    public GolferMatchPK getPk() {
        return pk;
    }

    public void setPk(GolferMatchPK pk) {
        this.pk = pk;
    }

    public int getMatchId() {
        return pk.getMatchId();
    }

    public void setMatchId(int matchId) {
        pk.setMatchId(matchId);
    }

    public int getGolferId() {
        return pk.getGolferId();
    }

    public void setGolferId(int golferId) {
        pk.setGolferId(golferId);
    }

    @Column(name = "handicap", nullable = false)
    public int getHandicap() {
        return handicap;
    }

    public void setHandicap(int handicap) {
        this.handicap = handicap;
    }


}
