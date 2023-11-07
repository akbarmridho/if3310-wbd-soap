package com.listwibuku.services;

import com.listwibuku.models.Subscriber;
import com.listwibuku.utils.Config;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailerService {

    private static MailerService instance;
    private Session session;

    private String sender;

    public MailerService() {
        Config config = Config.getInstance();

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", config.getMailHost());
        properties.setProperty("mail.smtp.port", config.getMailPort().toString());

        this.session = Session.getDefaultInstance(properties);
        this.sender = config.getMailFrom();
    }

    public static MailerService getInstance() {
        if (instance == null) {
            instance = new MailerService();
        }

        return instance;
    }

    public void notifyStartSubscription(Subscriber subscriber) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.sender));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(subscriber.getEmail()));
            message.setSubject("Subscription Started");
            message.setText(String.format("Hello!\n\nYour subscription has started on %s until %s\n\nCheers\nListwibuku",
                    subscriber.getSubscriptionStartTime().toString(),
                    subscriber.getSubscriptionEndTime().toString()));

            Transport.send(message);
        } catch (MessagingException exception) {
            exception.printStackTrace();
        }
    }

    public void notifyRenewSubscription(Subscriber subscriber) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.sender));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(subscriber.getEmail()));
            message.setSubject("Subscription Renewed");
            message.setText(String.format("Hello!\n\nYour subscription has been renewed until %s\n\nCheers\nListwibuku",
                    subscriber.getSubscriptionEndTime().toString()));

            Transport.send(message);
        } catch (MessagingException exception) {
            exception.printStackTrace();
        }
    }
}
