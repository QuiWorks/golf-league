package com.ejp.golf.league.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class User {
    private String username;
    private String password;
    private int golferId;
    private Timestamp dateAdded;

    @Id
    @Column(name = "username", nullable = false, length = 128)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 1024)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "golfer_id", nullable = false)
    public int getGolferId() {
        return golferId;
    }

    public void setGolferId(int golferId) {
        this.golferId = golferId;
    }

    @Basic
    @Column(name = "date_added", nullable = false)
    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return golferId == user.golferId && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(dateAdded, user.dateAdded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, golferId, dateAdded);
    }
}
