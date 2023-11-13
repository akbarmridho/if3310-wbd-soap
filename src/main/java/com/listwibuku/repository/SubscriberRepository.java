package com.listwibuku.repository;

import com.listwibuku.database.DatabaseInstance;
import com.listwibuku.database.DatabaseInstanceInterface;
import com.listwibuku.models.Subscriber;

import java.sql.*;
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

    public Subscriber create(String email) throws SQLException {
        PreparedStatement statement = this.database.getConnection().prepareStatement(
                "INSERT INTO " + this.tableName + "(email, end_date) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, email);

        // handle default end_time, default is now + 1 month
        statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now().plusMonths(1)));

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Create subscriber failed, no rows affected");
        }

        ResultSet rs = statement.getGeneratedKeys();

        Subscriber result = null;

        if (rs.next()) {
            int id = rs.getInt(1);
            result = this.findById(id);
        }

        return result;

//        TODO: validate email
    }

    public Subscriber findById(int userId) throws SQLException {
        Subscriber result = null;

        PreparedStatement statement = this.database.getConnection().prepareStatement("SELECT * FROM " + this.tableName + " WHERE id = ? LIMIT 1");
        statement.setInt(1, userId);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            Subscriber subscriber = new Subscriber();
            subscriber.constructFromSQL(rs);
            result = subscriber;
        }

        return result;
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
