package com.github.keraton.services;

import com.github.keraton.repository.QueryExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QueryResultService {

    private final QueryExecutor queryExecutor;

    @Autowired
    public QueryResultService(QueryExecutor queryExecutor) {
        this.queryExecutor = queryExecutor;
    }

    public List<Map<String, Object>> execute(String queryName) {
        return queryExecutor.execute(queryName);
    }

    public List<Map<String, Object>> execute(String queryName, String queryFilter, Integer limit, Integer offset) {
        return queryExecutor.execute(queryName, queryFilter, limit, offset);
    }

    public Map<String, Object> getDetail(String query, Map<String, String> map) {
        return queryExecutor.executeDetail(query, map);
    }
}
