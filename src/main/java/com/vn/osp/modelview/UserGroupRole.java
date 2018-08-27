package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class UserGroupRole {
  private Long userId;
  private Long groupid;
  private String grouprolename;

    @JsonCreator
    public UserGroupRole(@JsonProperty(value = "userId", required = false) final Long userId,
                         @JsonProperty(value = "groupid", required = false) final Long groupid,
                         @JsonProperty(value = "grouprolename", required = false) final String grouprolename) {
        this.userId = userId;
        this.groupid = groupid;
        this.grouprolename = grouprolename;
    }

    public UserGroupRole() {
    }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getGroupid() {
    return groupid;
  }

  public void setGroupid(Long groupid) {
    this.groupid = groupid;
  }

  public String getGrouprolename() {
    return grouprolename;
  }

  public void setGrouprolename(String grouprolename) {
    this.grouprolename = grouprolename;
  }
}
