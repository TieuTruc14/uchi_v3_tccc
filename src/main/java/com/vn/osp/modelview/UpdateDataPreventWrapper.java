package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.context.CommonContext;
import org.json.JSONObject;

/**
 * Created by tranv on 11/19/2016.
 */
public class UpdateDataPreventWrapper {

    private DataPrevent DataPrevent;

    private PropertyPrevent PropertyPrevent;

    private PreventHistory PreventHistory;

    private Long prevent_id;


    @JsonCreator
    public UpdateDataPreventWrapper(@JsonProperty(value = "dataPrevent", required = true) DataPrevent DataPrevent,
                                    @JsonProperty(value = "propertyPrevent", required = true) PropertyPrevent PropertyPrevent,
                                    @JsonProperty(value = "preventHistory", required = true) PreventHistory PreventHistory,
                                    @JsonProperty(value = "prevent_id", required = true) Long prevent_id) {
        this.DataPrevent = DataPrevent;
        this.PropertyPrevent = PropertyPrevent;
        this.PreventHistory = PreventHistory;
        this.prevent_id = prevent_id;
    }

    public UpdateDataPreventWrapper() {
    }

    public DataPrevent getDataPrevent() {
        return DataPrevent;
    }

    public void setDataPrevent(DataPrevent dataPrevent) {
        DataPrevent = dataPrevent;
    }

    public PropertyPrevent getPropertyPrevent() {
        return PropertyPrevent;
    }

    public void setPropertyPrevent(PropertyPrevent propertyPrevent) {
        PropertyPrevent = propertyPrevent;
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

    public JSONObject generateJsonObject(User user){
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        //
        DataPrevent.setUpdate_user_id(user.getUserId());
        try {
            obj.put("dataPrevent", new JSONObject(mapper.writeValueAsString(DataPrevent)));
            obj.put("propertyPrevent", new JSONObject(mapper.writeValueAsString(PropertyPrevent)));
            obj.put("preventHistory", new JSONObject(mapper.writeValueAsString(PreventHistory)));
            obj.put("prevent_id", prevent_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
