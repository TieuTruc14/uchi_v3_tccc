package com.vn.osp.modelview;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.context.CommonContext;
import org.json.JSONObject;

/**
 * Created by minh on 6/5/2017.
 */
public class ProvinceAdd {
    private String name;
    private Long entry_user_id;
    private String entry_user_name;
    private String code;

    private String name_;
    private String code_;
    private Boolean success;

    public ProvinceAdd() {
    }

    public ProvinceAdd(String name,  Long entry_user_id, String entry_user_name, String code) {
        this.name = name;
        this.entry_user_id = entry_user_id;
        this.entry_user_name = entry_user_name;
        this.code = code;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public String getCode_() {
        return code_;
    }

    public void setCode_(String code_) {
        this.code_ = code_;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getEntry_user_id() {
        return entry_user_id;
    }

    public void setEntry_user_id(Long entry_user_id) {
        this.entry_user_id = entry_user_id;
    }

    public String getEntry_user_name() {
        return entry_user_name;
    }

    public void setEntry_user_name(String entry_user_name) {
        this.entry_user_name = entry_user_name;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void validate(){
        success = true;
        String truong = SystemMessageProperties.getSystemProperty("v3_truong") +" ";
        String notEmpty = SystemMessageProperties.getSystemProperty("v3_not_empty");
        String max_length = SystemMessageProperties.getSystemProperty("v3_max_length");
        String min_length = SystemMessageProperties.getSystemProperty("v3_min_length");
        String ky_tu = SystemMessageProperties.getSystemProperty("v3_ky_tu");


        if(name == null || name.equals("")){
            name_ = ( truong + SystemMessageProperties.getSystemProperty("v3_province_name")+ notEmpty);
            success= false;
        }
        if(code == null || code.equals("")){
            code_= (truong+ SystemMessageProperties.getSystemProperty("v3_province_code")+ notEmpty);
            success= false;
        }
    }
    public String getOrderString(){
        String orderString2 = "";


        if(code!= null && !code.equals("")){

            orderString2 = "where code ='"+code+"'";
        }

        String query =orderString2 ;
        return query;
    }
    public String generateAddJson() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        try {
            ////System.out.println(mapper.writeValueAsString(this));
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
