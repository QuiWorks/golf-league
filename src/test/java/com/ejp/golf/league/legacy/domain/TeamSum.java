//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.06.05 at 02:28:26 PM CDT 
//


package com.ejp.golf.league.legacy.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Ctr" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Flight" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="Team" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="TeamName" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="120"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Wins" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Losses" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Ties" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="TotalPoints" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Matchs" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="PlayOffs" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Seed" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Place" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Active" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Paid" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ctr",
    "flight",
    "team",
    "teamName",
    "wins",
    "losses",
    "ties",
    "totalPoints",
    "matchs",
    "status",
    "playOffs",
    "seed",
    "place",
    "active",
    "paid"
})
@XmlRootElement(name = "TeamSum")
public class TeamSum {

    @XmlElement(name = "Ctr")
    protected int ctr;
    @XmlElement(name = "Flight")
    @XmlSchemaType(name = "unsignedByte")
    protected Short flight;
    @XmlElement(name = "Team")
    protected Integer team;
    @XmlElement(name = "TeamName")
    protected String teamName;
    @XmlElement(name = "Wins")
    protected Integer wins;
    @XmlElement(name = "Losses")
    protected Integer losses;
    @XmlElement(name = "Ties")
    protected Integer ties;
    @XmlElement(name = "TotalPoints")
    protected Integer totalPoints;
    @XmlElement(name = "Matchs")
    protected Integer matchs;
    @XmlElement(name = "Status")
    protected boolean status;
    @XmlElement(name = "PlayOffs")
    protected boolean playOffs;
    @XmlElement(name = "Seed")
    protected Integer seed;
    @XmlElement(name = "Place")
    protected Integer place;
    @XmlElement(name = "Active")
    protected boolean active;
    @XmlElement(name = "Paid")
    protected boolean paid;

    /**
     * Gets the value of the ctr property.
     * 
     */
    public int getCtr() {
        return ctr;
    }

    /**
     * Sets the value of the ctr property.
     * 
     */
    public void setCtr(int value) {
        this.ctr = value;
    }

    /**
     * Gets the value of the flight property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getFlight() {
        return flight;
    }

    /**
     * Sets the value of the flight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setFlight(Short value) {
        this.flight = value;
    }

    /**
     * Gets the value of the team property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTeam() {
        return team;
    }

    /**
     * Sets the value of the team property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTeam(Integer value) {
        this.team = value;
    }

    /**
     * Gets the value of the teamName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Sets the value of the teamName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeamName(String value) {
        this.teamName = value;
    }

    /**
     * Gets the value of the wins property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWins() {
        return wins;
    }

    /**
     * Sets the value of the wins property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWins(Integer value) {
        this.wins = value;
    }

    /**
     * Gets the value of the losses property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLosses() {
        return losses;
    }

    /**
     * Sets the value of the losses property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLosses(Integer value) {
        this.losses = value;
    }

    /**
     * Gets the value of the ties property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTies() {
        return ties;
    }

    /**
     * Sets the value of the ties property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTies(Integer value) {
        this.ties = value;
    }

    /**
     * Gets the value of the totalPoints property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalPoints() {
        return totalPoints;
    }

    /**
     * Sets the value of the totalPoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalPoints(Integer value) {
        this.totalPoints = value;
    }

    /**
     * Gets the value of the matchs property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMatchs() {
        return matchs;
    }

    /**
     * Sets the value of the matchs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMatchs(Integer value) {
        this.matchs = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(boolean value) {
        this.status = value;
    }

    /**
     * Gets the value of the playOffs property.
     * 
     */
    public boolean isPlayOffs() {
        return playOffs;
    }

    /**
     * Sets the value of the playOffs property.
     * 
     */
    public void setPlayOffs(boolean value) {
        this.playOffs = value;
    }

    /**
     * Gets the value of the seed property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSeed() {
        return seed;
    }

    /**
     * Sets the value of the seed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSeed(Integer value) {
        this.seed = value;
    }

    /**
     * Gets the value of the place property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPlace() {
        return place;
    }

    /**
     * Sets the value of the place property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPlace(Integer value) {
        this.place = value;
    }

    /**
     * Gets the value of the active property.
     * 
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the value of the active property.
     * 
     */
    public void setActive(boolean value) {
        this.active = value;
    }

    /**
     * Gets the value of the paid property.
     * 
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Sets the value of the paid property.
     * 
     */
    public void setPaid(boolean value) {
        this.paid = value;
    }

}
