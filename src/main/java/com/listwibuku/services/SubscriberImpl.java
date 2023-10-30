package com.listwibuku.services;

import javax.jws.*;

@WebService(endpointInterface = "com.listwibuku.services.SubscriberService")
public class SubscriberImpl implements SubscriberService {
    @WebMethod
    public String createSubscriber() {
        System.out.println("Subscriber created");
        return null;
    }

    // stub
}
