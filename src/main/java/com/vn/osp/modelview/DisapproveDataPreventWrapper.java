package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

/**
 * Created by tranv on 11/19/2016.
 */
public class DisapproveDataPreventWrapper {

    private int preventid;
    private PreventHistory PreventHistory;


    @JsonCreator
    public DisapproveDataPreventWrapper(@JsonProperty(value = "preventid", required = true) int preventid,
                                        @JsonProperty(value = "preventHistory", required = true) PreventHistory PreventHistory)
                                      {
        this.preventid = preventid;
        this.PreventHistory = PreventHistory;
    }

    public DisapproveDataPreventWrapper() {
    }

    public int getPreventid() {
        return preventid;
    }

    public void setPreventid(int preventid) {
        this.preventid = preventid;
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
            obj.put("preventHistory", new JSONObject(mapper.writeValueAsString(PreventHistory)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
