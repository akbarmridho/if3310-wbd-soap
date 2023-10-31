package com.listwibuku.repository;

import com.listwibuku.enums.SubscriberStatus;
import com.listwibuku.models.Subscriber;

public class SubscriberRepository {
    public Subscriber createSubscriber(int userId, int animeId, SubscriberStatus status) {
        System.out.printf("Created subscriber with id %d to anime %d%n", userId, animeId);
        System.out.println(status.toString());

        return new Subscriber(1, 1, SubscriberStatus.FAN);
    }

    public SubscriberStatus getSubscriberStatus(int userId, int animeId) {
        System.out.printf("User %d unsubscribed from anime %d", userId, animeId);

        return SubscriberStatus.UNSUBSCRIBED;
    }

    public String updateSubscriberStatus(int userId, int animeId, SubscriberStatus statusUpdate) {
        System.out.printf("Updating subscriber %d of anime %d", userId, animeId);
        System.out.println(statusUpdate.toString());

        return "Updated subscriber";
    }
}
