package com.ejp.golf.league.model;

public class RoundSummary {
    //Known data.
    private int matchId;
    private boolean homeTeam;
    private int teamNumber;
    private String name;
    private int[] grossHoleScores;
    private int grossMatchScore;
    private int handicap;
    private int newMatchScore;
    private int[] netHoleScores;

    // Calculated data.
    private int netPoints;
    private int matchPoints;
    private float teamNet;

    public RoundSummary(int matchId, boolean homeTeam, int teamNumber, String name, int[] grossHoleScores, int grossMatchScore, int handicap, int newMatchScore, int[] netHoleScores) {
        this.matchId = matchId;
        this.homeTeam = homeTeam;
        this.teamNumber = teamNumber;
        this.name = name;
        this.grossHoleScores = grossHoleScores;
        this.grossMatchScore = grossMatchScore;
        this.handicap = handicap;
        this.newMatchScore = newMatchScore;
        this.netHoleScores = netHoleScores;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public boolean isHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(boolean homeTeam) {
        this.homeTeam = homeTeam;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getGrossHoleScores() {
        return grossHoleScores;
    }

    public void setGrossHoleScores(int[] grossHoleScores) {
        this.grossHoleScores = grossHoleScores;
    }

    public int getGrossMatchScore() {
        return grossMatchScore;
    }

    public void setGrossMatchScore(int grossMatchScore) {
        this.grossMatchScore = grossMatchScore;
    }

    public int getHandicap() {
        return handicap;
    }

    public void setHandicap(int handicap) {
        this.handicap = handicap;
    }

    public int getNewMatchScore() {
        return newMatchScore;
    }

    public void setNewMatchScore(int newMatchScore) {
        this.newMatchScore = newMatchScore;
    }

    public int[] getNetHoleScores() {
        return netHoleScores;
    }

    public void setNetHoleScores(int[] netHoleScores) {
        this.netHoleScores = netHoleScores;
    }

    public int getNetPoints() {
        return netPoints;
    }

    public void setNetPoints(int netPoints) {
        this.netPoints = netPoints;
    }

    public int getMatchPoints() {
        return matchPoints;
    }

    public void setMatchPoints(int matchPoints) {
        this.matchPoints = matchPoints;
    }

    public float getTeamNet() {
        return teamNet;
    }

    public void setTeamNet(float teamNet) {
        this.teamNet = teamNet;
    }
}
