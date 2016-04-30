package com.github.keraton.services;

import com.github.keraton.repository.DatasourceRepository;
import com.github.keraton.repository.QueryRepository;
import com.github.keraton.repository.yaml.YamlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class InitializerService {

    private final DatasourceRepository datasourceRepository;
    private final QueryRepository queryRepository;

    @Autowired
    public InitializerService(
                              DatasourceRepository datasourceRepository,
                              QueryRepository queryRepository) {

        this.datasourceRepository = datasourceRepository;
        this.queryRepository = queryRepository;
    }

    @PostConstruct
    public void init() {
        this.datasourceRepository.init();
        this.queryRepository.init();
    }

}
