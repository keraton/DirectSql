package com.github.keraton.dto;

import java.util.Map;

public class Detail {

    private String selectedQuery;
    private Map<String,String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String getSelectedQuery() {
        return selectedQuery;
    }

    public void setSelectedQuery(String selectedQuery) {
        this.selectedQuery = selectedQuery;
    }
}
