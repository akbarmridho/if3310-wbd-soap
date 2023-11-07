package com.listwibuku.middleware;

import com.sun.net.httpserver.HttpExchange;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

public class Logger implements SOAPHandler<SOAPMessageContext> {
    private final String httpExchangeKey = "com.sun.xml.internal.ws.http.exchange";

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        String client = (String) context.get("authenticated-client");

        boolean isResponse = (boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        HttpExchange httpExchange = (HttpExchange) context.get(this.httpExchangeKey);

        if (isResponse) {
            return true;
        }

        try {
            SOAPPart soapPart = context.getMessage().getSOAPPart();
            SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
            SOAPBody soapBody = soapEnvelope.getBody();

            Node operation = soapBody.getChildNodes().item(1);
            String content = String.format("%s", operation.getLocalName());

            NodeList parameters = operation.getChildNodes();
            for (int i = 1; i < parameters.getLength(); i += 2) {
                content = String.format("%s %s(%s)", content, parameters.item(i).getLocalName(), parameters.item(i).getTextContent());
            }

            // save to db
            System.out.println("Client");
            System.out.println(client);
            System.out.println("Description");
            System.out.println(content);
            System.out.println("Endpoint");
            System.out.println(httpExchange.getRequestURI());
            System.out.println("Ip address");
            System.out.println(httpExchange.getRemoteAddress().getHostString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {

    }
}
