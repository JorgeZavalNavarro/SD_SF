/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integracion_sdtosf_local;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author ABS-Davo
 */
public class Ticket {

    private static org.apache.log4j.Logger log = Logger.getLogger(Ticket.class);
    private ArchivoPropiedades arch = new ArchivoPropiedades();
    private String message = "";
    private static Login login;
    private String ref_num = "";
    private String referencia = "";
    private String persid = "";
    private int ACCION = 0;
    private static final int FOLIO_CMAS = 4;
    private static final int STATUS = 5;
    private String prefix_log = "";
    private boolean repetido;
    private boolean error_busqueda;
    private boolean no_existe;
    private int num_repetidos = 0;
    private String log_comment_affecte_resource = "";
    private static final int BUSQUEDA_C1 = 40;
    private static final int BUSQUEDA_C2 = 41;
    private static final int BUSQUEDA_C3 = 42;
    private static final int BUSQUEDA_C4 = 43;
    String sCadena = "";

    public String getsLogsTCK() {
        return sLogsTCK;
    }

    public void setsLogsTCK(String sLogsTCK) {
        this.sLogsTCK = sLogsTCK;
    }
    private String sLogsTCK = "";

    public Ticket() {
    }

    public boolean updateTicketCNL(String persid, String sUsrHandle, int SDI, com.ca.unicenterserviceplus.servicedesk.USDWebServiceSoap usd) throws InterruptedException {
        boolean status = false;
        try {
            usd.changeStatus(SDI, sUsrHandle, persid, "Ticket cancelado por estado Inactivo/Cancelado en BRM", "crs:5214");
            setMessage("OK");
            status = true;
        } catch (Exception e) {

            int iCont = 0;
            for (iCont = 0; iCont <= 50; iCont++) {
                try {
                    usd.changeStatus(SDI, sUsrHandle, persid, "Ticket cancelado por estado Inactivo/Cancelado en BRM", "crs:5214");
                    status = true;
                    break;
                } catch (Exception ex) {

                    iCont = iCont + 1;
                    TimeUnit.SECONDS.sleep(5);
                    setMessage("Error al actualizar --> " + ex.getMessage());
                    log.error(prefix_log + "Intento" + String.valueOf(iCont) + " Erro al actualizar --> " + e.getMessage());
                }
            }
        }
        return status;
    }

    public boolean updateTicketSF(String ref_num, String persid, String sNumSF, int SDI, com.ca.unicenterserviceplus.servicedesk.USDWebServiceSoap usd) throws InterruptedException {
        boolean status = false;
        com.ca.unicenterserviceplus.servicedesk.ArrayOfString attrVals = new com.ca.unicenterserviceplus.servicedesk.ArrayOfString();
        com.ca.unicenterserviceplus.servicedesk.ArrayOfString attributes = new com.ca.unicenterserviceplus.servicedesk.ArrayOfString();
        try {

            attrVals.getString().clear();

            prefix_log = "[" + ref_num + "]  ";
            attrVals.getString().add("external_system_ticket");
            attrVals.getString().add(sNumSF.trim());

            log.info(prefix_log + " Valores a actualizar: ");
            String cadena = "";
            for (int i = 0; i < attrVals.getString().size(); i += 2) {

                cadena += "<" + attrVals.getString().get(i) + ",'" + attrVals.getString().get(i + 1) + "'>,";
            }

            log.info(prefix_log + cadena);

            usd.updateObject(SDI, "cr:" + persid.trim(), attrVals, attributes);

            setMessage("OK");

            status = true;
        } catch (Exception e) {

            int iCont = 0;
            for (iCont = 0; iCont <= 50; iCont++) {
                try {
                    usd.updateObject(SDI, "cr:" + persid.trim(), attrVals, attributes);
                    status = true;
                    break;
                } catch (Exception ex) {

                    iCont = iCont + 1;
                    TimeUnit.SECONDS.sleep(5);
                    setMessage("Error al actualizar --> " + ex.getMessage());
                    log.error(prefix_log + "Intento" + String.valueOf(iCont) + " Erro al actualizar --> " + e.getMessage());
                }
            }
        }
        return status;
    }

