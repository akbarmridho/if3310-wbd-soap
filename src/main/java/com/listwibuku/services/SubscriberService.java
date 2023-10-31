package com.listwibuku.services;


import com.listwibuku.enums.SubscriberStatus;
import com.listwibuku.models.Subscriber;
import com.listwibuku.repository.SubscriberRepository;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SubscriberService {
    @WebMethod
    Subscriber createSubscriber(int userId, int animeId, SubscriberStatus status);

    @WebMethod
    String updateSubscriberStatus(int userId, int animeId, SubscriberStatus statusUpdate);

    @WebMethod
    SubscriberStatus getSubscriberStatus(int userId, int animeId);
}
