package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Entity(name = "round")
public class Round {
    private int id;
    private int flightId;
    private int slot;
    private String nine;
    private int handicap;
    private boolean home;
    private Timestamp date;
    private Golfer golfer = new Golfer();
    private List<Score> grossScores = new ArrayList<>();
    private EventMatch match;

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
        return match;
    }

    public void setEventMatch(EventMatch match) {
        this.match = match;
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

    public int matchId()
    {
        return match.getId();
    }

    public int week()
    {
        return match.getEvent().getWeek();
    }

    @Basic
    @Column(name = "flight_id", nullable = false)
    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int eventId) {
        this.flightId = eventId;
    }

    @Basic
    @Column(name = "slot", nullable = false)
    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Basic
    @Column(name = "nine", nullable = false)
    public String getNine() {
        return nine;
    }

    public void setNine(String nine) {
        this.nine = nine;
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
    public Timestamp getDatePlayed() {
        return date;
    }

    public void setDatePlayed(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return id == round.id && matchId() == round.matchId() && golfer.getId() == round.getGolfer().getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matchId(), golfer.getId());
    }

    @Override
    public String toString() {
        return "Round{" +
                "id=" + id +
                ", matchId=" + matchId() +
                ", flightId=" + flightId +
                ", slot=" + slot +
                ", nine='" + nine + '\'' +
                ", handicap=" + handicap +
                ", home=" + home +
                ", date=" + date +
                ", golfer=" + golfer +
                ", grossScores=" + grossScores +
                '}';
    }
}
