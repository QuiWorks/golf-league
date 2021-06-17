package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name ="score")
public class Score {
    private int id;
    private int roundId;
    private int holeId;
    private Hole hole;
    private int score;
    private boolean win;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "round_id", nullable = false)
    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    @Basic
    @Column(name = "hole_id", nullable = false)
    public int getHoleId() {
        return holeId;
    }

    public void setHoleId(int holeId) {
        this.holeId = holeId;
    }

    @ManyToOne(targetEntity = Hole.class)
    public Hole getHole(){
        return hole;
    }

    public void setHole(Hole hole)
    {
        this.hole = hole;
    }

    @Basic
    @Column(name = "score", nullable = false)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return id == score1.id && roundId == score1.roundId && holeId == score1.holeId && score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roundId, holeId, score);
    }
}
