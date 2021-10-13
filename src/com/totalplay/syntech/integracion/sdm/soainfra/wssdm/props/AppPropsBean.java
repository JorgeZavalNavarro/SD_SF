package com.totalplay.syntech.integracion.sdm.soainfra.wssdm.props;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.totalplay.syntech.integracion.sdm.soainfra.wssdm.keys.ApplicationKeys;
import org.apache.log4j.Category;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Jorge Zavala Navarro
 */
public class AppPropsBean {

    // Propiedades de la clase
    private static AppPropsVO propsVO = null;
    private static Properties props = new Properties();

    static final Category log = Category.getInstance(AppPropsBean.class);

    static {

        log.info("Cargando propiedades de la clase...");

        // Cargar propiedades desde el archivo de configuración
        try {

            // Inicializamos las propiedades correspondientes
            cargarProps();

            // Inicializamos los logs
            iniciarLogs();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private static void iniciarLogs() throws FileNotFoundException, IOException {
        log.info("Iniciar los archivos de los logs...");
        Properties logProperties = new Properties();
        logProperties.load(new FileInputStream(AppPropsBean.getPropsVO().getConfiguracion_log()));
        PropertyConfigurator.configure(logProperties);
        log.info("Logs inicializados satisfactoriamente !!");
    }

    private static void cargarProps() throws FileNotFoundException, IOException {
        InputStream is = AppPropsBean.class.getResourceAsStream(ApplicationKeys.ARCHIVO_PROPIEDADES_WEB);
        if (is == null) {
            is = new FileInputStream(ApplicationKeys.ARCHIVO_PROPIEDADES_WEB);
        }
        props.load(is);

        // Inicializar la clase con las propiedades
        propsVO = new AppPropsVO();

        // Cargar las propiedades correspondientes
        propsVO.setBRM_password(props.getProperty(AppPropsKeys.BRM_password));
        propsVO.setBRM_url_wsdl(props.getProperty(AppPropsKeys.BRM_url_wsdl));
        propsVO.setBRM_username(props.getProperty(AppPropsKeys.BRM_username));
        propsVO.setCAServiceDesk_hostname_TPE(props.getProperty(AppPropsKeys.CAServiceDesk_hostname_TPE));
        propsVO.setCAServiceDesk_password_TPE(props.getProperty(AppPropsKeys.CAServiceDesk_password_TPE));
        propsVO.setCAServiceDesk_puerto(props.getProperty(AppPropsKeys.CAServiceDesk_puerto));
        propsVO.setCAServiceDesk_username_TPE(props.getProperty(AppPropsKeys.CAServiceDesk_username_TPE));
        propsVO.setConfiguracion_log(props.getProperty(AppPropsKeys.Configuracion_log));
        propsVO.setMW_IP(props.getProperty(AppPropsKeys.MW_IP));
        propsVO.setMW_IP_REST(props.getProperty(AppPropsKeys.MW_IP_REST));
        propsVO.setMW_Pass(props.getProperty(AppPropsKeys.MW_Pass));
        propsVO.setMW_Pass_REST(props.getProperty(AppPropsKeys.MW_Pass_REST));
        propsVO.setMW_URL_ChgStatusSF(props.getProperty(AppPropsKeys.MW_URL_ChgStatusSF));
        propsVO.setMW_URL_CommentSF_REST(props.getProperty(AppPropsKeys.MW_URL_CommentSF_REST));
        propsVO.setMW_URL_CreateTckSF(props.getProperty(AppPropsKeys.MW_URL_CreateTckSF));
        propsVO.setMW_User(props.getProperty(AppPropsKeys.MW_User));
        propsVO.setMW_User_REST(props.getProperty(AppPropsKeys.MW_User_REST));
        propsVO.setSF_AUR(props.getProperty(AppPropsKeys.SF_AUR));
        propsVO.setSF_AWTVNDR(props.getProperty(AppPropsKeys.SF_AWTVNDR));
        propsVO.setSF_CNCL(props.getProperty(AppPropsKeys.SF_CNCL));
        propsVO.setSF_OP(props.getProperty(AppPropsKeys.SF_OP));
        propsVO.setSF_ZPNDTRCR(props.getProperty(AppPropsKeys.SF_ZPNDTRCR));

    }

    public static AppPropsVO getPropsVO() {
        return propsVO;
    }

    public static void setPropsVO(AppPropsVO propsVO) {
        AppPropsBean.propsVO = propsVO;
    }

}
