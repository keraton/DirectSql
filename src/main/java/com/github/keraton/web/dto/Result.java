package com.github.keraton.web.dto;

import java.util.List;
import java.util.Map;

public class Result {

    private boolean hasDetail;

    private List<Map<String, Object>> rows;

    public boolean isHasDetail() {
        return hasDetail;
    }

    public void setHasDetail(boolean hasDetail) {
        this.hasDetail = hasDetail;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, Object>> rows) {
        this.rows = rows;
    }
}
