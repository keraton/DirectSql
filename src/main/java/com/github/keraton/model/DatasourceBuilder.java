package com.github.keraton.model;

public class DatasourceBuilder {
    private String name;
    private String url;
    private String driverClassName;
    private String username;
    private String password;

    public DatasourceBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public DatasourceBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public DatasourceBuilder setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
        return this;
    }

    public DatasourceBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public DatasourceBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public Datasource createDatasource() {
        return new Datasource(name, url, driverClassName, username, password);
    }
}