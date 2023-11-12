package com.listwibuku.repository;

import com.listwibuku.models.Subscriber;

import java.util.Date;

public class SubscriberRepository {
    public Subscriber create(String email) {
        System.out.print("Created subscriber\n");
//        TODO: validate email

        return new Subscriber(1, new Date(), new Date(), email);
    }

    public Subscriber findById(int userId) {
        // untuk case subscriber tidak ditemukan, return null saja
        return new Subscriber(userId, new Date(), new Date(), "testemail@email.com");
    }

    public String updateSubscription(int userId) {
        System.out.printf("Updating subscriber %d\n", userId);

//        TODO: renew subscriber by 1 month

        return "Updated subscriber";
    }
}
