package integracion_sdtosf_local;

import org.apache.log4j.Logger;

/**
 *
 * @author gmoraj
 */
import java.io.File;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Integracion_SDtoSF_local {

    private static final int TEST_LOGIN = 100;
    private static final int CREATE_TICKET = 200;
    private static final int ADD_COMMENT = 300;
    private static final int CHANGE_STATUS = 400;
    private static final int CHANGE_GRP = 500;
    private static Login login;
    private static org.apache.log4j.Logger log = Logger.getLogger(Integracion_SDtoSF_local.class);
    private static ArchivoPropiedades arch;

    private static String sIp = "";
    private static String sSerial = "";
    private static String sSerialb = "";
    private static String sSerialCodificacion = "";
    private static String sIdLogAgent = "";
    private static String sTicketSF = "";
    private static String sStatus = "";
    private static String sRespuestaChangeStatus = "";
    private static String sRespuestaAddCommentSF = "";
    private static String sNameGroup = "";
    private static String sRespuestCgangeGRP = "";

    public static void main(String[] args) throws InterruptedException {

        int action = Integer.parseInt(args[0]);
        arch = new ArchivoPropiedades();
        arch.read_NX_ROOT();
        arch.leeConfiguracion();
        PropertyConfigurator.configure(arch.getProp().getProperty("Configuracion_log"));
        log.info("Iniciando sistema ..... ");
        log.info("Cantidad de parámetros recibidos: " + args.length);

        // Desglose de los par{ametros recibidos
        for (int i = 0; i < args.length; i++) {
            log.info("Param [" + i + "]: " + args[i]);
        }

        log.info("Asignando Charset UTF-8");
        String charSet = "UTF-8";
        log.info("Configurando charset: " + charSet);

        try {
            asignarCharSet(charSet);
        } catch (Exception ex) {
            log.error("No se pudo asignar el charset " + charSet);
            log.error(ex);
            log.error("El programa puede presentar caracteres extraños en la información en lugar de letras con acentos, tildes, etc.");
        }

        log.info("Procesando...");

        // 
        boolean bBand = true;
        int iCont = 0;
        int iContLongin = 0;
        //
        switch (action) {
            case TEST_LOGIN:
                try {
                    login = new Login();
                    if (login.Login()) {
                        log.info("Login OK: " + login.getResult());
                        login.logout("TEST_LOGIN");
                        System.out.print("Login OK: " + login.getResult());
                    } else {
                        log.error("Error (Login) --> " + login.getFinalMessage());
                        log.error(login.getFinalMessage());
                        System.out.print("Login Error: " + login.getFinalMessage());
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }

                break;
                
            case CREATE_TICKET:

                log.info("Parametros para la creación de tieckt en SF");

                int iVerificaSerial = 0;
                String sIp = "";
                String sCodificacionSF = "";
                String sBandejaBzn = "";
                String sCodificacion = "";
                String sCusNom = "";
                String sCusTel = "";
                String sCusMail = "";
                String sRef_Num = "";
                String sSummary = "";
                String sDescripion = "";
                String sCategoria = "";
                String sStatusSF = "";
                String sPrioridad = "";
                String sOpen_date = "";
                String sOpenDate_SF = "";
                String sFecha = "";
                String sTicket_SF = "";

                for (int a = 0; a <= args.length - 1; a++) {
                    log.info("Parametros: " + args[a]);
                    if (args[a].trim().equals("Titulo")) {
                        sSummary = args[a + 1];
                    }
                    if (args[a].trim().equals("descripcion")) {
                        sDescripion = args[a + 1];
                    }
                    if (args[a].trim().equals("prioridad")) {
                        sPrioridad = args[a + 1];
                    }
                    if (args[a].trim().equals("ref_num")) {
                        sRef_Num = args[a + 1];
                    }
                    if (args[a].trim().equals("customer_nombre")) {
                        sCusNom = args[a + 1];
                    }
                    if (args[a].trim().equals("customer_numero")) {
                        sCusTel = args[a + 1];
                    }
                    if (args[a].trim().equals("customer_email")) {
                        sCusMail = args[a + 1];
                    }
                    if (args[a].trim().equals("CI_Serial")) {
                        sSerial = args[a + 1];
                    }
                    if (args[a].trim().equals("zserial_number")) {
                        sSerialb = args[a + 1];
                    }
                    if (args[a].trim().equals("CI_Alarm")) {
                        sIp = args[a + 1];
                    }
                    if (args[a].trim().equals("zserial_number")) {
                        sSerialCodificacion = args[a + 1];
                    }
                    if (args[a].trim().equals("zBuzonSF_id")) {
                        sCodificacionSF = args[a + 1];
                    }
                    if (args[a].trim().equals("zBuzonSF")) {
                        sBandejaBzn = args[a + 1];
                    }
                    if (args[a].trim().equals("categoria")) {
                        sCategoria = args[a + 1];
                    }
                }
                try {

                    if (!sSerial.trim().isEmpty()) {
                        iVerificaSerial = 1;
                    }
                    if (iVerificaSerial == 0) {
                        if (!sSerialb.trim().isEmpty()) {
                            sSerial = sSerialb;
                        }
                    }
                    if (sIp.trim().isEmpty()) {
                        sIp = "1.1.1.1";
                    }

                    if (!sSerialCodificacion.trim().isEmpty()) {
                        sCodificacion = getCodificacion(sSerialCodificacion);
                    }
                    ///
                    login = new Login();
                    if (!sSerial.isEmpty()) {
                        AffectedResourceInfo affectedResourceInfo = new AffectedResourceInfo();
                        affectedResourceInfo.setIp(sIp);
                        affectedResourceInfo.setSerialNumber(sSerial.trim());
                        try {

                            clsBRM_Valida oValidaBRM = new clsBRM_Valida();
                            BrmResponse brmResponse = oValidaBRM.processBrm(affectedResourceInfo);

                            if (brmResponse.getResult().trim().equals("0")) {

                                if (brmResponse.getStatusCuenta().trim().equals("10100")) {

                                    if (!sBandejaBzn.trim().isEmpty()) {
                                        if (!sCategoria.trim().isEmpty()) {

                                            Ticket oT = new Ticket();
                                            if (login.Login()) {
                                                oT.SearchFor(41, login.getResult(), sRef_Num.trim(), "", "", login.getPort(), login.getResult());
                                                sOpen_date = oT.getRef_num();
                                                sOpenDate_SF = getOpenDate(sOpen_date);
                                                String[] aFecha = null;

                                                aFecha = sOpenDate_SF.split(":");
                                                sFecha = sOpenDate_SF + "." + aFecha[3].substring(1, 4);
                                                log.info("sFecha Open" + sFecha);
                                            }
                                            if (sCategoria.trim().endsWith("pcat:407782")) {
                                                sStatusSF = "Cerrado";
                                            } else {
                                                sStatusSF = "Nuevo";
                                            }
                                            clsCreate_Ticket_SF oCreaST = new clsCreate_Ticket_SF();

                                            sTicket_SF = oCreaST.createTicketSalesforce(sCusNom, sCusTel, sCusMail,
                                                    sRef_Num, sSummary, sDescripion, sBandejaBzn, sStatusSF,
                                                    sFecha, sPrioridad, sSerial);

                                            if (!sTicket_SF.trim().isEmpty()) {

                                                if (oT.updateTicketSF(args[4].trim(), args[2].trim(), sTicket_SF.trim(), login.getResult(), login.getPort()) == true) {
                                                    log.info("Actualizado el ticket externo con número SF");
                                                } else {
                                                    log.error("No se actualizo el ticket externo");
                                                }
                                            }
                                        }

                                    } else {
                                        log.info("El ticket no cuenta con una Bandeja para SF");
                                        if (login.Login()) {
                                            ///
                                            ActivityLog actlog1 = new ActivityLog();
                                            actlog1.setLogin(login);
                                            List<String> parameters1 = new ArrayList<String>();
                                            parameters1.add(args[2].trim());
                                            parameters1.add("[Error, el ticket no cuenta con una Bandeja para SF agregar un abandeja valida]");
                                            parameters1.add("LOG");
                                            parameters1.add("0");
                                            parameters1.add("False");
                                            actlog1.createActivityLog(parameters1, login.getResult());
                                        }
                                        login.logout("CREATE_TICKET");
                                    }
                                }

                                if (brmResponse.getStatusCuenta().trim().equals("10102") || brmResponse.getStatusCuenta().trim().equals("10103")) {
                                    Ticket oT = new Ticket();
                                    if (login.Login()) {
                                        if (oT.updateTicketCNL(args[2].trim(), login.getUserHandle(), login.getResult(), login.getPort()) == true) {
                                            log.info("Ticket Cancelado OK");
                                        } else {
                                            log.info("Error al cancelar el ticket");
                                        }
                                    }
                                    login.logout("CREATE_TICKET");
                                }
                            }
                            login.logout("CREATE_TICKET");
                        } catch (Exception exx) {
                            log.error("Fallo de webservice no se alcanza BRM");
                            if (login.Login()) {
                                ///
                                ActivityLog actlog1 = new ActivityLog();
                                actlog1.setLogin(login);
                                List<String> parameters1 = new ArrayList<String>();
                                parameters1.add(args[2].trim());
                                parameters1.add("[Error de webservice, no se alcanza BRM]\n" + exx.getMessage());
                                parameters1.add("LOG");
                                parameters1.add("0");
                                parameters1.add("False");

                                actlog1.createActivityLog(parameters1, login.getResult());
                            }
                            login.logout("CREATE_TICKET");
                        }
                    } else {
                        log.error("Ticket no tiene numero de serie, no se envia la creación");
                        log.info("Actualizando Bitacora");

                        if (login.Login()) {
                            ///
                            ActivityLog actlog1 = new ActivityLog();
                            actlog1.setLogin(login);
                            List<String> parameters1 = new ArrayList<String>();
                            parameters1.add(args[2].trim());
                            parameters1.add("[Ticket no generado en Salesforce por falta de numero de serie.]");
                            parameters1.add("LOG");
                            parameters1.add("0");
                            parameters1.add("False");

                            actlog1.createActivityLog(parameters1, login.getResult());
                        }
                        login.logout("CREATE_TICKET");
                    }
                    ///
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
                break;
            case CHANGE_STATUS:

                log.info("Parametros para envio de cambio de status a SF");
                String sSolicion;
                String sDiagnostico;
                String sJustificacion = "---";

                for (int a = 0; a <= args.length - 1; a++) {
                    log.info("Parametros: " + args[a]);
                    if (args[a].trim().equals("log_agent")) {
                        sIdLogAgent = args[a + 1];
                    }

                    if (args[a].trim().equals("zfolio_dbw_sf")) {
                        sTicketSF = args[a + 1];
                    }

                    if (args[a].trim().equals("status")) {
                        sStatus = args[a + 1];
                    }
                }
                String sStstusSF = "";

                if (!sStatus.trim().isEmpty() && (sStatus.trim().equals("OP")
                        || sStatus.trim().equals("AUR") || sStatus.trim().equals("AWTVNDR")
                        || sStatus.trim().equals("ZPNDTRCR") || sStatus.trim().equals("CNCL"))) {
                    if (sStatus.trim().equals("OP")) {
                        sStstusSF = arch.getProp().getProperty("SF_OP");
                    }
                    if (sStatus.trim().equals("AUR")) {
                        sStstusSF = arch.getProp().getProperty("SF_AUR");
                    }
                    if (sStatus.trim().equals("AWTVNDR")) {
                        sStstusSF = arch.getProp().getProperty("SF_AWTVNDR");
                    }
                    if (sStatus.trim().equals("ZPNDTRCR")) {
                        sStstusSF = arch.getProp().getProperty("SF_ZPNDTRCR");
                    }
                    if (sStatus.trim().equals("CNCL")) {
                        sStstusSF = arch.getProp().getProperty("SF_CNCL");
                    }
                }
                ///
                if (sStatus.trim().equals("RE")) {
                    login = new Login();
                    while (bBand == true) {

                        try {
                            if (login.Login() == true) {

                                Ticket oT = new Ticket();
                                oT.SearchFor(42, login.getResult(), args[4].trim(), "", "", login.getPort(), login.getResult());
                                sSolicion = oT.getRef_num();
                                oT.setRef_num("");
                                oT.SearchFor(43, login.getResult(), args[4].trim(), "", "", login.getPort(), login.getResult());
                                sDiagnostico = oT.getRef_num();
                                oT.setRef_num("");
                                login.logout("Busqueda Diagnostico - Solucion");
                                sJustificacion = "Diagnostico: " + sDiagnostico + "\n" + "Solucion: " + sSolicion;

                                log.info("CadenaOri_Justificacion: " + sJustificacion);
                                bBand = false;
                            } else {
                                iContLongin = iContLongin + 1;
                                if (iContLongin >= 3) {
                                    iCont = 11;
                                    bBand = false;
                                }
                                if (iContLongin <= 3) {
                                    TimeUnit.SECONDS.sleep(3);
                                    log.error(" Intento: '" + iContLongin + " '[Error] login CSDM' " + login.getFinalMessage());
                                }
                                if (iContLongin >= 4 && iCont <= 6) {
                                    TimeUnit.MINUTES.sleep(5);
                                    log.error(" Intento: '" + iContLongin + " '[Error] login CSDM' " + login.getFinalMessage());
                                }
                                if (iContLongin >= 7 && iContLongin <= 9) {
                                    TimeUnit.MINUTES.sleep(20);
                                    log.error(" Intento: '" + iContLongin + " '[Error] login CSDM' " + login.getFinalMessage());
                                }
                                if (iContLongin >= 10) {
                                    TimeUnit.MINUTES.sleep(30);
                                    log.error(" Intento: '" + iContLongin + " '[Error] login CSDM' " + login.getFinalMessage());
                                }
                            }
                        } catch (Exception e) {
                            login.logout("Busqueda Diagnostico - Solucion");
                            log.error("[Error] Busqueda Diagnostico - Solucion" + e.getMessage());
                            iCont = iCont + 1;
                            if (iCont >= 3) {
                                iCont = 11;
                                bBand = false;
                            }
                            if (iCont <= 3) {
                                TimeUnit.SECONDS.sleep(3);
                                log.error(" Intento: '" + iCont + "' Error al Busqueda Diagnostico - Solucion: " + e.getMessage());
                            }
                            if (iCont >= 4 && iCont <= 6) {
                                TimeUnit.MINUTES.sleep(5);
                                log.error(" Intento: '" + iCont + "' Error al Busqueda Diagnostico - Solucion: " + e.getMessage());
                            }
                            if (iCont >= 7 && iCont <= 9) {
                                TimeUnit.MINUTES.sleep(20);
                                log.error(" Intento: '" + iCont + "' Error al Busqueda Diagnostico - Solucion: " + e.getMessage());
                            }
                            if (iCont >= 10) {
                                TimeUnit.MINUTES.sleep(30);
                                log.error(" Intento: '" + iCont + "' Error al Busqueda Diagnostico - Solucion: " + e.getMessage());
                            }
                        }
                    }
                }

                if (!sStstusSF.trim().isEmpty() || !sStatus.trim().isEmpty()) {
                    if (!sIdLogAgent.trim().isEmpty() && sIdLogAgent.trim().equals("Integracion SF Dashboard Web")) {

                        if (!sTicketSF.trim().isEmpty()) {

                            if ((sStatus.trim().equals("WIP") || sStatus.trim().equals("CL") || sStatus.trim().equals("RE")) || !sStstusSF.trim().isEmpty()) {

                                if (sStatus.trim().equals("WIP")) {
                                    sStstusSF = "Nuevo";
                                }
                                if (sStatus.trim().equals("CL")) {
                                    sStstusSF = "Cerrado";
                                }
                                if (sStatus.trim().equals("RE")) {
                                    sStstusSF = "Validación";
                                }
                                log.info("Se status a envia a SF:" + sStstusSF);

                                clsChange_Status_SF oEnvioStatus = new clsChange_Status_SF();
                                login = new Login();
                                if (iCont >= 11) {
                                    //  ¿¿¿¿ --- ????
                                } else {
                                    bBand = true;
                                }
                                while (bBand == true) {

                                    try {
                                        sRespuestaChangeStatus = oEnvioStatus.changeStatus(sTicketSF, sStstusSF, "", sJustificacion);

                                        if (login.Login() == true) {
                                            ///
                                            ActivityLog actlog1 = new ActivityLog();
                                            actlog1.setLogin(login);
                                            List<String> parameters1 = new ArrayList<String>();
                                            parameters1.add(args[2].trim());
                                            parameters1.add("[Respuesta de SF cambio de status]\n" + sRespuestaChangeStatus);
                                            parameters1.add("LOG");
                                            parameters1.add("0");
                                            parameters1.add("False");
                                            actlog1.createActivityLog(parameters1, login.getResult());
                                            bBand = false;
                                        } else {
                                            iContLongin = iContLongin + 1;
                                            if (iContLongin >= 3) {
                                                iCont = 11;
                                                bBand = false;
                                            }
                                            if (iContLongin <= 3) {
                                                TimeUnit.SECONDS.sleep(3);
                                                log.error(" Intento: '" + iContLongin + " '[Error] login CSDM' " + login.getFinalMessage());
                                            }
                                            if (iContLongin >= 4 && iContLongin <= 6) {
                                                TimeUnit.MINUTES.sleep(5);
                                                log.error(" Intento: '" + iContLongin + " '[Error] login CSDM' " + login.getFinalMessage());
                                            }
                                            if (iContLongin >= 7 && iContLongin <= 9) {
                                                TimeUnit.MINUTES.sleep(20);
                                                log.error(" Intento: '" + iContLongin + " '[Error] login CSDM' " + login.getFinalMessage());
                                            }
                                            if (iContLongin >= 10) {
                                                TimeUnit.MINUTES.sleep(30);
                                                log.error(" Intento: '" + iContLongin + " '[Error] login CSDM' " + login.getFinalMessage());
                                            }
                                        }
                                        login.logout("CHANGE_STATUS");
                                    } catch (Exception e) {
                                        iCont = iCont + 1;
                                        if (iCont >= 10) {
                                            bBand = false;
                                        } else {
                                            TimeUnit.SECONDS.sleep(3);
                                        }
                                        log.error("Error al enviar cambio de status a SF:" + e.getMessage());
                                        if (login.Login() == true) {
                                            ///
                                            ActivityLog actlog1 = new ActivityLog();
                                            actlog1.setLogin(login);
                                            List<String> parameters1 = new ArrayList<String>();
                                            parameters1.add(args[2].trim());
                                            parameters1.add("[Error al enviar cambio de status a SF]\n" + e.getMessage());
                                            parameters1.add("LOG");
                                            parameters1.add("0");
                                            parameters1.add("False");
                                            actlog1.createActivityLog(parameters1, login.getResult());
                                        } else {
                                            ////
                                            iCont = iCont + 1;
                                            if (iCont >= 10) {
                                                bBand = false;
                                            }
                                            if (iCont <= 3) {
                                                TimeUnit.SECONDS.sleep(3);
                                                log.error(" Intento: '" + iCont + "' Error al enviar cambio de status a SF:: " + e.getMessage());
                                            }
                                            if (iCont >= 4 && iCont <= 6) {
                                                TimeUnit.MINUTES.sleep(5);
                                                log.error(" Intento: '" + iCont + "' Error al enviar cambio de status a SF: " + e.getMessage());
                                            }
                                            if (iCont >= 7 && iCont <= 9) {
                                                TimeUnit.MINUTES.sleep(20);
                                                log.error(" Intento: '" + iCont + "' Error al enviar cambio de status a SF: " + e.getMessage());
                                            }
                                            if (iCont >= 10) {
                                                TimeUnit.MINUTES.sleep(30);
                                                log.error(" Intento: '" + iCont + "' Error al enviar cambio de status a SF: " + e.getMessage());
                                            }
                                            ////                                           
                                        }
                                        login.logout("CHANGE_STATUS");
                                    }
                                }
                            }
                        } else {
                            log.info("No existe ticket externo no se realiza envio de cambio de status ");
                        }
                    } else {
                        log.info("No es un ticket valido para envio de cambio de status a SF");
                    }
                } else {
                    log.info("Estatus no valido para envio a SF");
                }

                break;

            case ADD_COMMENT:

                log.info("Parametros para envio de comentario a ticket en SF");
                log.info("------> PRUEBA bbb");

                for (int a = 0; a <= args.length - 1; a++) {
                    log.info("Parametros: " + args[a]);
                    if (args[a].trim().equals("log_agent")) {
                        //  log_agent
                        sIdLogAgent = args[a + 1];
                    }
                    if (args[a].trim().equals("zfolio_dbw_sf")) {
                        sTicketSF = args[a + 1];
                    }
                }
                String sComentarios = "";
                String[] aS3 = null;
                String[] aS4 = null;
                String[] aS5 = null;
                String sDescripcionLog2 = "";
                String sComboName2 = "";
                int iEx = 0;

                log.info("Datos obtenidos");
                log.info("log_agent ......:" + sIdLogAgent);
                log.info("zfolio_dbw_sf ......:" + sTicketSF);

                if (!sIdLogAgent.trim().isEmpty() && sIdLogAgent.trim().equals("Integracion SF Dashboard Web")) {
                    //   "Integracion SF Dashboard Web"
                    if (!sTicketSF.trim().isEmpty()) {
                        Ticket oT = new Ticket();

                        while (bBand == true) {
                            try {
                                login = new Login();
                                if (login.Login() == true) {
                                    oT.buscaAlgTicket(args[4].trim(), args[2].trim(), login.getResult(), login.getPort());
                                    sComentarios = oT.getsLogsTCK();
                                    aS3 = new String[sComentarios.split("alg:").length];
                                    aS3 = sComentarios.split("alg:");
                                    if (sComentarios.trim().equals("alg:")) {
                                        log.info("No existen log a enviar a SF");
                                        iEx = iEx + 1;
                                        login.logout("ADD_COMMENT");
                                    } else {
                                        String sTratCadena = "";
                                        sTratCadena = aS3[aS3.length - 1].replace("alg:", "").replace("&¬&¬", "&¬");
                                        aS4 = new String[sTratCadena.split("&¬").length];
                                        aS4 = sTratCadena.split("&¬");
                                        ///
                                        for (int y = 0; y <= aS4.length - 1; y++) {
                                            for (int w = 1; w <= aS3.length - 2; w++) {
                                                aS5 = new String[aS3[w].trim().split("&¬").length];
                                                aS5 = aS3[w].split("&¬");
                                                if (aS4[y].trim().equals(aS5[0].trim())) {
                                                    for (int q = 0; q <= aS5.length - 1; q++) {
                                                        if (aS5[q].trim().equals("description")) {
                                                            sDescripcionLog2 = aS5[q + 1];
                                                        }
                                                        if (aS5[q].trim().equals("analyst.combo_name")) {
                                                            sComboName2 = aS5[q + 1];
                                                        }
                                                    }
                                                    try {

                                                        String sCadenaCommOri = "El analista:" + sComboName2 + " Registra: " + sDescripcionLog2;
                                                        //
                                                        log.info("CadenaOri_Comm: " + sCadenaCommOri.replace("\"", "\\\""));
                                                        byte[] bytes = sCadenaCommOri.getBytes(StandardCharsets.UTF_8);
                                                        String comUtf0 = new String(bytes, StandardCharsets.UTF_8);
                                                        clsComentario_Rest oCommRest = new clsComentario_Rest();
                                                        oCommRest.enviaComentario_Rest(sTicketSF, comUtf0.replace("\"", "\\\"").trim(), "");
                                                        if (login.getFinalMessage().trim().equals("OK")) {

                                                            ActivityLog actlog1 = new ActivityLog();
                                                            actlog1.setLogin(login);
                                                            List<String> parameters1 = new ArrayList<String>();
                                                            parameters1.add(args[2].trim());
                                                            parameters1.add("[Respuesta de SF al envio de comentario]\n" + oCommRest.getFinalMessageRest());
                                                            parameters1.add("LOG");
                                                            parameters1.add("0");
                                                            parameters1.add("False");
                                                            actlog1.createActivityLog(parameters1, login.getResult());
                                                            bBand = false;
                                                            iEx = iEx + 1;
                                                        }
                                                        bBand = false;
                                                    } catch (Exception ex) {
                                                        iCont = iCont + 1;
                                                        if (iCont >= 10) {
                                                            bBand = false;
                                                            iEx = iEx + 1;
                                                        }
                                                        if (iCont <= 3) {
                                                            TimeUnit.SECONDS.sleep(3);
                                                            log.error(" Intento: '" + iCont + "' Error de SF al envio de comentario: " + ex.getMessage());
                                                        }
                                                        if (iCont >= 4 && iCont <= 6) {
                                                            TimeUnit.MINUTES.sleep(5);
                                                            log.error(" Intento: '" + iCont + "' Error de SF al envio de comentario: " + ex.getMessage());
                                                        }
                                                        if (iCont >= 7 && iCont <= 9) {
                                                            TimeUnit.MINUTES.sleep(20);
                                                            log.error(" Intento: '" + iCont + "' Error de SF al envio de comentario: " + ex.getMessage());
                                                        }
                                                        if (iCont >= 10) {
                                                            TimeUnit.MINUTES.sleep(30);
                                                            log.error(" Intento: '" + iCont + "' Error de SF al envio de comentario: " + ex.getMessage());
                                                        }
                                                        if (login.getFinalMessage().trim().equals("OK")) {
                                                            ActivityLog actlog1 = new ActivityLog();
                                                            actlog1.setLogin(login);
                                                            List<String> parameters1 = new ArrayList<String>();
                                                            parameters1.add(args[2].trim());
                                                            parameters1.add("[Error de SF al envio de comentario]\n" + ex.getMessage());
                                                            parameters1.add("LOG");
                                                            parameters1.add("0");
                                                            parameters1.add("False");
                                                            actlog1.createActivityLog(parameters1, login.getResult());
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        //
                                        if (bBand == true) {
                                            login.logout("ADD_COMMENT");
                                        } else {
                                            if (oT.updateAlg(args[4].trim(), aS4, login.getResult(), login.getPort()) == true) {
                                                log.info("Se actualizo las banderas de alg ok");
                                            } else {
                                                log.info("No se actualizo las banderas de alg");
                                            }
                                            login.logout("ADD_COMMENT");
                                        }
                                    }
                                } else {
                                    iContLongin = iContLongin + 1;
                                    if (iContLongin >= 3) {
                                        bBand = false;
                                        iEx = iEx + 1;
                                    }
                                    if (iContLongin <= 3) {
                                        TimeUnit.SECONDS.sleep(3);
                                        log.error(" Intento: '" + iContLongin + " '[Error] login CSDM' " + login.getFinalMessage());
                                    }
                                    if (iContLongin >= 4 && iContLongin <= 6) {
                                        TimeUnit.MINUTES.sleep(5);
                                        log.error(" Intento: '" + iContLongin + " '[Error] login CSDM' " + login.getFinalMessage());
                                    }
                                    if (iContLongin >= 7 && iContLongin <= 9) {
                                        TimeUnit.MINUTES.sleep(20);
                                        log.error(" Intento: '" + iContLongin + " '[Error] login CSDM' " + login.getFinalMessage());
                                    }
                                    if (iContLongin >= 10) {
                                        TimeUnit.MINUTES.sleep(30);
                                        log.error(" Intento: '" + iContLongin + " '[Error] login CSDM' " + login.getFinalMessage());
                                    }
                                    log.error("Error (Login) --> " + login.getFinalMessage());
                                    log.error(login.getFinalMessage());
                                    System.out.print("Login Error: " + login.getFinalMessage());
                                }
                            } catch (Exception e) {
                                iCont = iCont + 1;
                                if (iCont >= 10) {
                                    bBand = false;
                                    iEx = iEx + 1;
                                    log.error(e.getMessage());
                                } else {
                                    log.error(e.getMessage());
                                    iEx = 0;
                                }
                            }
                            if (iEx > 0) {
                                bBand = false;
                            } else {
                                bBand = true;
                            }
                        }
                    } else {
                        log.info("No existe ticket externo no se realiza envio de comentario");
                    }
                } else {
                    log.info("No es un ticket valido para envio de comentario a SF");
                }
                break;

            case CHANGE_GRP:
                log.info("Parametros para envio cambio de grupo a ticket en SF");

                for (int a = 0; a <= args.length - 1; a++) {
                    log.info("Parametros: " + args[a]);
                    if (args[a].trim().equals("log_agent")) {
                        sIdLogAgent = args[a + 1];
                    }
                    if (args[a].trim().equals("zfolio_dbw_sf")) {
                        sTicketSF = args[a + 1];
                    }
                    if (args[a].trim().equals("group")) {
                        sNameGroup = args[a + 1];
                    }
                }
                int iExGR = 0;

                if (!sIdLogAgent.trim().isEmpty() && sIdLogAgent.trim().equals("Integracion SF Dashboard Web")) {
                    if (!sTicketSF.trim().isEmpty() && !sNameGroup.trim().isEmpty()) {
                        while (bBand == true) {
                            try {
                                login = new Login();
                                if (login.Login() == true) {
                                    ///
                                    try {
                                        clsComentario_Rest oGroupCHGRest = new clsComentario_Rest();
                                        oGroupCHGRest.enviaComentario_Rest(sTicketSF, "", sNameGroup);
                                        //
                                        if (login.getFinalMessage().trim().equals("OK")) {

                                            ActivityLog actlog1 = new ActivityLog();
                                            actlog1.setLogin(login);
                                            List<String> parameters1 = new ArrayList<String>();
                                            parameters1.add(args[2].trim());
                                            parameters1.add("[Respuesta de SF al envio de cambio de grupo]\n" + oGroupCHGRest.getFinalMessageRest());
                                            parameters1.add("LOG");
                                            parameters1.add("0");
                                            parameters1.add("False");
                                            actlog1.createActivityLog(parameters1, login.getResult());
                                            bBand = false;
                                            iExGR = iExGR + 1;
                                        }
                                        bBand = false;
                                    } catch (Exception ex) {
                                        iCont = iCont + 1;
                                        if (iCont >= 10) {
                                            bBand = false;
                                            iExGR = iExGR + 1;
                                        }
                                        if (iCont <= 3) {
                                            TimeUnit.SECONDS.sleep(3);
                                            log.error(" Intento: '" + iCont + "' Error de SF al envio de cambio de grupo " + ex.getMessage());
                                        }
                                        if (iCont >= 4 && iCont <= 6) {
                                            TimeUnit.MINUTES.sleep(5);
                                            log.error(" Intento: '" + iCont + "' Error de SF al envio de cambio de grupo: " + ex.getMessage());
                                        }
                                        if (iCont >= 7 && iCont <= 9) {
                                            TimeUnit.MINUTES.sleep(20);
                                            log.error(" Intento: '" + iCont + "' Error de SF al envio de cambio de grupo: " + ex.getMessage());
                                        }
                                        if (iCont >= 10) {
                                            TimeUnit.MINUTES.sleep(30);
                                            log.error(" Intento: '" + iCont + "' Error de SF al envio de cambio de grupo: " + ex.getMessage());
                                        }
                                        if (login.getFinalMessage().trim().equals("OK")) {
                                            ActivityLog actlog1 = new ActivityLog();
                                            actlog1.setLogin(login);
                                            List<String> parameters1 = new ArrayList<String>();
                                            parameters1.add(args[2].trim());
                                            parameters1.add("[Erro de SF al envio de grupo]\n" + ex.getMessage());
                                            parameters1.add("LOG");
                                            parameters1.add("0");
                                            parameters1.add("False");
                                            actlog1.createActivityLog(parameters1, login.getResult());
                                            bBand = true;
                                            iExGR = 0;
                                        }
                                        login.logout("CHANGE_GRP");
                                    }
                                    login.logout("CHANGE_GRP");
                                } else {
                                    iContLongin = iContLongin + 1;
                                    if (iContLongin >= 3) {
                                        bBand = false;
                                        iExGR = iExGR + 1;
                                    }
                                    if (iContLongin <= 3) {
                                        TimeUnit.SECONDS.sleep(3);
                                        log.error(" Intento: '" + iContLongin + " '[Error] login CSDM' " + login.getFinalMessage());
                                    }
                                    if (iContLongin >= 4 && iContLongin <= 6) {
                                        TimeUnit.MINUTES.sleep(5);
                                        log.error(" Intento: '" + iContLongin + " '[Error] login CSDM' " + login.getFinalMessage());
                                    }
                                    if (iContLongin >= 7 && iContLongin <= 9) {
                                        TimeUnit.MINUTES.sleep(20);
                                        log.error(" Intento: '" + iContLongin + " '[Error] login CSDM' " + login.getFinalMessage());
                                    }
                                    if (iContLongin >= 10) {
                                        TimeUnit.MINUTES.sleep(30);
                                        log.error(" Intento: '" + iContLongin + " '[Error] login CSDM' " + login.getFinalMessage());
                                    }
                                    log.error("Error (Login) --> " + login.getFinalMessage());
                                    log.error(login.getFinalMessage());
                                    System.out.print("Login Error: " + login.getFinalMessage());
                                }
                            } catch (Exception e) {
                                iCont = iCont + 1;
                                if (iCont >= 10) {
                                    bBand = false;
                                    iExGR = iExGR + 1;
                                    log.error(e.getMessage());
                                } else {
                                    log.error(e.getMessage());
                                    iEx = 0;
                                }
                            }
                            if (iExGR > 0) {
                                bBand = false;
                            } else {
                                bBand = true;
                            }
                        }
                    } else {
                        log.info("No existe ticket externo no se realiza envio de cambio grupo");
                    }
                } else {
                    log.info("No es un ticket valido para envio de cambio grupo a SF");
                }
                break;

        }
    }

    public static String getCodificacion(String sNSerie) {
        clsBuscaLocalidad oB = new clsBuscaLocalidad();
        String sToken = "";
        sToken = oB.sToken("", sNSerie);
        String sUbicacion = oB.sUbicacionBandeja("", sToken, sNSerie);
        return sUbicacion;
    }

    public static String getOpenDate(String sDate) {
        long unix_seconds = Long.parseLong(sDate);
        Date date = new Date(unix_seconds * 1000L);
        SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd*HH:mm:ss");
        String java_date = jdf.format(date);
        String java_date2 = "";
        java_date2 = java_date.replace("*", "T");
        return java_date2;
    }

    public static void asignarCharSet(String charset) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        log.info("   ::: Charset actual: " + Charset.defaultCharset().name());
        log.info("   ::: File encoding actual: " + System.getProperty("file.encoding"));
        log.info("   ::: Asignando el charset: " + charset);
        System.setProperty("file.encoding", charset);

        log.info("   ::: File encoding actual: " + System.getProperty("file.encoding"));

        Field fieldCharset = Charset.class.getDeclaredField("defaultCharset");
        fieldCharset.setAccessible(true);
        fieldCharset.set(null, null);
        log.info("   ::: Charset actual: " + Charset.defaultCharset().name());

    }

}
