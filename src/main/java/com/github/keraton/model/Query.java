package com.github.keraton.model;

import java.util.List;

public final class Query {

    private final String name;
    private final String description;
    private final String mainSql;
    private final String datasource;
    private final String detailSql;
    private final List<String> keys;

    public Query(String name,
                 String description,
                 String mainSql,
                 String datasource,
                 String detailSql,
                 List<String> keys) {
        this.name = name;
        this.description = description;
        this.mainSql = mainSql;
        this.datasource = datasource;
        this.detailSql = detailSql;
        this.keys = keys;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMainSql() {
        return mainSql;
    }

    public String getDatasource() {
        return datasource;
    }

    public String getDetailSql() {
        return detailSql;
    }

    public List<String> getKeys() {
        return keys;
    }

    public boolean hasDetail() {
        return detailSql != null;
    }
}
