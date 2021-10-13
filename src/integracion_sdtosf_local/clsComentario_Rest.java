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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class clsComentario_Rest {

    private static org.apache.log4j.Logger log = Logger.getLogger(clsComentario_Rest.class);
    private static ArchivoPropiedades arch;
    private String finalMessageRest = "";

    public boolean enviaComentario_Rest(String sExternal, String sComentario, String sGrupo) {

        boolean bVand = false;

        arch = new ArchivoPropiedades();
        arch.read_NX_ROOT();
        arch.leeConfiguracion();
        PropertyConfigurator.configure(arch.getProp().getProperty("Configuracion_log"));

        String sUrl = arch.getProp().getProperty("MW_URL_CommentSF_REST");
        String sUser = arch.getProp().getProperty("MW_User_REST");
        String sPassword = arch.getProp().getProperty("MW_Pass_REST");
        String sIp = arch.getProp().getProperty("MW_IP_REST");

        String sRespuestaRest = "";
        try {

            URL url = new URL(sUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"Login\":{"
                    + "\"UserId\":\"" + sUser.trim() + "\","
                    + "\"Password\":\"" + sPassword.trim() + "\","
                    + "\"IP\":\"" + sIp.trim() + "\""
                    + "},"
                    + "\"NoTicket\":\"" + sExternal.trim() + "\","
                    + "\"Comment\":\"" + sComentario.trim().replace("\n", " \\n ").replace("\t", " \\t ").replace("\r", " \\r ").replace("\f", " \\f ").replace("\b", " \\b ") + "\","
                    + "\"Grupo\":\"" + sGrupo + "\""
                    + "}";

            log.info("JSON:" + input);
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                finalMessageRest = "Failed : HTTP error code : "
                        + conn.getResponseCode() + "\n" + conn.getResponseMessage();
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode() + "\n" + conn.getResponseMessage());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                sRespuestaRest = sRespuestaRest + "\n" + output;
            }
            log.info("Respuesta desde SF: " + sRespuestaRest);
            setFinalMessageRest(sRespuestaRest);
            conn.disconnect();
            bVand = true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            log.info("Error MalformedURLException :" + e.getMessage());

        } catch (IOException e) {
            e.printStackTrace();
            log.info("Error IOException :" + e.getMessage());

        }
        return bVand;
    }
    public String getFinalMessageRest() {
        return finalMessageRest;
    }

    public void setFinalMessageRest(String finalMessageRest) {
        this.finalMessageRest = finalMessageRest;
    }
}
