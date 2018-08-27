package com.vn.osp.modelview;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by minh on 5/17/2017.
 */
public class UserByRoleList {
    private Integer id;
    private Integer office_id;
    private String family_name;
    private String first_name;
    private String account;
    private String password;
    private Boolean sex;
    private Boolean active_flg;
    private Boolean hidden_flg;
    private String role;
    private String birthday;
    private String telephone;
    private String mobile;
    private String email;
    private String address;
    private java.sql.Timestamp last_login_date;
    private Integer entry_user_id;
    private String entry_user_name;
    private java.sql.Timestamp entry_date_time;
    private Integer update_user_id;
    private String update_user_name;
    private java.sql.Timestamp update_date_time;

    @JsonCreator
    public UserByRoleList(@JsonProperty(value = "id", required = false) final Integer id,
                         @JsonProperty(value = "office_id", required = false) final Integer office_id,
                         @JsonProperty(value = "family_name", required = false) final String family_name,
                          @JsonProperty(value = "first_name", required = false) final String first_name,
                          @JsonProperty(value = "account", required = false) final String account,
                          @JsonProperty(value = "password", required = false) final String password,
                          @JsonProperty(value = "sex", required = false) final Boolean sex,
                          @JsonProperty(value = "active_flg", required = false) final Boolean active_flg,
                          @JsonProperty(value = "hidden_flg", required = false) final Boolean hidden_flg,
                          @JsonProperty(value = "role", required = false) final String role,
                          @JsonProperty(value = "birthday", required = false) final String birthday,
                          @JsonProperty(value = "telephone", required = false) final String telephone,
                          @JsonProperty(value = "mobile", required = false) final String mobile,
                          @JsonProperty(value = "email", required = false) final String email,
                          @JsonProperty(value = "address", required = false) final String address,
                          @JsonProperty(value = "last_login_date", required = false) final Timestamp last_login_date,
                          @JsonProperty(value = "entry_user_id", required = false) final Integer entry_user_id,
                          @JsonProperty(value = "entry_user_name", required = false) final String entry_user_name,
                          @JsonProperty(value = "entry_date_time", required = false) final Timestamp entry_date_time,
                          @JsonProperty(value = "update_user_id", required = false) final Integer update_user_id,
                          @JsonProperty(value = "update_user_name", required = false) final String update_user_name,
                          @JsonProperty(value = "update_date_time", required = false) final Timestamp update_date_time) {
        this.id = id;
        this.office_id = office_id;
        this.family_name = family_name;
        this.first_name = first_name;
        this.account = account;
        this.password = password;
        this.sex = sex;
        this.active_flg = active_flg;
        this.hidden_flg = hidden_flg;
        this.role = role;
        this.birthday = birthday;
        this.telephone = telephone;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.last_login_date = last_login_date;
        this.entry_user_id = entry_user_id;
        this.entry_user_name = entry_user_name;
        this.entry_date_time = entry_date_time;
        this.update_user_id = update_user_id;
        this.update_user_name = update_user_name;
        this.update_date_time = update_date_time;


    }


    public UserByRoleList(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOffice_id() {
        return office_id;
    }

    public void setOffice_id(Integer office_id) {
        this.office_id = office_id;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Boolean getActive_flg() {
        return active_flg;
    }

    public void setActive_flg(Boolean active_flg) {
        this.active_flg = active_flg;
    }

    public Boolean getHidden_flg() {
        return hidden_flg;
    }

    public void setHidden_flg(Boolean hidden_flg) {
        this.hidden_flg = hidden_flg;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getLast_login_date() {
        return last_login_date;
    }

    public void setLast_login_date(Timestamp last_login_date) {
        this.last_login_date = last_login_date;
    }

    public Integer getEntry_user_id() {
        return entry_user_id;
    }

    public void setEntry_user_id(Integer entry_user_id) {
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

    public Integer getUpdate_user_id() {
        return update_user_id;
    }

    public void setUpdate_user_id(Integer update_user_id) {
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

}
