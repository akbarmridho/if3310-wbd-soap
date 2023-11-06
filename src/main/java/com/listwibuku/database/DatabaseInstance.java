package com.listwibuku.database;

import com.listwibuku.utils.Config;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class DatabaseInstance implements DatabaseInstanceInterface {

    @Getter
    private final static DatabaseInstanceInterface instance = new DatabaseInstance();

    @Getter
    private Connection connection;

    public DatabaseInstance() {
        try {
            Config config = Config.getInstance();
            String url = String.format("jdbc:mysql://%s:%d/%s?allowMultiQueries=true", config.getDatabaseHost(), config.getDatabasePort(), config.getDatabaseName());
            String user = config.getDatabaseUser();
            String pass = config.getDatabasePassword();
            System.out.println("Trying to connect to database at " + url + " with user " + user + " and pass " + pass);

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            this.connection = DriverManager.getConnection(url, user, pass);

            System.out.println("Performing database migration");


            InputStream is = getClass().getClassLoader().getResourceAsStream("migration.sql");

            if (is == null) {
                throw new Exception("Cannot read migration.sql");
            }

            String ddl = new BufferedReader(new InputStreamReader(is))
                    .lines()
                    .collect(Collectors.joining("\n"));

            PreparedStatement statement = this.connection.prepareStatement(ddl);
            statement.executeUpdate();

            System.out.println("Database migration success");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}
