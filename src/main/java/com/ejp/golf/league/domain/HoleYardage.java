package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "hole_yardage", schema = "golf_league")
@IdClass(HoleYardagePK.class)
public class HoleYardage {
    private int holeId;
    private Timestamp created;
    private int yardage;

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
    @Column(name = "yardage", nullable = false)
    public int getYardage() {
        return yardage;
    }

    public void setYardage(int yardage) {
        this.yardage = yardage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoleYardage that = (HoleYardage) o;
        return holeId == that.holeId && yardage == that.yardage && Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(holeId, created, yardage);
    }
}
