package com.ejp.golf.league.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class HoleParPK implements Serializable {
    private int holeId;
    private Timestamp created;

    @Column(name = "hole_id", nullable = false)
    @Id
    public int getHoleId() {
        return holeId;
    }

    public void setHoleId(int holeId) {
        this.holeId = holeId;
    }

    @Column(name = "created", nullable = false)
    @Id
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HoleParPK holeParPK = (HoleParPK) o;
        return holeId == holeParPK.holeId && Objects.equals(created, holeParPK.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(holeId, created);
    }
}
