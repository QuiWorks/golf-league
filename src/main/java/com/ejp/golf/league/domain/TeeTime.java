package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@IdClass(TeeTimePK.class)
@Table(name = "tee_time", schema = "golf_league", catalog = "")
public class TeeTime {
    private int flightId;
    private int slot;
    private Date time;
    private int leagueId;

    @Id
    @Column(name = "flight_id", nullable = false)
    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    @Id
    @Column(name = "slot", nullable = false)
    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Date getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeeTime score1 = (TeeTime) o;
        return flightId == score1.flightId && slot == score1.slot && time == score1.time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, slot, time);
    }

    @Id
    @Column(name = "league_id", nullable = false)
    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }
}
