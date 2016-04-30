package com.github.keraton.model;

import java.util.List;

public class QueryBuilder {
    private String name;
    private String description;
    private String mainSql;
    private String datasource;
    private String detailSql;
    private List<String> keys;

    public QueryBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public QueryBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public QueryBuilder setMainSql(String mainSql) {
        this.mainSql = mainSql;
        return this;
    }

    public QueryBuilder setDatasource(String datasource) {
        this.datasource = datasource;
        return this;
    }

    public QueryBuilder setDetailSql(String detailQuery) {
        this.detailSql = detailQuery;
        return this;
    }

    public QueryBuilder setKeys(List<String> keys) {
        this.keys = keys;
        return this;
    }

    public Query createQuery() {
        return new Query(name, description, mainSql, datasource, detailSql, keys);
    }
}