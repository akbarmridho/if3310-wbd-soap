package com.listwibuku.models;

import com.listwibuku.enums.SubscriberStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class Subscriber {
    private final int userId;
    private final Date subscriptionStartTime;

    @Setter
    private SubscriberStatus status;

    @Setter
    private Date subscriptionEndTime;

    public Subscriber(int userId, SubscriberStatus status, Date startTime, Date endTime) {
        this.userId = userId;
        this.status = status;
        this.subscriptionStartTime = startTime;
        this.subscriptionEndTime = endTime;
    }

}
