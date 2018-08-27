package com.vn.osp.modelview;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

/**
 * Created by tranv on 3/8/2017.
 */
public class UserPermisson {
    private Long userId;
    private String cb01;

    public UserPermisson() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCb01() {
        return cb01;
    }

    public void setCb01(String cb01) {
        this.cb01 = cb01;
    }
    public String generateJson(){
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String a = mapper.writeValueAsString(this);
            return a;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
