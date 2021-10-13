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
import com.totalplay.soa.salesforce.changestatusticketsf.bpelprocesschangestatusticketsf.BpelprocesschangestatusticketsfClientEp;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class clsChange_Status_SF {

    BpelprocesschangestatusticketsfClientEp bpelprocesschangestatusticketsfClientEp = buildBpelprocesschangestatusticketsfClientEp();
    com.totalplay.soa.salesforce.changestatusticketsf.bpelprocesschangestatusticketsf.Process.Login login = new com.totalplay.soa.salesforce.changestatusticketsf.bpelprocesschangestatusticketsf.Process.Login();
    private static org.apache.log4j.Logger log = Logger.getLogger(clsChange_Status_SF.class);
    AlgoritmoAES al = new AlgoritmoAES();
    ArchivoPropiedades arcProp = new ArchivoPropiedades();

    public String changeStatus(String sTicketSF, String sStatuSF, String sSubStatusSF, String sJustSF) {
        
        log.info("Ejecutando changeStatus()");
        log.info("Ticket de Salesforce.....: " + sTicketSF);
        log.info("Ticket de sStatuSF.......: " + sStatuSF);
        log.info("Ticket de sSubStatusSF...: " + sSubStatusSF);
        log.info("Ticket de sJustSF........: " + sJustSF);

        arcProp = new ArchivoPropiedades();
        arcProp.read_NX_ROOT();
        arcProp.leeConfiguracion();

        login.setIp(arcProp.getProp().getProperty("MW_IP"));
        login.setUserId(arcProp.getProp().getProperty("MW_User"));
        login.setPassword(AlgoritmoAES.dencDatos(arcProp.getProp().getProperty("MW_Pass"))); 
        String sRespuestachangeStatus = "";
        final Holder<String> result = new Holder<String>();
        final Holder<String> resultID = new Holder<String>();
        final Holder<String> resultIDescription = new Holder<String>();
        bpelprocesschangestatusticketsfClientEp.getBPELProcessChangeStatusTicketSFPt().process(login, sTicketSF, sStatuSF, sSubStatusSF, sJustSF, result, resultID, resultIDescription);
        log.info("Finalizando Cambio de estatus, imprimiendo resultado");
        log.info(" Respuesta de salesforce idResult:" + resultID.value);
        log.info(" Respuesta de salesforce result:" + result.value);
        log.info(" Respuesta de salesforce resultDescription:" + resultIDescription.value);
        sRespuestachangeStatus = "idResult:" + resultID.value + "\n" + "result:" + result.value + "\n" + "resultDescription:" + resultIDescription.value;
        log.info(" Respuesta para el cambio de estado:\n     ********" + resultIDescription.value);
        return sRespuestachangeStatus;
    }

    private BpelprocesschangestatusticketsfClientEp buildBpelprocesschangestatusticketsfClientEp() {
        log.info("Ejecutando: buildBpelprocesschangestatusticketsfClientEp");
        arcProp = new ArchivoPropiedades();
        arcProp.read_NX_ROOT();
        arcProp.leeConfiguracion();

        WebServiceException e = null;
        URL url = null;
        BpelprocesschangestatusticketsfClientEp bpelprocesschangestatusticketsfClientEp = null;
        try {
            url = new URL(arcProp.getProp().getProperty("MW_URL_ChgStatusSF"));
            log.info("WSDLOCATION:" + arcProp.getProp().getProperty("MW_URL_ChgStatusSF"));
            bpelprocesschangestatusticketsfClientEp = new BpelprocesschangestatusticketsfClientEp(url);
            log.info("getWSDLDocumentLocation:" + bpelprocesschangestatusticketsfClientEp.getWSDLDocumentLocation());
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
            log.error(e.getMessage());
            
        // -- Agregar catcheo de exceptiones para mejor control
        // -- jzavala@syntech.com.mx
        // -- Inicia: 22/09/2021 15:15
        } catch (Exception ex) {
            e = new WebServiceException(ex);
            log.error(e.getMessage());
        }catch (Throwable ex) {
            e = new WebServiceException(ex);
            log.error(e.getMessage());
        }
        // -- Inicia: 22/09/2021 15:15
        return bpelprocesschangestatusticketsfClientEp;
    }
}
