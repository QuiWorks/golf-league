package com.ejp.golf.league.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FlightPK implements Serializable {
    private int id;
    private int leagueId;

    @Column(name = "id", nullable = false)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "league_id", nullable = false)
    @Basic
    @Id
    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightPK flightPK = (FlightPK) o;
        return id == flightPK.id && leagueId == flightPK.leagueId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, leagueId);
    }
}
