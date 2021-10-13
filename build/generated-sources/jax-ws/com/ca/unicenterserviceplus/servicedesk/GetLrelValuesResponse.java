
package com.ca.unicenterserviceplus.servicedesk;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getLrelValuesReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getLrelValuesReturn"
})
@XmlRootElement(name = "getLrelValuesResponse")
public class GetLrelValuesResponse {

    @XmlElement(required = true)
    protected String getLrelValuesReturn;

    /**
     * Obtiene el valor de la propiedad getLrelValuesReturn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetLrelValuesReturn() {
        return getLrelValuesReturn;
    }

    /**
     * Define el valor de la propiedad getLrelValuesReturn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetLrelValuesReturn(String value) {
        this.getLrelValuesReturn = value;
    }

}
