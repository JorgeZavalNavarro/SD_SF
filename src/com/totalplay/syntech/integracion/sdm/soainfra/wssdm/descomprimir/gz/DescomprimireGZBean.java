package com.totalplay.syntech.integracion.sdm.soainfra.wssdm.descomprimir.gz;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Jorge Zavala Navarro
 */
public class DescomprimireGZBean {

    public InputStream descomprimirTOInputStream(String origen, String extension) throws DescomprimireGZException {
        InputStream retorno = null;
        if (origen != null && !origen.isEmpty()) {

            // definimos el nombre de nuestro archivo destino
            String destino = origen + "." + extension;
            try {
                GZIPInputStream in = new GZIPInputStream(new FileInputStream(origen));

                // nuestro in debe de contener un archivo zip/rar/tar
                ZipInputStream zipIs = new ZipInputStream(in);
                ZipEntry entry = null;
                // Fetch entries (From Top to Bottom)
                while ((entry = zipIs.getNextEntry()) != null) {
                    if (entry.isDirectory()) {
                        System.out.print("Directory: ");
                    } else {
                        // Cargar el contenido de 
                        byte[] byteO = new byte[(int)entry.getSize()];
                        zipIs.read(byteO);
                        
                        // El byte leido lo convertimos a InputStream
                        retorno = new ByteArrayInputStream(byteO);
                        
                    }
                    System.out.println(entry.getName());
                }
                // retorno = in;

                // El contenido de nuestro 
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new DescomprimireGZException(ex);
            }
        } else {
            throw new DescomprimireGZException("No se está recibiendo la información completa para trabajar ");
        }
        return retorno;
    }

    public static void main(String... params) {
        try {
            String archivoOrigen = "C:\\SYNTECH\\SERVICIOS\\TotalPlay\\WebServiceConsultasPDR\\Desarrollo\\IntegracionSDvsSoaInfra\\src\\java\\resources\\Ejemplos\\C25C83750386BF43BC2D9E2D2F2D4DFC_Incidente CARE.jpg.gz";
            DescomprimireGZBean bean = new DescomprimireGZBean();
            bean.descomprimirTOInputStream(archivoOrigen, "jpg");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
