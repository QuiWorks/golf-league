package com.ejp.golf.league.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalTime;
import java.util.Objects;

@Entity(name = "flight")
public class Flight {
    private int id;
    private int leagueId;
    private LocalTime start;
    private LocalTime end;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "league_id", nullable = false)
    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    @Basic
    @Column(name = "start", nullable = false)
    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    @Basic
    @Column(name = "end", nullable = false)
    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id && leagueId == flight.leagueId && Objects.equals(start, flight.start) && Objects.equals(end, flight.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, leagueId, start, end);
    }
}
