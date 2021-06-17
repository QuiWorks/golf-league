package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity(name = "round")
public class Round {
    private int id;
    private int matchId;
    private int golferId;
    private Golfer golfer;
    private GolferMatch golferMatch;
    private List<Score> grossScores;

    @OneToOne(targetEntity = Golfer.class, mappedBy = "id")
    public Golfer getGolfer() {
        return golfer;
    }

    public void setGolfer(Golfer golfer) {
        this.golfer = golfer;
    }


    @OneToOne(targetEntity = GolferMatch.class, mappedBy = "pk")
    public GolferMatch getGolferMatch() {
        return golferMatch;
    }

    public void setGolferMatch(GolferMatch golferMatch) {
        this.golferMatch = golferMatch;
    }

    @OneToMany(targetEntity = Score.class, mappedBy = "roundId")
    public List<Score> getGrossScores() {
        return grossScores;
    }

    public void setGrossScores(List<Score> grossScores) {
        this.grossScores = grossScores;
    }

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
