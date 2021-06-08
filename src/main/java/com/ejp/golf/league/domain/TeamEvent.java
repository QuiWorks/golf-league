package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "team_event")
@IdClass(TeamEventPK.class)
public class TeamEvent {
    private int matchId;
    private int teamId;
    private int handicap;
    private boolean home;

    @Id
    @Column(name = "match_id", nullable = false)
    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int eventId) {
        this.matchId = eventId;
    }

    @Id
    @Column(name = "team_id", nullable = false)
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Column(name = "hdcp", nullable = false)
    public int getHandicap() {
        return handicap;
    }

    public void setHandicap(int flightId) {
        this.handicap = flightId;
    }

    @Column(name = "home", nullable = false)
    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }
}
