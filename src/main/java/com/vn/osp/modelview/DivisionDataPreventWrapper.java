package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

/**
 * Created by tranv on 11/19/2016.
 */

public class DivisionDataPreventWrapper {

    private int preventid;
    private int divisionId;
    private PreventHistory PreventHistory;


    @JsonCreator
    public DivisionDataPreventWrapper(@JsonProperty(value = "preventid", required = true) int preventid,
                                      @JsonProperty(value = "divisionId", required = true) int divisionId,
                                      @JsonProperty(value = "preventHistory", required = true) PreventHistory PreventHistory)
                                      {
        this.preventid = preventid;
        this.divisionId = divisionId;
        this.PreventHistory = PreventHistory;
    }

    public DivisionDataPreventWrapper() {
    }

    public int getPreventid() {
        return preventid;
    }

    public void setPreventid(int preventid) {
        this.preventid = preventid;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public PreventHistory getPreventHistory() {
        return PreventHistory;
    }

    public void setPreventHistory(PreventHistory preventHistory) {
        PreventHistory = preventHistory;
    }
    public JSONObject generateJsonObject(){
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();

        try {
            obj.put("preventid", preventid);
            obj.put("divisionId", divisionId);
            obj.put("preventHistory", new JSONObject(mapper.writeValueAsString(PreventHistory)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
