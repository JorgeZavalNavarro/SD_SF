package mx.com.syntech.totalplay.sdmsf.integracion.cambiarestatus;

import com.totalplay.syntech.integracion.sdm.soainfra.wssdm.core.CoreBean;
import com.totalplay.syntech.integracion.sdm.soainfra.wssdm.props.AppPropsBean;

/**
 *
 * @author dell
 */
public class CambiarEstatusbean extends CoreBean{
    
    /**
     * testeo
     */
    public static void main(String[] params){
        System.out.println("URL: " + AppPropsBean.getPropsVO().getCAServiceDesk_hostname_TPE());
    }
    
}
