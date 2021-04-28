package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "hole_handicap", schema = "golf_league")
@IdClass(HoleHandicapPK.class)
public class HoleHandicap {
    private int holeId;
    private Timestamp created;
    private int handicap;

    @Id
    @Column(name = "hole_id", nullable = false)
    public int getHoleId() {
        return holeId;
    }

    public void setHoleId(int holeId) {
        this.holeId = holeId;
    }

    @Id
    @Column(name = "created", nullable = false)
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "handicap", nullable = false)
    public int getHandicap() {
        return handicap;
    }

    public void setHandicap(int handicap) {
        this.handicap = handicap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoleHandicap that = (HoleHandicap) o;
        return holeId == that.holeId && handicap == that.handicap && Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(holeId, created, handicap);
    }
}
