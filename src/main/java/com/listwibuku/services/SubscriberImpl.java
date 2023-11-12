package com.listwibuku.services;

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
    public Subscriber createSubscriber(int userId, String email) {
        System.out.println("create subscriber via service");

        Subscriber result = repository.create(userId, email);

        MailerService.getInstance().notifyStartSubscription(result);

        return result;
    }

    public String renewSubscriber(int userId) {
        System.out.println("updating subscription using service");

//        TODO: renew using repository
        Subscriber result = repository.create(userId, "test@email.com");

        MailerService.getInstance().notifyRenewSubscription(result);

        return repository.updateSubscription(userId);
    }

    @WebMethod
    public Subscriber getSubscriber(int userId) {
        System.out.println("getting status via service");

        return repository.findById(userId);
    }


}
