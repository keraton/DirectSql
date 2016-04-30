package com.github.keraton.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QueryExecutorTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private DatasourceRepository datasourceRepository;

    @InjectMocks
    private QueryExecutor queryExecutor;

    private Map<String, Object> resultMap = new HashMap<>();


    @Test
    public void should_execute_detail () {
        // Given
        when(datasourceRepository.findJdbcTemplate("datasource")).thenReturn(jdbcTemplate);
        when(jdbcTemplate.queryForMap("realDetailSql")).thenReturn(resultMap);

        // When
        Map<String, Object> result = queryExecutor.executeDetail("realDetailSql", "datasource");

        // Then
        assertThat(result).isEqualTo(resultMap);
    }

    @Test
    public void should_execute_main () {
        // Given
        when(datasourceRepository.findJdbcTemplate("datasource")).thenReturn(jdbcTemplate);
        when(jdbcTemplate.queryForList("mainSql")).thenReturn(Arrays.asList(resultMap));

        // When
        List<Map<String, Object>> results = queryExecutor.executeMain("mainSql", "datasource");

        // Then
        assertThat(results).containsExactly(resultMap);
    }

}