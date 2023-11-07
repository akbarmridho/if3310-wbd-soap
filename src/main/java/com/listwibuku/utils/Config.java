package com.listwibuku.utils;

import lombok.Getter;
import lombok.NonNull;

import java.util.Optional;

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

    @Getter
    @NonNull
    private final String mailHost;

    @Getter
    @NonNull
    private final Integer mailPort;

    @Getter
    @NonNull
    private final String mailFrom;


    public Config() {
        this.host = getenvOr("HOST", "http://0.0.0.0");
        this.port = Integer.parseInt(getenvOr("PORT", "3050"));

        this.databaseName = getenvOr("DATABASE_NAME", "soap");
        this.databaseHost = getenvOr("DATABASE_HOST", "localhost");
        this.databasePort = Integer.parseInt(getenvOr("DATABASE_PORT", "3307"));
        this.databaseUser = getenvOr("DATABASE_USER", "soap");
        this.databasePassword = getenvOr("DATABASE_PASSWORD", "soap");
        this.mailHost = getenvOr("MAIL_HOST", "host.docker.internal");
        this.mailPort = Integer.parseInt(getenvOr("MAIL_PORT", "1025"));
        this.mailFrom = getenvOr("MAIL_FROM", "soap@listwibuku.com");
    }

    private String getenvOr(String key, String defaultValue) {
        return Optional.ofNullable(System.getenv(key)).orElse(defaultValue);
    }
}
