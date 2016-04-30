package com.github.keraton.repository.yaml;

import com.github.keraton.model.Datasource;
import com.github.keraton.model.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class YamlParserTest {

    @Mock
    private YamlFileScanner yamlFileScanner;

    @Spy
    private YamlMapper yamlMapper = new YamlMapper();

    @InjectMocks
    private YamlParser yamlParser;

    public File getFile(String fileClassPath) {
        try {
            ResourcePatternResolver resourcePatternResolver =
                    new PathMatchingResourcePatternResolver(this.getClass().getClassLoader());

            return resourcePatternResolver.getResource(fileClassPath).getFile();
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }

    private List<File> getFiles(String fileClassPath) {
        File file = getFile(fileClassPath);
        return asList(file);
    }

    @Test
    public void should_get_query () {
        // Given
        List<File> listFile = getFiles("classpath:sqlquery/number1-query.yml");
        when(yamlFileScanner.scanQuery()).thenReturn(listFile);

        // When
        List<Query> queries = yamlParser.parseQuery();

        // Then
        Query query = queries.get(0);
        assertThat(query.getDatasource()).isEqualTo("datasource1");
        assertThat(query.getName()).isEqualTo("query1");
        assertThat(query.getDescription()).isEqualTo("This is a query 1 description");
        assertThat(query.getMainSql()).isEqualTo("Select column1 as column1, column2 as column2\n" +
                                                 "from table_1\n");

    }

    @Test
    public void should () {
        // Given
        List<File> listFile = getFiles("classpath:sqlquery/jdbc-datasource.yml");
        when(yamlFileScanner.scanDatasource()).thenReturn(listFile.get(0));

        // When
        List<Datasource> datasources = yamlParser.parseDatasource();

        // Then
        assertThat(datasources).hasSize(2);

        // Datasource1
        Datasource datasource1 = datasources.get(0);
        assertThat(datasource1.getName()).isEqualTo("datasource1");
        assertThat(datasource1.getDriverClassName()).isEqualTo("com.mysql.jdbc.Driver");
        assertThat(datasource1.getUrl()).isEqualTo("jdbc:mysql://localhost:3306/test");
        assertThat(datasource1.getUsername()).isEqualTo("sa");
        assertThat(datasource1.getPassword()).isEqualTo("none");
    }
}