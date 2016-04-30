package com.github.keraton.repository;

import com.github.keraton.model.Datasource;
import com.github.keraton.model.DatasourceBuilder;
import com.github.keraton.repository.yaml.YamlParser;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DatasourceRepositoryTest {

    @Mock
    private YamlParser yamlParser;

    @InjectMocks
    private DatasourceRepository datasourceRepository;

    @Before
    public void init() {
        Datasource datasource = new DatasourceBuilder()
                .setName("datasource1")
                .setDriverClassName("driverClassName")
                .setUrl("Url")
                .setUsername("userName")
                .setPassword("password")
                .createDatasource();

        when(yamlParser.parseDatasource()).thenReturn(asList(datasource));

        datasourceRepository.init();
    }

    @Test
    public void should_find_jdbcTemplate () {
        // Given

        // When
        JdbcTemplate jdbcTemplate = datasourceRepository.findJdbcTemplate("datasource1");

        // Then
        assertThat(jdbcTemplate).isNotNull();
    }

    @Test
    public void should_not_find_jdbcTemplate () {
        // Given

        // When
        JdbcTemplate jdbcTemplate = datasourceRepository.findJdbcTemplate("datasource_not_exist");

        // Then
        assertThat(jdbcTemplate).isNull();

    }

}