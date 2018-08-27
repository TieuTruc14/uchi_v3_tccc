package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;


/**
 * Created by tranv on 11/19/2016.
 */

public class ApproveDataPreventWrapper{

    private int preventid;
    private int leaderId;
    private PreventHistory PreventHistory;


    @JsonCreator
    public ApproveDataPreventWrapper(@JsonProperty(value = "preventid", required = true) int preventid,
                                     @JsonProperty(value = "leaderId", required = true) int leaderId,
                                     @JsonProperty(value = "preventHistory", required = true) PreventHistory PreventHistory)
                                      {
        this.preventid = preventid;
        this.leaderId = leaderId;
        this.PreventHistory = PreventHistory;
    }

    public ApproveDataPreventWrapper() {
    }

    public int getPreventid() {
        return preventid;
    }

    public void setPreventid(int preventid) {
        this.preventid = preventid;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
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
            obj.put("leaderId", leaderId);
            obj.put("preventHistory", new JSONObject(mapper.writeValueAsString(PreventHistory)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
