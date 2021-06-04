package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name ="score")
@IdClass(ScorePK.class)
public class Score {
    private int roundId;
    private int holeId;
    private int score;

    @Id
    @Column(name = "round_id", nullable = false)
    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    @Id
    @Column(name = "hole_id", nullable = false)
    public int getHoleId() {
        return holeId;
    }

    public void setHoleId(int holeId) {
        this.holeId = holeId;
    }

    @Basic
    @Column(name = "score", nullable = false)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return roundId == score1.roundId && holeId == score1.holeId && score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundId, holeId, score);
    }
}
