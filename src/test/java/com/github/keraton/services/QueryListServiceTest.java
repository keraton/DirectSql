package com.github.keraton.services;

import com.github.keraton.model.Query;
import com.github.keraton.model.QueryBuilder;
import com.github.keraton.repository.QueryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QueryListServiceTest {

    @Mock
    private QueryRepository queryRepository;

    @InjectMocks
    private QueryListService queryListService;

    @Test
    public void should_list_all () {
        // Given
        when(queryRepository.findAll())
                    .thenReturn(
                            Arrays.asList(
                                new QueryBuilder().setName("name1").createQuery(),
                                new QueryBuilder().setName("name2").createQuery()));

        // When
        List<String> names = queryListService.list();

        // Then
        assertThat(names).containsExactly("name1", "name2");
    }

    @Test
    public void should_search () {
        // Given
         when(queryRepository.findByName("name1"))
                        .thenReturn(new QueryBuilder().setName("name1").createQuery());

        // When
        Query query = queryListService.search("name1");

        // Then
        assertThat(query.getName()).isEqualTo("name1");
    }
}