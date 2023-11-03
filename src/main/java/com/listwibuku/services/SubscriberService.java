package com.listwibuku.services;


import com.listwibuku.enums.SubscriberStatus;
import com.listwibuku.models.Subscriber;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Date;

@WebService
public interface SubscriberService {
    @WebMethod
    Subscriber createSubscriber(int userId, SubscriberStatus status);

    @WebMethod
    String updateSubscriberStatus(int userId, SubscriberStatus statusUpdate);

    @WebMethod
    SubscriberStatus getSubscriberStatus(int userId);

    @WebMethod
    Date getSubscriberStartTime(int userId);

    @WebMethod
    Date getSubscriberEndTime(int userId);

    @WebMethod
    Date updateSubscriberEndTime(int userId, Date endTime);
}
