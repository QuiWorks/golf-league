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
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="Counter" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="GameDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="Golfer1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Hdcp1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="a1" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="a2" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="a3" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="a4" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="a5" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="a6" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="a7" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="a8" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="a9" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="Golfer2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Hdcp2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="b1" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="b2" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="b3" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="b4" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="b5" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="b6" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="b7" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="b8" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="b9" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="Golfer3" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Hdcp3" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="c1" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="c2" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="c3" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="c4" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="c5" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="c6" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="c7" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="c8" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="c9" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="Golfer4" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Hdcp4" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="d1" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="d2" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="d3" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="d4" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="d5" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="d6" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="d7" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="d8" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="d9" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Week" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Flight" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Team1" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Flight2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Team2" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="Back9" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Slot" type="{http://www.w3.org/2001/XMLSchema}unsignedByte" minOccurs="0"/&gt;
 *         &lt;element name="Tees" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="50"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DatePlayed" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="WeekBucket" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
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
    "counter",
    "gameDate",
    "golfer1",
    "hdcp1",
    "a1",
    "a2",
    "a3",
    "a4",
    "a5",
    "a6",
    "a7",
    "a8",
    "a9",
    "golfer2",
    "hdcp2",
    "b1",
    "b2",
    "b3",
    "b4",
    "b5",
    "b6",
    "b7",
    "b8",
    "b9",
    "golfer3",
    "hdcp3",
    "c1",
    "c2",
    "c3",
    "c4",
    "c5",
    "c6",
    "c7",
    "c8",
    "c9",
    "golfer4",
    "hdcp4",
    "d1",
    "d2",
    "d3",
    "d4",
    "d5",
    "d6",
    "d7",
    "d8",
    "d9",
    "status",
    "week",
    "flight",
    "team1",
    "flight2",
    "team2",
    "back9",
    "slot",
    "tees",
    "datePlayed",
    "weekBucket"
})
@XmlRootElement(name = "ScoreCard")
public class ScoreCard {

