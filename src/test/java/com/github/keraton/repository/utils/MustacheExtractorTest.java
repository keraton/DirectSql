package com.github.keraton.repository.utils;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MustacheExtractorTest {

    private MustacheExtractor mustacheExtractor = new MustacheExtractor();

    @Test
    public void should_extract_string_around_mustache () {
        // Given
        String stringWithMustache = "Hello {{A}} your are {{cool}}";

        // When
        List<String> words = mustacheExtractor.extract(stringWithMustache);

        // Then
        assertThat(words).containsExactly("A", "cool");

    }

}