package com.ejp.golf.league.domain;

import javax.persistence.*;

@Entity(name = "team_match")
@IdClass(TeamMatchPK.class)
public class TeamMatch {
    private int matchId;
    private int teamId;
    private boolean home;

    @Id
    @Column(name = "match_id", nullable = false)
    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    @Id
    @Column(name = "team_id", nullable = false)
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Column(name = "home", nullable = false)
    public boolean isHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }


}
