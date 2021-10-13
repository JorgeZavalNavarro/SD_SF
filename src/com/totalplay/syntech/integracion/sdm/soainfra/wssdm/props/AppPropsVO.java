package com.totalplay.syntech.integracion.sdm.soainfra.wssdm.props;

/**
 *
 * @author Jorge Zavala Navarro
 */
public class AppPropsVO {
    
    private String Configuracion_log = null;
    private String CAServiceDesk_puerto = null;
    private String CAServiceDesk_hostname_TPE = null;
    private String CAServiceDesk_username_TPE = null;
    private String CAServiceDesk_password_TPE = null;
    private String MW_User = null;
    private String MW_Pass = null;
    private String MW_IP = null;
    private String MW_URL_ChgStatusSF = null;
    private String MW_URL_CommentSF_REST = null;
    private String MW_User_REST = null;
    private String MW_Pass_REST = null;
    private String MW_IP_REST = null;
    private String MW_URL_CreateTckSF = null;
    private String BRM_username = null;
    private String BRM_password = null;
    private String BRM_url_wsdl = null;
    private String SF_OP = null;
    private String SF_AUR = null;
    private String SF_AWTVNDR = null;
    private String SF_ZPNDTRCR = null;
    private String SF_CNCL = null;

    public String getConfiguracion_log() {
        return Configuracion_log;
    }

    public void setConfiguracion_log(String Configuracion_log) {
        this.Configuracion_log = Configuracion_log;
    }

    public String getCAServiceDesk_puerto() {
        return CAServiceDesk_puerto;
    }

    public void setCAServiceDesk_puerto(String CAServiceDesk_puerto) {
        this.CAServiceDesk_puerto = CAServiceDesk_puerto;
    }

    public String getCAServiceDesk_hostname_TPE() {
        return CAServiceDesk_hostname_TPE;
    }

    public void setCAServiceDesk_hostname_TPE(String CAServiceDesk_hostname_TPE) {
        this.CAServiceDesk_hostname_TPE = CAServiceDesk_hostname_TPE;
    }

    public String getCAServiceDesk_username_TPE() {
        return CAServiceDesk_username_TPE;
    }

    public void setCAServiceDesk_username_TPE(String CAServiceDesk_username_TPE) {
        this.CAServiceDesk_username_TPE = CAServiceDesk_username_TPE;
    }

    public String getCAServiceDesk_password_TPE() {
        return CAServiceDesk_password_TPE;
    }

    public void setCAServiceDesk_password_TPE(String CAServiceDesk_password_TPE) {
        this.CAServiceDesk_password_TPE = CAServiceDesk_password_TPE;
    }

    public String getMW_User() {
        return MW_User;
    }

    public void setMW_User(String MW_User) {
        this.MW_User = MW_User;
    }

    public String getMW_Pass() {
        return MW_Pass;
    }

    public void setMW_Pass(String MW_Pass) {
        this.MW_Pass = MW_Pass;
    }

    public String getMW_IP() {
        return MW_IP;
    }

    public void setMW_IP(String MW_IP) {
        this.MW_IP = MW_IP;
    }

    public String getMW_URL_ChgStatusSF() {
        return MW_URL_ChgStatusSF;
    }

    public void setMW_URL_ChgStatusSF(String MW_URL_ChgStatusSF) {
        this.MW_URL_ChgStatusSF = MW_URL_ChgStatusSF;
    }

    public String getMW_URL_CommentSF_REST() {
        return MW_URL_CommentSF_REST;
    }

    public void setMW_URL_CommentSF_REST(String MW_URL_CommentSF_REST) {
        this.MW_URL_CommentSF_REST = MW_URL_CommentSF_REST;
    }

    public String getMW_User_REST() {
        return MW_User_REST;
    }

    public void setMW_User_REST(String MW_User_REST) {
        this.MW_User_REST = MW_User_REST;
    }

    public String getMW_Pass_REST() {
        return MW_Pass_REST;
    }

    public void setMW_Pass_REST(String MW_Pass_REST) {
        this.MW_Pass_REST = MW_Pass_REST;
    }

    public String getMW_IP_REST() {
        return MW_IP_REST;
    }

    public void setMW_IP_REST(String MW_IP_REST) {
        this.MW_IP_REST = MW_IP_REST;
    }

    public String getMW_URL_CreateTckSF() {
        return MW_URL_CreateTckSF;
    }

    public void setMW_URL_CreateTckSF(String MW_URL_CreateTckSF) {
        this.MW_URL_CreateTckSF = MW_URL_CreateTckSF;
    }

    public String getBRM_username() {
        return BRM_username;
    }

    public void setBRM_username(String BRM_username) {
        this.BRM_username = BRM_username;
    }

    public String getBRM_password() {
        return BRM_password;
    }

    public void setBRM_password(String BRM_password) {
        this.BRM_password = BRM_password;
    }

    public String getBRM_url_wsdl() {
        return BRM_url_wsdl;
    }

    public void setBRM_url_wsdl(String BRM_url_wsdl) {
        this.BRM_url_wsdl = BRM_url_wsdl;
    }

    public String getSF_OP() {
        return SF_OP;
    }

    public void setSF_OP(String SF_OP) {
        this.SF_OP = SF_OP;
    }

    public String getSF_AUR() {
        return SF_AUR;
    }

    public void setSF_AUR(String SF_AUR) {
        this.SF_AUR = SF_AUR;
    }

    public String getSF_AWTVNDR() {
        return SF_AWTVNDR;
    }

    public void setSF_AWTVNDR(String SF_AWTVNDR) {
        this.SF_AWTVNDR = SF_AWTVNDR;
    }

    public String getSF_ZPNDTRCR() {
        return SF_ZPNDTRCR;
    }

    public void setSF_ZPNDTRCR(String SF_ZPNDTRCR) {
        this.SF_ZPNDTRCR = SF_ZPNDTRCR;
    }

    public String getSF_CNCL() {
        return SF_CNCL;
    }

    public void setSF_CNCL(String SF_CNCL) {
        this.SF_CNCL = SF_CNCL;
    }

    @Override
    public String toString() {
        return "AppPropsVO{" + "Configuracion_log=" + Configuracion_log + ", CAServiceDesk_puerto=" + CAServiceDesk_puerto + ", CAServiceDesk_hostname_TPE=" + CAServiceDesk_hostname_TPE + ", CAServiceDesk_username_TPE=" + CAServiceDesk_username_TPE + ", CAServiceDesk_password_TPE=" + CAServiceDesk_password_TPE + ", MW_User=" + MW_User + ", MW_Pass=" + MW_Pass + ", MW_IP=" + MW_IP + ", MW_URL_ChgStatusSF=" + MW_URL_ChgStatusSF + ", MW_URL_CommentSF_REST=" + MW_URL_CommentSF_REST + ", MW_User_REST=" + MW_User_REST + ", MW_Pass_REST=" + MW_Pass_REST + ", MW_IP_REST=" + MW_IP_REST + ", MW_URL_CreateTckSF=" + MW_URL_CreateTckSF + ", BRM_username=" + BRM_username + ", BRM_password=" + BRM_password + ", BRM_url_wsdl=" + BRM_url_wsdl + ", SF_OP=" + SF_OP + ", SF_AUR=" + SF_AUR + ", SF_AWTVNDR=" + SF_AWTVNDR + ", SF_ZPNDTRCR=" + SF_ZPNDTRCR + ", SF_CNCL=" + SF_CNCL + '}';
    }
    
    
    
    


}
