package com.listwibuku.models;

import com.listwibuku.enums.SubscriberStatus;

public class Subscriber {
    private final int userId;
    private final int animeId;
    private SubscriberStatus status;

    public Subscriber(int userId, int animeId, SubscriberStatus status) {
        this.userId = userId;
        this.animeId = animeId;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public int getAnimeId() {
        return animeId;
    }

    public SubscriberStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriberStatus newStatus) {
        this.status = newStatus;
    }
}
