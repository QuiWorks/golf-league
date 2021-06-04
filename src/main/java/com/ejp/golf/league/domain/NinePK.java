package com.ejp.golf.league.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class NinePK implements Serializable {
    private int courseId;
    private String name;

    @Column(name = "course_id", nullable = false)
    @Id
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Column(name = "name", nullable = false, length = 256)
    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NinePK ninePK = (NinePK) o;
        return courseId == ninePK.courseId && Objects.equals(name, ninePK.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, name);
    }
}
