package com.listwibuku.utils;

import lombok.Getter;
import lombok.NonNull;

public class Config {

    @Getter
    @NonNull
    private final static Config instance = new Config();

    @Getter
    @NonNull
    private final String host;

    @Getter
    @NonNull
    private final Integer port;

    @Getter
    @NonNull
    private final String databaseName;

    @Getter
    @NonNull
    private final String databaseHost;

    @Getter
    @NonNull
    private final Integer databasePort;

    @Getter
    @NonNull
    private final String databaseUser;

    @Getter
    @NonNull
    private final String databasePassword;

    public Config() {
        this.host = System.getProperty("HOST", "http://0.0.0.0");
        this.port = Integer.parseInt(System.getProperty("PORT", "3050"));

        this.databaseName = System.getProperty("DATABASE_NAME", "soap");
        this.databaseHost = System.getProperty("DATABASE_HOST", "127.0.0.0");
        this.databasePort = Integer.parseInt(System.getProperty("DATABASE_PORT", "3306"));
        this.databaseUser = System.getProperty(System.getProperty("DATABASE_USER", "root"));
        this.databasePassword = System.getProperty("DATABASE_PASSWORD", "root");
    }
}
