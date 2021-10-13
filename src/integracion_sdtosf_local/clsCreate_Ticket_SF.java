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
import com.totalplay.soa.salesforce.createticketinsf.bpelcreateticketinsf.BpelcreateticketinsfClientEp;
import com.totalplay.soa.salesforce.createticketinsf.bpelcreateticketinsf.Process;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class clsCreate_Ticket_SF {

    private static org.apache.log4j.Logger log = Logger.getLogger(clsCreate_Ticket_SF.class);
    AlgoritmoAES al = new AlgoritmoAES();
    ArchivoPropiedades arcProp = new ArchivoPropiedades();

    public String createTicketSalesforce(String sNombreContacto, String sTelefonoCotacto, String sCorreoContacto, String sIdServiceDesk,
            String sTitulo, String sDescrip, String sCodifica, String sTatus, String sFechaApe, String sPrioridad, String sNumeroSerie) {
        arcProp = new ArchivoPropiedades();
        arcProp.read_NX_ROOT();
        arcProp.leeConfiguracion();
        BpelcreateticketinsfClientEp bpelcreateticketinsfClientEp = buildBpelcreateticketinsfClientEp();
        Process.Login login = new Process.Login();

        login.setIp(arcProp.getProp().getProperty("MW_IP"));
        login.setUserId(arcProp.getProp().getProperty("MW_User"));
        login.setPassword(AlgoritmoAES.dencDatos(arcProp.getProp().getProperty("MW_Pass")));

        Process.Contact contact = new Process.Contact();
        contact.setNombredeContacto(sNombreContacto);
        contact.setTelefonodeContacto(sTelefonoCotacto);
        contact.setCorreodeContacto(sCorreoContacto);
        List<Process.Contact> contactList = new ArrayList<>();
        contactList.add(contact);
        log.info("Creando Ticket en salesforce");
        final Holder<String> idResult = new Holder<String>();
        final Holder<String> result = new Holder<String>();
        final Holder<String> resultDescription = new Holder<String>();
        final Holder<String> numeroTicket = new Holder<String>();

        bpelcreateticketinsfClientEp.getBPELCreateTicketInSFPt().process(login,
                sIdServiceDesk,
                sTitulo,
                sDescrip,
                "",
                sCodifica,
                sTatus,
                sFechaApe,
                sPrioridad,
                sNumeroSerie,
                contactList,
                idResult,
                result,
                resultDescription,
                numeroTicket
        );

        log.info("Respuesta de salesforce");
        log.info(" Respuesta de salesforce idResult:" + idResult.value);
        log.info(" Respuesta de salesforce result:" + result.value);
        log.info(" Respuesta de salesforce resultDescription:" + resultDescription.value);
        log.info(" Respuesta de salesforce numeroTicket:" + numeroTicket.value);
        return numeroTicket.value;
    }

    private BpelcreateticketinsfClientEp buildBpelcreateticketinsfClientEp() {
        arcProp = new ArchivoPropiedades();
        arcProp.read_NX_ROOT();
        arcProp.leeConfiguracion();
        
        WebServiceException e = null;
        URL url = null;
        BpelcreateticketinsfClientEp bpelcreateticketinsfClientEp = null;
        try {
            url = new URL(arcProp.getProp().getProperty("MW_URL_CreateTckSF")); 
            log.info("WSDLOCATION:"+ arcProp.getProp().getProperty("MW_URL_CreateTckSF"));
            bpelcreateticketinsfClientEp = new BpelcreateticketinsfClientEp(url);
            log.info("getWSDLDocumentLocation:"+ bpelcreateticketinsfClientEp.getWSDLDocumentLocation());
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        return bpelcreateticketinsfClientEp;
    }
}
