
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
 *         &lt;element name="addBookmarkReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "addBookmarkReturn"
})
@XmlRootElement(name = "addBookmarkResponse")
public class AddBookmarkResponse {

    @XmlElement(required = true)
    protected String addBookmarkReturn;

    /**
     * Obtiene el valor de la propiedad addBookmarkReturn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddBookmarkReturn() {
        return addBookmarkReturn;
    }

    /**
     * Define el valor de la propiedad addBookmarkReturn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddBookmarkReturn(String value) {
        this.addBookmarkReturn = value;
    }

}
