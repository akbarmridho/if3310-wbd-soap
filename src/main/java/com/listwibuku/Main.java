package com.listwibuku;

import com.listwibuku.services.SubscriberImpl;
import com.listwibuku.utils.Config;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        try {
            Config instance = Config.getInstance();
            String host = instance.getHost();
            Integer port = instance.getPort();

            System.out.println("Starting server at " + host + ":" + port);
            Endpoint.publish(host + ":" + port + "/subscriberservice", new SubscriberImpl());
            System.out.println("Server started at " + host + ":" + port);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}