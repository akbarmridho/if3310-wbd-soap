package com.listwibuku.middleware;

import com.listwibuku.exception.InvalidApiKeyException;
import com.listwibuku.models.ApiKey;
import com.listwibuku.repository.ApiKeyRepository;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Authentication implements SOAPHandler<SOAPMessageContext> {
    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        boolean isResponse = (boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        if (isResponse) {
            return true;
        }

        boolean result = this.handleAuth(context);

        if (!result) {
            throw InvalidApiKeyException.create();
        }

        return result;
    }

    public boolean handleAuth(SOAPMessageContext context) {
        Map<String, Object> headers = (Map) context.get(context.HTTP_REQUEST_HEADERS);
        List<String> authorizationHeaders = (List<String>) headers.get("authorization");

        if (authorizationHeaders == null) {
            return false;
        }

        String apiKey = authorizationHeaders.get(0);

        System.out.println("api key: " + apiKey);

        try {
            ApiKey key = ApiKeyRepository.getInstance().findByKey(apiKey);

            if (key == null) {
                return false;
            }

            context.put("authenticated-client", key.getClient());
        } catch (SQLException e) {
            System.out.println("Invalid api key");
            return false;
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }
}
