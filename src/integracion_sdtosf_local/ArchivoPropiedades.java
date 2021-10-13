/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integracion_sdtosf_local;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author Guadalupe
 * @author David - Adaptacion para leer NX_ROOT del ambiente
 */
public class ArchivoPropiedades {

    private String propertiesFile = "NX_ROOT\\Integraciones\\Integracion_SDtoSF_local\\config\\Integracion_SDtoSF_local.properties";
    // private String configuracionLog = "NX_ROOT\\Integraciones\\Integracion_SDtoSF_local\\config\\configuracionLog.properties";
    // private String configuracionLog_local = "NX_ROOT\\Integraciones\\Integracion_SDtoSF_local\\config\\configuracionLog_local.properties";

    private final String NX_ROOT_str = "NX_ROOT";
    private String NX_ROOT = "";
    AlgoritmoAES al = new AlgoritmoAES();
    Properties prop = new Properties();

    public void leeConfiguracion() {

        // Validar desarrollo
        if (this.getNombrePC().equals("DESKTOP-TBBN11R")) {
            propertiesFile = "C:\\APIs\\ProvexIntegraciones\\Config\\Integraciones\\Integracion_SDtoSF_local\\config\\Integracion_SDtoSF_local.properties";
            System.out.println("Usar archivo de propiedades: " + propertiesFile);
        }

        InputStream is = null;
        String p1 = "";

        try {
            if (this.getNombrePC().equals("DESKTOP-TBBN11R")) {
                propertiesFile = "C:\\APIs\\ProvexIntegraciones\\Config\\Integraciones\\Integracion_SDtoSF_local\\config\\Integracion_SDtoSF_local.properties";
                is = new FileInputStream(propertiesFile);
                System.out.println("Usar archivo de propiedades: " + propertiesFile);
            } else {
                System.out.println("Nombre del servidor... " + getNombrePC());
                is = new FileInputStream(propertiesFile.replaceFirst(NX_ROOT_str, getNX_ROOT()));
            }
            prop.load(is);

            is.close();
        } catch (IOException ioe) {
            p1 = ioe.getMessage();
            System.out.println("Error -> leeConfiguracion(): " + ioe.getMessage());
        }

    }

    public void read_NX_ROOT() {
        if(ArchivoPropiedades.isPcPrivate()){
            return;
        }
        
        
        try {

            Process pr;
            try {
                String comando = "nx_env " + NX_ROOT_str;

                pr = Runtime.getRuntime().exec(comando);
                InputStream is = pr.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String aux = br.readLine();
                while (aux != null) {
                    if (aux.startsWith(NX_ROOT_str)) {
                        setNX_ROOT(aux.substring(NX_ROOT_str.length() + 1));
                    }
                    aux = br.readLine();
                }

            } catch (Exception e) {
                System.out.println("Error -comando- -> " + e.getMessage());
                NX_ROOT = "";
            }

        } catch (Exception e) {
            System.out.println("Ha ocurrido una excepción al abrir " + propertiesFile + " el fichero, no se encuentra o está protegido");
            System.out.println(e.getMessage());
        }

    }
    
    public static Boolean isPcPrivate(){
        Boolean retorno = Boolean.FALSE;
        ArchivoPropiedades archivo = new ArchivoPropiedades();
        if(archivo.getNombrePC().equals("DESKTOP-TBBN11R")){
            retorno = Boolean.TRUE;
        }
        return retorno;
        
    }

    private String getNombrePC() {
        InetAddress ip = null;
        String hostname = null;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
        return hostname;
    }

    public String getNX_ROOT() {
        return NX_ROOT;
    }

    public void setNX_ROOT(String NX_ROOT) {
        this.NX_ROOT = NX_ROOT;
    }

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }
}
