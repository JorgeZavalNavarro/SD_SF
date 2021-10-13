package com.totalplay.syntech.integracion.sdm.soainfra.wssdm.core;

import java.sql.Timestamp;

/**
 * 
 * @author Jorge Zavala Navarro
 */
public class CoreBean {
    
    protected Timestamp hoy(){
        return new Timestamp((new java.util.Date()).getTime());
    }

}
