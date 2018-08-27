package com.vn.osp.modelview;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.context.CommonContext;
import org.json.JSONObject;

import java.sql.Timestamp;

/**
 * Created by nmha on 3/29/2017.
 */
public class CreateOfficeForm {
    private Long noid;
    private Long office_type;
    private String name;
    private String address;
    private String phone;
    private String fax;
    private String email;
    private String website;
    private String other_info;
    private String mac_address;
    private String authentication_id;
    private String authentication_code;
    private Long active_flg;
    private Long hidden_flg;
    private Timestamp last_connection_time;
    private Long synchronize_type;
    private Long entry_user_id;
    private String entry_user_name;
    private Timestamp entry_date_time;
    private Long update_user_id;
    private String update_user_name;
    private Timestamp update_date_time;
    private String code;

    public CreateOfficeForm() {
    }

    public Long getNoid() {
        return noid;
    }

    public void setNoid(Long noid) {
        this.noid = noid;
    }

    public Long getOffice_type() {
        return office_type;
    }

    public void setOffice_type(Long office_type) {
        this.office_type = office_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getOther_info() {
        return other_info;
    }

    public void setOther_info(String other_info) {
        this.other_info = other_info;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public String getAuthentication_id() {
        return authentication_id;
    }

    public void setAuthentication_id(String authentication_id) {
        this.authentication_id = authentication_id;
    }

    public String getAuthentication_code() {
        return authentication_code;
    }

    public void setAuthentication_code(String authentication_code) {
        this.authentication_code = authentication_code;
    }

    public Long getActive_flg() {
        return active_flg;
    }

    public void setActive_flg(Long active_flg) {
        this.active_flg = active_flg;
    }

    public Long getHidden_flg() {
        return hidden_flg;
    }

    public void setHidden_flg(Long hidden_flg) {
        this.hidden_flg = hidden_flg;
    }

    public Timestamp getLast_connection_time() {
        return last_connection_time;
    }

    public void setLast_connection_time(Timestamp last_connection_time) {
        this.last_connection_time = last_connection_time;
    }

    public Long getSynchronize_type() {
        return synchronize_type;
    }

    public void setSynchronize_type(Long synchronize_type) {
        this.synchronize_type = synchronize_type;
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

    public Timestamp getEntry_date_time() {
        return entry_date_time;
    }

    public void setEntry_date_time(Timestamp entry_date_time) {
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

    public Timestamp getUpdate_date_time() {
        return update_date_time;
    }

    public void setUpdate_date_time(Timestamp update_date_time) {
        this.update_date_time = update_date_time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String generateAddJson(User user) {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();

        try {
            // get from common
            this.setEntry_user_name(user.getAccount());
            this.setEntry_user_id(user.getUserId());
            this.setEntry_date_time(new Timestamp(System.currentTimeMillis()));
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateUpdateJson(User user) {
        JSONObject obj = new JSONObject();
        ObjectMapper mapper = new ObjectMapper();

        try {
            // get from common
            this.setUpdate_user_name(user.getAccount());
            this.setUpdate_user_id(user.getUserId());
            this.setUpdate_date_time(new Timestamp(System.currentTimeMillis()));
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
