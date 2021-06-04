package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "hole_par")
@IdClass(HoleParPK.class)
public class HolePar {
    private int holeId;
    private Timestamp created;
    private int par;

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
    @Column(name = "par", nullable = false)
    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HolePar holePar = (HolePar) o;
        return holeId == holePar.holeId && par == holePar.par && Objects.equals(created, holePar.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(holeId, created, par);
    }
}
