package com.github.keraton.services;


import com.github.keraton.model.QueryBuilder;
import com.github.keraton.repository.QueryExecutor;
import com.github.keraton.repository.QueryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QueryResultServiceTest {

    @Mock
    private QueryRepository queryRepository;

    @Mock
    private QueryExecutor queryExecutor;

    @InjectMocks
    private QueryResultService queryResultService;

    @Test
    public void should_replace_LIMIT_OFFSET_with_default () {
        // Given
        when(queryRepository.findByName("query1")).thenReturn(
                new QueryBuilder()
                        .setName("query1")
                        .setMainSql("SELECT TEST {{LIMIT}} {{OFFSET}}")
                        .setDatasource("datasource")
                        .createQuery());

        // When
        queryResultService.getResult("query1");

        // Then
        verify(queryExecutor).executeMain("SELECT TEST 100 0","datasource");
    }

    @Test
    public void should_replace_WHERE_LIMIT_OFFSET_with_input() {
        // Given
        when(queryRepository.findByName("query1")).thenReturn(
                new QueryBuilder()
                        .setName("query1")
                        .setMainSql("SELECT TEST {{WHERE}} {{LIMIT}} {{OFFSET}}")
                        .setDatasource("datasource")
                        .createQuery());

        // When
        queryResultService.getResult("query1", "1=1", 50, 25 );

        // Then
        verify(queryExecutor).executeMain("SELECT TEST  where 1=1 50 25","datasource");
    }

    @Test
    public void should_execute_detail () {
        // Given
        when(queryRepository.findByName("query1")).thenReturn(
                new QueryBuilder()
                        .setName("query1")
                        .setDetailSql("SELECT TEST WHERE ID = {{ID}} AND NAME = {{NAME}}")
                        .setKeys(asList("ID", "NAME"))
                        .setDatasource("datasource")
                        .createQuery());

        Map<String, String> map = new HashMap<>();
        map.put("ID", "100");
        map.put("NAME", "'JAKA'");

        // When
        queryResultService.getDetailResult("query1", map);

        // Then
        verify(queryExecutor).executeDetail("SELECT TEST WHERE ID = 100 AND NAME = 'JAKA'", "datasource");

    }

}