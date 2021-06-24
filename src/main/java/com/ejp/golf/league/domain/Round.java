package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.*;

@Entity(name = "round")
public class Round {
    private int id;
    private EventMatch eventMatch;
    private TeamMatch teamMatch;
    private int handicap;
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

    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "id", nullable = false)
    public EventMatch getEventMatch() {
        return eventMatch;
    }

    public void setEventMatch(EventMatch match) {
        this.eventMatch = match;
    }

    @ManyToOne
    @JoinTable(name = "team_match", joinColumns = {
            @JoinColumn(name = "match_id", referencedColumnName = "match_id", nullable = false),
            @JoinColumn(name = "team_id", referencedColumnName = "team_id", nullable = false),
            @JoinColumn(name = "league_id", referencedColumnName = "league_id", nullable = false),
            @JoinColumn(name = "flight_id", referencedColumnName = "flight_id", nullable = false)})

    public TeamMatch getTeamMatch() {
        return teamMatch;
    }

    public void setTeamMatch(TeamMatch match) {
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
        return eventMatch.getEvent().getWeek();
    }

    public int flight() {
        return eventMatch.getFlightId();
    }

    public int slot() {
        return eventMatch.getSlot();
    }

    public String nine() {
        return eventMatch.getNine();
    }

    public boolean home() {
        return teamMatch.isHome();
    }

}
