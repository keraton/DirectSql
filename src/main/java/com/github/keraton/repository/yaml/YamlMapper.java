package com.github.keraton.repository.yaml;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

@Component
public class YamlMapper {

    public Map<String, Object> load(File file) {
        try {
            FileInputStream fileInputStream = getFileInputStream(file);
            return getStringObjectMap(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("File not found");
        }
    }


    private FileInputStream getFileInputStream(File file) throws FileNotFoundException {
        FileInputStream fileInputStream = null;
        fileInputStream = new FileInputStream(file);
        return fileInputStream;
    }

    private Map<String, Object> getStringObjectMap(FileInputStream fileInputStream) {
        Yaml yaml = new Yaml();
        return (Map<String, Object>) yaml.load(fileInputStream);
    }
}