    @XmlElement(name = "Counter")
    protected int counter;
    @XmlElement(name = "GameDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar gameDate;
    @XmlElement(name = "Golfer1")
    protected Integer golfer1;
    @XmlElement(name = "Hdcp1")
    protected Integer hdcp1;
    @XmlSchemaType(name = "unsignedByte")
    protected Short a1;
    @XmlSchemaType(name = "unsignedByte")
    protected Short a2;
    @XmlSchemaType(name = "unsignedByte")
    protected Short a3;
    @XmlSchemaType(name = "unsignedByte")
    protected Short a4;
    @XmlSchemaType(name = "unsignedByte")
    protected Short a5;
    @XmlSchemaType(name = "unsignedByte")
    protected Short a6;
    @XmlSchemaType(name = "unsignedByte")
    protected Short a7;
    @XmlSchemaType(name = "unsignedByte")
    protected Short a8;
    @XmlSchemaType(name = "unsignedByte")
    protected Short a9;
    @XmlElement(name = "Golfer2")
    protected Integer golfer2;
    @XmlElement(name = "Hdcp2")
    protected Integer hdcp2;
    @XmlSchemaType(name = "unsignedByte")
    protected Short b1;
    @XmlSchemaType(name = "unsignedByte")
    protected Short b2;
    @XmlSchemaType(name = "unsignedByte")
    protected Short b3;
    @XmlSchemaType(name = "unsignedByte")
    protected Short b4;
    @XmlSchemaType(name = "unsignedByte")
    protected Short b5;
    @XmlSchemaType(name = "unsignedByte")
    protected Short b6;
    @XmlSchemaType(name = "unsignedByte")
    protected Short b7;
    @XmlSchemaType(name = "unsignedByte")
    protected Short b8;
    @XmlSchemaType(name = "unsignedByte")
    protected Short b9;
    @XmlElement(name = "Golfer3")
    protected Integer golfer3;
    @XmlElement(name = "Hdcp3")
    protected Integer hdcp3;
    @XmlSchemaType(name = "unsignedByte")
    protected Short c1;
    @XmlSchemaType(name = "unsignedByte")
    protected Short c2;
    @XmlSchemaType(name = "unsignedByte")
    protected Short c3;
    @XmlSchemaType(name = "unsignedByte")
    protected Short c4;
    @XmlSchemaType(name = "unsignedByte")
    protected Short c5;
    @XmlSchemaType(name = "unsignedByte")
    protected Short c6;
    @XmlSchemaType(name = "unsignedByte")
    protected Short c7;
    @XmlSchemaType(name = "unsignedByte")
    protected Short c8;
    @XmlSchemaType(name = "unsignedByte")
    protected Short c9;
    @XmlElement(name = "Golfer4")
    protected Integer golfer4;
    @XmlElement(name = "Hdcp4")
    protected Integer hdcp4;
    @XmlSchemaType(name = "unsignedByte")
    protected Short d1;
    @XmlSchemaType(name = "unsignedByte")
    protected Short d2;
    protected Integer d3;
    @XmlSchemaType(name = "unsignedByte")
    protected Short d4;
    protected Integer d5;
    @XmlSchemaType(name = "unsignedByte")
    protected Short d6;
    @XmlSchemaType(name = "unsignedByte")
    protected Short d7;
    @XmlSchemaType(name = "unsignedByte")
    protected Short d8;
    @XmlSchemaType(name = "unsignedByte")
    protected Short d9;
    @XmlElement(name = "Status")
    protected boolean status;
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
    @XmlElement(name = "Back9")
    protected boolean back9;
    @XmlElement(name = "Slot")
    @XmlSchemaType(name = "unsignedByte")
    protected Short slot;
    @XmlElement(name = "Tees")
    protected String tees;
    @XmlElement(name = "DatePlayed")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar datePlayed;
    @XmlElement(name = "WeekBucket")
    protected Integer weekBucket;

    /**
     * Gets the value of the counter property.
     * 
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Sets the value of the counter property.
     * 
     */
    public void setCounter(int value) {
        this.counter = value;
    }

    /**
     * Gets the value of the gameDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGameDate() {
        return gameDate;
    }

    /**
     * Sets the value of the gameDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGameDate(XMLGregorianCalendar value) {
        this.gameDate = value;
    }

    /**
     * Gets the value of the golfer1 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGolfer1() {
        return golfer1;
    }

    /**
     * Sets the value of the golfer1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGolfer1(Integer value) {
        this.golfer1 = value;
    }

    /**
     * Gets the value of the hdcp1 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHdcp1() {
        return hdcp1;
    }

    /**
     * Sets the value of the hdcp1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHdcp1(Integer value) {
        this.hdcp1 = value;
    }

    /**
     * Gets the value of the a1 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getA1() {
        return a1;
    }

    /**
     * Sets the value of the a1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setA1(Short value) {
        this.a1 = value;
    }

    /**
     * Gets the value of the a2 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getA2() {
        return a2;
    }

    /**
     * Sets the value of the a2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setA2(Short value) {
        this.a2 = value;
    }

    /**
     * Gets the value of the a3 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getA3() {
        return a3;
    }

    /**
     * Sets the value of the a3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setA3(Short value) {
        this.a3 = value;
    }

    /**
     * Gets the value of the a4 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getA4() {
        return a4;
    }

    /**
     * Sets the value of the a4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setA4(Short value) {
        this.a4 = value;
    }

    /**
     * Gets the value of the a5 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getA5() {
        return a5;
    }

    /**
     * Sets the value of the a5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setA5(Short value) {
        this.a5 = value;
    }

    /**
     * Gets the value of the a6 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getA6() {
        return a6;
    }

    /**
     * Sets the value of the a6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setA6(Short value) {
        this.a6 = value;
    }

    /**
     * Gets the value of the a7 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getA7() {
        return a7;
    }

    /**
     * Sets the value of the a7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setA7(Short value) {
        this.a7 = value;
    }

    /**
     * Gets the value of the a8 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getA8() {
        return a8;
    }

    /**
     * Sets the value of the a8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setA8(Short value) {
        this.a8 = value;
    }

    /**
     * Gets the value of the a9 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getA9() {
        return a9;
    }

    /**
     * Sets the value of the a9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setA9(Short value) {
        this.a9 = value;
    }

    /**
     * Gets the value of the golfer2 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGolfer2() {
        return golfer2;
    }

    /**
     * Sets the value of the golfer2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGolfer2(Integer value) {
        this.golfer2 = value;
    }

    /**
     * Gets the value of the hdcp2 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHdcp2() {
        return hdcp2;
    }

    /**
     * Sets the value of the hdcp2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHdcp2(Integer value) {
        this.hdcp2 = value;
    }

    /**
     * Gets the value of the b1 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getB1() {
        return b1;
    }

    /**
     * Sets the value of the b1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setB1(Short value) {
        this.b1 = value;
    }

    /**
     * Gets the value of the b2 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getB2() {
        return b2;
    }

    /**
     * Sets the value of the b2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setB2(Short value) {
        this.b2 = value;
    }

    /**
     * Gets the value of the b3 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getB3() {
        return b3;
    }

    /**
     * Sets the value of the b3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setB3(Short value) {
        this.b3 = value;
    }

    /**
     * Gets the value of the b4 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getB4() {
        return b4;
    }

    /**
     * Sets the value of the b4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setB4(Short value) {
        this.b4 = value;
    }

    /**
     * Gets the value of the b5 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getB5() {
        return b5;
    }

    /**
     * Sets the value of the b5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setB5(Short value) {
        this.b5 = value;
    }

    /**
     * Gets the value of the b6 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getB6() {
        return b6;
    }

    /**
     * Sets the value of the b6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setB6(Short value) {
        this.b6 = value;
    }

    /**
     * Gets the value of the b7 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getB7() {
        return b7;
    }

    /**
     * Sets the value of the b7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setB7(Short value) {
        this.b7 = value;
    }

    /**
     * Gets the value of the b8 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getB8() {
        return b8;
    }

    /**
     * Sets the value of the b8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setB8(Short value) {
        this.b8 = value;
    }

    /**
     * Gets the value of the b9 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getB9() {
        return b9;
    }

    /**
     * Sets the value of the b9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setB9(Short value) {
        this.b9 = value;
    }

    /**
     * Gets the value of the golfer3 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGolfer3() {
        return golfer3;
    }

    /**
     * Sets the value of the golfer3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGolfer3(Integer value) {
        this.golfer3 = value;
    }

    /**
     * Gets the value of the hdcp3 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHdcp3() {
        return hdcp3;
    }

    /**
     * Sets the value of the hdcp3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHdcp3(Integer value) {
        this.hdcp3 = value;
    }

    /**
     * Gets the value of the c1 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getC1() {
        return c1;
    }

    /**
     * Sets the value of the c1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setC1(Short value) {
        this.c1 = value;
    }

    /**
     * Gets the value of the c2 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getC2() {
        return c2;
    }

    /**
     * Sets the value of the c2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setC2(Short value) {
        this.c2 = value;
    }

    /**
     * Gets the value of the c3 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getC3() {
        return c3;
    }

    /**
     * Sets the value of the c3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setC3(Short value) {
        this.c3 = value;
    }

    /**
     * Gets the value of the c4 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getC4() {
        return c4;
    }

    /**
     * Sets the value of the c4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setC4(Short value) {
        this.c4 = value;
    }

    /**
     * Gets the value of the c5 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getC5() {
        return c5;
    }

    /**
     * Sets the value of the c5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setC5(Short value) {
        this.c5 = value;
    }

    /**
     * Gets the value of the c6 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getC6() {
        return c6;
    }

    /**
     * Sets the value of the c6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setC6(Short value) {
        this.c6 = value;
    }

    /**
     * Gets the value of the c7 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getC7() {
        return c7;
    }

    /**
     * Sets the value of the c7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setC7(Short value) {
        this.c7 = value;
    }

    /**
     * Gets the value of the c8 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getC8() {
        return c8;
    }

    /**
     * Sets the value of the c8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setC8(Short value) {
        this.c8 = value;
    }

    /**
     * Gets the value of the c9 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getC9() {
        return c9;
    }

    /**
     * Sets the value of the c9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setC9(Short value) {
        this.c9 = value;
    }

    /**
     * Gets the value of the golfer4 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGolfer4() {
        return golfer4;
    }

    /**
     * Sets the value of the golfer4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGolfer4(Integer value) {
        this.golfer4 = value;
    }

    /**
     * Gets the value of the hdcp4 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHdcp4() {
        return hdcp4;
    }

    /**
     * Sets the value of the hdcp4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHdcp4(Integer value) {
        this.hdcp4 = value;
    }

    /**
     * Gets the value of the d1 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getD1() {
        return d1;
    }

    /**
     * Sets the value of the d1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setD1(Short value) {
        this.d1 = value;
    }

    /**
     * Gets the value of the d2 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getD2() {
        return d2;
    }

    /**
     * Sets the value of the d2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setD2(Short value) {
        this.d2 = value;
    }

    /**
     * Gets the value of the d3 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getD3() {
        return d3;
    }

    /**
     * Sets the value of the d3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setD3(Integer value) {
        this.d3 = value;
    }

    /**
     * Gets the value of the d4 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getD4() {
        return d4;
    }

    /**
     * Sets the value of the d4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setD4(Short value) {
        this.d4 = value;
    }

    /**
     * Gets the value of the d5 property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getD5() {
        return d5;
    }

    /**
     * Sets the value of the d5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setD5(Integer value) {
        this.d5 = value;
    }

    /**
     * Gets the value of the d6 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getD6() {
        return d6;
    }

    /**
     * Sets the value of the d6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setD6(Short value) {
        this.d6 = value;
    }

    /**
     * Gets the value of the d7 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getD7() {
        return d7;
    }

    /**
     * Sets the value of the d7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setD7(Short value) {
        this.d7 = value;
    }

    /**
     * Gets the value of the d8 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getD8() {
        return d8;
    }

    /**
     * Sets the value of the d8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setD8(Short value) {
        this.d8 = value;
    }

    /**
     * Gets the value of the d9 property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getD9() {
        return d9;
    }

    /**
     * Sets the value of the d9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setD9(Short value) {
        this.d9 = value;
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
     * Gets the value of the tees property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTees() {
        return tees;
    }

    /**
     * Sets the value of the tees property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTees(String value) {
        this.tees = value;
    }

    /**
     * Gets the value of the datePlayed property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatePlayed() {
        return datePlayed;
    }

    /**
     * Sets the value of the datePlayed property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatePlayed(XMLGregorianCalendar value) {
        this.datePlayed = value;
    }

    /**
     * Gets the value of the weekBucket property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWeekBucket() {
        return weekBucket;
    }

    /**
     * Sets the value of the weekBucket property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWeekBucket(Integer value) {
        this.weekBucket = value;
    }

}
