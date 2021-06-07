package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name ="Tee")
public class Tee {
    private String id;

    @Id
    @Column(name = "id", nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String roundId) {
        this.id = roundId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tee score1 = (Tee) o;
        return Objects.equals(id, score1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
