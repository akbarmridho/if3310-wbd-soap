package com.listwibuku.models;

import com.listwibuku.enums.SubscriberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Subscriber {
    private int userId;

    private Date subscriptionStartTime;

    private SubscriberStatus status;

    private Date subscriptionEndTime;

    private String email;

    public Subscriber(int userId, SubscriberStatus status, Date startTime, Date endTime, String email) {
        this.userId = userId;
        this.status = status;
        this.subscriptionStartTime = startTime;
        this.subscriptionEndTime = endTime;
        this.email = email;
    }

}
