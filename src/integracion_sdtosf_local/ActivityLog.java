/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integracion_sdtosf_local;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

/**
 *
 * @author ABS-Davo
 */
public class ActivityLog {

    private static org.apache.log4j.Logger log = Logger.getLogger(ActivityLog.class);
    private static Login login;
    private String message;
    private String ref_num;
    private final int seachCreator = 0;
    private final int searchHandleTicket = 1;

    private String prefix_log = "";

    public ActivityLog() {

    }

    public boolean createActivityLog(List<String> parameters, int iSdiTPE) throws InterruptedException {
        boolean status = true;
        boolean registro = true;
        int cont = 0;
        int sid = 0;
        String creator = null;
        String objectHandle = null;
        String description = null;
        String logType = null;
        int timeSpent = 0;
        boolean internal = Boolean.FALSE;
        while (registro == true) {
            try {
                sid = iSdiTPE;
                creator = "";
                objectHandle = parameters.get(0);
                description = parameters.get(1);
                logType = parameters.get(2);
                timeSpent = Integer.parseInt(parameters.get(3));
                internal = parameters.get(4).equals("True");
                creator = login.getUserHandle();

                login.getPort().createActivityLog(sid, creator, objectHandle, description, logType, timeSpent, internal);
                status = true;
                setMessage("OK");
                registro = false;
            } catch (Exception e) {
                log.error("Error al intentar generar un registro en el ActivityLog de SDM, con los siguientes parámetros");
                log.error("Session ID .......: " + sid);
                log.error("Creator ..........: " + creator);
                log.error("objectHandle .....: " + objectHandle);
                log.error("description ......: " + description);
                log.error("logType ..........: " + logType);
                log.error("timeSpent ........: " + timeSpent);
                log.error("internal .........: " + internal);
                cont = cont + 1;
                if (cont >= 10) {
                    registro = false;
                    TimeUnit.SECONDS.sleep(3);
                    status = false;
                    System.out.println("Error createActivityLog() ->" + e.getMessage());
                    log.error(prefix_log + e.getMessage());
                    setMessage(e.getMessage());
                }

            }
        }
        return status;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPrefix_log() {
        return prefix_log;
    }

    public void setPrefix_log(String prefix_log) {
        this.prefix_log = prefix_log;
    }

}
