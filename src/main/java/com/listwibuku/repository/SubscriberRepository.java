package com.listwibuku.repository;

import com.listwibuku.database.DatabaseInstance;
import com.listwibuku.database.DatabaseInstanceInterface;
import com.listwibuku.models.Subscriber;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

public class SubscriberRepository {
    private static SubscriberRepository instance;
    private final String tableName = "subscriber";
    private final DatabaseInstanceInterface database;

    protected SubscriberRepository(DatabaseInstanceInterface db) {
        this.database = db;
    }

    public static SubscriberRepository getInstance() {
        if (instance == null) {
            instance = new SubscriberRepository(
                    DatabaseInstance.getInstance()
            );
        }
        return instance;
    }

    public Subscriber create(int userId, String email) throws SQLException {
        PreparedStatement statement = this.database.getConnection().prepareStatement(
                "INSERT INTO " + this.tableName + "(id, email, end_date) VALUES (?, ?, ?)");
        statement.setInt(1, userId);
        statement.setString(2, email);

        // handle default end_time, default is now + 1 month
        statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now().plusMonths(1)));

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Create subscriber failed, no rows affected");
        }

        return this.findById(userId);
    }

    public Subscriber findById(int userId) throws SQLException {
        PreparedStatement statement = this.database.getConnection().prepareStatement("SELECT * FROM " + this.tableName + " WHERE id = ? LIMIT 1");
        statement.setInt(1, userId);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Subscriber subscriber = new Subscriber();
            subscriber.constructFromSQL(rs);
            return subscriber;
        }

        return null;
    }

    public Subscriber updateSubscription(int userId) throws SQLException {
        Subscriber oldData = findById(userId);

        if (oldData == null) {
            // no subscriber with id userId found
            return null;
        }

        PreparedStatement statement = this.database.getConnection().prepareStatement("UPDATE " + this.tableName + " SET end_date = ? WHERE id = ?");

        // prepare new end_date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldData.getSubscriptionEndTime());
        calendar.add(Calendar.MONTH, 1);

        statement.setTimestamp(1, new Timestamp(calendar.getTime().getTime()));
        statement.setInt(2, userId);

        int updatedRows = statement.executeUpdate();

        if (updatedRows == 0) {
            throw new SQLException("Update subscription failed, no rows affected");
        }

        return findById(userId);
    }
}
