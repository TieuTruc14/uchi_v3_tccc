package com.vn.osp.modelview;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.common.util.EditString;
import com.vn.osp.common.util.SystemMessageProperties;
import com.vn.osp.context.CommonContext;
import com.vn.osp.util.StringUtils;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by tranv on 3/10/2017.
 */
public class CreateGroupRoleForm {
    private Long groupRoleId;
    private String grouprolename;
    private String description;
    private Long active_flg;
    private Long entry_user_id;
    private String entry_user_name;
    private String entry_date_time;
    private Long update_user_id;
    private String update_user_name;
    private String update_date_time;

    private ArrayList<Authority> systemManagerList;
    private ArrayList<Authority> functionList;
    private ArrayList<Authority> reportList;

    private String cb01;
    private String cb02;
    private String cb03;

    private GroupRole groupRole;

    private String grouprolename_;
    private String description_;
    private boolean success = true;


    public CreateGroupRoleForm() {
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

    public ArrayList<Authority> getSystemManagerList() {
        return systemManagerList;
    }

    public void setSystemManagerList(ArrayList<Authority> systemManagerList) {
        this.systemManagerList = systemManagerList;
    }

    public ArrayList<Authority> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(ArrayList<Authority> functionList) {
        this.functionList = functionList;
    }

    public ArrayList<Authority> getReportList() {
        return reportList;
    }

    public void setReportList(ArrayList<Authority> reportList) {
        this.reportList = reportList;
    }

    public String getCb01() {
        return cb01;
    }

    public void setCb01(String cb01) {
        this.cb01 = cb01;
    }

    public String getCb02() {
        return cb02;
    }

    public void setCb02(String cb02) {
        this.cb02 = cb02;
    }

    public String getCb03() {
        return cb03;
    }

    public void setCb03(String cb03) {
        this.cb03 = cb03;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEntry_user_id() {
        return entry_user_id;
    }

    public void setEntry_user_id(Long entry_user_id) {
        this.entry_user_id = entry_user_id;
    }

    public GroupRole getGroupRole() {
        return groupRole;
    }

    public void setGroupRole(GroupRole groupRole) {
        this.groupRole = groupRole;
    }

    public String getGrouprolename_() {
        return grouprolename_;
    }

    public void setGrouprolename_(String grouprolename_) {
        this.grouprolename_ = grouprolename_;
    }

    public String getDescription_() {
        return description_;
    }

    public void setDescription_(String description_) {
        this.description_ = description_;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void validate() {
        success = true;
        String truong = SystemMessageProperties.getSystemProperty("v3_truong") + " ";
        String notEmpty = SystemMessageProperties.getSystemProperty("v3_not_empty");
        String max_length = SystemMessageProperties.getSystemProperty("v3_max_length");
        String min_length = SystemMessageProperties.getSystemProperty("v3_min_length");
        String ky_tu = SystemMessageProperties.getSystemProperty("v3_ky_tu");
        boolean name = true;
        boolean descrip = true;
        if (org.apache.commons.lang3.StringUtils.isBlank(grouprolename) == false) {
            name = StringUtils.checkSpecialCharacter(grouprolename.replaceAll("@", "").replaceAll("&", ""));
            if (!name) {
                grouprolename_ = SystemMessageProperties.getSystemProperty("not_special");
                success = false;
            } else {
                if (grouprolename == null || grouprolename.equals("")) {
                    grouprolename_ = (truong + SystemMessageProperties.getSystemProperty("v3_grouprole_name") + notEmpty);
                    success = false;
                } else if (grouprolename.length() > 200) {
                    grouprolename_ = (truong + SystemMessageProperties.getSystemProperty("v3_grouprole_name") + max_length + EditString.SPACE + 200 + ky_tu);
                    success = false;
                }
            }
        } else {
            grouprolename_ = "Tên nhóm quyền không được để trống !";
            success = false;
        }
        if (org.apache.commons.lang3.StringUtils.isBlank(description) == false) {
            descrip = StringUtils.checkSpecialCharacter(description);
            if (!descrip) {
                description_ = SystemMessageProperties.getSystemProperty("not_special");
                success = false;
            } else if (description != null && description.length() > 1000) {
                description_ = (truong + SystemMessageProperties.getSystemProperty("v3_description") + max_length + EditString.SPACE + 1000 + ky_tu);
                success = false;
            }
        }
    }
//    public void validateupdate(){
//        success = true;
//        String truong = SystemMessageProperties.getSystemProperty("v3_truong") +" ";
//        String notEmpty = SystemMessageProperties.getSystemProperty("v3_not_empty");
//        String max_length = SystemMessageProperties.getSystemProperty("v3_max_length");
//        String min_length = SystemMessageProperties.getSystemProperty("v3_min_length");
//        String ky_tu = SystemMessageProperties.getSystemProperty("v3_ky_tu");
//        boolean name=StringUtils.checkSpecialCharacter(grouprolename);
//        boolean descrip=StringUtils.checkSpecialCharacter(description);
//        if(name){
//            grouprolename_=SystemMessageProperties.getSystemProperty("not_special");
//            success=false;
//        }else{
//            if(grouprolename == null || grouprolename.equals("")){
//                grouprolename_ = (truong + SystemMessageProperties.getSystemProperty("v3_grouprole_name") + notEmpty);
//                success = false;
//            }else if (grouprolename.length()>200){
//                grouprolename_ = (truong+SystemMessageProperties.getSystemProperty("v3_grouprole_name")+ max_length + EditString.SPACE+ 200+ ky_tu);
//                success= false;
//            }
//        }
//        if(descrip){
//            description_=SystemMessageProperties.getSystemProperty("not_special");
//            success=false;
//        }else if(description.length()>1000){
//            description_ = (truong+SystemMessageProperties.getSystemProperty("v3_description")+ max_length + EditString.SPACE +1000+ ky_tu);
//            success = false;
//        }
//
//    }

    public String generateAddJson(User user) {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();

        // get from common
        this.setEntry_user_name(user.getAccount());
        this.setEntry_user_id(user.getUserId());
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateUpdateJson(User user) {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();

        // get from common
        this.setUpdate_user_name(user.getAccount());
        this.setUpdate_user_id(user.getUserId());
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
