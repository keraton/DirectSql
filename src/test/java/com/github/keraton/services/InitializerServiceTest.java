package com.github.keraton.services;

import com.github.keraton.repository.DatasourceRepository;
import com.github.keraton.repository.QueryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InitializerServiceTest {

    @Mock
    private DatasourceRepository datasourceRepository;

    @Mock
    private QueryRepository queryRepository;

    @InjectMocks
    private InitializerService initializerService;

    @Test
    public void should_init_datasource_and_query () {
        // Given

        // When
        initializerService.init();

        // Then
        verify(datasourceRepository).init();
        verify(queryRepository).init();
    }

}