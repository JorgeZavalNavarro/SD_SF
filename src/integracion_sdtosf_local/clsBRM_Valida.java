/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integracion_sdtosf_local;

/**
 *
 * @author gmoraj
 */
import com.totalplay.soa.servicedesk.obtenerstatuscuentaenbrm.bpelobtenerstatuscuentaenbrm.BpelobtenerstatuscuentaenbrmClientEp;
import com.totalplay.soa.servicedesk.obtenerstatuscuentaenbrm.bpelobtenerstatuscuentaenbrm.Process;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class clsBRM_Valida {

    private static org.apache.log4j.Logger log = Logger.getLogger(clsBRM_Valida.class);
    AlgoritmoAES al = new AlgoritmoAES();
    ArchivoPropiedades arcProp = new ArchivoPropiedades();

    public BrmResponse processBrm(final AffectedResourceInfo affectedResourceInfo) {

        log.info("processBrm");
        BpelobtenerstatuscuentaenbrmClientEp bpelobtenerstatuscuentaenbrmClientEp = buildBpelobtenerstatuscuentaenbrmClientEp();
        Process.Login login = new Process.Login();
        login.setIp(affectedResourceInfo.getIp());
        login.setUserId(arcProp.getProp().getProperty("BRM_username"));
        login.setPassword(arcProp.getProp().getProperty("BRM_password"));
        final Holder<String> result = new Holder<String>();
        final Holder<String> resultID = new Holder<String>();
        final Holder<String> resultIDescription = new Holder<String>();
        final Holder<String> cuenta = new Holder<String>();
        final Holder<String> statusCuenta = new Holder<String>();
        
        log.info("Haciendo peticion a BRM");
        
        bpelobtenerstatuscuentaenbrmClientEp.getBPELObtenerStatusCuentaEnBrmPt().process(login,
                affectedResourceInfo.getSerialNumber(),
                resultID, result, resultIDescription,
                cuenta,
                statusCuenta);
        log.info("Imprimiendo resultados");
        log.info("result:"+ result.value);
        log.info("resultID:"+ resultID.value);
        log.info("resultIDescription:"+ resultIDescription.value);
        log.info("cuenta:"+ cuenta.value);
        log.info("statusCuenta:"+ statusCuenta.value);
        BrmResponse brmResponse = new BrmResponse();
        brmResponse.setCuenta(cuenta.value);
        brmResponse.setResult(result.value);
        brmResponse.setResultID(resultID.value);
        brmResponse.setResultIDescription(resultIDescription.value);
        brmResponse.setStatusCuenta(statusCuenta.value);
        return brmResponse;
    }

    public BpelobtenerstatuscuentaenbrmClientEp buildBpelobtenerstatuscuentaenbrmClientEp() {
        WebServiceException e = null;
        URL url = null;
        BpelobtenerstatuscuentaenbrmClientEp bpelobtenerstatuscuentaenbrmClientEp = null;
        try {
            url = new URL(arcProp.getProp().getProperty("BRM_url_wsdl"));
            log.info("WSDLOCATION:"+ arcProp.getProp().getProperty("BRM_url_wsdl"));
            bpelobtenerstatuscuentaenbrmClientEp = new BpelobtenerstatuscuentaenbrmClientEp(url);
            log.info("getWSDLDocumentLocation::"+ bpelobtenerstatuscuentaenbrmClientEp.getWSDLDocumentLocation());
        } catch (MalformedURLException ex) {
            log.error("TEST");
            e = new WebServiceException(ex);
        }
        return bpelobtenerstatuscuentaenbrmClientEp;
    }
}
