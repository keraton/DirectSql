package com.github.keraton.repository;

import com.github.keraton.model.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class QueryExecutor {

    private final static int DEFAULT_LIMIT = 100;
    private final static int DEFAULT_OFFSET = 0;

    private final DatasourceRepository datasourceRepository;
    private final QueryRepository queryRepository;

    @Autowired
    public QueryExecutor(DatasourceRepository datasourceRepository, QueryRepository queryRepository) {
        this.datasourceRepository = datasourceRepository;
        this.queryRepository = queryRepository;
    }

    public List<Map<String, Object>> execute(String queryName)  {
        return execute(queryName, null, DEFAULT_LIMIT, DEFAULT_OFFSET);
    }


    public List<Map<String, Object>> execute(String queryName, String queryFilter, Integer limit, Integer offset)  {
        Query query = queryRepository.findByName(queryName);
        JdbcTemplate jdbcTemplate = datasourceRepository.findJdbcTemplate(query.getDatasource());

        String mainSql = filterQuery(queryFilter, query);
        mainSql = replace(mainSql, "{{LIMIT}}", limit, DEFAULT_LIMIT);
        mainSql = replace(mainSql, "{{OFFSET}}", offset, DEFAULT_OFFSET);

        System.out.println(mainSql);
        return jdbcTemplate.queryForList(mainSql);
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

    public Map<String, Object> executeDetail(String queryName, final Map<String, String> map) {
        Query query = queryRepository.findByName(queryName);
        JdbcTemplate jdbcTemplate = datasourceRepository.findJdbcTemplate(query.getDatasource());

        String detailSql = query.getDetailSql();
        List<String> keys = query.getKeys();

        String realDetailSql = keys.stream()
                .reduce(detailSql, (sql,key) -> { return sql.replaceAll("\\{\\{" + key + "\\}\\}", map.get(key));} );


        return jdbcTemplate.queryForMap(realDetailSql);
    }
}
