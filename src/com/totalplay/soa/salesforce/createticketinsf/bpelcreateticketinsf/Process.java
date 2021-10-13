
package com.totalplay.soa.salesforce.createticketinsf.bpelcreateticketinsf;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="Login">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="IdTicket" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Resumen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="GrupoCodificador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Codificacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Estatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="FechaApertura" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Prioridad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NumeroDeSerie" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Contact" maxOccurs="100" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="NombredeContacto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="TelefonodeContacto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CorreodeContacto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "login",
    "idTicket",
    "resumen",
    "descripcion",
    "grupoCodificador",
    "codificacion",
    "estatus",
    "fechaApertura",
    "prioridad",
    "numeroDeSerie",
    "contact"
})
@XmlRootElement(name = "process")
public class Process {

    @XmlElement(name = "Login", required = true)
    protected Process.Login login;
    @XmlElement(name = "IdTicket", required = true)
    protected String idTicket;
    @XmlElement(name = "Resumen", required = true)
    protected String resumen;
    @XmlElement(name = "Descripcion", required = true)
    protected String descripcion;
    @XmlElement(name = "GrupoCodificador", required = true)
    protected String grupoCodificador;
    @XmlElement(name = "Codificacion", required = true)
    protected String codificacion;
    @XmlElement(name = "Estatus", required = true)
    protected String estatus;
    @XmlElement(name = "FechaApertura", required = true)
    protected String fechaApertura;
    @XmlElement(name = "Prioridad", required = true)
    protected String prioridad;
    @XmlElement(name = "NumeroDeSerie", required = true)
    protected String numeroDeSerie;
    @XmlElement(name = "Contact")
    protected List<Process.Contact> contact;

    /**
     * Obtiene el valor de la propiedad login.
     * 
     * @return
     *     possible object is
     *     {@link Process.Login }
     *     
     */
    public Process.Login getLogin() {
        return login;
    }

    /**
     * Define el valor de la propiedad login.
     * 
     * @param value
     *     allowed object is
     *     {@link Process.Login }
     *     
     */
    public void setLogin(Process.Login value) {
        this.login = value;
    }

    /**
     * Obtiene el valor de la propiedad idTicket.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdTicket() {
        return idTicket;
    }

    /**
     * Define el valor de la propiedad idTicket.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdTicket(String value) {
        this.idTicket = value;
    }

    /**
     * Obtiene el valor de la propiedad resumen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResumen() {
        return resumen;
    }

    /**
     * Define el valor de la propiedad resumen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResumen(String value) {
        this.resumen = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad grupoCodificador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrupoCodificador() {
        return grupoCodificador;
    }

    /**
     * Define el valor de la propiedad grupoCodificador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrupoCodificador(String value) {
        this.grupoCodificador = value;
    }

    /**
     * Obtiene el valor de la propiedad codificacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodificacion() {
        return codificacion;
    }

    /**
     * Define el valor de la propiedad codificacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodificacion(String value) {
        this.codificacion = value;
    }

    /**
     * Obtiene el valor de la propiedad estatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * Define el valor de la propiedad estatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstatus(String value) {
        this.estatus = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaApertura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaApertura() {
        return fechaApertura;
    }

    /**
     * Define el valor de la propiedad fechaApertura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaApertura(String value) {
        this.fechaApertura = value;
    }

    /**
     * Obtiene el valor de la propiedad prioridad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrioridad() {
        return prioridad;
    }

    /**
     * Define el valor de la propiedad prioridad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrioridad(String value) {
        this.prioridad = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroDeSerie.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    /**
     * Define el valor de la propiedad numeroDeSerie.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDeSerie(String value) {
        this.numeroDeSerie = value;
    }

    /**
     * Gets the value of the contact property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contact property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContact().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Process.Contact }
     * 
     * 
     */
    public List<Process.Contact> getContact() {
        if (contact == null) {
            contact = new ArrayList<Process.Contact>();
        }
        return this.contact;
    }


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
     *         &lt;element name="NombredeContacto" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="TelefonodeContacto" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CorreodeContacto" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "nombredeContacto",
        "telefonodeContacto",
        "correodeContacto"
    })
    public static class Contact {

        @XmlElement(name = "NombredeContacto", required = true)
        protected String nombredeContacto;
        @XmlElement(name = "TelefonodeContacto", required = true)
        protected String telefonodeContacto;
        @XmlElement(name = "CorreodeContacto", required = true)
        protected String correodeContacto;

        /**
         * Obtiene el valor de la propiedad nombredeContacto.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombredeContacto() {
            return nombredeContacto;
        }

        /**
         * Define el valor de la propiedad nombredeContacto.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombredeContacto(String value) {
            this.nombredeContacto = value;
        }

        /**
         * Obtiene el valor de la propiedad telefonodeContacto.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTelefonodeContacto() {
            return telefonodeContacto;
        }

        /**
         * Define el valor de la propiedad telefonodeContacto.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTelefonodeContacto(String value) {
            this.telefonodeContacto = value;
        }

        /**
         * Obtiene el valor de la propiedad correodeContacto.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCorreodeContacto() {
            return correodeContacto;
        }

        /**
         * Define el valor de la propiedad correodeContacto.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCorreodeContacto(String value) {
            this.correodeContacto = value;
        }

    }


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
     *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ip" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "userId",
        "password",
        "ip"
    })
    public static class Login {

        @XmlElement(required = true)
        protected String userId;
        @XmlElement(required = true)
        protected String password;
        @XmlElement(required = true)
        protected String ip;

        /**
         * Obtiene el valor de la propiedad userId.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUserId() {
            return userId;
        }

        /**
         * Define el valor de la propiedad userId.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUserId(String value) {
            this.userId = value;
        }

        /**
         * Obtiene el valor de la propiedad password.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPassword() {
            return password;
        }

        /**
         * Define el valor de la propiedad password.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPassword(String value) {
            this.password = value;
        }

        /**
         * Obtiene el valor de la propiedad ip.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIp() {
            return ip;
        }

        /**
         * Define el valor de la propiedad ip.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIp(String value) {
            this.ip = value;
        }

    }

}
