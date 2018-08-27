package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by tranv on 11/8/2016.
 */

public class PreventHistory {

    private Long hid;
    private Long prevent_id;
    private String client_info;
    private String execute_date_time;
    private String execute_person;
    private String execute_content;
    private String recipient;
    private String handled_info;


    @JsonCreator
    public PreventHistory(
            @JsonProperty(value = "hid", required = true) final Long hid,
            @JsonProperty(value = "prevent_id", required = true) final Long prevent_id,
            @JsonProperty(value = "client_info", required = false) final String client_info,
            @JsonProperty(value = "execute_date_time", required = false) final String execute_date_time,
            @JsonProperty(value = "execute_person", required = false) final String execute_person,
            @JsonProperty(value = "execute_content", required = false) final String execute_content,
            @JsonProperty(value = "recipient", required = false) final String recipient,
            @JsonProperty(value = "handled_info", required = false) final String handled_info) {
        this.hid = hid;
        this.prevent_id = prevent_id;
        this.client_info = client_info;
        this.execute_date_time = execute_date_time;
        this.execute_person = execute_person;
        this.execute_content = execute_content;
        this.recipient = recipient;
        this.handled_info = handled_info;
    }

    public PreventHistory() {
    }

    public Long getHid() {
        return hid;
    }

    public void setHid(Long hid) {
        this.hid = hid;
    }

    public Long getPrevent_id() {
        return prevent_id;
    }

    public void setPrevent_id(Long prevent_id) {
        this.prevent_id = prevent_id;
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

    public String getExecute_person() {
        return execute_person;
    }

    public void setExecute_person(String execute_person) {
        this.execute_person = execute_person;
    }

    public String getExecute_content() {
        return execute_content;
    }

    public void setExecute_content(String execute_content) {
        this.execute_content = execute_content;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getHandled_info() {
        return handled_info;
    }

    public void setHandled_info(String handled_info) {
        this.handled_info = handled_info;
    }

}
