package com.github.keraton.web.controller;

import com.github.keraton.web.dto.Detail;
import com.github.keraton.web.dto.Result;
import com.github.keraton.model.Query;
import com.github.keraton.services.InitializerService;
import com.github.keraton.services.QueryResultService;
import com.github.keraton.services.QueryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class QueryRestController {

    @Autowired
    private QueryListService queryListService;

    @Autowired
    private InitializerService initializerService;

    @Autowired
    private QueryResultService queryResultService;

    @RequestMapping("/init")
    public String init() {
        initializerService.init();
        return "init done";
    }

    @RequestMapping(value = "/search", params = "name")
    public Query query(@RequestParam("name") String name) {
        return queryListService.search(name);
    }

    @RequestMapping(value = "/list")
    public List<String> list() {
        return queryListService.list();
    }

    @RequestMapping(value = "/getResult", params = "name")
    public Result execute(@RequestParam("name") String name) {
        Result result = new Result();
        result.setRows(queryResultService.getResult(name));
        result.setHasDetail(queryListService.search(name).hasDetail());
        return result;
    }

    @RequestMapping(value = "/executeFilter", params = {"name"})
    public Result executeFilter(@RequestParam(value = "name", required = true) String name,
                                                   @RequestParam(value = "queryFilter", required = false) String queryFilter,
                                                   @RequestParam(value = "limit", required = false) Integer limit,
                                                   @RequestParam(value = "offset", required = false) Integer offset) {
        Result result = new Result();
        result.setRows(queryResultService.getResult(name,
                                                    queryFilter == null ? null : queryFilter.replaceAll("__PERCENT__", "%"),
                                                    limit,
                                                    offset));
        result.setHasDetail(queryListService.search(name).hasDetail());
        return result;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public Map<String, Object> detail(@RequestBody Detail detail) {
        return queryResultService.getDetailResult(detail.getSelectedQuery(), detail.getMap());
    }
}
