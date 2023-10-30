package com.listwibuku.services;


import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SubscriberService {
    @WebMethod
    String createSubscriber();
}
