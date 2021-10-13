package integracion_sdtosf_local;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.ca.unicenterserviceplus.servicedesk.USDWebService;
import com.ca.unicenterserviceplus.servicedesk.USDWebServiceSoap;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javax.xml.namespace.QName;
import org.apache.log4j.Logger;

/**
 *
 * @author ABS-Davo
 */
public class Login {

    private static org.apache.log4j.Logger log = Logger.getLogger(Login.class);
    private String username = "";
    private String password = "";
    private String hostname = "";
    private String puerto = "";
    private ResourceBundle rb;
    private String ref_num = "";
    private String userHandle = "";
    private int result = 0;
    private String finalMessage = "";
    com.ca.unicenterserviceplus.servicedesk.USDWebService service = null;
    com.ca.unicenterserviceplus.servicedesk.USDWebServiceSoap port = null;
    AlgoritmoAES al = new AlgoritmoAES();
    ArchivoPropiedades arcProp = new ArchivoPropiedades();

    public Login() {
    }

    public boolean Login() {

        boolean status = true;
        try {
            al = new AlgoritmoAES();
            arcProp = new ArchivoPropiedades();
            arcProp.read_NX_ROOT();
            arcProp.leeConfiguracion();

            hostname = arcProp.getProp().getProperty("CAServiceDesk_hostname_TPE");
            username = arcProp.getProp().getProperty("CAServiceDesk_username_TPE");
            if(ArchivoPropiedades.isPcPrivate()){
                password = arcProp.getProp().getProperty("CAServiceDesk_password_TPE");
            }else{
                password = AlgoritmoAES.dencDatos(arcProp.getProp().getProperty("CAServiceDesk_password_TPE"));
            }

            URL url = null;
            try {
                String url_string = hostname;
                url = new URL(url_string);
            } catch (MalformedURLException ex) {

                log.error(ex.getMessage());
            }
            service = new USDWebService(url, new QName("http://www.ca.com/UnicenterServicePlus/ServiceDesk", "USD_WebService"));
            port = service.getUSDWebServiceSoap();
            result = port.login(username, password);

            if (result <= 2000) { 
                finalMessage = "Login Incorrecto.";
                log.error("Login Incorrecto");
                status = false;
                return status;
            }
            userHandle = port.getHandleForUserid(result, username);
            finalMessage = "OK";
            log.info("Login ok");
        } catch (Exception e) {
            finalMessage = e.getMessage();
            System.out.println("Erro Login() " + e.getMessage());
            log.error("Erro Login() " + e.getMessage());
            e.printStackTrace();
            status = false;
        }
        return status;
    }

    public boolean logout(String str) {
        boolean status = true;
        try {
            port.logout(result);
            log.info("----LogOut desde " + str);

        } catch (Exception e) {
            status = false;
        }
        return status;
    }


    public void InitProperties() {
        al = new AlgoritmoAES();
        arcProp = new ArchivoPropiedades();
        arcProp.read_NX_ROOT();
        arcProp.leeConfiguracion();
    }

    public String getFinalMessage() {
        return finalMessage;
    }

    public void setFinalMessage(String finalMessage) {
        this.finalMessage = finalMessage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public USDWebServiceSoap getPort() {
        return port;
    }

    public void setPort(USDWebServiceSoap port) {
        this.port = port;
    }

    public String getRef_num() {
        return ref_num;
    }

    public void setRef_num(String ref_num) {
        this.ref_num = ref_num;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public USDWebService getService() {
        return service;
    }

    public void setService(USDWebService service) {
        this.service = service;
    }

    public String getUserHandle() {
        return userHandle;
    }

    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArchivoPropiedades getArcProp() {
        return arcProp;
    }

    public void setArcProp(ArchivoPropiedades arcProp) {
        this.arcProp = arcProp;
    }
}
