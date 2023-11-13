package com.listwibuku.services;

import com.listwibuku.models.Subscriber;
import com.listwibuku.repository.SubscriberRepository;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;

@WebService(endpointInterface = "com.listwibuku.services.SubscriberService")
@HandlerChain(file = "handlers.xml")
public class SubscriberImpl implements SubscriberService {
    @WebMethod
    public Subscriber createSubscriber(String email) {
        System.out.println("Create subscriber via service");

        try {
            Subscriber result = SubscriberRepository.getInstance().create(email);

            MailerService.getInstance().notifyStartSubscription(result);

            return result;
        } catch (SQLException e) {
            System.out.println("Failed to create subscriber");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Subscriber renewSubscriber(int userId) {
        System.out.println("Updating subscription using service");

        try {
            Subscriber result = SubscriberRepository.getInstance().create("test@email.com");

            MailerService.getInstance().notifyRenewSubscription(result);

            return SubscriberRepository.getInstance().updateSubscription(userId);
        } catch (SQLException e) {
            System.out.println("Failed to renew subscription for subscriber " + userId);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public Subscriber getSubscriber(int userId) {
        System.out.println("Getting status via service");

        try {
            return SubscriberRepository.getInstance().findById(userId);
        } catch (SQLException e) {
            System.out.println("Failed to get subscriber " + userId);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
