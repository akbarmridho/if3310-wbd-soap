package com.listwibuku.services;

import com.listwibuku.enums.SubscriberStatus;
import com.listwibuku.models.Subscriber;
import com.listwibuku.repository.SubscriberRepository;

import javax.inject.Inject;
import javax.jws.*;
import java.util.Date;

@WebService(endpointInterface = "com.listwibuku.services.SubscriberService")
public class SubscriberImpl implements SubscriberService {
    @Inject
    private final SubscriberRepository repository = new SubscriberRepository();

    @WebMethod
    public Subscriber createSubscriber(int userId, SubscriberStatus status) {
        System.out.println("create subscriber via service");

        return repository.createSubscriber(userId, status);
    }

    @WebMethod
    public String updateSubscriberStatus(int userId, SubscriberStatus statusUpdate) {
        System.out.println("updating subscription using service");

        return repository.updateSubscriberStatus(userId, statusUpdate);
    }

    @WebMethod
    public SubscriberStatus getSubscriberStatus(int userId) {
        System.out.println("getting status via service");

        return repository.getSubscriberStatus(userId);
    }

    @WebMethod
    public Date getSubscriberStartTime(int userId) {
        System.out.printf("start time of user %d", userId);
        return new Date();
    }

    @WebMethod
    public Date getSubscriberEndTime(int userId) {
        System.out.printf("end time of user %d", userId);
        return new Date();
    }

    @WebMethod
    public Date updateSubscriberEndTime(int userId, Date endTime) {
        System.out.println(endTime.toString());

        return endTime;
    }
}
