package com.ejp.golf.league.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@Entity(name = "nine")
@IdClass(NinePK.class)
public class Nine {
    private int courseId;
    private String name;

    @Id
    @Column(name = "course_id", nullable = false)
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Id
    @Column(name = "name", nullable = false, length = 256)
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
        Nine nine = (Nine) o;
        return courseId == nine.courseId && Objects.equals(name, nine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, name);
    }
}
