package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity(name = "golfer")
public class Golfer {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String city;
    private String state;
    private String zip;
    private String homePhone;
    private String workPhone;
    private String notes;
    private Boolean active;
    private LocalDateTime dateAdded;
    private List<Team> teams;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 128)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 128)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "middle_name", nullable = true, length = 128)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 256)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 128)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "state", nullable = true, length = 128)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "zip", nullable = true, length = 128)
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Basic
    @Column(name = "home_phone", nullable = true, length = 128)
    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    @Basic
    @Column(name = "work_phone", nullable = true, length = 128)
    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    @Basic
    @Column(name = "notes", nullable = true, length = 256)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic
    @Column(name = "active", nullable = true)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "date_added", nullable = false)
    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "team_member",
            joinColumns = { @JoinColumn(name = "golfer_id") },
            inverseJoinColumns = { @JoinColumn(name = "team_id")}
    )
    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Optional<Team> teamForLeague(League league)
    {
        return teams.stream()
                .filter(team -> team.getLeagueId() == league.getId())
                .findAny();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Golfer golfer = (Golfer) o;
        return id == golfer.id && Objects.equals(firstName, golfer.firstName) && Objects.equals(lastName, golfer.lastName) && Objects.equals(middleName, golfer.middleName) && Objects.equals(email, golfer.email) && Objects.equals(city, golfer.city) && Objects.equals(state, golfer.state) && Objects.equals(zip, golfer.zip) && Objects.equals(homePhone, golfer.homePhone) && Objects.equals(workPhone, golfer.workPhone) && Objects.equals(notes, golfer.notes) && Objects.equals(active, golfer.active) && Objects.equals(dateAdded, golfer.dateAdded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, middleName, email, city, state, zip, homePhone, workPhone, notes, active, dateAdded);
    }

    public String fullName() {
        return getFirstName() + " " + getLastName();
    }
}
