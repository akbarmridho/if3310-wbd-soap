package com.listwibuku.exception;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;

public class InvalidApiKeyException extends SOAPFaultException {
    /**
     * Constructor for SOAPFaultException
     *
     * @see SOAPFactory#createFault
     **/
    public InvalidApiKeyException(SOAPFault fault) {
        super(fault);
    }

    public static InvalidApiKeyException create() {
        try {
            SOAPFactory soapFactory = SOAPFactory.newInstance();
            SOAPFault soapFault = soapFactory.createFault("Invalid Api key", new QName("http://schemas.xmlsoap.org/soap/envelope/", "Client"));

            return new InvalidApiKeyException(soapFault);
        } catch (Exception ignored) {
            throw new RuntimeException("wtf is this?!");
        }
    }
}
