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
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import org.json.JSONObject;
import java.io.DataOutputStream;
import org.glassfish.jersey.internal.util.Base64;
import org.apache.log4j.Logger;

public class clsBuscaLocalidad {

    private static org.apache.log4j.Logger log = Logger.getLogger(clsBuscaLocalidad.class);
    AlgoritmoAES al = new AlgoritmoAES();
    ArchivoPropiedades arcProp = new ArchivoPropiedades();

    public String sToken(String URL_API, String sSerie) {

        try {
            String url = "http://10.180.199.117:8080/api/oauth/token?grant_type=password&username=telegram&password=telegram062019";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");

            String user_name = "client";
            String password = "client";

            String userCredentials = user_name + ":" + password;
            String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
            con.setRequestProperty("Authorization", basicAuth);
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64; rv:27.0) Gecko/20100101 Firefox/27.0.2 Waterfox/27.0");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String urlParameters = "username=telegram&password=telegram062019";
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject jsonObj = new JSONObject(response.toString());
            String access_token = jsonObj.getString("access_token");
            log.info("access_token : " + access_token);
            return access_token;
        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
            log.error("Error: " + e.getMessage());
            return "Error: " + e.getMessage();
        }

    }

    public String sUbicacionBandeja(String sIp, String sTokenAPI, String sNumeroSerie) {

        boolean success = false;
        String bandeja = "";

        try {

            URL url = new URL("http://10.180.199.117:8080/api/ont/region?access_token=" + sTokenAPI + "");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            String jsonInputString = "{\"numeroSerie\": \"" + sNumeroSerie + "\"}";
            OutputStream os = conn.getOutputStream();
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            System.out.println(response.toString());
            JSONObject jsonObj = new JSONObject(response.toString());
            success = jsonObj.getBoolean("success");
            System.out.println("Respuesta_success : " + success);

            if (success == true) {
                bandeja = jsonObj.getString("bandeja");
                log.info("Respuesta_bandeja : " + bandeja);
                return bandeja;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            log.error("Error Bandeja1: " + e.getMessage());
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Error Bandeja2: " + e.getMessage());
            return "";
        }
        return "";
    }
}
