package com.listwibuku.services;


import com.listwibuku.models.Subscriber;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SubscriberService {
    @WebMethod
    Subscriber createSubscriber(String email);

    @WebMethod
    Subscriber renewSubscriber(int userId);

    @WebMethod
    Subscriber getSubscriber(int userId);
}
