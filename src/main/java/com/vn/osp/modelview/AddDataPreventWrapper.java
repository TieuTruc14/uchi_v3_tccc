package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.context.CommonContext;
import org.json.JSONObject;


/**
 * Created by tranv on 11/19/2016.
 */

public class AddDataPreventWrapper {

    public static final String SESSION_KEY = "AddDataPreventWrapper";

    private DataPrevent DataPrevent;

    private PropertyPrevent PropertyPrevent;

    private PreventHistory PreventHistory;

    private String authentication_id;

    @JsonCreator
    public AddDataPreventWrapper(@JsonProperty(value = "dataPrevent", required = true) DataPrevent DataPrevent,
                                 @JsonProperty(value = "propertyPrevent", required = true) PropertyPrevent PropertyPrevent,
                                 @JsonProperty(value = "preventHistory", required = true) PreventHistory PreventHistory,
                                 @JsonProperty(value = "authentication_id", required = true) String authentication_id) {
        this.DataPrevent = DataPrevent;
        this.PropertyPrevent = PropertyPrevent;
        this.PreventHistory = PreventHistory;
        this.authentication_id = authentication_id;
    }

    public AddDataPreventWrapper() {
    }

    public com.vn.osp.modelview.DataPrevent getDataPrevent() {
        return DataPrevent;
    }

    public void setDataPrevent(com.vn.osp.modelview.DataPrevent dataPrevent) {
        DataPrevent = dataPrevent;
    }

    public com.vn.osp.modelview.PropertyPrevent getPropertyPrevent() {
        return PropertyPrevent;
    }

    public void setPropertyPrevent(com.vn.osp.modelview.PropertyPrevent propertyPrevent) {
        PropertyPrevent = propertyPrevent;
    }

    public com.vn.osp.modelview.PreventHistory getPreventHistory() {
        return PreventHistory;
    }

    public void setPreventHistory(com.vn.osp.modelview.PreventHistory preventHistory) {
        PreventHistory = preventHistory;
    }

    public String getAuthentication_id() {
        return authentication_id;
    }

    public void setAuthentication_id(String authentication_id) {
        this.authentication_id = authentication_id;
    }

    public JSONObject generateJsonObject(CommonContext commonContext){
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        DataPrevent.setPrevent_regist_agency(commonContext.getAgency());
        //entryUserInfor
        // trường type của dataprevent ko có
        //DataPrevent.setPrevent_person_info("Đơn vị gửi yêu cầu");
        //DataPrevent.setStatus(Constants.TIEP_NHAN);
        DataPrevent.setEntry_user_id(commonContext.getUser().getUserId());
        DataPrevent.setUpdate_user_id(commonContext.getUser().getUserId());
        DataPrevent.setEntry_user_name(commonContext.getUser().getAccount());
        DataPrevent.setUpdate_user_name(commonContext.getUser().getAccount());
        //Đến property
        //Đến History
        PreventHistory.setExecute_person(commonContext.getUser().getAccount());
        authentication_id = "FIX_CODE";


        try {
            obj.put("dataPrevent", new JSONObject(mapper.writeValueAsString(DataPrevent)));
            obj.put("propertyPrevent", new JSONObject(mapper.writeValueAsString(PropertyPrevent)));
            obj.put("preventHistory", new JSONObject(mapper.writeValueAsString(PreventHistory)));
            obj.put("authentication_id", authentication_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
