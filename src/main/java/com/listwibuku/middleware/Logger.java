package com.listwibuku.middleware;

import com.listwibuku.models.Log;
import com.listwibuku.repository.LogRepository;
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
            String description = String.format("called %s", operation.getLocalName());

            NodeList parameters = operation.getChildNodes();
            for (int i = 1; i < parameters.getLength(); i += 2) {
                description = String.format("%s %s(%s)", description, parameters.item(i).getLocalName(), parameters.item(i).getTextContent());
            }

            Log log = LogRepository.getInstance().create(
                    client,
                    description,
                    httpExchange.getRemoteAddress().getHostString(),
                    httpExchange.getRequestURI().toString()
            );

            System.out.format("Client %s (%s) requested %s with %s at %s\n", log.getClient(), log.getIp(), log.getEndpoint(), log.getDescription(), log.getRequestedAt().toString());
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
