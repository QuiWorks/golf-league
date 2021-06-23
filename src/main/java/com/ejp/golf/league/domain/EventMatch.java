package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity(name = "event_match")
public class EventMatch {
    private int id;
    private int eventId;
    private int courseId;
    private String nine;
    private int week;
    private int flightId;
    private int slot;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "event_id", nullable = false)
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int seasonId) {
        this.eventId = seasonId;
    }

    @Basic
    @Column(name = "course_id", nullable = false)
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "nine", nullable = false)
    public String getNine() {
        return nine;
    }

    public void setNine(String nine) {
        this.nine = nine;
    }

    @Basic
    @Column(name = "flight_id", nullable = false)
    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    @Basic
    @Column(name = "slot", nullable = false)
    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventMatch that = (EventMatch) o;
        return eventId == that.eventId && flightId == that.flightId && slot == that.slot && Objects.equals(nine, that.nine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventId, courseId, nine, flightId, slot);
    }
}
