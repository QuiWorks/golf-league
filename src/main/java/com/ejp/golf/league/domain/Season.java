package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "season")
public class Season {
    private int id;
    private int year;
    private int leagueId;
    private int courseId;

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
    @Column(name = "year", nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "league_id", nullable = false)
    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    @Basic
    @Column(name = "course_id", nullable = false)
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Season season = (Season) o;
        return id == season.id && year == season.year && leagueId == season.leagueId && courseId == season.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, leagueId, courseId);
    }
}
