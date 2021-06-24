package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "event_type")
public class EventType {
    private String name;
    private String description;

    @Id
    @Column(name = "name", nullable = false, length = 128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 256)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventType eventType = (EventType) o;
        return Objects.equals(name, eventType.name) && Objects.equals(description, eventType.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
