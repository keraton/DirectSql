package com.github.keraton.repository.yaml;

import com.github.keraton.model.Datasource;
import com.github.keraton.model.DatasourceBuilder;
import com.github.keraton.model.Query;
import com.github.keraton.model.QueryBuilder;
import com.github.keraton.repository.utils.MustacheExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class YamlParser {

    private final YamlFileScanner yamlFileScanner;
    private final YamlMapper yamlMapper;
    private final MustacheExtractor mustacheExtractor;

    @Autowired
    public YamlParser(YamlFileScanner yamlFileScanner, YamlMapper yamlMapper, MustacheExtractor mustacheExtractor) {
        this.yamlFileScanner = yamlFileScanner;
        this.yamlMapper = yamlMapper;
        this.mustacheExtractor = mustacheExtractor;
    }

    public List<Query> parseQuery() {
        List<File> files = yamlFileScanner.scanQuery();
        return files.stream()
                    .map(this::mapToQuery)
                    .collect(Collectors.toList());
    }

    private Query mapToQuery(File file) {
        Map<String, Object> map = yamlMapper.load(file);

        Query query = new QueryBuilder()
                            .setName((String) map.get("name"))
                            .setDescription((String) map.get("description"))
                            .setMainSql((String) map.get("mainSql"))
                            .setDetailSql((String) map.get("detailSql"))
                            .setKeys(mustacheExtractor.extract((String) map.get("detailSql")))
                            .setDatasource((String) map.get("datasource"))
                            .createQuery();
        return query;
    }

    public List<Datasource> parseDatasource() {
        File file = yamlFileScanner.scanDatasource();

        Map<String,Object> map = yamlMapper.load(file);

        return map.entrySet()
                .stream()
                .map(this::mapToDatasource)
                .collect(Collectors.toList());
    }

    private Datasource mapToDatasource(Map.Entry<String, Object> stringObjectEntry) {
        Map<String,Object> map = (Map<String, Object>) stringObjectEntry.getValue();
        return new DatasourceBuilder()
                .setName(stringObjectEntry.getKey())
                .setUrl((String) map.get("url"))
                .setDriverClassName((String) map.get("driverClassName"))
                .setUsername((String) map.get("username"))
                .setPassword((String) map.get("password"))
                .createDatasource();
    }



}
