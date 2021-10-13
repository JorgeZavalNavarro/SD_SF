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
import com.totalplay.soa.salesforce.createcommentinsf.bpelprocesscreatecommentinsf.BpelprocesscreatecommentinsfClientEp;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class clsAdd_Comment_SF {

    BpelprocesscreatecommentinsfClientEp bpelprocesscreatecommentinsfClientEp = buildBpelprocesscreatecommentinsfClientEp();
    com.totalplay.soa.salesforce.createcommentinsf.bpelprocesscreatecommentinsf.Process.Login login = new com.totalplay.soa.salesforce.createcommentinsf.bpelprocesscreatecommentinsf.Process.Login();
    private static org.apache.log4j.Logger log = Logger.getLogger(clsAdd_Comment_SF.class);
    AlgoritmoAES al = new AlgoritmoAES();
    ArchivoPropiedades arcProp = new ArchivoPropiedades();

    public String addComments(String sTicketSF, String sCommentSF, String sNameFile, String sContentFileSF) {
        arcProp = new ArchivoPropiedades();
        arcProp.read_NX_ROOT();
        arcProp.leeConfiguracion();

        login.setIp(arcProp.getProp().getProperty("MW_IP")); 
        login.setUserId(arcProp.getProp().getProperty("MW_User")); 
        login.setPassword(AlgoritmoAES.dencDatos(arcProp.getProp().getProperty("MW_Pass"))); 

        String sResponseAddCommentSF = "";
        final Holder<String> result = new Holder<String>();
        final Holder<String> resultID = new Holder<String>();
        final Holder<String> resultIDescription = new Holder<String>();
        final Holder<String> numeroticket = new Holder<String>();
        bpelprocesscreatecommentinsfClientEp.getBPELProcessCreateCommentInSFPt().process(login,
                sTicketSF, sCommentSF, sNameFile, sContentFileSF, resultID,
                result, resultIDescription, numeroticket);

        log.info("Finalizando actualizacion de ticket en salesforce, imprimiendo resultados: ");
        log.info("result:" + result.value);
        log.info("resultID:" + resultID.value);
        log.info("resultIDescription:" + resultIDescription.value);
        log.info("numeroticket:" + numeroticket.value);

        sResponseAddCommentSF = "idResult:" + resultID.value + "\n" + "result:" + result.value + "\n" + "resultDescription:" + resultIDescription.value;
        return sResponseAddCommentSF;
    }

    private BpelprocesscreatecommentinsfClientEp buildBpelprocesscreatecommentinsfClientEp() {
        arcProp = new ArchivoPropiedades();
        arcProp.read_NX_ROOT();
        arcProp.leeConfiguracion();

        WebServiceException e = null;
        URL url = null;
        BpelprocesscreatecommentinsfClientEp bpelprocesscreatecommentinsfClientEp = null;
        try {
            url = new URL(arcProp.getProp().getProperty("MW_URL_CommentSF"));
            log.info("WSDLOCATION:" + arcProp.getProp().getProperty("MW_URL_CommentSF"));
            bpelprocesscreatecommentinsfClientEp = new BpelprocesscreatecommentinsfClientEp(url);
            log.info("getWSDLDocumentLocation:" + bpelprocesscreatecommentinsfClientEp.getWSDLDocumentLocation());
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
            log.error(e.getMessage());
        }
        return bpelprocesscreatecommentinsfClientEp;
    }

}
