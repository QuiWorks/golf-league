package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Round {
    private int id;
    private int matchId;
    private EventMatch eventMatch;
    private TeamMatch teamMatch;
    private int handicap;
    private Date date;
    private Golfer golfer = new Golfer();
    private List<Score> grossScores = new ArrayList<>();
//    private int week;
    private int golferId;

    @ManyToOne
    @JoinColumn(name = "golfer_id", referencedColumnName = "id", nullable = false)
    public Golfer getGolfer() {
        return golfer;
    }

    public void setGolfer(Golfer golfer) {
        this.golfer = golfer;
    }

    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "id", nullable = false)
    public EventMatch getEventMatch() {
        return eventMatch;
    }

    public void setEventMatch(EventMatch match) {
        this.eventMatch = match;
    }

    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "match_id", nullable = false)
    public TeamMatch getMatch() {
        return teamMatch;
    }

    public void setMatch(TeamMatch match) {
        this.teamMatch = match;
    }

    @OneToMany
    @JoinColumn(name = "round_id")
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
    @Column(name = "date_played", nullable = false)
    public Date getDatePlayed() {
        return date;
    }

    public void setDatePlayed(Date date) {
        this.date = date;
    }


    public int week() {
//        return match.get
    }

//    public void setWeek(int week) {
//        this.week = week;
//    }

    public int flight() {
        return eventMatch.getFlightId();
    }

//    public void setFlightId(int eventId) {
//        this.flightId = eventId;
//    }

    public int slot() {
        return eventMatch.getSlot();
    }

//    public void setSlot(int slot) {
//        this.slot = slot;
//    }

    public String nine() {
        return eventMatch.getNine();
    }

    //    public void setNine(String nine) {
//        this.nine = nine;
//    }

    public boolean home() {
        return eventMatch.get;
    }

//    public void setHome(boolean home) {
//        this.home = home;
//    }

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

    @Basic
    @Column(name = "golfer_id", nullable = false)
    public int getGolferId() {
        return golferId;
    }

    public void setGolferId(int golferId) {
        this.golferId = golferId;
    }
}
