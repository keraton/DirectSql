package com.github.keraton.repository.yaml;

import org.assertj.core.api.Condition;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class YamlFileScannerTest {

    private YamlFileScanner yamlFileScanner = new YamlFileScanner();

    @Test
    public void should_return_query_yaml_from_default_folder () {
        // Given :  We have put a test-query.yml file in test/resources/directsql/

        // When
        List<File> files = yamlFileScanner.scanQuery();

        // Then : We should have at least test-query.yml
        assertThat(files).isNotEmpty();
        assertThat(files).haveAtLeastOne(new Condition<>(
                                             file -> ((File)file).getName().contains("person-query.yml"),
                                            "file name person-query")
        );

    }

    @Test
    public void should_return_default_jdbc_connection_yaml () {
        // Given : We have put a jdbc-datasource.yml in test/resources/directsql/

        // When
        File file = yamlFileScanner.scanDatasource();

        // Then : We should have one jdbc-datasource.yml
        assertThat(file.getName()).contains("jdbc-datasource.yml");
    }
}