    public boolean buscaAlgTicket(String ref_num, String ObjectHandle, int iSDI, com.ca.unicenterserviceplus.servicedesk.USDWebServiceSoap usd) {
        boolean status = false;
        try {
            SearchFor(BUSQUEDA_C1, iSDI, ObjectHandle.trim(), "", "", usd, iSDI);
            setsLogsTCK(sCadena);
            status = true;

        } catch (Exception e) {
            setMessage("Error al buscar_logs " + ref_num + " --> " + e.getMessage());
            log.error("Error al buscar_logs " + ref_num + " --> " + e.getMessage());
        }
        return status;
    }

    public boolean updateAlg(String ref_num, String[] aLogs, int SDI, com.ca.unicenterserviceplus.servicedesk.USDWebServiceSoap usd) {
        boolean status = false;
        try {

            com.ca.unicenterserviceplus.servicedesk.ArrayOfString attrVals = new com.ca.unicenterserviceplus.servicedesk.ArrayOfString();
            com.ca.unicenterserviceplus.servicedesk.ArrayOfString attributes = new com.ca.unicenterserviceplus.servicedesk.ArrayOfString();
            attrVals.getString().clear();

            prefix_log = "[" + ref_num + "]  ";
            attrVals.getString().add("zIntegra_Reg");
            attrVals.getString().add("1");

            log.info(prefix_log + " Valores a actualizar: ");
            String cadena = "";
            for (int i = 0; i < attrVals.getString().size(); i += 2) {

                cadena += "<" + attrVals.getString().get(i) + ",'" + attrVals.getString().get(i + 1) + "'>,";
            }

            log.info(prefix_log + cadena);

            for (int q = 0; q <= aLogs.length - 1; q++) {
                usd.updateObject(SDI, "alg:" + aLogs[q].trim(), attrVals, attributes);
            }
            setMessage("OK");

            status = true;
        } catch (Exception e) {
            setMessage("Error al actualizar --> " + e.getMessage());
            log.error(prefix_log + "Erro al actualizar --> " + e.getMessage());
        }
        return status;
    }

    public String extractHandle(String source) {
        String handle = "";
        String beginString = "<Handle>";
        String endString = "</Handle>";
        int beginIndex = source.indexOf(beginString) + beginString.length();
        int endIndex = source.lastIndexOf(endString);
        handle = source.substring(beginIndex, endIndex);
        return handle;
    }

