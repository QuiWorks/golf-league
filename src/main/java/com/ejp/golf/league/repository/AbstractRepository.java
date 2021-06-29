package com.ejp.golf.league.repository;

public class AbstractRepository {

    protected int getTeamId(int flight, int teamNumber) {
        int teamId;
        if (String.valueOf(teamNumber).length() == 1) {
            teamId = Integer.parseInt(flight + "0" + teamNumber);
        } else {
            teamId = Integer.parseInt(String.valueOf(flight) + teamNumber);
        }
        return teamId;
    }
}
