package com.github.keraton.repository.utils;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class MustacheExtractorTest {

    private MustacheExtractor mustacheExtractor = new MustacheExtractor();

    @Test
    public void should () {
        // Given
        String stringWithMustache = "Hello {{A}} your are {{cool}}";

        // When
        List<String> words = mustacheExtractor.extract(stringWithMustache);

        // Then
        assertThat(words).containsExactly("A", "cool");

    }

}