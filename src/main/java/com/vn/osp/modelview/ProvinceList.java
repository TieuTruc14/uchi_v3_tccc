package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.context.CommonContext;
import org.json.JSONObject;

/**
 * Created by minh on 4/19/2017.
 */
public class ProvinceList {
    private Long id;
    private String name;
    private Long order_number;
    private Long entry_user_id;
    private String entry_user_name;
    private String entry_date_time;
    private Long update_user_id;
    private String update_user_name;
    private String update_date_time;
    private String code;
    private Long notary_index;

    @JsonIgnore
    private String name_;
    @JsonIgnore
    private String code_;
    @JsonIgnore
    private Boolean success;
    @JsonIgnore
    private String codeUpdateView;


    @JsonCreator
    public ProvinceList(
            @JsonProperty(value = "id") final Long id,
            @JsonProperty(value = "name")final String name,
            @JsonProperty(value = "order_number") final Long order_number,
            @JsonProperty(value = "entry_user_id") final Long entry_user_id,
            @JsonProperty(value = "entry_user_name") final String entry_user_name,
            @JsonProperty(value = "entry_date_time") final String entry_date_time,
            @JsonProperty(value = "update_user_id") final Long update_user_id,
            @JsonProperty(value = "update_user_name") final String update_user_name,
            @JsonProperty(value = "update_date_time") final String update_date_time,
            @JsonProperty(value = "code") final String code,
            @JsonProperty(value = "notary_index") final Long notary_index

    ){
        this.id = id;
        this.name= name;
        this.order_number = order_number;
        this.entry_user_id = entry_user_id;
        this.entry_user_name = entry_user_name;
        this.entry_date_time = entry_date_time;
        this.update_user_id = update_user_id;
        this.update_user_name = update_user_name;
        this.update_date_time = update_date_time;
        this.code = code;
        this.notary_index = notary_index;

    }
    public ProvinceList(){

    }


    public Long getSid() {
        return id;
    }

    public void setSid(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getOrder_number() {
        return order_number;
    }

    public void setOrder_number(Long order_number) {
        this.order_number = order_number;
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

    public String getEntry_date_time() {
        return entry_date_time;
    }

    public void setEntry_date_time(String entry_date_time) {
        this.entry_date_time = entry_date_time;
    }

    public Long getUpdate_user_id() {
        return update_user_id;
    }

    public void setUpdate_user_id(Long update_user_id) {
        this.update_user_id = update_user_id;
    }

    public String getUpdate_user_name() {
        return update_user_name;
    }

    public void setUpdate_user_name(String update_user_name) {
        this.update_user_name = update_user_name;
    }

    public String getUpdate_date_time() {
        return update_date_time;
    }

    public void setUpdate_date_time(String update_date_time) {
        this.update_date_time = update_date_time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getNotary_index() {
        return notary_index;
    }

    public void setNotary_index(Long notary_index) {
        this.notary_index = notary_index;
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

    public String getCodeUpdateView() {
        return codeUpdateView;
    }

    public void setCodeUpdateView(String codeUpdateView) {
        this.codeUpdateView = codeUpdateView;
    }


}
