package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

/**
 * Created by minh on 3/23/2017.
 */
public class AccessHistory {
    private Integer hid;
    private Integer usid;
    private String execute_person;
    private String client_info;
    private String execute_date_time;
    private Integer access_type;
    private String description;


    @JsonCreator
    public AccessHistory(@JsonProperty(value = "hid", required = true) final Integer hid,
                         @JsonProperty(value = "usid", required = true) final Integer usid,
                         @JsonProperty(value = "execute_person", required = false) final String execute_person,
                         @JsonProperty(value = "client_info", required = false) final String client_info,
                         @JsonProperty(value = "execute_date_time", required = false) final String execute_date_time,
                         @JsonProperty(value = "access_type", required = false) final Integer access_type) {
        this.hid = hid;
        this.usid = usid;
        this.execute_person = execute_person;
        this.client_info = client_info;
        this.execute_date_time = execute_date_time;
        this.access_type = access_type;
    }

    public AccessHistory() {
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Integer getUsid() {
        return usid;
    }

    public void setUsid(Integer usid) {
        this.usid = usid;
    }

    public String getExecute_person() {
        return execute_person;
    }

    public void setExecute_person(String execute_person) {
        this.execute_person = execute_person;
    }

    public String getClient_info() {
        return client_info;
    }

    public void setClient_info(String client_info) {
        this.client_info = client_info;
    }

    public String getExecute_date_time() {
        return execute_date_time;
    }

    public void setExecute_date_time(String execute_date_time) {
        this.execute_date_time = execute_date_time;
    }

    public Integer getAccess_type() {
        return access_type;
    }

    public void setAccess_type(Integer access_type) {
        this.access_type = access_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String generateUpdateJson() {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
