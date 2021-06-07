package com.ejp.golf.league.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ScorePK implements Serializable {
    private int roundId;
    private int holeId;

    @Column(name = "round_id", nullable = false)
    @Id
    public int getId() {
        return roundId;
    }

    public void setId(int roundId) {
        this.roundId = roundId;
    }

    @Column(name = "hole_id", nullable = false)
    @Id
    public int getHoleId() {
        return holeId;
    }

    public void setHoleId(int holeId) {
        this.holeId = holeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScorePK scorePK = (ScorePK) o;
        return roundId == scorePK.roundId && holeId == scorePK.holeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundId, holeId);
    }
}
