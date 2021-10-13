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
class BrmResponse {

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResultID() {
        return resultID;
    }

    public void setResultID(String resultID) {
        this.resultID = resultID;
    }

    public String getResultIDescription() {
        return resultIDescription;
    }

    public void setResultIDescription(String resultIDescription) {
        this.resultIDescription = resultIDescription;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getStatusCuenta() {
        return statusCuenta;
    }

    public void setStatusCuenta(String statusCuenta) {
        this.statusCuenta = statusCuenta;
    }
    private String result;
    private String resultID;
    private String resultIDescription;
    private String cuenta;
    private String statusCuenta;
}
