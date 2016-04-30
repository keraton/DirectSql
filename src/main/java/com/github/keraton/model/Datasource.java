package com.github.keraton.model;

public final class Datasource {

    private final String name;
    private final String url;
    private final String driverClassName;
    private final String username;
    private final String password;

    public Datasource(String name, String url, String driverClassName, String username, String password) {
        this.name = name;
        this.url = url;
        this.driverClassName = driverClassName;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
