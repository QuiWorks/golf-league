package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(FlightPK.class)
public class Flight {
    private int id;
    private int leagueId;
    private Date start;
    private Date end;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
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
    public Date getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @Basic
    @Column(name = "end", nullable = false)
    public Date getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public void setEnd(Date end) {
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
