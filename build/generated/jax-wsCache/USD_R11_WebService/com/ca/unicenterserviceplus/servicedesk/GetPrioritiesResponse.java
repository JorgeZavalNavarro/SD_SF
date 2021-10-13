
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
 *         &lt;element name="getPrioritiesReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "getPrioritiesReturn"
})
@XmlRootElement(name = "getPrioritiesResponse")
public class GetPrioritiesResponse {

    @XmlElement(required = true)
    protected String getPrioritiesReturn;

    /**
     * Obtiene el valor de la propiedad getPrioritiesReturn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetPrioritiesReturn() {
        return getPrioritiesReturn;
    }

    /**
     * Define el valor de la propiedad getPrioritiesReturn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetPrioritiesReturn(String value) {
        this.getPrioritiesReturn = value;
    }

}
