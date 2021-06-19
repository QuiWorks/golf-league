package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "event")
public class Event {
    private int id;
    private int seasonId;
    private int week;
    private LocalDate day;
    private String eventType;
    private String notes;

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
    @Column(name = "season_id", nullable = false)
    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    @Basic
    @Column(name = "week", nullable = false)
    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    @Basic
    @Column(name = "day", nullable = false)
    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    @Basic
    @Column(name = "event_type", nullable = false, length = 128)
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Basic
    @Column(name = "notes", nullable = true, length = 256)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return seasonId == event.seasonId && Objects.equals(day, event.day) && Objects.equals(eventType, event.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seasonId, day, eventType, notes);
    }
}
