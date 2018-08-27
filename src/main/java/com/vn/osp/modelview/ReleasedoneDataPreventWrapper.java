package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

/**
 * Created by tranv on 11/19/2016.
 */

public class ReleasedoneDataPreventWrapper {

    private DataPrevent DataPrevent;

    private PreventHistory PreventHistory;

    private Long prevent_id;

    @JsonCreator
    public ReleasedoneDataPreventWrapper(@JsonProperty(value = "dataPrevent", required = true) DataPrevent DataPrevent,
                                         @JsonProperty(value = "preventHistory", required = true) PreventHistory PreventHistory,
                                         @JsonProperty(value = "prevent_id", required = true) Long prevent_id) {
        this.DataPrevent = DataPrevent;
        this.PreventHistory = PreventHistory;
        this.prevent_id = prevent_id;
    }

    public ReleasedoneDataPreventWrapper() {
    }

    public DataPrevent getDataPrevent() {
        return DataPrevent;
    }

    public void setDataPrevent(DataPrevent dataPrevent) {
        DataPrevent = dataPrevent;
    }


    public PreventHistory getPreventHistory() {
        return PreventHistory;
    }

    public void setPreventHistory(PreventHistory preventHistory) {
        PreventHistory = preventHistory;
    }

    public Long getPrevent_id() {
        return prevent_id;
    }

    public void setPrevent_id(Long prevent_id) {
        this.prevent_id = prevent_id;
    }

    public JSONObject generateJsonObject(){
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();

        try {
            obj.put("dataPrevent", new JSONObject(mapper.writeValueAsString(DataPrevent)));
            obj.put("preventHistory", new JSONObject(mapper.writeValueAsString(PreventHistory)));
            obj.put("prevent_id", prevent_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
