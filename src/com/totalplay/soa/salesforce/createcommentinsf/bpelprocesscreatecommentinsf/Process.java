
package com.totalplay.soa.salesforce.createcommentinsf.bpelprocesscreatecommentinsf;

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
 *         &lt;element name="numTicket" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Comment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nameAtachment" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contentFile" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "numTicket",
    "comment",
    "nameAtachment",
    "contentFile"
})
@XmlRootElement(name = "process")
public class Process {

    @XmlElement(name = "Login", required = true)
    protected Process.Login login;
    @XmlElement(required = true)
    protected String numTicket;
    @XmlElement(name = "Comment", required = true)
    protected String comment;
    @XmlElement(required = true)
    protected String nameAtachment;
    @XmlElement(required = true)
    protected String contentFile;

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
     * Obtiene el valor de la propiedad numTicket.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumTicket() {
        return numTicket;
    }

    /**
     * Define el valor de la propiedad numTicket.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumTicket(String value) {
        this.numTicket = value;
    }

    /**
     * Obtiene el valor de la propiedad comment.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComment() {
        return comment;
    }

    /**
     * Define el valor de la propiedad comment.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComment(String value) {
        this.comment = value;
    }

    /**
     * Obtiene el valor de la propiedad nameAtachment.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameAtachment() {
        return nameAtachment;
    }

    /**
     * Define el valor de la propiedad nameAtachment.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameAtachment(String value) {
        this.nameAtachment = value;
    }

    /**
     * Obtiene el valor de la propiedad contentFile.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentFile() {
        return contentFile;
    }

    /**
     * Define el valor de la propiedad contentFile.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentFile(String value) {
        this.contentFile = value;
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
