package com.github.keraton.services;

import com.github.keraton.model.Query;
import com.github.keraton.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuerySearchService {

    private final QueryRepository queryRepository;

    @Autowired
    public QuerySearchService(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    public Query search(String name) {
        return queryRepository.findByName(name);
    }

    public List<String> list() {
        return queryRepository.findAll()
                    .stream()
                    .map(Query::getName)
                    .collect(Collectors.toList());
    }

}
