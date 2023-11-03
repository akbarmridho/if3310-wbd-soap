package com.listwibuku.repository;

import com.listwibuku.enums.SubscriberStatus;
import com.listwibuku.models.Subscriber;

import java.util.Date;

public class SubscriberRepository {
    public Subscriber createSubscriber(int userId, SubscriberStatus status) {
        System.out.printf("Created subscriber with id %d", userId);

        return new Subscriber(1, status, new Date(), new Date());
    }

    public SubscriberStatus getSubscriberStatus(int userId) {
        System.out.printf("User %d unsubscribed", userId);

        return SubscriberStatus.UNSUBSCRIBED;
    }

    public String updateSubscriberStatus(int userId, SubscriberStatus statusUpdate) {
        System.out.printf("Updating subscriber %d", userId);
        System.out.println(statusUpdate.toString());

        return "Updated subscriber";
    }
}
