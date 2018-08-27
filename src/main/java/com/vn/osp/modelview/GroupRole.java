package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class GroupRole {
    private Long groupRoleId;
    private String grouprolename;
    private String description;
    private Long active_flg;
    private int activeByPreventUser;
    private Long entry_user_id;
    private String entry_user_name;
    private String entry_date_time;
    private Long update_user_id;
    private String update_user_name;
    private String update_date_time;

    @JsonCreator
    public GroupRole(@JsonProperty(value = "groupRoleId", required = false) final Long groupRoleId,
                     @JsonProperty(value = "grouprolename", required = false) final String grouprolename,
                     @JsonProperty(value = "description", required = false) final String description,
                     @JsonProperty(value = "active_flg", required = false) final Long active_flg,
                     @JsonProperty(value = "entry_user_id", required = false) final Long entry_user_id,
                     @JsonProperty(value = "entry_user_name", required = false) final String entry_user_name,
                     @JsonProperty(value = "entry_date_time", required = false) final String entry_date_time,
                     @JsonProperty(value = "update_user_id", required = false) final Long update_user_id,
                     @JsonProperty(value = "update_user_name", required = false) final String update_user_name,
                     @JsonProperty(value = "update_date_time", required = false) final String update_date_time) {
        this.groupRoleId = groupRoleId;
        this.grouprolename = grouprolename;
        this.description = description;
        this.active_flg = active_flg;
        this.entry_user_id = entry_user_id;
        this.entry_user_name = entry_user_name;
        this.entry_date_time = entry_date_time;
        this.update_user_id = update_user_id;
        this.update_user_name = update_user_name;
        this.update_date_time = update_date_time;
    }

    public GroupRole() {
    }

    public Long getGroupRoleId() {
        return groupRoleId;
    }

    public void setGroupRoleId(Long groupRoleId) {
        this.groupRoleId = groupRoleId;
    }

    public String getGrouprolename() {
        return grouprolename;
    }

    public void setGrouprolename(String grouprolename) {
        this.grouprolename = grouprolename;
    }

    public Long getActive_flg() {
        return active_flg;
    }

    public void setActive_flg(Long active_flg) {
        this.active_flg = active_flg;
    }


    public int getActiveByPreventUser() {
        return activeByPreventUser;
    }

    public void setActiveByPreventUser(int activeByPreventUser) {
        this.activeByPreventUser = activeByPreventUser;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
