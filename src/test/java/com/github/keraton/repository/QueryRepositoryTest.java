package com.github.keraton.repository;

import com.github.keraton.model.Query;
import com.github.keraton.model.QueryBuilder;
import com.github.keraton.repository.yaml.YamlParser;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QueryRepositoryTest {

    @Mock
    private YamlParser yamlParser;

    @InjectMocks
    private QueryRepository queryRepository;

    @Test
    public void should_findbyName () {
        // Given
        Query query = new QueryBuilder()
                            .setName("query1")
                            .createQuery();

        when(yamlParser.parseQuery()).thenReturn(Arrays.asList(query));
        queryRepository.init();

        // When
        Query queryResult = queryRepository.findByName("query1");

        // Then
        assertThat(queryResult.getName()).isEqualTo("query1");
    }

    @Test
    public void should_findAll () {
        // Given

        when(yamlParser.parseQuery()).thenReturn(Arrays.asList(new QueryBuilder()
                                                                        .setName("query1")
                                                                        .createQuery(),
                                                                new QueryBuilder()
                                                                        .setName("query2")
                                                                        .createQuery()
                                                                ));
        queryRepository.init();


        // When
        List<Query> results = queryRepository.findAll();

        // Then
        assertThat(results).extracting("name").containsExactly("query1", "query2");
    }

}