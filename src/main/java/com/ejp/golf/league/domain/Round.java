package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.*;

@Entity(name = "round")
public class Round {
    private int id;
    private int matchId;
    private int handicap;
    private boolean home;
    private Date date;
    private Golfer golfer = new Golfer();
    private List<Score> grossScores = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "golfer_id", referencedColumnName = "id", nullable = false)
    public Golfer getGolfer() {
        return golfer;
    }

    public void setGolfer(Golfer golfer) {
        this.golfer = golfer;
    }

    @OneToMany(mappedBy = "score")
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
    @Column(name = "handicap", nullable = false)
    public int getHandicap() {
        return handicap;
    }

    public void setHandicap(int handicap) {
        this.handicap = handicap;
    }

    @Basic
    @Column(name = "home", nullable = false)
    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    @Basic
    @Column(name = "date_played", nullable = false)
    public Date getDatePlayed() {
        return date;
    }

    public void setDatePlayed(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return id == round.id && matchId == round.matchId && golfer.getId() == round.getGolfer().getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matchId, golfer.getId());
    }
}
