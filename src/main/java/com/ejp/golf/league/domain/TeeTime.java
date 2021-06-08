package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Objects;

@Entity(name ="tee_time")
@IdClass(TeeTimePK.class)
public class TeeTime {
    private int flightId;
    private int slot;
    private LocalTime time;

    @Id
    @Column(name = "flight_id", nullable = false)
    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    @Id
    @Column(name = "slot", nullable = false)
    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeeTime score1 = (TeeTime) o;
        return flightId == score1.flightId && slot == score1.slot && time == score1.time;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, slot, time);
    }
}
