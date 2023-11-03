package com.listwibuku;

import com.listwibuku.middleware.Authentication;
import com.listwibuku.middleware.Logger;
import com.listwibuku.services.SubscriberImpl;
import com.listwibuku.utils.Config;

import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Config instance = Config.getInstance();
            String host = instance.getHost();
            Integer port = instance.getPort();

            Endpoint endpoint = Endpoint.publish(host + ":" + port + "/subscriberservice", new SubscriberImpl());
            List<Handler> handlerChain = endpoint.getBinding().getHandlerChain();

            // setup api middleware
            handlerChain.add(new Authentication());

            // setup logger middleware
            handlerChain.add(new Logger());

            endpoint.getBinding().setHandlerChain(handlerChain);

            System.out.println("Server started at " + host + ":" + port);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}