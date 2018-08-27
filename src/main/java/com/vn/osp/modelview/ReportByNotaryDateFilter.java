package com.vn.osp.modelview;

import org.json.JSONObject;

/**
 * Created by tranv on 12/28/2016.
 */
public class ReportByNotaryDateFilter {
    private String notaryDateFromFilter;
    private String notaryDateToFilter;
    private String notary_office_name;
    private String notary_person;

    public ReportByNotaryDateFilter() {
    }

    public ReportByNotaryDateFilter(String notary_office_name, String notary_person, String notaryDateFromFilter, String notaryDateToFilter) {
        this.notary_office_name = notary_office_name;
        this.notary_person = notary_person;
        this.notaryDateFromFilter = notaryDateFromFilter;
        this.notaryDateToFilter = notaryDateToFilter;
    }
    public String getNotary_office_name() {
        return notary_office_name;
    }

    public void setNotary_office_name(String notary_office_name) {
        this.notary_office_name = notary_office_name;
    }

    public String getNotary_person() {
        return notary_person;
    }

    public void setNotary_person(String notary_person) {
        this.notary_person = notary_person;
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
            obj.put("notary_person",notary_person);
            obj.put("notaryDateFromFilter", notaryDateFromFilter);
            obj.put("notaryDateToFilter", notaryDateToFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
