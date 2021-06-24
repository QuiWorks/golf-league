package com.ejp.golf.league.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TeeTimePK implements Serializable {
    private int flightId;
    private int slot;
    private int leagueId;

    @Column(name = "flight_id", nullable = false)
    @Id
    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    @Column(name = "slot", nullable = false)
    @Id
    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeeTimePK scorePK = (TeeTimePK) o;
        return flightId == scorePK.flightId && slot == scorePK.slot;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, slot);
    }

    @Column(name = "league_id", nullable = false)
    @Id
    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }
}
