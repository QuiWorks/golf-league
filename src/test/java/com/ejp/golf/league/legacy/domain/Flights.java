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
 *         &lt;element name="Flight" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="FDesc" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="50"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Teams" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="FDesc_Save" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="50"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Group" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
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
    "flight",
    "fDesc",
    "teams",
    "fDescSave",
    "group"
})
@XmlRootElement(name = "Flights")
public class Flights {

    @XmlElement(name = "Flight")
    protected Integer flight;
    @XmlElement(name = "FDesc")
    protected String fDesc;
    @XmlElement(name = "Teams")
    protected Integer teams;
    @XmlElement(name = "FDesc_Save")
    protected String fDescSave;
    @XmlElement(name = "Group")
    protected Short group;

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
     * Gets the value of the fDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFDesc() {
        return fDesc;
    }

    /**
     * Sets the value of the fDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFDesc(String value) {
        this.fDesc = value;
    }

    /**
     * Gets the value of the teams property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTeams() {
        return teams;
    }

    /**
     * Sets the value of the teams property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTeams(Integer value) {
        this.teams = value;
    }

    /**
     * Gets the value of the fDescSave property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFDescSave() {
        return fDescSave;
    }

    /**
     * Sets the value of the fDescSave property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFDescSave(String value) {
        this.fDescSave = value;
    }

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setGroup(Short value) {
        this.group = value;
    }

}
