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
 *         &lt;element name="Home1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Away1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Home2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Away2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Home3" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Away3" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Home4" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Away4" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Home5" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Away5" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Home6" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Away6" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Home7" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Away7" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Home8" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Away8" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Home9" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Away9" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Back9" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
    "home1",
    "away1",
    "home2",
    "away2",
    "home3",
    "away3",
    "home4",
    "away4",
    "home5",
    "away5",
    "home6",
    "away6",
    "home7",
    "away7",
    "home8",
    "away8",
    "home9",
    "away9",
    "back9"
})
@XmlRootElement(name = "ScheduleMaster")
public class ScheduleMaster {

    @XmlElement(name = "Ctr")
    protected int ctr;
    @XmlElement(name = "Week")
    protected Integer week;
    @XmlElement(name = "Flight")
    protected Integer flight;
    @XmlElement(name = "Home1")
    protected Integer home1;
    @XmlElement(name = "Away1")
    protected Integer away1;
    @XmlElement(name = "Home2")
    protected Integer home2;
    @XmlElement(name = "Away2")
    protected Integer away2;
    @XmlElement(name = "Home3")
    protected Integer home3;
    @XmlElement(name = "Away3")
    protected Integer away3;
    @XmlElement(name = "Home4")
    protected Integer home4;
    @XmlElement(name = "Away4")
    protected Integer away4;
    @XmlElement(name = "Home5")
    protected Integer home5;
    @XmlElement(name = "Away5")
    protected Integer away5;
    @XmlElement(name = "Home6")
    protected Integer home6;
    @XmlElement(name = "Away6")
    protected Integer away6;
    @XmlElement(name = "Home7")
    protected Integer home7;
    @XmlElement(name = "Away7")
    protected Integer away7;
    @XmlElement(name = "Home8")
    protected Integer home8;
    @XmlElement(name = "Away8")
    protected Integer away8;
    @XmlElement(name = "Home9")
    protected Integer home9;
    @XmlElement(name = "Away9")
    protected Integer away9;
    @XmlElement(name = "Back9")
    protected boolean back9;

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
     * Gets the value of the home1 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHome1() {
        return home1;
    }

    /**
     * Sets the value of the home1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHome1(Integer value) {
        this.home1 = value;
    }

    /**
     * Gets the value of the away1 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAway1() {
        return away1;
    }

    /**
     * Sets the value of the away1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAway1(Integer value) {
        this.away1 = value;
    }

    /**
     * Gets the value of the home2 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHome2() {
        return home2;
    }

    /**
     * Sets the value of the home2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHome2(Integer value) {
        this.home2 = value;
    }

    /**
     * Gets the value of the away2 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAway2() {
        return away2;
    }

    /**
     * Sets the value of the away2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAway2(Integer value) {
        this.away2 = value;
    }

    /**
     * Gets the value of the home3 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHome3() {
        return home3;
    }

    /**
     * Sets the value of the home3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHome3(Integer value) {
        this.home3 = value;
    }

    /**
     * Gets the value of the away3 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAway3() {
        return away3;
    }

    /**
     * Sets the value of the away3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAway3(Integer value) {
        this.away3 = value;
    }

    /**
     * Gets the value of the home4 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHome4() {
        return home4;
    }

    /**
     * Sets the value of the home4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHome4(Integer value) {
        this.home4 = value;
    }

    /**
     * Gets the value of the away4 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAway4() {
        return away4;
    }

    /**
     * Sets the value of the away4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAway4(Integer value) {
        this.away4 = value;
    }

    /**
     * Gets the value of the home5 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHome5() {
        return home5;
    }

    /**
     * Sets the value of the home5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHome5(Integer value) {
        this.home5 = value;
    }

    /**
     * Gets the value of the away5 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAway5() {
        return away5;
    }

    /**
     * Sets the value of the away5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAway5(Integer value) {
        this.away5 = value;
    }

    /**
     * Gets the value of the home6 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHome6() {
        return home6;
    }

    /**
     * Sets the value of the home6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHome6(Integer value) {
        this.home6 = value;
    }

    /**
     * Gets the value of the away6 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAway6() {
        return away6;
    }

    /**
     * Sets the value of the away6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAway6(Integer value) {
        this.away6 = value;
    }

    /**
     * Gets the value of the home7 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHome7() {
        return home7;
    }

    /**
     * Sets the value of the home7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHome7(Integer value) {
        this.home7 = value;
    }

    /**
     * Gets the value of the away7 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAway7() {
        return away7;
    }

    /**
     * Sets the value of the away7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAway7(Integer value) {
        this.away7 = value;
    }

    /**
     * Gets the value of the home8 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHome8() {
        return home8;
    }

    /**
     * Sets the value of the home8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHome8(Integer value) {
        this.home8 = value;
    }

    /**
     * Gets the value of the away8 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAway8() {
        return away8;
    }

    /**
     * Sets the value of the away8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAway8(Integer value) {
        this.away8 = value;
    }

    /**
     * Gets the value of the home9 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHome9() {
        return home9;
    }

    /**
     * Sets the value of the home9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHome9(Integer value) {
        this.home9 = value;
    }

    /**
     * Gets the value of the away9 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAway9() {
        return away9;
    }

    /**
     * Sets the value of the away9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAway9(Integer value) {
        this.away9 = value;
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

}
