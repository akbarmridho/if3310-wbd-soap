package com.listwibuku;

import com.listwibuku.services.SubscriberImpl;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/subscriberservice", new SubscriberImpl());
        System.out.println("Subscribe for More!");
    }
}