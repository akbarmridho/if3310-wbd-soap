package com.listwibuku.services;

import com.listwibuku.enums.SubscriberStatus;
import com.listwibuku.models.Subscriber;
import com.listwibuku.repository.SubscriberRepository;

import javax.inject.Inject;
import javax.jws.*;

@WebService(endpointInterface = "com.listwibuku.services.SubscriberService")
public class SubscriberImpl implements SubscriberService {
    @Inject
    private final SubscriberRepository repository = new SubscriberRepository();

    @WebMethod
    public Subscriber createSubscriber(int userId, int animeId, SubscriberStatus status) {
        System.out.println("create subscriber via service");

        return repository.createSubscriber(userId, animeId, status);
    }

    @WebMethod
    public String updateSubscriberStatus(int userId, int animeId, SubscriberStatus statusUpdate) {
        System.out.println("updating subscription using service");

        return repository.updateSubscriberStatus(userId, animeId, statusUpdate);
    }

    @WebMethod
    public SubscriberStatus getSubscriberStatus(int userId, int animeId) {
        System.out.println("getting status via service");

        return repository.getSubscriberStatus(userId, animeId);
    }
}
