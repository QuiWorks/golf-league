package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "team")
@IdClass(TeamMatchPK.class)
public class TeamPK {
    private int id;
    private int leagueId;
    private int flightId;

    public TeamPK() {
    }

    public TeamPK(int id, int leagueId, int flightId) {
        this.id = id;
        this.leagueId = leagueId;
        this.flightId = flightId;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Id
    @Column(name = "flight_id", nullable = false)
    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
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
