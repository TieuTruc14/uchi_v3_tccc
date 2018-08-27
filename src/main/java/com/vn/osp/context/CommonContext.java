package com.vn.osp.context;

import com.vn.osp.common.global.Constants;
import com.vn.osp.elasticsearch.ESQueryFactory;
import com.vn.osp.modelview.GroupRole;
import com.vn.osp.modelview.GrouproleAuthority;
import com.vn.osp.modelview.User;

import java.util.ArrayList;

/**
 * Created by tranv on 12/5/2016.
 */
public class CommonContext {
//    public static final String SESSION_KEY = "User";

    private String agency;
    private User user;
    private ArrayList<GroupRole> groupRoles;
    private ArrayList<GrouproleAuthority> grouproleAuthorities;
    private String province_code;
    public static String authentication_id;
    public static Integer notSyncContract;


    public CommonContext() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<GroupRole> getGroupRoles() {
        return groupRoles;
    }

    public void setGroupRoles(ArrayList<GroupRole> groupRoles) {
        this.groupRoles = groupRoles;
    }

    public ArrayList<GrouproleAuthority> getGrouproleAuthorities() {
        return grouproleAuthorities;
    }

    public void setGrouproleAuthorities(ArrayList<GrouproleAuthority> grouproleAuthorities) {
        this.grouproleAuthorities = grouproleAuthorities;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getAuthentication_id() {
        return authentication_id;
    }

    public void setAuthentication_id(String authentication_id) {
        this.authentication_id = authentication_id;
    }

    public static Integer getNotSyncContract() {
        return notSyncContract;
    }

    public static void setNotSyncContract(Integer notSyncContract) {
        CommonContext.notSyncContract = notSyncContract;
    }

    public String getEntryUserName(){
        return user.getFamily_name() + " " + user.getFirst_name();
    }
}

