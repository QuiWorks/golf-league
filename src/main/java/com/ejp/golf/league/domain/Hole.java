package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity(name="hole")
public class Hole {
    private int id;
    private int courseId;
    private String nineName;
    private int holeNumber;
    private int handicap;
    private int par;
    private int yardage;
    private Timestamp created;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "nine_name", nullable = false, length = 256)
    public String getNineName() {
        return nineName;
    }

    public void setNineName(String nineName) {
        this.nineName = nineName;
    }

    @Basic
    @Column(name = "hole_number", nullable = false)
    public int getHoleNumber() {
        return holeNumber;
    }

    public void setHoleNumber(int holeNumber) {
        this.holeNumber = holeNumber;
    }
    @Basic
    @Column(name = "handicap", nullable = false)
    public int getHandicap() {
        return handicap;
    }

    public void setHandicap(int handicap) {
        this.handicap = handicap;
    }
    @Basic
    @Column(name = "par", nullable = false)
    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }
    @Basic
    @Column(name = "yardage", nullable = false)
    public int getYardage() {
        return yardage;
    }

    public void setYardage(int yardage) {
        this.yardage = yardage;
    }

    @Basic
    @Column(name = "created", nullable = false)
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
        Hole hole = (Hole) o;
        return id == hole.id && courseId == hole.courseId && holeNumber == hole.holeNumber && Objects.equals(nineName, hole.nineName) && Objects.equals(created, hole.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, nineName, holeNumber, created);
    }
}
