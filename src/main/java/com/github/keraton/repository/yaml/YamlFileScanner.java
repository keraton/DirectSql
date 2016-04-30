package com.github.keraton.repository.yaml;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Scan and find all yaml files that is placed in a specific files
 * by default is directsql, but it can be modified (not yet)
 * - The query pattern is *-query.yml
 *      | This will contains one query
 * - The connection pattern is jdbc-connection.yml
 *      | This will contains all jdbc connection informations
 */
@Component
public class YamlFileScanner {

    private final ResourcePatternResolver resourcePatternResolver =
                                        new PathMatchingResourcePatternResolver(this.getClass().getClassLoader());

    public List<File> scanQuery() {
        return asList(getResources())
                .stream()
                .map(this::resourceToFile)
                .filter(file -> file != null)
                .collect(Collectors.toList());
    }

    public File scanDatasource() {
        try {
            return resourcePatternResolver.getResource("classpath:directsql/jdbc-datasource.yml").getFile();
        } catch (IOException e) {
            throw new IllegalStateException("Should have jdbc-datasource.yml");
        }
    }

    private Resource[] getResources()  {
        try {
            return resourcePatternResolver.getResources("classpath:directsql/*-query.yml");
        } catch (IOException e) {
            return new Resource [] {};
        }
    }

    private File resourceToFile(Resource resource) {
        try {
            return resource.getFile();
        } catch (IOException e) {
            return null;
        }
    }
}
