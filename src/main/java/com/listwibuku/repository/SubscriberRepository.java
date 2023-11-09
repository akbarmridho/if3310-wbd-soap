package com.listwibuku.repository;

import com.listwibuku.enums.SubscriberStatus;
import com.listwibuku.models.Subscriber;

import java.util.Date;

public class SubscriberRepository {
    public Subscriber createSubscriber(int userId, SubscriberStatus status) {
        System.out.printf("Created subscriber with id %d\n", userId);

        return new Subscriber(1, status, new Date(), new Date(), "testemail@email.com");
    }

    public Subscriber getSubscriber(int userId) {
        // untuk case subscriber tidak ditemukan, return null saja
        return new Subscriber(1, SubscriberStatus.SUBSCRIBED, new Date(), new Date(), "testemail@email.com");
    }

    public String updateSubscriberStatus(int userId, SubscriberStatus statusUpdate) {
        System.out.printf("Updating subscriber %d\n", userId);

        return "Updated subscriber";
    }
}