    public String extractAttrValue(String source) {
        String handle = "";
        String beginString = "<AttrValue>";
        String endString = "</AttrValue>";
        int beginIndex = source.indexOf(beginString) + beginString.length();
        int endIndex = source.lastIndexOf(endString);
        handle = source.substring(beginIndex, endIndex);
        return handle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRef_num() {
        return ref_num;
    }

    public void setRef_num(String ref_num) {
        this.ref_num = ref_num;
    }

    public boolean SearchFor(Integer actionCode, Integer sid, String toSearch, String toSearch1, String toSearch2, com.ca.unicenterserviceplus.servicedesk.USDWebServiceSoap usd, int SDI) {

        boolean status = true;

        String obj = new String();
        String cond = new String(); 
        try {

            com.ca.unicenterserviceplus.servicedesk.ArrayOfString attrArr = new com.ca.unicenterserviceplus.servicedesk.ArrayOfString();

            attrArr.getString().clear();
            int iAccion = 0;
            switch (actionCode) {

                case FOLIO_CMAS:
                    obj = "cr";
                    cond = "ref_num = '" + toSearch + "' and active=1";
                    attrArr.getString().add("ref_num");
                    break;
                case STATUS:
                    obj = "crs";
                    cond = "code = '" + toSearch + "' and delete_flag=0";
                    attrArr.getString().add("code");
                    break;

                case BUSQUEDA_C1:
                    obj = "alg";
                    cond = "call_req_id = '" + toSearch + "' "
                            + " and type ='LOG' "
                            + " and analyst.type = 2307"
                            + " and analyst.userid <> 'Integracion SF Dashboard Web'"
                            + " and analyst.userid <> 'ServiceDesk'"
                            + " and analyst.userid <> 'ahd'";
                    attrArr.getString().add("analyst.last_name");
                    attrArr.getString().add("zIntegra_Reg");
                    attrArr.getString().add("description");
                    attrArr.getString().add("analyst.combo_name");
                    attrArr.getString().add("id");
                    iAccion = BUSQUEDA_C1;
                    break;
                case BUSQUEDA_C2:
                    obj = "cr";
                    cond = "ref_num = '" + toSearch + "' and active=1";
                    attrArr.getString().add("open_date");
                    break;
                case BUSQUEDA_C3:
                    obj = "cr";
                    cond = "ref_num = '" + toSearch + "' and active=1";
                    attrArr.getString().add("zsolucion_id.sym");
                    break;
                case BUSQUEDA_C4:
                    obj = "cr";
                    cond = "ref_num = '" + toSearch + "' and active=1";
                    attrArr.getString().add("zDiagnostico_id.sym");
                    break;

            }
            com.ca.unicenterserviceplus.servicedesk.ListResult qryLst = usd.doQuery(sid, obj, cond);
            int listHandle = qryLst.getListHandle();
            int listLegth = qryLst.getListLength();

            if (listLegth != 0) {
                if (listLegth == 1) {
                    setRepetido(false);
                }
                if (listLegth > 1 && iAccion != 31) {
                    setNum_repetidos(listLegth);
                    setRepetido(true);
                    log.info(prefix_log + "Se arrojaron '" + listLegth + "' resultados para(" + obj + "): '" + cond + "'");
                    setMessage("Se arrojaron '" + listLegth + "' resultados para(" + obj + "): '" + cond + "'");
                } else {
                    String lstVals = usd.getListValues(sid, listHandle, 0, 0, attrArr);
                    referencia = extractHandle(lstVals);
                    ref_num = extractAttrValue(lstVals);
                }
                if (listLegth >= 1 && iAccion == 31) {
                    String lstVals = "";
                    lstVals = lstVals + "\n" + usd.getListValues(login.getResult(), listHandle, 0, 0, attrArr);
                    ref_num = lstVals;
                }

                if (listLegth > 0 && iAccion == 40) {
                    String sValores = "";
                    sValores = tratamiento_CAMPOS_ACTIVO_REMEDYProtoComentario(listHandle, listLegth, attrArr, usd, iAccion, SDI);
                    String[] sArray = null;
                    sArray = new String[sValores.split("\\|").length];
                    sArray = sValores.split("\\|");
                    if (sArray.length > 1) {
                        referencia = sValores;
                    } else {
                        referencia = "No existen comentarios|";
                    }
                }

                setError_busqueda(false);
                setNo_existe(false);
            } else {
                setNo_existe(true);
                setMessage("No se arrojaron resultados para para(" + obj + "): '" + cond + "'");
            }
        } catch (Exception ex) {
            setError_busqueda(true);
            setMessage(ex.getMessage());
            log.error(prefix_log + "Error al buscar : '" + cond + "'. " + ex.getMessage());
        }
        return status;
    }

    public String tratamiento_CAMPOS_ACTIVO_REMEDYProtoComentario(int listHandle, int listLegth, com.ca.unicenterserviceplus.servicedesk.ArrayOfString attrArr, com.ca.unicenterserviceplus.servicedesk.USDWebServiceSoap usd, int iAccion, int SDI) {
        String aR1[];
        String aR2[];
        String aR3[] = null;
        String f = "";
        int bBand = 0;
        String sIdR3 = "";
        String sDescripcion = "";
        String sDesc1 = "";
        int iVerifica = 0;
        int iContDesc = 0;
        for (int i = 0; i <= listLegth - 1; i++) {
            String lstVals = usd.getListValues(SDI, listHandle, i, (i), attrArr);
            String array[];
            array = new String[lstVals.replace("</AttrValue>", "\n</AttrValue>").split("\n").length];
            array = lstVals.replace("</AttrValue>", "\n</AttrValue>").split("\n");

            for (int iLinea = 0, x = 0; x <= array.length - 1; x++) {
                iContDesc = 0;
                sDescripcion = "";
                if (array[x].trim().toString().startsWith("<Handle>") || array[x].trim().toString().startsWith("</Handle>")) {
                    sCadena = sCadena + "&¬" + (array[x].toString().replace("<Handle>", "")).replace("</Handle>", "");
                } else {
                    if (array[x].toString().trim().equals("<AttrName>description</AttrName>") && array[x + 1].toString().trim().equals("<AttrValue>")
                            || (array[x].toString().trim().equals("<AttrName>description</AttrName>") && array[x + 1].toString().trim().startsWith("<AttrValue>")
                            && array[x + 1].toString().trim().replace("<AttrValue>", "").length() > 0)) {
                        if (array[x + 1].toString().trim().endsWith("</AttrValue>")) {
                        } else {
                            for (int x2 = x + 1; x2 <= array.length - 1; x2++) {
                                if (array[x2].toString().trim().equals("</AttrValue>")) {
                                    iContDesc = iContDesc + 1;
                                } else {
                                    if (iContDesc == 0) {
                                        if (array[x2 + 1].trim().toString().equals("</AttrValue>")) {
                                            if (array[x2].toString().trim().startsWith("<AttrValue>") && array[x2].toString().trim().replace("<AttrValue>", "").length() > 0) {
                                                sDescripcion = sDescripcion + array[x2].toString().replace("<AttrValue>", "") + "\n";
                                            } else {
                                                if (array[x2].toString().trim().startsWith("<AttrValue>") || array[x2].toString().endsWith("</AttrValue>")) {
                                                } else {
                                                    sDescripcion = sDescripcion + array[x2] + "\n";
                                                }
                                            }
                                        } else {

                                            if (array[x2].toString().trim().startsWith("<AttrValue>") && array[x2].toString().trim().replace("<AttrValue>", "").length() > 0) {
                                                sDescripcion = sDescripcion + array[x2].toString().replace("<AttrValue>", "") + "\n";
                                                iVerifica = iVerifica + 1;
                                            } else {
                                                if (array[x2].toString().trim().replace("<AttrValue>", "").length() > 0) {
                                                    sDescripcion = sDescripcion + array[x2].toString().replace("<AttrValue>", "") + "\n";
                                                } else {
                                                    if (iVerifica > 0) {
                                                    } else {
                                                        sDescripcion = sDescripcion + array[x2 + 1].toString() + "\n";
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }
                            sCadena = sCadena + "&¬description&¬" + sDescripcion;
                        }

                    } else {

                        if (array[x].trim().toString().startsWith("<AttrName>") || array[x].trim().toString().startsWith("</AttrName>")) {
                            sCadena = sCadena + "&¬" + (array[x].trim().toString().replace("<AttrName>", "")).replace("</AttrName>", "");
                            if (array[x + 1].trim().toString().startsWith("<AttrValue>") || array[x + 1].trim().toString().startsWith("</AttrValue>")) {
                                sCadena = sCadena + "&¬" + (array[x + 1].trim().toString().replace("<AttrValue>", "")).replace("</AttrValue>", " ");
                                iLinea++;
                            } else {
                            }
                        }

                    }
                }
            }
            sCadena = sCadena + "¬¬";
        }
        String sCadena2 = "";
        if (iAccion == 40 || iAccion == 55 || iAccion == 70) {
            aR1 = new String[sCadena.split("¬¬").length];
            aR1 = sCadena.split("¬¬");

            for (int m = 0; m <= aR1.length - 1; m++) {
                aR2 = new String[aR1[m].split("&¬").length];
                aR2 = aR1[m].split("&¬");
                for (int j = 0; j <= aR2.length - 1; j++) {
                    if (aR2[j].trim().equals("analyst.last_name")) {
                        if (aR2[j + 1].trim().equals("System_AHD_generated") || aR2[j + 1].trim().equals("Integracion SF Dashboard Web") || aR2[j + 1].trim().equals("Integracion_SalesForce")) {
                        } else {
                            if (aR2[j + 3].trim().equals("description") || aR2[j + 3].trim().equals("0")) {
                                sCadena2 = sCadena2 + aR1[m] + "\n";
                                bBand = 1;
                            }
                        }
                    }
                    if (aR2[j].trim().equals("id") && bBand == 1) {
                        if (sIdR3.trim().isEmpty()) {
                            sIdR3 = aR2[j + 1].trim();
                        } else {
                            sIdR3 = sIdR3 + "&¬" + aR2[j + 1].trim();
                        }
                        bBand = 0;
                    }
                }
            }

            if (!sIdR3.isEmpty()) {
                aR3 = new String[sIdR3.split("&¬").length];
                aR3 = sIdR3.split("&¬");

                int[] aInt = null;
                aInt = new int[aR3.length];
                for (int h = 0; h <= aR3.length - 1; h++) {
                    aInt[h] = Integer.parseInt(aR3[h]);
                }
                Arrays.sort(aInt);
                for (int h = 0; h <= aInt.length - 1; h++) {
                    if (f.trim().isEmpty()) {
                        f = Integer.toString(aInt[h]) + "&¬";
                    } else {
                        f = f + "&¬" + Integer.toString(aInt[h]).replace("&¬&¬", "&¬");//&¬&¬
                    }
                }
            }
            sCadena = sCadena2.replace("&¬alg:", "alg:") + "alg:" + f;
        }

        log.info("Comentarios encontrados:" + sCadena);
        return sCadena;
    }

    public void limpiaVariables() {
        setError_busqueda(false);
        setNo_existe(false);
        setNum_repetidos(0);
        setRepetido(false);
        setReferencia("");
    }

    public boolean isError_busqueda() {
        return error_busqueda;
    }

    public void setError_busqueda(boolean error_busqueda) {
        this.error_busqueda = error_busqueda;
    }

    public boolean isNo_existe() {
        return no_existe;
    }

    public void setNo_existe(boolean no_existe) {
        this.no_existe = no_existe;
    }

    public int getNum_repetidos() {
        return num_repetidos;
    }

    public void setNum_repetidos(int num_repetidos) {
        this.num_repetidos = num_repetidos;
    }

    public boolean isRepetido() {
        return repetido;
    }

    public void setRepetido(boolean repetido) {
        this.repetido = repetido;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getACCION() {
        return ACCION;
    }

    public void setACCION(int ACCION) {
        this.ACCION = ACCION;
    }

    public String getPrefix_log() {
        return prefix_log;
    }

    public void setPrefix_log(String prefix_log) {
        this.prefix_log = prefix_log;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getPersid() {
        return persid;
    }

    public void setPersid(String persid) {
        this.persid = persid;
    }

    public String obtenValorDefault(String arg) {
//
//        }
        if (arg.equals("customer") || arg.equals("assignee")) {
            return getLogin().getUserHandle();
        }

        return "";
    }

    public String convierteFecha(String arg) {
        String fecha = "";

        try {

            int anio = Integer.parseInt(arg.substring(0, 4));
            int mes = Integer.parseInt(arg.substring(5, 7));
            int dia = Integer.parseInt(arg.substring(8, 10));

            int hora = Integer.parseInt(arg.substring(11, 13));
            int min = Integer.parseInt(arg.substring(14, 16));
            int seg = Integer.parseInt(arg.substring(17, 19));

            Date d = new Date((anio - 1900), (mes - 1), dia, hora, min, seg);

            fecha = d.getTime() + "";
            fecha = fecha.substring(0, (fecha.length() - 3));

        } catch (Exception e) {
            log.error("Error al convertir la fecha '" + arg + "' . " + e.getMessage());
        }
        return fecha;
    }

    public ArchivoPropiedades getArch() {
        return arch;
    }

    public void setArch(ArchivoPropiedades arch) {
        this.arch = arch;
    }

    public String getLog_comment_affecte_resource() {
        return log_comment_affecte_resource;
    }

    public void setLog_comment_affecte_resource(String log_comment_affecte_resource) {
        this.log_comment_affecte_resource = log_comment_affecte_resource;
    }
}
