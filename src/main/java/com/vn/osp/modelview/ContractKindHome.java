package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

/**
 * Created by Admin on 5/8/2017.
 */
public class ContractKindHome {
    private long id;
    private String name;
    private Long entryUserId;
    private String entryUserName;
    private Timestamp entryDateTime;
    private Long updateUserId;
    private String updateUserName;
    private Timestamp updateDateTime;

    public ContractKindHome() {
    }

    @JsonCreator
    public ContractKindHome(
            @JsonProperty(value = "id", required = false) long id,
            @JsonProperty(value = "name", required = false) String name,
            @JsonProperty(value = "entryUserId", required = false) Long entryUserId,
            @JsonProperty(value = "entryUsername", required = false) String entryUserName,
            @JsonProperty(value = "entryDateTime", required = false) Timestamp entryDateTime,
            @JsonProperty(value = "updateUserId", required = false) Long updateUserId,
            @JsonProperty(value = "updateUserName", required = false) String updateUserName,
            @JsonProperty(value = "updateDateTime", required = false) Timestamp updateDateTime)
    {
        this.id = id;
        this.name = name;
        this.entryUserId = entryUserId;
        this.entryUserName = entryUserName;
        this.entryDateTime = entryDateTime;
        this.updateUserId = updateUserId;
        this.updateUserName = updateUserName;
        this.updateDateTime = updateDateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEntryUserId() {
        return entryUserId;
    }

    public void setEntryUserId(Long entryUserId) {
        this.entryUserId = entryUserId;
    }

    public String getEntryUserName() {
        return entryUserName;
    }

    public void setEntryUserName(String entryUserName) {
        this.entryUserName = entryUserName;
    }

    public Timestamp getEntryDateTime() {
        return entryDateTime;
    }

    public void setEntryDateTime(Timestamp entryDateTime) {
        this.entryDateTime = entryDateTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Timestamp getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Timestamp updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
}
