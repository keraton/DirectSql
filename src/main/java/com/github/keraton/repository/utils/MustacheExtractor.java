package com.github.keraton.repository.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MustacheExtractor {

    private static Pattern pattern = Pattern.compile("(\\{\\{.+?\\}\\})");

    public List<String> extract(String stringWithMustache) {
        if (stringWithMustache == null) {
            return new ArrayList<>();
        }

        List<String> keys = new ArrayList<>();
        Matcher matcher = pattern.matcher(stringWithMustache);
        while (matcher.find()) {
            String keyWithMustache = matcher.group();
            keys.add(keyWithMustache.substring(2, keyWithMustache.length()-2));
        }

        return keys;
    }
}
