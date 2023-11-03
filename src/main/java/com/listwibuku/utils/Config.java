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

    public Config()
    {
        this.host = System.getProperty("HOST", "http://0.0.0.0");
        this.port = Integer.parseInt(System.getProperty("PORT", "3050"));
    }
}
