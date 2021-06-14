package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "round")
public class Round {
    private int id;
    private int matchId;
    private int golferId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "match_id", nullable = false)
    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int eventId) {
        this.matchId = eventId;
    }

    @Basic
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
        Round round = (Round) o;
        return id == round.id && matchId == round.matchId && golferId == round.golferId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matchId, golferId);
    }
}
