package com.listwibuku.services;

import com.listwibuku.database.DatabaseInstance;
import com.listwibuku.models.Subscriber;
import com.listwibuku.repository.SubscriberRepository;

import javax.inject.Inject;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;

@WebService(endpointInterface = "com.listwibuku.services.SubscriberService")
@HandlerChain(file = "handlers.xml")
public class SubscriberImpl implements SubscriberService {
    @Inject
    private final SubscriberRepository repository = new SubscriberRepository(DatabaseInstance.getInstance());

    @WebMethod
    public Subscriber createSubscriber(String email) {
        System.out.println("create subscriber via service");

        try {
            Subscriber result = repository.create(email);
            //        TODO: trycatch

            MailerService.getInstance().notifyStartSubscription(result);

            return result;
        } catch (SQLException e) {
            System.out.println("sqlexception");
            return null;
        }
    }

    public Subscriber renewSubscriber(int userId) {
        System.out.println("updating subscription using service");

        try {
            Subscriber result = repository.create("test@email.com");
            //        TODO: trycatch

            MailerService.getInstance().notifyRenewSubscription(result);

            return repository.updateSubscription(userId);
        } catch (SQLException e) {
            System.out.println("sqlexception");
            return null;
        }
    }

    @WebMethod
    public Subscriber getSubscriber(int userId) {
        System.out.println("getting status via service");

        try {
//        TODO: renew using repository
            return repository.findById(userId);
        } catch (SQLException e) {
            System.out.println("sqlexception");
            return null;
        }
    }


}
