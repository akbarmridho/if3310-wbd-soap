package com.listwibuku.services;

import com.listwibuku.enums.SubscriberStatus;
import com.listwibuku.models.Subscriber;
import com.listwibuku.repository.SubscriberRepository;

import javax.inject.Inject;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "com.listwibuku.services.SubscriberService")
@HandlerChain(file = "handlers.xml")
public class SubscriberImpl implements SubscriberService {
    @Inject
    private final SubscriberRepository repository = new SubscriberRepository();

    @WebMethod
    public Subscriber createSubscriber(int userId, SubscriberStatus status) {
        System.out.println("create subscriber via service");

        Subscriber result = repository.createSubscriber(userId, status);

        MailerService.getInstance().notifyStartSubscription(result);

        return result;
    }

    @WebMethod
    public String updateSubscriberStatus(int userId, SubscriberStatus statusUpdate) {
        System.out.println("updating subscription using service");

        Subscriber result = repository.createSubscriber(userId, statusUpdate);
        MailerService.getInstance().notifyRenewSubscription(result);

        return repository.updateSubscriberStatus(userId, statusUpdate);
    }

    @WebMethod
    public Subscriber getSubscriber(int userId) {
        System.out.println("getting status via service");

        return repository.getSubscriber(userId);
    }


}
