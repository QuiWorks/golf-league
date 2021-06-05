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
 *         &lt;element name="Week" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Flight" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Team1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Flight2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Team2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Slot" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="Back9" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Hole" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
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
    "week",
    "flight",
    "team1",
    "flight2",
    "team2",
    "slot",
    "back9",
    "status",
    "hole"
})
@XmlRootElement(name = "Schedule")
public class Schedule {

    @XmlElement(name = "Ctr")
    protected int ctr;
    @XmlElement(name = "Week")
    protected Integer week;
    @XmlElement(name = "Flight")
    protected Integer flight;
    @XmlElement(name = "Team1")
    protected Integer team1;
    @XmlElement(name = "Flight2")
    protected Integer flight2;
    @XmlElement(name = "Team2")
    protected Integer team2;
    @XmlElement(name = "Slot")
    @XmlSchemaType(name = "unsignedByte")
    protected Short slot;
    @XmlElement(name = "Back9")
    protected boolean back9;
    @XmlElement(name = "Status")
    protected boolean status;
    @XmlElement(name = "Hole")
    protected String hole;

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
     * Gets the value of the week property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWeek() {
        return week;
    }

    /**
     * Sets the value of the week property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWeek(Integer value) {
        this.week = value;
    }

    /**
     * Gets the value of the flight property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFlight() {
        return flight;
    }

    /**
     * Sets the value of the flight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFlight(Integer value) {
        this.flight = value;
    }

    /**
     * Gets the value of the team1 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTeam1() {
        return team1;
    }

    /**
     * Sets the value of the team1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTeam1(Integer value) {
        this.team1 = value;
    }

    /**
     * Gets the value of the flight2 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFlight2() {
        return flight2;
    }

    /**
     * Sets the value of the flight2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFlight2(Integer value) {
        this.flight2 = value;
    }

    /**
     * Gets the value of the team2 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTeam2() {
        return team2;
    }

    /**
     * Sets the value of the team2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTeam2(Integer value) {
        this.team2 = value;
    }

    /**
     * Gets the value of the slot property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getSlot() {
        return slot;
    }

    /**
     * Sets the value of the slot property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setSlot(Short value) {
        this.slot = value;
    }

    /**
     * Gets the value of the back9 property.
     * 
     */
    public boolean isBack9() {
        return back9;
    }

    /**
     * Sets the value of the back9 property.
     * 
     */
    public void setBack9(boolean value) {
        this.back9 = value;
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
     * Gets the value of the hole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHole() {
        return hole;
    }

    /**
     * Sets the value of the hole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHole(String value) {
        this.hole = value;
    }

}
