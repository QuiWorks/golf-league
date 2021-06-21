package com.ejp.golf.league.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "team")
public class Team {
    private int id;
    private int leagueId;
    private String name;
    private String description;
    private List<Golfer> golferList;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "name", nullable = true, length = 128)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 128)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "team_member",
            joinColumns = { @JoinColumn(name = "team_id") },
            inverseJoinColumns = { @JoinColumn(name = "golfer_id")}
    )
    public List<Golfer> getGolferList() {
        return golferList;
    }

    public void setGolferList(List<Golfer> golferList) {
        this.golferList = golferList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id == team.id && leagueId == team.leagueId && Objects.equals(name, team.name) && Objects.equals(description, team.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, leagueId, name, description);
    }
}
