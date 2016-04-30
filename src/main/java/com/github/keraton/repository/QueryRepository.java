package com.github.keraton.repository;

import com.github.keraton.model.Query;
import com.github.keraton.repository.yaml.YamlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class QueryRepository {

    private Map<String, Query> queryMap = new HashMap<>();

    private final YamlParser yamlParser;

    @Autowired
    public QueryRepository(YamlParser yamlParser) {
        this.yamlParser = yamlParser;
    }

    public void init() {
        queryMap.clear();
        yamlParser.parseQuery()
                    .stream()
                    .forEach(query -> queryMap.put(query.getName(), query));

    }

    public Query findByName(String name) {
        return queryMap.get(name);
    }

    public List<Query> findAll() {
        return new ArrayList<>(queryMap.values());
    }
}
