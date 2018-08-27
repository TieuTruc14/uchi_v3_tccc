package com.vn.osp.modelview;

import org.json.JSONObject;

/**
 * Created by tranv on 12/28/2016.
 */
public class NotaryDateFilter {
    private String notaryDateFromFilter;
    private String notaryDateToFilter;

    public NotaryDateFilter() {
    }

    public NotaryDateFilter(String notaryDateFromFilter, String notaryDateToFilter) {
        this.notaryDateFromFilter = notaryDateFromFilter;
        this.notaryDateToFilter = notaryDateToFilter;
    }

    public String getNotaryDateFromFilter() {
        return notaryDateFromFilter;
    }

    public void setNotaryDateFromFilter(String notaryDateFromFilter) {
        this.notaryDateFromFilter = notaryDateFromFilter;
    }

    public String getNotaryDateToFilter() {
        return notaryDateToFilter;
    }

    public void setNotaryDateToFilter(String notaryDateToFilter) {
        this.notaryDateToFilter = notaryDateToFilter;
    }

    public JSONObject generateJsonObject(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("notaryDateToFilter", notaryDateToFilter);
            obj.put("notaryDateFromFilter", notaryDateFromFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
