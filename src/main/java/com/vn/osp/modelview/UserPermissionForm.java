package com.vn.osp.modelview;

import com.vn.osp.common.global.Constants;

import java.util.List;

/**
 * Created by tranv on 3/7/2017.
 */
public class UserPermissionForm {
    private User user;
    private List<GroupRole> groupRoles;
    private List<UserGroupRole> userGroupRoles;
    private String cb01;

    public UserPermissionForm() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<GroupRole> getGroupRoles() {
        return groupRoles;
    }

    public void setGroupRoles(List<GroupRole> groupRoles) {
        this.groupRoles = groupRoles;
    }

    public String getCb01() {
        return cb01;
    }

    public void setCb01(String cb01) {
        this.cb01 = cb01;
    }

    public List<UserGroupRole> getUserGroupRoles() {
        return userGroupRoles;
    }

    public void setUserGroupRoles(List<UserGroupRole> userGroupRoles) {
        this.userGroupRoles = userGroupRoles;
    }

    public void mappingUserRole(){
        for(int i=0; i<groupRoles.size(); i++){
            for(int j=0; j<userGroupRoles.size();j++){
                if(groupRoles.get(i).getGroupRoleId() == userGroupRoles.get(j).getGroupid()){
                    groupRoles.get(i).setActiveByPreventUser(1);
                }
            }
        }
    }
}
