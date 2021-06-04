package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "golfer_status")
@IdClass(GolferStatusPK.class)
public class GolferStatus {
    private int golferId;
    private String statusId;
    private Timestamp updated;

    @Id
    @Column(name = "golfer_id", nullable = false)
    public int getGolferId() {
        return golferId;
    }

    public void setGolferId(int golferId) {
        this.golferId = golferId;
    }

    @Id
    @Column(name = "status_id", nullable = false, length = 128)
    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "updated", nullable = false)
    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GolferStatus that = (GolferStatus) o;
        return golferId == that.golferId && Objects.equals(statusId, that.statusId) && Objects.equals(updated, that.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(golferId, statusId, updated);
    }
}
