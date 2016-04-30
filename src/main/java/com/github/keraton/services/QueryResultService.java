package com.github.keraton.services;

import com.github.keraton.model.Query;
import com.github.keraton.repository.QueryExecutor;
import com.github.keraton.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QueryResultService {

    private final static int DEFAULT_LIMIT = 100;
    private final static int DEFAULT_OFFSET = 0;

    private final QueryExecutor queryExecutor;
    private final QueryRepository queryRepository;

    @Autowired
    public QueryResultService(QueryExecutor queryExecutor, QueryRepository queryRepository) {
        this.queryExecutor = queryExecutor;
        this.queryRepository = queryRepository;
    }


    public List<Map<String, Object>> getResult(String queryName)  {
        return getResult(queryName, null, DEFAULT_LIMIT, DEFAULT_OFFSET);
    }

    public List<Map<String, Object>> getResult(String queryName, String queryFilter, Integer limit, Integer offset)  {
        Query query = queryRepository.findByName(queryName);

        String mainSql = filterQuery(queryFilter, query);
        mainSql = replace(mainSql, "{{LIMIT}}", limit, DEFAULT_LIMIT);
        mainSql = replace(mainSql, "{{OFFSET}}", offset, DEFAULT_OFFSET);

        return queryExecutor.executeMain(mainSql, query.getDatasource());
    }


    private String replace(String mainSql, String s, Integer replacement, Integer defaultValue) {
        int realReplacement = replacement != null ? replacement : defaultValue;
        if (mainSql.contains(s)) {
            mainSql = mainSql.replace(s, String.valueOf(realReplacement));
        }
        return mainSql;
    }

    private String filterQuery(String queryFilter, Query query) {
        String realQuery = queryFilter != null ?  " where " + queryFilter : "";
        String mainSql = query.getMainSql();

        if (mainSql.contains("{{WHERE}}")) {
            mainSql = mainSql.replace("{{WHERE}}", realQuery);
        }
        else {
            mainSql = mainSql +  realQuery;
        }
        return mainSql;
    }

    public Map<String, Object> getDetailResult(String queryName, final Map<String, String> map) {
        Query query = queryRepository.findByName(queryName);

        String detailSql = query.getDetailSql();

        String realDetailSql = query.getKeys().stream()
                .reduce(detailSql, (sql,key) -> replaceKey(map, sql, key));


        return queryExecutor.executeDetail(realDetailSql, query.getDatasource());
    }

    private String replaceKey(Map<String, String> map, String sql, String key) {
        return sql.replaceAll("\\{\\{" + key + "\\}\\}", map.get(key)) ;
    }

}
