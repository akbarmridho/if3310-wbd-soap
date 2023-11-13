package com.listwibuku.repository;

import com.listwibuku.database.DatabaseInstance;
import com.listwibuku.database.DatabaseInstanceInterface;
import com.listwibuku.models.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LogRepository {
    private static LogRepository instance;
    private final String tableName = "log";
    private final DatabaseInstanceInterface database;

    protected LogRepository(DatabaseInstanceInterface db) {
        this.database = db;
    }

    public static LogRepository getInstance() {
        if (instance == null) {
            instance = new LogRepository(
                    DatabaseInstance.getInstance()
            );
        }
        return instance;
    }

    public Log create(
            String client,
            String description,
            String ip,
            String endpoint
    ) throws SQLException {
        PreparedStatement statement = this.database.getConnection().prepareStatement(
                "INSERT INTO " + this.tableName + "(description, ip, endpoint, client) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, description);
        statement.setString(2, ip);
        statement.setString(3, endpoint);
        statement.setString(4, client);

        int affectedRows = statement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating log failed, no rows affected.");
        }

        ResultSet rs = statement.getGeneratedKeys();

        Log result = null;

        if (rs.next()) {
            int id = rs.getInt(1);
            result = this.findById(id);
        }

        return result;
    }

    public Log findById(int id) throws SQLException {
        Log result = null;

        PreparedStatement stmt = this.database.getConnection().prepareStatement("SELECT * FROM " + this.tableName + " WHERE id = ? LIMIT 1");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Log log = new Log();
            log.constructFromSQL(rs);
            result = log;
        }

        return result;
    }
}