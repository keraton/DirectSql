package com.github.keraton.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class QueryExecutor {

    private final DatasourceRepository datasourceRepository;

    @Autowired
    public QueryExecutor(DatasourceRepository datasourceRepository) {
        this.datasourceRepository = datasourceRepository;
    }

    public Map<String, Object> executeDetail(String realDetailSql, String datasource) {
        JdbcTemplate jdbcTemplate = datasourceRepository.findJdbcTemplate(datasource);
        return jdbcTemplate.queryForMap(realDetailSql);
    }

    public List<Map<String, Object>> executeMain(String mainSql, String datasource) {
        JdbcTemplate jdbcTemplate = datasourceRepository.findJdbcTemplate(datasource);
        return jdbcTemplate.queryForList(mainSql);
    }
}
