package com.listwibuku.repository;

import com.listwibuku.models.Subscriber;

import java.util.Date;

public class SubscriberRepository {
    public Subscriber create(int userId, String email) {
        System.out.printf("Created subscriber with id %d\n", userId);
//        TODO: validate email

        return new Subscriber(1, new Date(), new Date(), "testemail@email.com");
    }

    public Subscriber findById(int userId) {
        // untuk case subscriber tidak ditemukan, return null saja
        return new Subscriber(1, new Date(), new Date(), "testemail@email.com");
    }

    public String updateSubscription(int userId) {
        System.out.printf("Updating subscriber %d\n", userId);

//        TODO: renew subscriber by 1 month

        return "Updated subscriber";
    }
}
