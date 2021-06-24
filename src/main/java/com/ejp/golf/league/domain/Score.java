package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Score implements Cloneable{
    private int id;
    private Round round = new Round();
    private Hole hole = new Hole();
    private int score;
    private boolean win;
    private int roundId;
    private int holeId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "round_id", referencedColumnName = "id", nullable = false)
    public Round getRound(){
        return round;
    }

    public void setRound(Round round)
    {
        this.round = round;
    }

    @ManyToOne
    @JoinColumn(name = "hole_id", referencedColumnName = "id", nullable = false)
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

    @Transient
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
        return id == score1.id && round.getId() == score1.round.getId() && hole.getId() == score1.getHole().getId() && score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, round.getId(), hole.getId(), score);
    }

    @Override
    public Score clone() {
        try {
            Score clone = (Score) super.clone();
            clone.setId(getId());
            clone.setHole(getHole());
            clone.setScore(getScore());
            clone.setRound(getRound());
            clone.setWin(isWin());
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
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
}
