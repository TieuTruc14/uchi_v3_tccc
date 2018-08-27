package com.vn.osp.modelview;

import org.json.JSONObject;

/**
 * Created by tranv on 12/28/2016.
 */
public class ReportByUserDateFilter {
    private String notaryDateFromFilter;
    private String notaryDateToFilter;
    private String notary_office_name;
    private String entry_user_name;

    public ReportByUserDateFilter() {
    }

    public ReportByUserDateFilter(String notary_office_name, String entry_user_name, String notaryDateFromFilter, String notaryDateToFilter) {
        this.notary_office_name = notary_office_name;
        this.entry_user_name = entry_user_name;
        this.notaryDateFromFilter = notaryDateFromFilter;
        this.notaryDateToFilter = notaryDateToFilter;
    }
    public String getNotary_office_name() {
        return notary_office_name;
    }

    public void setNotary_office_name(String notary_office_name) {
        this.notary_office_name = notary_office_name;
    }

    public String getEntry_user_name() {
        return entry_user_name;
    }

    public void setEntry_user_name(String entry_user_name) {
        this.entry_user_name = entry_user_name;
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
            obj.put("notary_office_name",notary_office_name);
            obj.put("entry_user_name",entry_user_name);
            obj.put("notaryDateFromFilter", notaryDateFromFilter);
            obj.put("notaryDateToFilter", notaryDateToFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
