package com.listwibuku.services;


import com.listwibuku.enums.SubscriberStatus;
import com.listwibuku.models.Subscriber;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SubscriberService {
    @WebMethod
    Subscriber createSubscriber(int userId, SubscriberStatus status);

    @WebMethod
    String updateSubscriberStatus(int userId, SubscriberStatus statusUpdate);

    @WebMethod
    Subscriber getSubscriber(int userId);
}
