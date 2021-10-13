
package com.totalplay.soa.servicedesk.obtenerstatuscuentaenbrm.bpelobtenerstatuscuentaenbrm;

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
 *         &lt;element name="IdResult" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Result" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ResultDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Cuenta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StatusCuenta" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "idResult",
    "result",
    "resultDescription",
    "cuenta",
    "statusCuenta"
})
@XmlRootElement(name = "processResponse")
public class ProcessResponse {

    @XmlElement(name = "IdResult", required = true)
    protected String idResult;
    @XmlElement(name = "Result", required = true)
    protected String result;
    @XmlElement(name = "ResultDescription", required = true)
    protected String resultDescription;
    @XmlElement(name = "Cuenta", required = true)
    protected String cuenta;
    @XmlElement(name = "StatusCuenta", required = true)
    protected String statusCuenta;

    /**
     * Obtiene el valor de la propiedad idResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdResult() {
        return idResult;
    }

    /**
     * Define el valor de la propiedad idResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdResult(String value) {
        this.idResult = value;
    }

    /**
     * Obtiene el valor de la propiedad result.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResult() {
        return result;
    }

    /**
     * Define el valor de la propiedad result.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResult(String value) {
        this.result = value;
    }

    /**
     * Obtiene el valor de la propiedad resultDescription.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultDescription() {
        return resultDescription;
    }

    /**
     * Define el valor de la propiedad resultDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultDescription(String value) {
        this.resultDescription = value;
    }

    /**
     * Obtiene el valor de la propiedad cuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * Define el valor de la propiedad cuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuenta(String value) {
        this.cuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad statusCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCuenta() {
        return statusCuenta;
    }

    /**
     * Define el valor de la propiedad statusCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCuenta(String value) {
        this.statusCuenta = value;
    }

}
