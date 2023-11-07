package com.listwibuku;

import com.listwibuku.database.DatabaseInstance;
import com.listwibuku.database.DatabaseInstanceInterface;
import com.listwibuku.services.SubscriberImpl;
import com.listwibuku.utils.Config;

import javax.xml.ws.Endpoint;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Config instance = Config.getInstance();
            String host = instance.getHost();
            Integer port = instance.getPort();

            // init database instance
            DatabaseInstanceInterface databaseInstance = DatabaseInstance.getInstance();

            Endpoint.publish(host + ":" + port + "/subscriberservice", new SubscriberImpl());

            System.out.println("Server started at " + host + ":" + port);

            Thread printingHook = new Thread(() -> {
                System.out.println("Closing connection");
                try {
                    databaseInstance.getConnection().close();
                } catch (SQLException e) {
                    System.out.println("Failed to close connection");
                }
            });
            Runtime.getRuntime().addShutdownHook(printingHook);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}