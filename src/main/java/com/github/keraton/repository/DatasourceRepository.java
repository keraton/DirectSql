package com.github.keraton.repository;

import com.github.keraton.model.Datasource;
import com.github.keraton.repository.yaml.YamlParser;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DatasourceRepository {

    private Map<String, Datasource> map = new HashMap<>();
    private Map<String, JdbcTemplate> mapResource = new HashMap<>();

    private final YamlParser yamlParser;

    @Autowired
    public DatasourceRepository(YamlParser yamlParser) {
        this.yamlParser = yamlParser;
    }

    public Datasource findDatasourceByName(String name) {
        return map.get(name);
    }

    public JdbcTemplate findJdbcTemplate(String name) {
        return mapResource.get(name);
    }

    public void init() {
        map.clear();
        mapResource.clear();

        yamlParser.parseDatasource()
                    .stream()
                    .forEach(this::initMap);
    }

    private void initMap(Datasource datasource) {
        map.put(datasource.getName(), datasource);
        mapResource.put(datasource.getName(), getJdbcTemplate(datasource));
    }

    private JdbcTemplate getJdbcTemplate(Datasource datasource) {
        BasicDataSource basicDataSource = getBasicDataSource(datasource);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(basicDataSource);
        return jdbcTemplate;
    }

    private BasicDataSource getBasicDataSource(Datasource datasource) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(datasource.getDriverClassName());
        basicDataSource.setUrl(datasource.getUrl());
        basicDataSource.setUsername(datasource.getUsername());
        basicDataSource.setPassword(datasource.getPassword());
        basicDataSource.setInitialSize(3);
        return basicDataSource;
    }
}
