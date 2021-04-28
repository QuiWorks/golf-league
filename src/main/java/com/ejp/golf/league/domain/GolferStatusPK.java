package com.ejp.golf.league.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class GolferStatusPK implements Serializable {
    private int golferId;
    private String statusId;

    @Column(name = "golfer_id", nullable = false)
    @Id
    public int getGolferId() {
        return golferId;
    }

    public void setGolferId(int golferId) {
        this.golferId = golferId;
    }

    @Column(name = "status_id", nullable = false, length = 128)
    @Id
    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GolferStatusPK that = (GolferStatusPK) o;
        return golferId == that.golferId && Objects.equals(statusId, that.statusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(golferId, statusId);
    }
}
