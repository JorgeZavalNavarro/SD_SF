
package com.totalplay.soa.salesforce.createcommentinsf.bpelprocesscreatecommentinsf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "BPELProcessCreateCommentInSF", targetNamespace = "http://soa.totalplay.com/SalesForce/CreateCommentInSF/BPELProcessCreateCommentInSF")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface BPELProcessCreateCommentInSF {


    /**
     * 
     * @param result
     * @param nameAtachment
     * @param contentFile
     * @param idResult
     * @param numTicket
     * @param comment
     * @param resultDescription
     * @param login
     * @param numeroTicket
     */
    @WebMethod(action = "process")
    @RequestWrapper(localName = "process", targetNamespace = "http://soa.totalplay.com/SalesForce/CreateCommentInSF/BPELProcessCreateCommentInSF", className = "com.totalplay.soa.salesforce.createcommentinsf.bpelprocesscreatecommentinsf.Process")
    @ResponseWrapper(localName = "processResponse", targetNamespace = "http://soa.totalplay.com/SalesForce/CreateCommentInSF/BPELProcessCreateCommentInSF", className = "com.totalplay.soa.salesforce.createcommentinsf.bpelprocesscreatecommentinsf.ProcessResponse")
    public void process(
        @WebParam(name = "Login", targetNamespace = "http://soa.totalplay.com/SalesForce/CreateCommentInSF/BPELProcessCreateCommentInSF")
        com.totalplay.soa.salesforce.createcommentinsf.bpelprocesscreatecommentinsf.Process.Login login,
        @WebParam(name = "numTicket", targetNamespace = "http://soa.totalplay.com/SalesForce/CreateCommentInSF/BPELProcessCreateCommentInSF")
        String numTicket,
        @WebParam(name = "Comment", targetNamespace = "http://soa.totalplay.com/SalesForce/CreateCommentInSF/BPELProcessCreateCommentInSF")
        String comment,
        @WebParam(name = "nameAtachment", targetNamespace = "http://soa.totalplay.com/SalesForce/CreateCommentInSF/BPELProcessCreateCommentInSF")
        String nameAtachment,
        @WebParam(name = "contentFile", targetNamespace = "http://soa.totalplay.com/SalesForce/CreateCommentInSF/BPELProcessCreateCommentInSF")
        String contentFile,
        @WebParam(name = "IdResult", targetNamespace = "http://soa.totalplay.com/SalesForce/CreateCommentInSF/BPELProcessCreateCommentInSF", mode = WebParam.Mode.OUT)
        Holder<String> idResult,
        @WebParam(name = "Result", targetNamespace = "http://soa.totalplay.com/SalesForce/CreateCommentInSF/BPELProcessCreateCommentInSF", mode = WebParam.Mode.OUT)
        Holder<String> result,
        @WebParam(name = "ResultDescription", targetNamespace = "http://soa.totalplay.com/SalesForce/CreateCommentInSF/BPELProcessCreateCommentInSF", mode = WebParam.Mode.OUT)
        Holder<String> resultDescription,
        @WebParam(name = "numeroTicket", targetNamespace = "http://soa.totalplay.com/SalesForce/CreateCommentInSF/BPELProcessCreateCommentInSF", mode = WebParam.Mode.OUT)
        Holder<String> numeroTicket);

}
